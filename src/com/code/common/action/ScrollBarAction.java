package com.code.common.action;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ScrollBarAction {

	//������������������һ������driver���ڶ�������Ԫ�ص�xpath
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

	//�����������������ȵ������������һ������driver���ڶ�������Ԫ�ص�xpath
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

	//������������������һ������driver���ڶ�������Ԫ�ص�xpath
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
