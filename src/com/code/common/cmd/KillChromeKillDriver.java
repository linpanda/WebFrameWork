package com.code.common.cmd;

public class KillChromeKillDriver {

	public static void main(String args[]) {
		Runtime run = Runtime.getRuntime();
		Process process = null;
		try {
			process = run.exec("taskkill /im chrome.exe /F"); // ÷¥––cmd√¸¡Ó
			process = run.exec("taskkill /im chromedriver.exe /F"); // ÷¥––cmd√¸¡Ó
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
