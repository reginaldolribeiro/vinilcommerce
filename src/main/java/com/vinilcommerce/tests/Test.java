package com.vinilcommerce.tests;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Test {
	
	public static void main(String[] args) {
		/*int range = 2;
		BigDecimal max = new BigDecimal(range);
		BigDecimal randFromDouble = new BigDecimal(Math.random());
		System.out.println(randFromDouble);*/
		
		String range = args[0];
	    BigDecimal max = new BigDecimal(range + ".0");
	    BigDecimal randFromDouble = new BigDecimal(Math.random());
	    BigDecimal actualRandomDec = randFromDouble.divide(max,BigDecimal.ROUND_DOWN);

	    BigInteger actualRandom = actualRandomDec.toBigInteger();
	    
	    System.out.println(actualRandom);
		
	}

	/*public static void main(String[] args) {

		int range = 2;
		System.out.println(random(range));

	}

	public static BigDecimal random(int range) {
		BigDecimal max = new BigDecimal(range);
		BigDecimal randFromDouble = new BigDecimal(Math.random());
		BigDecimal actualRandomDec = randFromDouble.multiply(max);
		actualRandomDec = actualRandomDec.setScale(2, BigDecimal.ROUND_DOWN);
		return actualRandomDec;
	}*/

	/*
	 * public static void main(String[] args) {
	 * 
	 * LocalDate data = LocalDate.now();
	 * System.out.println(data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	 * 
	 * System.out.println(data.getDayOfWeek().getDisplayName(TextStyle.
	 * FULL_STANDALONE, new Locale("pt")));
	 * 
	 * }
	 */
}
