package webui.xUtils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;


@Slf4j
public class TestNGListener extends TestListenerAdapter {
    private static WebDriver driver;
    //logUtil log = new logUtil(TestNGListener.class);
    public static void setDriver(WebDriver driver) {
        TestNGListener.driver = driver;
    }

    //用例执行结束后，用例执行成功时调用
    public void onTestSuccess(ITestResult tr) {
        log.info("测试步骤成功完成。"+"------Test Success！");
        Reporter.log("测试步骤成功完成。"+"------Test Success！");
        super.onTestSuccess(tr);
    }
    @Override
    //用例执行结束后，用例执行失败时调用
    public void onTestFailure(ITestResult tr) {
        log.error("测试步骤执行失败。"+"------Test Failure！");
        Reporter.log("测试步骤执行失败。"+"------Test Failure！");
        super.onTestFailure(tr);

        //发生错误后截图的功能可以根据实际需求进行添加。
        //ScreenShot screenShot = new ScreenShot(driver);
        //获取当前project目录
        //String path = System.getProperty("user.dir").replace("\\", "/");
        //加上时间戳以区分截图
        //    String curTime = TimeUtil.formatDate("yyyy-MM-dd");
        //screenShot.saveScreenShot(path + "/img/", "testFail" + ".png");
    }
    @Override
    //用例执行结束后，用例执行skip时调用
    public void onTestSkipped(ITestResult tr) {
        log.error("测试执行步骤跳过。"+"------Test Skipped！");
        Reporter.log("测试执行步骤跳过。"+"------Test Skipped！");
        super.onTestSkipped(tr);
    }
    @Override
    //每次调用测试@Test之前调用
    public void onTestStart(ITestResult tr) {
        log.info("------测试开始。内容：" + tr.getMethod().getDescription() +"------Start!");
        Reporter.log("------测试开始。内容：" + tr.getMethod().getDescription() +"------Start!");
        super.onTestStart(tr);
    }
    @Override
    //在所有测试运行之后调用，并且所有的配置方法都被调用
    public void onFinish(ITestContext testContext) {
        log.info("------结束："+" ------Test Finish！");
        Reporter.log("------结束："+" ------Test Finish！");
        super.onFinish(testContext);
    }

}
