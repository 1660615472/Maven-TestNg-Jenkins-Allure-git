package com.cheer.testCase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cheer.page.WarehouseInformation;
import com.cheer.testUtils.KeywordOfWeb;

public class TestNgCase {
	 KeywordOfWeb keyword ;
	    //提升driver对象的作用域
	    public WebDriver driver;

	    public WebDriver getDriver(){
	        return driver;
	    }

	    /**
	     * 类运行执行前缀操作
	     */
	    @BeforeClass
	    public void startExplorer() {
	        //打开谷歌浏览器
	        keyword = new KeywordOfWeb ();
	        keyword.openBrowser ( "Chrome" );
	        String url = "http://cztest.winlison.cn/Home/Index";
	        keyword.visitWebUrl ( url );
	        //设置全局隐式等待
	        keyword.displayWait ();
	        //输入系统账号
	        keyword.inputByXpath ( "//input[@id='hs_username']","jack" );
	        //输入系统密码
	        keyword.inputByXpath ( "//input[@id='hs_password']","000000" );
	        //点击登录
	        keyword.click ( "//div[@id='hs_login_btn']/span");

	    }





	    /**
	     * AfterMethod注解，执行完每个方法后的操作
	     */
	    @AfterMethod
	    public void wait1()throws Exception{
	        Thread.sleep ( 3000 );
	    }

	    /**
	     * @Test
	     * 测试方法注解，如果没有定义先后顺序则从上而下执行
	     */
	   // @Feature( "仓库资料自动化测试" )
	  //  @Stories( value = {@Story( value = "初始化")})
	    @Test()
	    public void openWeb()throws Exception {
	        WarehouseInformation warehouseInformation = new WarehouseInformation ( keyword );
	        //新增仓库资料数据
	        warehouseInformation.insertData ();
	        //测试启用禁用
	        warehouseInformation.EnableAndProhibit();
	    }







	    /**
	     * 类运行执行后置操作
	     */
	    @AfterClass
	    public void closeExplorer() {
	        //关闭浏览器
	        keyword.closeBrowser ();
	    }



}
