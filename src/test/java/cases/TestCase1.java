package cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import webui.xUtils.BasePageX;
import webui.xUtils.JsonReadUtil;
import webui.xUtils.TestNGListener;
import webui.xUtils.browserUtil;

import java.util.List;

/**
 * @author: Gordon.Yu
 * @date: 1/12/22 11:26 a.m.
 * @description:
 */
@Slf4j
@Listeners({TestNGListener.class})
public class TestCase1 {

    WebDriver driver;

    @Test(description = "测试用例1")//测试展示9个卡片
    public void getCards() throws Exception {

        BasePageX testPage = new BasePageX(driver,"Review","dianpingH5.xml");
        List<WebElement> elelist = testPage.getElements("展示卡片");
        String listcount = String.valueOf(elelist.size());
        Assert.assertEquals(listcount,"9");


    }

    @DataProvider
    public Object[][] keyWord() {
        Object [][] keyWord = new Object [3][1];
        //String path = JsonReadUtil.class.getClassLoader().getResource("keyword.json").getPath();
        String path = "src\\test\\resources\\data\\keyword.json";
        log.info(path+"aaaaaaa");
        String s = JsonReadUtil.readJsonFile(path);
        JSONObject jobj = JSON.parseObject(s);
        JSONArray searchword = jobj.getJSONArray("searchword");
        for (int i = 0 ; i < searchword.size();i++){
            JSONObject key = (JSONObject)searchword.get(i);
            String keyword = (String)key.get("keyword");
            keyWord[i][0] = keyword;
        }
        return keyWord;
    }
    @Test(dataProvider="keyWord",description = "测试用例2")//测试查找关键字后，得到的卡片包含关键字
    public void searchKeyword(String keyword) throws Exception {
        BasePageX testPage = new BasePageX(driver,"Review","dianpingH5.xml");
        testPage.getElement("查询输入框").clear();
        log.info("查找"+keyword);
        testPage.getElement("查询输入框").sendKeys(keyword);
        testPage.getElement("搜索按钮").click();
        Thread.sleep(1000);//等待内容加载
        List<WebElement> elelist = testPage.getElements("展示卡片");
        for (int i = 0; i < elelist.size(); i++) {
            //Assert.assertEquals(keyword,);
            if (elelist.get(i).getText().contains(keyword)) {
                log.info(keyword+"在卡片中存在！！！！");
                continue;
            }
            else {
                break;
            }
        }
    }


    @BeforeMethod
    public void beforeMethod() {
        TestNGListener.setDriver(driver);
    }

    @AfterMethod
    public void afterMethod() {

    }

    @BeforeClass
    @Parameters({"browser","url"})
    public void beforeClass(String browser,String url) {
        driver = browserUtil.setDriver(browser,url);

    }

    @AfterClass
    public void afterClass() {
        browserUtil.quit();
    }
}
