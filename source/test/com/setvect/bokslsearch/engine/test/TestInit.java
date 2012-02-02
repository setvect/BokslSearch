package com.setvect.bokslsearch.engine.test;

import java.io.File;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.setvect.DummyClass;
import com.setvect.bokslsearch.engine.SearchAppBootup;

/**
 * 테스트시 어플리케이션 시작(부트업), 종료 관련 구문 실행<br>
 * 테스트 케이스 클래스는 본 클래스 상속 받아 사용함
 */
public class TestInit {
	@BeforeClass
	public static void load() {
		URL a = DummyClass.class.getResource("");
		File appHome = new File(a.getFile(), "../../../../");
		SearchAppBootup.bootUp(appHome);
	}

	@AfterClass
	public static void afterClass() {
	}
}
