package com.vinilcommerce.tests;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

public class Test {

	/*private static void loadCashbacks(BigDecimal productValue) {
		List<Genre> genres = Arrays.asList(Genre.values());

		genres.stream().forEach(genre -> {
			
			String genreString = genre.name();
			System.out.println("--------"+genreString+"--------");
			
			List<DayOfWeek> days = Arrays.asList(DayOfWeek.values());
			
			days.stream().forEach(day -> {
				System.out.println(day);
				
				new Cashback(genre, day, productValue);
				
			});
			
		});
	}*/
	
	public static void main(String[] args) {
			
		for(int i=0; i < 100; i++) {
			
			double random = (Math.random()*50)+20;
			BigDecimal randomNumber = new BigDecimal(random).setScale(2, RoundingMode.CEILING);
			System.out.println(randomNumber);
		}
		
	}
	
	/*public static void main(String[] args) {
		int range = 2;
		BigDecimal max = new BigDecimal(range);
		BigDecimal randFromDouble = new BigDecimal(Math.random());
		System.out.println(randFromDouble);
		
		String range = args[0];
	    BigDecimal max = new BigDecimal(range + ".0");
	    BigDecimal randFromDouble = new BigDecimal(Math.random());
	    BigDecimal actualRandomDec = randFromDouble.divide(max,BigDecimal.ROUND_DOWN);

	    BigInteger actualRandom = actualRandomDec.toBigInteger();
	    
	    System.out.println(actualRandom);
		
	}*/

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
