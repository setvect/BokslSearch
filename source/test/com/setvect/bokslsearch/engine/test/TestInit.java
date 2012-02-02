package com.setvect.bokslsearch.engine.test;

import java.io.File;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.setvect.DummyClass;
import com.setvect.bokslsearch.engine.SearchAppBootup;

/**
 * �׽�Ʈ�� ���ø����̼� ����(��Ʈ��), ���� ���� ���� ����<br>
 * �׽�Ʈ ���̽� Ŭ������ �� Ŭ���� ��� �޾� �����
 */
public class TestInit {
	@BeforeClass
	public static void load() {
		URL a = DummyClass.class.getResource("");
		File appHome = new File(a.getFile(), "../../../../");
		SearchAppBootup.bootUp(appHome, true);
	}

	@AfterClass
	public static void afterClass() {
	}
}
