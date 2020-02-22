package com.cheer.testUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.cheer.driverUtils.ChromeDriverUtil;

public class KeywordOfWeb {
	 //成员变量webdriver，先初始化
    public WebDriver driver;

    //构造方法
    public KeywordOfWeb() {

    }
    //关键字驱动

    /***
     * 打开浏览器的方法，其实就是完成driver实例化的过程
     *
     * @param browserType 选用浏览器的类型
     */
    public void openBrowser(String browserType) {
        switch (browserType) {
            case "Chrome":
                ChromeDriverUtil ggDriver = new ChromeDriverUtil ( "C:\\Users\\HP\\AllureTEST00000001\\src\\main\\java\\com\\cheer\\chromedriver\\chromedriver.exe" );
                driver = ggDriver.getDriver ();
                //调用隐式等待
                displayWait ();
                break;
            case "FireFox":
                //还没有封装firefox驱动方法
                //调用隐式等待
                displayWait ();
                break;
            case "IE":
                //还没有封装IE驱动方法
                //调用隐式等待
                displayWait ();
                break;
            default:
                ChromeDriverUtil ggDriver2 = new ChromeDriverUtil ( "D:\\AllureReportTest\\src\\main\\java\\com\\cheer\\chromedriver\\chromedriver.exe" );
                driver = ggDriver2.getDriver ();
                //调用隐式等待
                displayWait ();
                break;
        }
    }

    /**
     * 需要测试web网页的url
     *
     * @param url
     */
    public void visitWebUrl(String url) {
        driver.get ( url );
    }

    /**
     * 清空输入框并输入内容的方法
     *
     * @param name    定位到的name属性
     * @param content 输入的内容
     */
    public void inputByName(String name, String content) {
        WebElement element = driver.findElement ( By.name ( name ) );
        //清空输入框的内容
        element.clear ();
        element.sendKeys ( content );
    }

    /**
     * 根据xpath定位
     *
     * @param xpath
     * @param content
     */
    public void inputByXpath(String xpath, String content) {
        WebElement element = driver.findElement ( By.xpath ( xpath ) );
        //清空输入框内容cc
        element.clear ();
        element.sendKeys ( content );
    }

    /**
     * 根据css定位方法
     *
     * @param css
     * @param content
     */
    public void inputByCss(String css, String content) {
        WebElement element = driver.findElement ( By.cssSelector ( css ) );
        element.clear ();
        element.sendKeys ( content );
    }

    /**
     * 单击元素的方法
     *
     * @param xpath
     */
    public void click(String xpath) {
        driver.findElement ( By.xpath ( xpath ) ).click ();
    }

    /**
     * 获取web title方法
     *
     * @return
     */
    public String getTitle() {
        return driver.getTitle ();
    }

    /**
     * 设置强制等待时间
     *
     * @param waitTime
     */
    public void sleep(String waitTime) throws Exception {
        int time = 0;
        time = Integer.parseInt ( waitTime );
        //毫秒乘以1000为秒
        Thread.sleep ( time * 1000 );
    }


    /**
     * 显示等待方法,实现尝试使用Xpath定位，等待这个元素的出现
     */
    public void visibleWait(final String xPath) {
        try {

            //用于设置最长等待时间
            WebDriverWait webDriverWait = new WebDriverWait ( driver, 10 );
            //调用until方法,实现expectedCondition<V>接口，V代表预期时间类型
            webDriverWait.until ( new ExpectedCondition<WebElement> () {
                //返回类型必须和expectedCondition<v>接口类型一致
                @Override
                public WebElement apply(WebDriver webDriver) {
                    WebElement webElement = webDriver.findElement ( By.xpath ( xPath ) );
                    return webElement;
                }
            } );
        } catch (Exception e) {
            System.out.println ( e.fillInStackTrace () );
        }
    }

    /**
     * 隐式等待,全局等待
     * 一次设置永久生效
     */
    public void displayWait() {
        driver.manage ().timeouts ().implicitlyWait ( 10, TimeUnit.SECONDS );

    }

    /**
     * 断言方法
     *
     * @param xpath
     * @param username
     * @return
     */
    public String assertUsername(String xpath, String username) {
        WebElement element = driver.findElement ( By.xpath ( xpath ) );
        if (element != null) {
            if (element.getText ().equals ( username )) {
                return "登录成功!";
            } else {
                return "登录失败！";
            }
        }
        return null;
    }

    /**
     * 鼠标悬停方法
     *
     * @param xpath
     */
    public void hover(String xpath) {
        Actions action = new Actions ( driver );
        action.moveToElement ( driver.findElement ( By.xpath ( xpath ) ) ).perform ();
    }

