package com.lafinance.dashboard.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "ativo")
public class Ativo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6649075861060230218L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="status", nullable=false)
	private String status;
	
	public Ativo () {}
	
}
