package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.CompraDTO;
import com.lafinance.dashboard.dto.InvestimentoDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.Investimento;
import com.lafinance.dashboard.model.Usuario;
import com.lafinance.dashboard.repository.InvestimentoRepository;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.service.CompraService;
import com.lafinance.dashboard.service.InvestimentoService;
import com.lafinance.dashboard.service.LogService;
import com.lafinance.dashboard.service.UsuarioService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;
import com.lafinance.dashboard.util.Util;

@Service
@Transactional
public class InvestimentoServiceImpl implements InvestimentoService {
	
	private final Logger log = LoggerFactory.getLogger(InvestimentoServiceImpl.class);

	private Integer quantidadeTotal = 0;
	private BigDecimal total = BigDecimal.ZERO;

	private final InvestimentoRepository investimentoRepository;
	private final AcaoService acaoService;
	private final UsuarioService usuarioService;
	private final CompraService compraService;
	private final LogService logService;

	public InvestimentoServiceImpl(InvestimentoRepository investimentoRepository, AcaoService acaoService,
			CompraService compraService, UsuarioService usuarioService, LogService logService) {
		this.investimentoRepository = investimentoRepository;
		this.acaoService = acaoService;
		this.compraService = compraService;
		this.usuarioService = usuarioService;
		this.logService = logService;
	}

	@Override
	public Response salvarInvestimento(Object[] dados) {

		Response response = new Response();
		
		quantidadeTotal = 0;
		total = BigDecimal.ZERO;

		try {
			Acao acaoTemp = acaoService.consultarAcaoNome((String) dados[0]);
	
			Investimento investimentoTemp = investimentoRepository.consultarInvestimento(acaoTemp.getUsuario(), acaoTemp);
	
			if (investimentoTemp != null) {
				montarInvestimento(dados, investimentoTemp, acaoTemp);
			} else {
				Investimento investimento = new Investimento();
				montarInvestimento(dados, investimento, acaoTemp);
				investimentoTemp = investimento;
			}
		
			Investimento investimento = investimentoRepository.saveAndFlush(investimentoTemp);
			
			compraService.salvarCompra(dados, investimento);
			
			response.setTipo(TipoResponse.SUCESSO);
			response.setMensagem("Registro salvo com sucesso!");
		} catch (Exception e) {
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Registro já cadastrado!");
		}

		return response;
	}

	private void montarInvestimento(Object[] dados, Investimento investimento, Acao acaoTemp) {
		investimento.setAcao(acaoTemp);
		investimento.setUsuario(acaoTemp.getUsuario());
		investimento.setUltimaDataAtualizacao(LocalDate.now());
		investimento.setUltimaDataCompra(Util.montarData((String) dados[4]));
		
		atualizarQuantidadeTotalCompra(investimento, dados);
		
	}
	
	private Investimento atualizarQuantidadeTotalCompra(Investimento investimento, Object[] dados) {
		Investimento investimentoTemp = investimento;
		
		if(investimentoTemp.getId() != null) {
			List<CompraDTO> compraTemp = compraService.consultarInvestimento(investimentoTemp);
			
			if(!compraTemp.isEmpty()) {
				compraTemp.forEach(det -> quantidadeTotal = quantidadeTotal + det.getQuantidade());
				quantidadeTotal = quantidadeTotal + Integer.parseInt((String) dados[1]);

				compraTemp.forEach(det -> total = total.add(det.getTotalCompra()));
				total = total.add(Util.converterParaBigDecimalSemReplace((String) dados[3]));	
				
				investimentoTemp.setQuantidade(quantidadeTotal);
				investimentoTemp.setTotalCompra(total);
			}else {
				investimentoTemp.setQuantidade(Integer.parseInt((String) dados[1]));
				investimentoTemp.setTotalCompra(Util.converterParaBigDecimalSemReplace((String) dados[3]));
			}					
		}else {
			investimentoTemp.setQuantidade(Integer.parseInt((String) dados[1]));
			investimentoTemp.setTotalCompra(Util.converterParaBigDecimalSemReplace((String) dados[3]));
		}

		investimentoTemp.setUltimoValorCompra(Util.converterParaBigDecimalSemReplace((String) dados[2]));
		
		return investimentoTemp;
	}

	@Override
	public List<InvestimentoDTO> consultarInvestimento(String usuario) {
		log.debug("Consultar investimentos do usuario: {}", usuario);
		
		List<InvestimentoDTO> compraListDTO = new ArrayList<>();
		
		try {
			Usuario user = usuarioService.consultarNome(usuario);
			investimentoRepository.consultarListaInvestimento(usuario).forEach(i -> compraListDTO.add(new InvestimentoDTO(i)));			
			logService.salvarLog(user, "Consultar investimentos do usuario: {}" + usuario);
		}catch (Exception e) {
			log.warn(e.getMessage());
		}
		return compraListDTO;
	}

}
