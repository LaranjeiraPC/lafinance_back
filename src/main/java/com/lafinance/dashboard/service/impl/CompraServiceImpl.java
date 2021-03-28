package com.lafinance.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.CompraDTO;
import com.lafinance.dashboard.model.Compra;
import com.lafinance.dashboard.model.Investimento;
import com.lafinance.dashboard.repository.CompraRepository;
import com.lafinance.dashboard.service.CompraService;
import com.lafinance.dashboard.service.LogService;
import com.lafinance.dashboard.util.Util;

@Service
@Transactional
public class CompraServiceImpl implements CompraService {

	private final Logger log = LoggerFactory.getLogger(CompraServiceImpl.class);
	
	private final CompraRepository compraRepository;
	private final LogService logService;

	public CompraServiceImpl(CompraRepository compraRepository, LogService logService) {
		this.compraRepository = compraRepository;
		this.logService = logService;
	}
	
	@Override
	public List<CompraDTO> consultarCompras(Integer id) {
		log.debug("Consultar lista de compra(s) do investimento: {}", id);
				
		List<CompraDTO> compraListDTO = new ArrayList<>();
		
		List<Compra> compraList = compraRepository.consultarInvestimentoPorId(id);
		
		compraList.forEach(m -> compraListDTO.add(new CompraDTO(m)));
		
		logService.salvarLog(compraList.get(0).getInvestimento().getUsuario(), "Consultar lista de compra(s) do investimento: {}" + id);
		
		return compraListDTO;
	}
	
	@Override
	public void salvarCompra(Object[] dados, Investimento investimento) {
		
		log.debug("Salvando compra - Investimento: {}", investimento.getId());
		
		Compra compra = new Compra();
		compra.setQuantidade(Integer.parseInt((String) dados[1]));
		compra.setTotalCompra(Util.converterParaBigDecimalSemReplace((String) dados[3]));
		compra.setValorCompra(Util.converterParaBigDecimalSemReplace((String) dados[2]));
		compra.setDataCompra(Util.montarData((String) dados[4]));
		compra.setIndicadorAtivo("S");
		compra.setInvestimento(investimento);
		
		logService.salvarLog(investimento.getUsuario(), "Salvando compra - Investimento: {}" + investimento.getId());
		
		compraRepository.save(compra);
	}

	@Override
	public List<CompraDTO> consultarInvestimento(Investimento investimento) {
		
		log.debug("Consultar lista de investimento(s): {}", investimento.getId());
		
		List<CompraDTO> dtoList = new ArrayList<>();
		List<Compra> compraList = compraRepository.consultarInvestimento(investimento);
		
		compraList.forEach(c -> dtoList.add(new CompraDTO(c)));
		
		logService.salvarLog(investimento.getUsuario(), "Consultar lista de investimento(s): {}" + investimento.getId());
		
		return dtoList;
	}

	@Override
	public Compra consultarCompra(Integer id) {
		log.debug("Consultar Compra: {}", id);
		
		Compra compra = compraRepository.consultarCompra(id);
		
		logService.salvarLog(compra.getInvestimento().getUsuario(), "Consultar Compra: {}" + id);
		
		return compra;
	}

	@Override
	public void atualizarCompra(Compra compra) {
		log.debug("Atualizar Compra: {}", compra.getId());
		
		logService.salvarLog(compra.getInvestimento().getUsuario(), "Atualizar Compra: {}" + compra.getId());
		
		compraRepository.save(compra);
	}


}
