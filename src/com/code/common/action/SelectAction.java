package com.code.common.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectAction {

	// 下拉框操作，第一个参数driver，第二个参数下拉框的xpath，第三个参数value属性值
	public static String selectElementValue(WebDriver driver, String xpathString, String values) {
		try {
			Select datasource = new Select(driver.findElement(By.xpath(xpathString)));
			datasource.selectByValue(values);
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
