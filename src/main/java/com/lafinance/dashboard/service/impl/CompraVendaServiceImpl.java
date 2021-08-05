package com.lafinance.dashboard.service.impl;

import java.util.List;

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
	
	public CompraVendaServiceImpl(CompraVendaRepository repository) {
		this.repository = repository;
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

}
