package com.lafinance.dashboard.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.lafinance.dashboard.model.Ativo;
import com.lafinance.dashboard.model.Venda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDTO {

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
