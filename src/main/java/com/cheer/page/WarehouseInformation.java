package com.cheer.page;

import com.cheer.testUtils.KeywordOfWeb;

public class WarehouseInformation {
	 KeywordOfWeb keyword ;

	    public WarehouseInformation(KeywordOfWeb keyword) {
	        this.keyword = keyword;

	    }

	    /**
	     * 新增一条仓库资料
	     * @throws Exception
	     */
	    public void insertData()throws Exception{
	        keyword.sleep ( "5" );
	        keyword.click ( "//a[@id='9687988d-4bbd-486a-9eda-e77c3c189a1f']/span[1]" );
	        keyword.sleep ( "5" );
	        keyword.click ( "//a[@id='ebfb0d40-3c4d-4407-a994-ed81cafd2ce2']/span[1]" );
	        //进入第一个Iframe
	        keyword.intoIframeByName ( "hs_iframe_ebfb0d40-3c4d-4407-a994-ed81cafd2ce2" );
	        keyword.click ( "//a[@id='hs_add']" );
	        keyword.outIframe ();
	        keyword.intoIframeByName ( "layui-layer-iframe1" );
	        keyword.sleep ( "2" );
	        keyword.inputByXpath ( "//input[@id='F_WH_CODE']","Jack"+ (int)(Math.random()*100+1) );
	        keyword.sleep ( "2" );
	        keyword.inputByXpath ( "//input[@id='F_WH_NAME']","Jack仓库"+ (int)(Math.random()*100+1) );
	        keyword.sleep ( "2" );
	        keyword.click ( "//div[@id='F_IE_TRADE']/div/input[1]" );
	        keyword.sleep ( "2" );
	        keyword.click ( "//*[@id='hansel_select_option_contentF_IE_TRADE']/li[2]" );
	        keyword.sleep ( "2" );
	        keyword.inputByXpath ( "//div[@id='F_COP_CODE']/input","Sea" );
	        keyword.sleep ( "2" );
	        keyword.inputByXpath ( "//input[@id='F_SORTCODE']",(int)(Math.random()*100+1)+"" );
	        keyword.sleep ( "2" );
	        keyword.outIframe ();
	        keyword.sleep ( "2" );
	        keyword.click ( "//a[text()='确认']" );
	        

	    }

	    /**
	     * 测试启用禁用功能
	     */
	    public void EnableAndProhibit()throws Exception{
	        keyword.outIframe ();
	        keyword.intoIframeByName ( "hs_iframe_ebfb0d40-3c4d-4407-a994-ed81cafd2ce2" );
	        keyword.sleep ( "2" );
	        keyword.click ( "//div[@id='jfgrid_scrollarea_content_girdtable']" );
	        keyword.sleep ( "2" );
	        //点击启用
	        keyword.click ( "//*[@id='hs_enable']" );

	    }



}
