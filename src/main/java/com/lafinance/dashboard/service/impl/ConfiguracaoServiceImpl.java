package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.ConfiguracaoDTO;
import com.lafinance.dashboard.model.Configuracao;
import com.lafinance.dashboard.repository.ConfiguracaoRepository;
import com.lafinance.dashboard.service.ConfiguracaoService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;

@Service
@Transactional
public class ConfiguracaoServiceImpl implements ConfiguracaoService{
	
	private final Logger log = LoggerFactory.getLogger(ConfiguracaoServiceImpl.class);

	private final ConfiguracaoRepository configuracaoRepository;
	
	public ConfiguracaoServiceImpl(ConfiguracaoRepository configuracaoRepository) {
	this.configuracaoRepository = configuracaoRepository;	
	}
	
	@Override
	public ConfiguracaoDTO consultarDadosConfiguracao() {
		log.debug("Consultando dados configuracao");
		List<Configuracao> config = configuracaoRepository.findAll();
		if(!config.isEmpty()) {
			return new ConfiguracaoDTO(config.get(0));
		}else {
			return null;			
		}
	}

	@Override
	public Response salvarConfiguracao(Object[] dados) {
		Response response = new Response();
		BigDecimal valorBrutoMeta = new BigDecimal((String) dados[0]);
		
		try {
			log.debug("Preparando entidade Configuracao");

			List<Configuracao> configList = configuracaoRepository.findAll();
			
			if(configList.isEmpty()) {
				Configuracao configuracao = new Configuracao();
				configuracao.setValorBrutoMeta(valorBrutoMeta);
				configuracaoRepository.save(configuracao);
			}else {
				configList.get(0).setValorBrutoMeta(valorBrutoMeta);
				configuracaoRepository.saveAll(configList);
			}

			log.debug("Entidade Configuracao armazenado");
			response.setTipo(TipoResponse.SUCESSO);
			return response;
		} catch (Exception e) {
			log.debug("Erro ao armazenar entidade Configuracao");
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Erro ao salvar registro!");
			return response;
		}
	}

	@Override
	public BigDecimal consultarValorBrutoMeta() {
		log.debug("Consultando valor bruto meta");
		List<Configuracao> config = configuracaoRepository.findAll();
		if(!config.isEmpty()) {
			return config.get(0).getValorBrutoMeta();
		}else {
			return new BigDecimal("0.00");			
		}
	};

}
