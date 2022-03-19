package com.lafinance.dashboard.service.impl;

import java.util.List;

import com.lafinance.dashboard.domain.model.Acao;
import com.lafinance.dashboard.service.AcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.domain.model.CompraVenda;
import com.lafinance.dashboard.repository.CompraVendaRepository;
import com.lafinance.dashboard.service.CompraVendaService;

@Service
@Transactional
public class CompraVendaServiceImpl implements CompraVendaService{

	@Autowired
	private CompraVendaRepository repository;

	@Autowired
	private AcaoService acaoService;

	@Override
	public void salvarRegistro(List<CompraVenda> compraVenda) {
		repository.saveAll(compraVenda);
		repository.flush();
	}

	@Override
	public List<CompraVenda> consultarCompraVendaPeloIdVenda(Integer id) {
		return repository.findByVendaId(id);
	}

	@Override
	public List<CompraVenda> consultarCompraVendaPeloIdCompra(Integer id) {;
		return repository.findByCompraId(id);
	}

	@Override
	public void excluirCompraVendaPeloIdVenda(Integer id) {
		try {
			List<Acao> acaoList = this.acaoService.consultarAcoesPeloIdVenda(id);
			this.acaoService.ativarAcoes(acaoList);

			List<CompraVenda> registros = this.repository.findByVendaId(id);

			this.repository.deleteInBatch(registros);
		}catch (Exception e){
			throw e;
		}
	}

}
