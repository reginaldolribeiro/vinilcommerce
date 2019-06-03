package com.vinilcommerce.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cashback {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Genre genre;
	
	@Enumerated(EnumType.STRING)
	private DiaSemana diaSemana;
	private BigDecimal value;

	public Cashback() {
		// TODO Auto-generated constructor stub
	}

	public Cashback(Genre Genre, DiaSemana diaSemana, BigDecimal value) {
		this.genre = genre;
		this.diaSemana = diaSemana;
		this.value = value;
	}

	/*public BigDecimal calculate(Product product, LocalDate data) {

		String diaDaSemana = data.getDayOfWeek().name();
		
		System.out.println(product.getGenre());
		System.out.println(Genre.ROCK);

		if (product.getGenre().equals(Genre.ROCK.name())) {

			if (diaDaSemana.equals(DiaSemana.SATURDAY.name())) {
				//return BigDecimal.ONE.subtract(new BigDecimal(40).divide(new BigDecimal(100)));
				return new BigDecimal(40);
			} else if (diaDaSemana.equals("Segunda-feira")) {
				//return BigDecimal.ONE.subtract(new BigDecimal(10).divide(new BigDecimal(100)));
				return new BigDecimal(10);
			}
		} else if (product.getGenre().equals(Genre.POP.name())) {

			if (diaDaSemana.equals(DiaSemana.SATURDAY.name())) {
				//return BigDecimal.ONE.subtract(new BigDecimal(25).divide(new BigDecimal(100)));
				return new BigDecimal(25);
			} else if (diaDaSemana.equals("Segunda-feira")) {
				//return BigDecimal.ONE.subtract(new BigDecimal(7).divide(new BigDecimal(100)));
				return new BigDecimal(7);
			}

		}

		return BigDecimal.ZERO;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
