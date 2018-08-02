package ua.rozhkov;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NumberParserTest {
	String actual;
	String expected;
	NumberParser numberParser;
	
	public NumberParserTest (String actual, String expected) {
		this.actual = actual;
		this.expected = expected;
		this.numberParser = new NumberParser();
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> data () {
		return Arrays.asList(new Object[][]{
				{"22", "двадцать два"},
				{"459", "четыреста пятьдесят девять"}
		});
	}
	
	@Ignore
	@org.junit.Before
	public void setUp () throws Exception {
	}
	
	@Test
	public void parseNumber () throws Exception {
		String ac = numberParser.parseNumber(actual);
		assertEquals(ac, expected);
	}
	
}