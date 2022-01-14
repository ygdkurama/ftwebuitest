package webui.xUtils;

import org.openqa.selenium.WebElement;

public interface UIExcutor {
    //点击
    public void click(Position position) throws Exception;
    //输入文本
    public void sendKey(Position position,String value) throws Exception;
    //获取元素文本
    public String getText(Position position) throws Exception;
    //获取元素
    public WebElement getElement(Position position) throws Exception;
    //判断元素是否显示
    public boolean isElementDisplayed(Position position) throws Exception;
    //切换页面
    public void switchWindow(String winTitle);
    //切换frame
    public void switchFrame(Position position);
    //智能等待
    public void waitElement(Position position,int sec);
    //获取弹窗的文字（并关闭弹窗）
    public String getAlertText();
    //获取元素属性
    public String getAttribute(Position position,String attributeName) throws Exception;
    //javaScript 强制点击
    public void jsClick (Position position) throws Exception;
    //截图
    public void getscreenshot() throws Exception;
}