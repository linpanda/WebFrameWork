package com.code.common.cmd;

public class KillChromeKillDriver {

	public static void main(String args[]) {
		Runtime run = Runtime.getRuntime();
		Process process = null;
		try {
			process = run.exec("taskkill /im chrome.exe /F"); // ִ��cmd����
			process = run.exec("taskkill /im chromedriver.exe /F"); // ִ��cmd����
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
