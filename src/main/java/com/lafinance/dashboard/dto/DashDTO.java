package com.lafinance.dashboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DashDTO {
	
	private Integer quantidadeTotal;
	private BigDecimal valorBrutoTotal;
	private BigDecimal valorInvestimentoTotal;
	private BigDecimal valorBrutoMeta;
	private String ativoUm;
	private String ativoDois;
	
	public DashDTO () {}

}
