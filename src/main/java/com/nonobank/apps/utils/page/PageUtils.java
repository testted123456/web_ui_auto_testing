package com.nonobank.apps.utils.page;

import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Function;
import com.nonobank.apps.objectRepository.WebElementType;
import com.nonobank.apps.utils.driver.WebDriverUtils;
import com.nonobank.apps.utils.file.ParseXML;

public class PageUtils {
	
	protected static Logger logger = LogManager.getLogger(PageUtils.class);
	
	/**
	 * 函数说明：等待指定时间
	 * @param time
	 */
	public static void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 函数说明：跳转到指定url
	 * @param url
	 */
	public static void navigateToURL(String url){
		WebDriver driver = WebDriverUtils.getWebDriver();
		driver.navigate().to(url);
//		refreshPage();
		waitForPageLoad();
		sleep(100);
	}
	
	/**
	 * 函数说明：页面加载是否完成
	 * @return
	 */
	protected static Function<WebDriver, Boolean> isPageLoaded() {
        return new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
    }
	
	/**
	 * 函数说明：等待页面加载完成
	 * @param driver
	 */
	public static void waitForPageLoad() {
		WebDriver driver = WebDriverUtils.getWebDriver();
	    WebDriverWait wait = new WebDriverWait(driver, 60);
	    wait.until(isPageLoaded());
	}
	
	/**
	 * 函数说明：刷新页面
	 * @param driver
	 */
	public static void refreshPage(){
		WebDriver driver = WebDriverUtils.getWebDriver();
		driver.navigate().refresh();
	}
	
	/**
	 * 函数说明：获取当前url
	 * @param driver
	 * @return
	 */
	public static String getUrl(){
		WebDriver driver = WebDriverUtils.getWebDriver();
		return driver.getCurrentUrl();
	}
	
	/**
	 * 函数说明：获取页面的title
	 * @param driver
	 * @return
	 */
	public static String getTitle(){
		WebDriver driver = WebDriverUtils.getWebDriver();
		try {
			return new String(driver.getTitle().getBytes("utf-8"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 函数说明：根据页面title跳转
	 * @param driver
	 * @param windowTitle
	 * @return
	 */
	public static boolean switchToWindow(String windowTitle){
		WebDriver driver = WebDriverUtils.getWebDriver();
		boolean flag = false;
		try {
			String currentHandle = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			for (String s : handles) {
				if (s.equals(currentHandle))
					continue;
				else {
					driver.switchTo().window(s);
					if (getTitle().contains(windowTitle)) {
						flag = true;
							logger.info("Switch to window: " + windowTitle  + " successfully!");
						break;
					} else
						continue;
				}
			}
		} catch (NoSuchWindowException e) {
			logger.info("Window: " + windowTitle
					+ " cound not found!");
			e.printStackTrace();
			flag = false;
		}
	    return flag;
	}

	/**
	 * 函数说明：关闭所有窗口
	 * @param driver
	 */
	public static void closeAllWindows(){
		WebDriver driver = WebDriverUtils.getWebDriver();
		Set<String> handles = driver.getWindowHandles();
		
		for(String s : handles){
			String title = driver.switchTo().window(s).getTitle();
			logger.info("try to cloase window " + title);
			driver.switchTo().window(s).close();
		}
		
		handles = driver.getWindowHandles();
		
		while(handles.size() != 0){
			handles = driver.getWindowHandles();
		}
	}
	
	/**
	 * 函数说明：根据指定url打开窗口
	 * @param driver
	 * @param url
	 */
	public static void openPage(String url){
		WebDriver driver = WebDriverUtils.getWebDriver();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	/**
	 * 函数说明：在当前位置左击
	 */
	public static void leftClick(){
		WebDriver driver = WebDriverUtils.getWebDriver();
		Actions action = new Actions(driver);
		action.click();
	}
	
	/**
	 * 函数说明：在当前位置右击
	 */
	public static void rightClick(){
		WebDriver driver = WebDriverUtils.getWebDriver();
		Actions action = new Actions(driver);
		action.contextClick();
	}
	
	/**
	 * 函数说明：在当前位置双击
	 */
	public static void doubleClick(){
		WebDriver driver = WebDriverUtils.getWebDriver();
		Actions action = new Actions(driver);
		action.doubleClick();
	}
	
	/**
	 * 函数说明：在当前位置鼠标悬停
	 */
	public static void clickAndHold(){
		WebDriver driver = WebDriverUtils.getWebDriver();
		Actions action = new Actions(driver);
		action.clickAndHold();
	} 
	
	/**
	 * 函数说明：鼠标释放
	 */
	public static void release(){
		WebDriver driver = WebDriverUtils.getWebDriver();
		Actions action = new Actions(driver);
		action.release();
	}
	
	/**
	 * 函数说明：模拟键盘输入
	 * @param driver
	 * @param keys
	 */
	public static void sendKeys(Keys keys){
		WebDriver driver = WebDriverUtils.getWebDriver();
		Actions action = new Actions(driver);
		action.sendKeys(keys).perform();
	}
	
	/**
	 * 函数说明：模拟修饰键
	 * @param driver
	 * @param keys:Keys.ALT,Keys.SHIFT,Keys.CONTROL
	 */
	public static void keyUp(Keys keys){
		WebDriver driver = WebDriverUtils.getWebDriver();
		Actions action = new Actions(driver);
		action.keyUp(keys).perform();
	}
	
	/**
	 * 函数说明：模拟修饰键
	 * @param driver
	 * @param keys:Keys.ALT,Keys.SHIFT,Keys.CONTROL
	 */
	public static void keyDown(Keys keys){
		WebDriver driver = WebDriverUtils.getWebDriver();
		Actions action = new Actions(driver);
		action.keyDown(keys).perform();
	}
}

