package com.code.common.action;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ScrollBarAction {

	//滚动条下拉操作，第一个参数driver，第二个参数元素的xpath
	public static String scrollBarDown(WebDriver driver, String xpathString) {
		try {
			Thread.sleep(5000);
			WebElement elementItem = driver.findElement(By.xpath(xpathString));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementItem);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementItem);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	//滚动条下拉操作，先点击再下拉，第一个参数driver，第二个参数元素的xpath
	public static String scrollBarClickDown(WebDriver driver, String xpathString) {
		try {
			Thread.sleep(5000);
			driver.findElement(By.xpath(xpathString)).click();
			Thread.sleep(5000);
			WebElement elementItem = driver.findElement(By.xpath(xpathString));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementItem);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementItem);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	//滚动条上拉操作，第一个参数driver，第二个参数元素的xpath
	public static String scrollBarUP(WebDriver driver, String xpathString) {
		try {
			Thread.sleep(5000);
			WebElement elementItem = driver.findElement(By.xpath(xpathString));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", elementItem);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
