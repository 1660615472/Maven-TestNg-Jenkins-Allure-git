package com.cheer.driverUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverUtil {
	 private WebDriver driver = null;

	    public ChromeDriverUtil(String driverPath) {
	        //设置chrome驱动路径
	        System.setProperty ( "webdriver.chrome.driver", driverPath );
	        //创建浏览器参数对象
	        ChromeOptions options = new ChromeOptions ();
	        //去除chorme浏览器上的黄色警告
	        options.addArguments ( "--disable-infobars" );
	        //加载chorme用户文件
	        options.addArguments ( "--user-data-dir=C:\\Users\\HP\\AppData\\Local\\Google\\Chrome\\Application" );
	        //最大化浏览器窗口
	        options.addArguments ( "--start-maximized" );
	        //白名单设置
	        options.addArguments ( "--whitelisted-ips=\"\"" );
	        //创建chrome浏览器实例
	        try {
	            this.driver = new ChromeDriver ( options );
	        } catch (Exception e) {
	            e.printStackTrace ();
	            System.out.println ( "log--error,创建driver失败了" );
	        }
	    }

	    public WebDriver getDriver() {
	        return this.driver;
	    }
}
