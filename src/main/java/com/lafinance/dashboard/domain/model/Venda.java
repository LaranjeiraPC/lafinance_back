package com.lafinance.dashboard.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "venda")
public class Venda implements Serializable {

	private static final long serialVersionUID = -3574568785078311013L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ativo", nullable = false)
	private Ativo ativo;
	 
	@Column(name="quantidade", nullable=false)
	private Integer quantidade;
	
	@Column(name="valorbrutovenda", nullable=false)
	private BigDecimal valorBrutoVenda;
	
	@Column(name="valorativovenda", nullable=false)
	private BigDecimal valorAtivoVenda;
	
	@Column(name="datavenda", nullable=false)
	private LocalDate dataVenda;
	
	@Column(name="datacriacao", nullable=false)
	private LocalDate mesCriacao;
	
	@Column(name="dataatualizacao")
	private LocalDate mesAtualizacao;
		
	public Venda() {}
}
