package com.code.testcode.deletefile;

import org.testng.annotations.Test;

import com.code.common.conutil.GetTCproperties;
import com.code.common.file.FileProcess;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class test_delete {
	// ��ȡtestcon.properties�����ļ��е�����excel�ļ���·��
	public String getExportFilePath() throws Exception {
		return new String(GetTCproperties.getValue("ExportFilePath"));
	}

	// ƴ�ӵ���excel�ļ�
	public String getExportFile() throws Exception {
		return new String(getExportFilePath() + "10004300-OR04-20170331.xlsx");
	}
	
	@Test(enabled = true, priority = 1)
	public void deletefile() {
		
		try {
			Thread.sleep(10000);
			FileProcess.deletefile(getExportFile());
		} catch (Exception e) {
			System.out.println("ɾ���ļ�����" + e);
		}
	}
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

}
