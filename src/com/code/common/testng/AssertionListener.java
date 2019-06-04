package com.code.common.testng;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class AssertionListener extends TestListenerAdapter {
	private int m_count = 0;
	public static Logger logger= Logger.getLogger(AssertionListener.class);
	
	@Override
	public void onTestStart(ITestResult result) {
		Assertion.flag=true;
		Assertion.errors.clear();
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		log(tr.getName() + "--Test method failed\n");
		this.handleAssertion(tr);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log(tr.getName()+ "--Test method skipped\n");
		this.handleAssertion(tr);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		log(tr.getName()+ "--Test method success\n");
		this.handleAssertion(tr);
	}

	private int index = 0;

	private void handleAssertion(ITestResult tr) {
		if (!Assertion.flag) {
			Throwable throwable = tr.getThrowable();
			if (throwable == null) {
				throwable = new Throwable();
			}
			StackTraceElement[] traces = throwable.getStackTrace();
			StackTraceElement[] alltrace = new StackTraceElement[0];
			for (Error e : Assertion.errors) {	
				int i=Assertion.errors.indexOf(e);
				StackTraceElement[] errorTraces = e.getStackTrace();
				StackTraceElement[] et = this.getKeyStackTrace(tr, errorTraces);
				StackTraceElement[] message = new StackTraceElement[] { new StackTraceElement(
						"错误"+(i+1)+": " + e.getMessage() + " \n           执行失败的方法名: ", tr.getMethod().getMethodName(),
						tr.getTestClass().getRealClass().getSimpleName(), index) };
				index = 0;
				alltrace = this.merge(alltrace, message);
				//alltrace = this.merge(alltrace, et);
			}
			if (traces != null) {
				traces = this.getKeyStackTrace(tr, traces);
				alltrace = this.merge(alltrace, traces);
			}
			throwable.setStackTrace(alltrace);
			tr.setThrowable(throwable);
			Assertion.flag = true;
			Assertion.errors.clear();
			tr.setStatus(ITestResult.FAILURE);
		}
	}

	private StackTraceElement[] getKeyStackTrace(ITestResult tr, StackTraceElement[] stackTraceElements) {
		List<StackTraceElement> ets = new ArrayList<StackTraceElement>();
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			if (stackTraceElement.getClassName().equals(tr.getTestClass().getName())) {
				ets.add(stackTraceElement);
				index = stackTraceElement.getLineNumber();
			}
		}
		StackTraceElement[] et = new StackTraceElement[ets.size()];
		for (int i = 0; i < et.length; i++) {
			et[i] = ets.get(i);
		}
		return et;
	}

	private StackTraceElement[] merge(StackTraceElement[] traces1, StackTraceElement[] traces2) {
		StackTraceElement[] ste = new StackTraceElement[traces1.length + traces2.length];
		for (int i = 0; i < traces1.length; i++) {
			ste[i] = traces1[i];
		}
		for (int i = 0; i < traces2.length; i++) {
			ste[traces1.length + i] = traces2[i];
		}
		return ste;
	}

	private void log(String string) {
		//System.out.print(string);
		logger.debug(string);
		if (++m_count % 40 == 0) {
			logger.debug("");
			//System.out.println("");
		}
	}

}