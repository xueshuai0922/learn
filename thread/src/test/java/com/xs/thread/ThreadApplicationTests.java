package com.xs.thread;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ThreadApplicationTests {

	@Test
	@EnabledOnOs(OS.WINDOWS) //条件测试
	void contextLoads() {
	}

	@ParameterizedTest //参数化测试
	@ValueSource(ints = { 0, 1, 5, 100 })
	void testAbs(int x) {
		assertEquals(x, Math.abs(x));
	}

	@ParameterizedTest// 参数化测试，这个参数可以是个静态的同名方法提供的Arguments的列表List
	@MethodSource
	void testCapitalize(String input, String result) {
		assertEquals(result, StringUtils.capitalize(input));
	}

	static List<Arguments> testCapitalize() {
		ArrayList<Arguments> arguments = new ArrayList<>();
		arguments.add(Arguments.arguments("abc", "Abc"));
		arguments.add(Arguments.arguments("APPLE", "Apple"));
		return		 arguments;
	}
}
