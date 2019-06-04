package com.code.common.clip;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class ClipboardData {
	public static void setClipboardData(String string) {

		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	
	
	public static void ctrlRobert(){
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}

		// robot模拟键盘操作，模拟键盘操作有其它方法，类似
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		// 同时按下CONTROL+V，将上传文件路径黏贴到文件名
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		// 释放CONTROL+V
		robot.keyPress(KeyEvent.VK_ENTER);
		// 回车事件，相当于点击打开
	}
	
}
