package com.att.eg.profile.mysubscriptions.info.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.ajsc.common.RequestScopeModifier;
import com.att.eg.profile.mysubscriptions.info.Application;
import com.att.eg.profile.mysubscriptions.info.model.ColorCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, RequestScopeModifier.class }, webEnvironment=WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class ColorCodeBuilderTest {
	
	private ColorCodeBuilder colorCodeBuilder;
	private ColorCode LIVE_A_LITTLE_COLOR_CODE = new ColorCode("FAC261", "D33221");
	private ColorCode JUST_RIGHT_COLOR_CODE = new ColorCode("C4EA80", "429321");
	private ColorCode GO_BIG_COLOR_CODE = new ColorCode("5FBDE0", "1B81A7");
	private ColorCode GOTTA_HAVE_IT_COLOR_CODE = new ColorCode("847DE5", "5C228A");
	private ColorCode DEFAULT_COLOR_CODE = new ColorCode("9F9F9F", "5A5A5A");
	private ColorCode NULL_COLOR_CODE = new ColorCode("4A4A4A", "4A4A4A");
	private ColorCode actualColorCode;
	private String basePackageName;
	
	@Before
	public void setUp() throws Exception {
		colorCodeBuilder = new ColorCodeBuilderImpl();
	}
	
	@Test
	public void colorCodeBuilderReturnsExpectedColorCodeForLiveALittlePackage() throws Exception {
		givenPackageName("Live a Little");
		whenColorCodeIsBuilt();
		thenColorCodeIs(LIVE_A_LITTLE_COLOR_CODE);
	}
	
	@Test
	public void colorCodeBuilderReturnsExpectedColorCodeForJustRightPackage() throws Exception {
		givenPackageName("Just Right");
		whenColorCodeIsBuilt();
		thenColorCodeIs(JUST_RIGHT_COLOR_CODE);
	}

	@Test
	public void colorCodeBuilderReturnsExpectedColorCodeForGoBigPackage() throws Exception {
		givenPackageName("Go Big");
		whenColorCodeIsBuilt();
		thenColorCodeIs(GO_BIG_COLOR_CODE);
	}
	
	@Test
	public void colorCodeBuilderReturnsExpectedColorCodeForGottaHaveItPackage() throws Exception {
		givenPackageName("Gotta Have It");
		whenColorCodeIsBuilt();
		thenColorCodeIs(GOTTA_HAVE_IT_COLOR_CODE);
	}
	
	@Test
	public void colorCodeBuilderReturnsExpectedColorCodeForOtherPackage() throws Exception {
		givenPackageName("Other");
		whenColorCodeIsBuilt();
		thenColorCodeIs(DEFAULT_COLOR_CODE);
	}
	
	@Test
	public void colorCodeBuilderReturnsExpectedColorCodeForNoPackage() throws Exception {
		givenPackageName(null);
		whenColorCodeIsBuilt();
		thenColorCodeIs(NULL_COLOR_CODE);
	}
	
	private void thenColorCodeIs(ColorCode expectedColorCode) {
		assertEquals(expectedColorCode.getStartCode(),actualColorCode.getStartCode());
		assertEquals(expectedColorCode.getEndCode(),actualColorCode.getEndCode());
	}

	private void whenColorCodeIsBuilt() {
		this.actualColorCode = colorCodeBuilder.buildColorCode(basePackageName);
		
	}

	private void givenPackageName(String basePackageName) {
		this.basePackageName = basePackageName;
	}
	
}
