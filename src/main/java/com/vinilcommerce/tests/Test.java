package com.vinilcommerce.tests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Test {

	
	public static void main(String[] args) {
		
		LocalDate data = LocalDate.now();
		System.out.println(data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		
		System.out.println(data.getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE, new Locale("pt")));
		
	}
}
