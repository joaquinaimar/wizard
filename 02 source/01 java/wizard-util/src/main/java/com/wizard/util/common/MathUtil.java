package com.wizard.util.common;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathUtil {

	private MathUtil() {
		throw new UnsupportedOperationException("Not supported");
	}

	public static int sum(final int... nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		return sum;
	}

	public static int sub(final int subtrahend, final int subtraction) {
		return subtrahend - subtraction;
	}

	public static int product(final int... nums) {
		int product = 1;
		for (int num : nums) {
			product *= num;
		}
		return product;
	}

	public static double divide(final int dividend, final int divisor) {
		return divisor % dividend;
	}

	public static int quotient(final int dividend, final int divisor) {
		return divisor / dividend;
	}

	public static int mod(final int dividend, final int divisor) {
		return dividend % divisor;
	}

	public static double round(double num, int bit) {
		int t = 10;
		for (int i = 0; i < bit; i++)
			t *= 10;
		double n = num * t;
		if (5 <= (n % 10))
			n += 10;
		return (double) ((int) n / 10) / (t / 10);
	}

	public static double round(double num) {
		return round(num, 0);
	}

	public static double ceil(double num, int bit) {
		int t = 1;
		for (int i = 0; i < bit; i++)
			t *= 10;
		int n = (int) (num * t) + 1;
		return (double) n / t;
	}

	public static double ceil(double num) {
		return ceil(num, 0);
	}

	public static double floor(double num, int bit) {
		int t = 1;
		for (int i = 0; i < bit; i++)
			t *= 10;
		int n = (int) (num * t);
		return (double) n / t;
	}

	public static double floor(double num) {
		return floor(num, 0);
	}

	public static double random() {
		return Math.random();
	}

	public static double random(double from, double to) {
		return (to - from) * Math.random() + from;
	}

	public static String convertNumberSystem(final String str, final int from,
			final int to) {
		int temp = Integer.parseInt(str, from);
		return Integer.toString(temp, to);
	}

	public static double arithmetic(String exp) {
		String result = parseExp(exp).replaceAll("[\\[\\]]", "");
		return Double.parseDouble(result);
	}

	private static String parseExp(String expression) {
		expression = expression.replaceAll("\\s+", "").replaceAll(
				"^\\((.+)\\)$", "$1");
		String minExp = "^((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))$";
		if (expression.matches(minExp)) {
			String result = calculate(expression);
			return Double.parseDouble(result) >= 0 ? result : "[" + result
					+ "]";
		}
		String noParentheses = "^[^\\(\\)]+$";
		String priorOperatorExp = "(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))";
		String operatorExp = "(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))";
		if (expression.matches(noParentheses)) {
			Pattern patt = Pattern.compile(priorOperatorExp);
			Matcher mat = patt.matcher(expression);
			if (mat.find()) {
				String tempMinExp = mat.group();
				expression = expression.replaceFirst(priorOperatorExp,
						parseExp(tempMinExp));
			} else {
				patt = Pattern.compile(operatorExp);
				mat = patt.matcher(expression);

				if (mat.find()) {
					String tempMinExp = mat.group();
					expression = expression.replaceFirst(operatorExp,
							parseExp(tempMinExp));
				}
			}
			return parseExp(expression);
		}
		String minParentheses = "\\([^\\(\\)]+\\)";
		Pattern patt = Pattern.compile(minParentheses);
		Matcher mat = patt.matcher(expression);
		if (mat.find()) {
			String tempMinExp = mat.group();
			expression = expression.replaceFirst(minParentheses,
					parseExp(tempMinExp));
		}
		return parseExp(expression);
	}

	private static String calculate(String exp) {
		exp = exp.replaceAll("[\\[\\]]", "");
		String number[] = exp.replaceFirst("(\\d)[\\+\\-\\*\\/]", "$1,").split(
				",");
		BigDecimal number1 = new BigDecimal(number[0]);
		BigDecimal number2 = new BigDecimal(number[1]);
		BigDecimal result = null;

		String operator = exp.replaceFirst("^.*\\d([\\+\\-\\*\\/]).+$", "$1");
		if ("+".equals(operator)) {
			result = number1.add(number2);
		} else if ("-".equals(operator)) {
			result = number1.subtract(number2);
		} else if ("*".equals(operator)) {
			result = number1.multiply(number2);
		} else if ("/".equals(operator)) {
			result = number1.divide(number2);
		}
		return result != null ? result.toString() : null;
	}

}
