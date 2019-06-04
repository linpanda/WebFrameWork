package com.code.common.driver;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.code.common.conutil.GetTCproperties;

public class DriverFactory {

	// 读取testcon.properties配置文件中chrome个人设置路径
	public static String getChromeSetting() throws Exception {
		return new String(GetTCproperties.getValue("ChromePersonalSetting"));
	}

	public static WebDriver ChromeCreate(String URLAddress) {

		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		try {
			options.addArguments(getChromeSetting());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriver driver = new ChromeDriver(options);
		driver.get(URLAddress);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
}
