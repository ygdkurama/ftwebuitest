package webui.xUtils;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

@Slf4j
public class browserUtil {
    static WebDriver driver;
    //static logUtil logs = new logUtil(browserUtil.class);
    @SuppressWarnings("deprecation")
    public static WebDriver setDriver(String browserName,String url) {
        log.info("读取执行xml配置的"+browserName+"浏览器初始化\n");
        Reporter.log("读取执行xml配置的"+browserName+"浏览器初始化\n");
        switch (browserName) {
            case "firefox":
                //此处设置firefox的webdriver地址
                System.setProperty("webdriver.gecko.driver", ".\\libs\\webdriver\\geckodriver.exe");
                FirefoxProfile profile = new FirefoxProfile();
                //设置成 0 代表下载到浏览器默认下载路径， 设置成 2 则可以保存到指定目录。
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.dir", ".\\firefox-download");
                //browser.helperApps.neverAsk.saveToDisk
                //指定要下载页面的 Content-type 值， “binary/octet-stream” 为文件的类型。
                //下载的文件不同，这里的类型也会有所不一样。如果不清楚你下载的文件什么类型，请用Fiddler抓包。
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel");
                profile.setPreference("plugin.state.flash", 2);
                FirefoxOptions options = new FirefoxOptions();
                options.setProfile(profile);
                driver = new FirefoxDriver(options);
                driver.manage().window().maximize();
                //隐式等待
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                log.info("打开浏览器，访问"+url+"网址!");
                Reporter.log("打开浏览器，访问"+url+"网址!");
                driver.get(url);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                log.info("打开浏览器，访问"+url+"网址!");
                Reporter.log("打开浏览器，访问"+url+"网址!");
                driver.get(url);
                break;
//            case "IE":
//                System.setProperty("webdriver.ie.driver", ".\\libs\\webdriver\\IEDriverServer32.exe");
//                DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
//                dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//                dc.setCapability("ignoreProtectedModeSettings", true);
//                driver=new InternetExplorerDriver(dc);
//                driver.manage().window().maximize();
//                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//                log.info("打开浏览器，访问"+url+"网址!");
//                Reporter.log("打开浏览器，访问"+url+"网址!");
//                driver.get(url);
//                break;
            default:
                break;
        }
        return driver;
    }

    public static void quit() {
        driver.quit();
    }

    //参考资料https://www.cnblogs.com/generalli2019/p/11419952.html
}