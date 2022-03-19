package com.lafinance.dashboard.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "compravenda")
public class CompraVenda implements Serializable {

	private static final long serialVersionUID = -760598687272966313L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "compra", nullable = false)
	private Acao compra;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venda", nullable = false)
	private Venda venda;
	
	public CompraVenda() {}
}
