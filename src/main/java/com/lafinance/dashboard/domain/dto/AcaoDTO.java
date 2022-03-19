package com.lafinance.dashboard.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.lafinance.dashboard.domain.model.Acao;
import com.lafinance.dashboard.domain.model.Ativo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcaoDTO {

	private Integer id;
	private Ativo ativo;
	private Integer quantidade;
	private BigDecimal valorBrutoPago;
	private BigDecimal valorAtivoPago;
	private LocalDate dataCompra;
	private LocalDate mesCriacao;
	private LocalDate mesAtualizacao;
	private String status;
	private BigDecimal precoAlvo;
	private BigDecimal precoHoje;
	
	public AcaoDTO() {}
	
	public AcaoDTO(Acao acao) {
		setId(acao.getId());
		setAtivo(acao.getAtivo());
		setQuantidade(acao.getQuantidade());
		setValorBrutoPago(acao.getValorBrutoPago());
		setValorAtivoPago(acao.getValorAtivoPago());
		setDataCompra(acao.getDataCompra());
		setMesCriacao(acao.getMesCriacao());
		setMesAtualizacao(acao.getMesAtualizacao());
		setStatus(acao.getStatus());
		setPrecoAlvo(acao.getPrecoAlvo());
		setPrecoHoje(acao.getPrecoHoje());
	}

}
