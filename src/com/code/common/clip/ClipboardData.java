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

		// robotģ����̲�����ģ����̲�������������������
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		// ͬʱ����CONTROL+V�����ϴ��ļ�·��������ļ���
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		// �ͷ�CONTROL+V
		robot.keyPress(KeyEvent.VK_ENTER);
		// �س��¼����൱�ڵ����
	}
	
}
