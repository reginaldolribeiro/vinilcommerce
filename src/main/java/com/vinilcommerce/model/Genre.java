package com.vinilcommerce.model;

public enum Genre {

	POP, MPB, CLASSIC, ROCK;
	
	public static Genre getEnum(String genre) {
		return Genre.valueOf(genre.toUpperCase());
	}
	
}