    public void switchWindow(String target) {
        //创建一个字符串方便之后存放句柄
        String targetHandle = null;
        //获取当前页面的句柄
        Set<String> handle = driver.getWindowHandles ();
        //遍历句柄集合，尝试找到目标句柄
        for (String h : handle) {
            //如果尝试切换句柄的标题是目标句柄则记录目标句柄
            if (driver.switchTo ().window ( h ).getTitle ().equals ( target )) {
                targetHandle = h;
            }
        }
        try {
            //找到目标句柄完成切换
            driver.switchTo ().window ( targetHandle );
        } catch (Exception e) {
            System.out.println ( "浏览器窗口切换失败" );
        }

    }

    /**
     * 关闭旧窗口，切换到新窗口
     */
    public void closeOldWindow() {
        //因为set是无序的所以迭代set集合到list集合中通过下标调用
        List<String> windowList = new ArrayList<String> ();
        //找到当前所有窗口句柄
        Set<String> windowHandles = driver.getWindowHandles ();
        //迭代set中的所有窗口句柄加入List中
        Iterator<String> iterator = windowHandles.iterator ();
        while (iterator.hasNext ()) {
            windowList.add ( iterator.next ().toString () );
        }
        //关闭正在操作的窗口
        driver.close ();
        //切换到新窗口
        try {                                   //获取第二个就是新的窗口
            driver.switchTo ().window ( windowList.get ( 1 ) );
        } catch (Exception e) {
            System.out.println ( "切换到新窗口失败！" );
        }

    }

    /**
     * 关闭新窗口，切换回旧窗口
     */
    public void closeNewWindow() {
        List<String> windowList = new ArrayList<String> ();
        Set<String> windowHandles = driver.getWindowHandles ();
        Iterator<String> iterator = windowHandles.iterator ();
        while (iterator.hasNext ()) {
            windowHandles.add ( iterator.next ().toString () );
        }
        try {
            //先切换到新窗口关闭掉
            driver.switchTo ().window ( windowList.get ( 1 ) );
            driver.close ();
            //切回旧窗口
            driver.switchTo ().window ( windowList.get ( 0 ) );
        } catch (Exception e) {
        }
    }

    /**
     * 通过xpath定位元素找到iframe  可能iframe没有name和id属性啥的
     *
     * @param xpath
     */
    public void intoIframe(String xpath) {
        try {
            //定位到iframe元素，然后传递webElement给switchto.frame方法
            WebElement element = driver.findElement ( By.xpath ( xpath ) );
            driver.switchTo ().frame ( element );
        } catch (Exception e) {
            System.out.println ( "切入iframe失败" );
        }
    }

    /**
     * 通过iframe name定位
     * @param name
     */
    public void intoIframeByName(String name) {
        try {
            driver.switchTo ().frame ( name );
        } catch (Exception e) {
            System.out.println ( "切入iframe失败" );
        }

    }

    /**
     * 执行无返回的js脚本
     * @param text
     */
    public void runJs(String text){
        //强转driver为js执行器类型
       JavascriptExecutor js = (JavascriptExecutor)driver;

       try {
           js.executeScript ( text );
       }catch (Exception e){
           System.out.println("JS脚本执行失败");
       }
    }

    /**
     *
     * @param text
     * @return
     */
    public String getJs(String text){
        String t ="";
        JavascriptExecutor js = (JavascriptExecutor)driver;
        try {
           t = js.executeScript ("return" + text ).toString ();
        }catch (Exception e){
            System.out.println("JS脚本执行失败");
        }
        return t;
    }

    /**
     * 浏览器滚动操作
     * @param height
     */
    public void scrollWindowStraight(String height){
      JavascriptExecutor js =  (JavascriptExecutor)driver;
      try {
          String jsCmd = "window.scrollTo(0," + height + ")";
          js.executeScript ( jsCmd );
      }catch (Exception e){
          System.out.println("滚动浏览器失败");
      }
    }

    /**
     * 退出子页面
     */
    public void outIframe(){
        try {
            //切回主页面
            driver.switchTo ().defaultContent ();
        }catch (Exception e){
            System.out.println("切回主页面失败");
        }
    }

    /**
     * 使用optiond的value属性完成select的选择
     * @param xpath
     * @param selectValue
     */
    public void selectValue(String xpath,String selectValue){
        WebElement element = driver.findElement ( By.xpath ( xpath ) );
        Select select = new Select ( element );
        select.selectByValue ( selectValue );
    }

    /**
     * 使用option的文本内容完成select的选择
     * @param xpath
     * @param selectText
     */
    public void selectText(String xpath,String selectText){
        WebElement element = driver.findElement ( By.xpath ( xpath ) );
        Select select = new Select ( element );
        select.selectByVisibleText ( selectText );
    }


    /**操作input文件上传方法
     * 文件上传定位
     * @param xpath
     * @param filePath
     */
    public void upload(String xpath,String filePath){
        try {
            driver.findElement ( By.xpath ( xpath ) ).sendKeys ( filePath );
        } catch (Exception e) {
            System.out.println("文件上传失败");
        }
    }

    /**
     * 退出浏览器
     */
    public void closeBrowser() {
        driver.quit ();
    }



}
