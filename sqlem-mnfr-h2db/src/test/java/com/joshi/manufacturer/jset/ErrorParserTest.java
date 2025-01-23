package com.joshi.manufacturer.jset;

import static org.assertj.core.api.Assertions.linesOf;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class ErrorParserTest {

//	@Test
//	void test42122() {
//		
//		System.out.println("42122|Column \"NAME\" not found");
//		String err = "42122";
//		String msg = "Column \"NAME\" not found";
//		String p1 = "\"([^\"]*)[\s|\"]";				//"\"([^\"]*)\"";
//		
//		Pattern p = Pattern.compile(p1);
//		Matcher m = p.matcher(msg);
//		System.out.print(err + "|" + msg);
//		while (m.find()) {
//			for (int i = 0; i <= m.groupCount(); i++) {
//				System.out.print("|" + m.group(i));
//			}
//		}
//		System.out.println("");
//	}
	
	@Test
	void testApplyPattern() {
		
		System.out.println("testApplyPattern...");
		List<H2Error> errObjs = getConstraintsTestOutput();
		
		for (H2Error h2Error : errObjs) {
			//System.out.println(patternBased(h2Error));
			System.out.println(splitBased(h2Error));
//			Pattern p = Pattern.compile((h2Error.pattern == null? defPat : h2Error.pattern));
//			Matcher m = p.matcher(h2Error.originalMessage);
//			System.out.print(h2Error.errorCode + "|" + h2Error.originalMessage);
//			if (m.find()) {
//				System.out.print("|" + m.groupCount());
//				String t = m.group(1);
//				if (t.contains(" ")) {
//					t = t.split(" ")[0];
//				}
////				for (int i = 1; i <= m.groupCount(); i++) {
//					System.out.print("|" + t);
////				}
//			}
//			System.out.println("");
		}
	}
	
	private List<H2Error> getConstraintsTestOutput() {
		List<String> output = linesOf(ErrorParserTest.class.getResource("/h2-error-output.txt"));
		List<H2Error> errObjs = new ArrayList<H2Error>();
		output.forEach(s -> {
			String t[] = s.split("\\|");
			errObjs.add(new H2Error(t[0], t[1]));
		});
		return errObjs;
	}
	
	private String patternBased(H2Error h2Error) {
		String defPat = "\"([^\"]*)\"";
		Pattern p = Pattern.compile((h2Error.pattern == null? defPat : h2Error.pattern));
		Matcher m = p.matcher(h2Error.originalMessage);
		String result = h2Error.errorCode + "|" + h2Error.originalMessage;
		if (m.find()) {
			result += "|" + m.groupCount();
			String t = m.group(1);
			if (t.contains(" ")) {
				t = t.split(" ")[0];
			}
//			for (int i = 1; i <= m.groupCount(); i++) {
				result += "|" + t;
//			}
		}
		return result;

	}
	
	private String splitBased(H2Error h2Error) {
		String defPat = "\"";
		String[] tokens = h2Error.originalMessage.split(defPat);
		String result = h2Error.errorCode + "|" + h2Error.originalMessage;
		if (tokens.length > 1) {
			if (tokens[1].contains(" ")) {
				result += "|" + tokens[1].split(" ")[0];
			} else {
				result += "|" + tokens[1];
			}
		}
		return result;
	}
	
}

class H2Error {
	String errorCode;
	String originalMessage;
	String pattern;
	
	H2Error(String errorCode, String originalMessage) {
		this.errorCode = errorCode;
		this.originalMessage = originalMessage;
	}
	
	
	public String toString() {
		return errorCode + "|" + originalMessage;
	}
}