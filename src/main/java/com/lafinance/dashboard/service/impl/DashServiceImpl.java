package com.lafinance.dashboard.service.impl;

import java.time.LocalDate;

import com.lafinance.dashboard.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.domain.dto.DashDTO;

import javax.inject.Inject;
import javax.inject.Singleton;

@Service
@Singleton
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DashServiceImpl implements DashService {

	@Autowired
	private AcaoService acaoService;

	@Autowired
	private CompraVendaService compraVendaService;

	@Override
	public DashDTO consultarDadosDahsboard() {
		DashDTO dto = new DashDTO();

		dto.setValorBrutoTotal(this.compraVendaService.calcularLucroBrutoAno(LocalDate.now().getYear()));
		dto.setValorInvestimentoTotal(this.acaoService.consultarInvestimentoTotal());
		dto.setQuantidadeTotal(this.acaoService.consultaQuantidadeCota());

		return dto;
	}

}
