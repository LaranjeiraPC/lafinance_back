package com.lafinance.dashboard.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.lafinance.dashboard.domain.model.Ativo;
import com.lafinance.dashboard.domain.model.Venda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDTO implements Serializable {

	private Integer id;
	private Ativo ativo;
	private Integer quantidade;
	private BigDecimal valorBrutoVenda;
	private BigDecimal valorAtivoVenda;
	private LocalDate dataVenda;
	private LocalDate mesCriacao;
	private LocalDate mesAtualizacao;
	private List<AcaoDTO> acaoDTO;

	public VendaDTO() {
	}

	public VendaDTO(Venda venda) {
		setId(venda.getId());
		setAtivo(venda.getAtivo());
		setQuantidade(venda.getQuantidade());
		setValorBrutoVenda(venda.getValorBrutoVenda());
		setValorAtivoVenda(venda.getValorAtivoVenda());
		setDataVenda(venda.getDataVenda());
		setMesCriacao(venda.getMesCriacao());
		setMesAtualizacao(venda.getMesAtualizacao());
		setAcaoDTO(acaoDTO);
	}

}
