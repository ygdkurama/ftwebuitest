package webui.xUtils;

/**
 * @author: Gordon.Yu
 * @date: 1/10/22 8:14 p.m.
 * @description:
 */
public class Position {


    private String path; //----->路径
    //位置名字，就是我们通常意义上叫的名字。例如：用户名输入框
    private String positionName;
    private ByType type;//定位方法
    private int waitSec;//等待时间

    //selenium的基本八种定位方法。
    public enum ByType{xpath,id,name,className, cssSelector,tagName, partialLinkText,linkText}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public ByType getType() {
        return type;
    }

    public void setType(ByType type) {
        this.type = type;
    }

    public Position(String path, ByType type, String positionName) {
        super();
        this.path = path;
        this.positionName = positionName;
        this.type = type;
    }

    //这种可以看作是针对iframe的情况
    public Position(String path,String positionName) {
        super();
        this.path = path;
        this.positionName = positionName;
    }

    public Position(String path, int waitSec, ByType type, String positionName) {
        super();
        this.path = path;
        this.waitSec = waitSec;
        this.positionName = positionName;
        this.type = type;
    }

    public int getWaitSec() {
        return waitSec;
    }

    public void setWaitSec(int waitSec) {
        this.waitSec = waitSec;
    }
}
