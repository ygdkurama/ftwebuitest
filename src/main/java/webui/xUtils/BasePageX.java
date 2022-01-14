package webui.xUtils;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.HashMap;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

@Slf4j
//基础页面类
public class BasePageX extends UIExcutorImpl {
    protected WebDriver driver;
    protected String pageName;
    // 页面名称
    protected String xmlPath;
    // 页面元素配置文件路径
    protected HashMap<String, Position> positionMap;
    //存储页面元素信息
    //protected logUtil log = new logUtil(BasePageX.class);
    Position position = null;
    public BasePageX(WebDriver driver, String pageName,String xmlName)  throws Exception {
        super(driver);
        this.driver = driver;
        this.pageName = pageName;        // 获取page.xml路径，page.xml在同级目录
        //xmlPath = this.getClass().getResource("").getPath() + xmlName;
        xmlPath = "D:/webautotest-home/src/main/resources/" + xmlName;
        positionMap = XmlReadUtil.readXMLDocument(xmlPath, pageName);
        log.info("成功读取：" + pageName + "页面信息");
        Reporter.log("成功读取：" + pageName + "页面信息");
    }

    public void click(String positionName) throws Exception {
        super.click(getPosition(positionName));
    }
    public void sendKey(String positionName, String value) throws Exception {
        super.sendKey(getPosition(positionName), value);
    }
    public String getText(String positionName) throws Exception {
        return super.getText(getPosition(positionName));
    }
    public String getAttribute(String positionName,String value) throws Exception {
        return super.getAttribute(getPosition(positionName), value);
    }
    public WebElement getElement(String positionName) throws Exception {
        return super.getElement(getPosition(positionName));
    }
    public List getElements(String positionName) throws Exception {
        return super.getElements(getPosition(positionName));
    }
    public boolean isElementDisplayed(String positionName) throws Exception {
        return super.isElementDisplayed(getPosition(positionName));
    }
    @Override
    public void switchWindow(String title) {
        super.switchWindow(title);
        log.info("切换窗口");
        Reporter.log("切换窗口"+title);
    }
    public void switchFrame(String positionName) {
        super.switchFrame(getPosition(positionName));
        log.info("切换frame至：" + positionName);
        Reporter.log("切换frame至：" + positionName);
    }
    @Override
    public String getAlertText() {
        return super.getAlertText();
    }     //使用Robot强制点击某处坐标，用于无法定位的元素，比如（Object类型的元素）
    public void mouseMoveClick(int x , int y) throws AWTException {
        Robot rb1 = new Robot();
        rb1.mouseMove(x,y);
        rb1.delay(500);
        rb1.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        rb1.delay(500);
        rb1.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        rb1.delay(500);
        log.info("将鼠标移动至：" + "(" + x +"," + y + ")");
        Reporter.log("将鼠标移动至：" + "(" + x +"," + y + ")");
    }
    public void jsClick(String positionName) throws Exception {
        super.jsClick(getPosition(positionName));
    }
    public void waitElement(String positionName,int sec) {
        super.waitElement(getPosition(positionName), sec);
    }

    /*根据positionName返回对应的position
     */
    public Position getPosition(String positionName) {
        Position position = null;
        if (positionMap != null) {
            position = positionMap.get(positionName);
        }
        if(position ==null) {
            log.error("没有找到"+positionName+"页面元素");
            Reporter.log("没有找到"+positionName+"页面元素");
        }
        return position;
    }

}