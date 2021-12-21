package com.lafinance.dashboard.service.impl;

import java.util.List;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.service.AcaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.model.CompraVenda;
import com.lafinance.dashboard.repository.CompraVendaRepository;
import com.lafinance.dashboard.service.CompraVendaService;

@Service
@Transactional
public class CompraVendaServiceImpl implements CompraVendaService{

	private final Logger log = LoggerFactory.getLogger(CompraVendaServiceImpl.class);
	
	private final CompraVendaRepository repository;
	private final AcaoService acaoService;
	
	public CompraVendaServiceImpl(CompraVendaRepository repository, AcaoService acaoService) {
		this.repository = repository;
		this.acaoService = acaoService;
	}	
	
	@Override
	public void salvarRegistro(List<CompraVenda> compraVenda) {
		repository.saveAll(compraVenda);
		repository.flush();
		log.debug("Armazenando entidade CompraVenda");
	}

	@Override
	public List<CompraVenda> consultarCompraVendaPeloIdVenda(Integer id) {
		log.debug("Consultar registros CompraVenda pelo id Venda");
		return repository.findByVendaId(id);
	}

	@Override
	public List<CompraVenda> consultarCompraVendaPeloIdCompra(Integer id) {
		log.debug("Consultar registros CompraVenda pelo id Compra");
		return repository.findByCompraId(id);
	}

	@Override
	public void excluirCompraVendaPeloIdVenda(Integer id) {
		try {
			log.trace("ID {}", id);
			log.debug("Removendo registros de compra e venda pelo id {}", id);

			List<Acao> acaoList = this.repository.findByVenda(id);
			log.trace("Quantidade de ações habilitadas: {}", acaoList);
			this.acaoService.ativarAcoes(acaoList);

			List<CompraVenda> registros = this.repository.findByVendaId(id);
			log.trace("Total de registros {}", registros);

			log.info("Registros excluídos!");
			this.repository.deleteInBatch(registros);
		}catch (Exception e){
			log.warn(e.getMessage());
			throw e;
		}
	}

}
