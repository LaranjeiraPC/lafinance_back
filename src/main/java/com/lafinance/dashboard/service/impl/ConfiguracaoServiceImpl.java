package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.ConfiguracaoDTO;
import com.lafinance.dashboard.model.Configuracao;
import com.lafinance.dashboard.repository.ConfiguracaoRepository;
import com.lafinance.dashboard.service.ConfiguracaoService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;

@Slf4j
@Service
@Transactional
public class ConfiguracaoServiceImpl implements ConfiguracaoService{

	@Autowired
	private ConfiguracaoRepository configuracaoRepository;

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
		BigDecimal valorBrutoMeta;	
		Configuracao configuracao = new Configuracao();
		
		String valorBrutoTemp = (String) dados[0];
		if(!valorBrutoTemp.equals("")) {
			valorBrutoMeta = new BigDecimal((String) dados[0]);	
		}else {
			valorBrutoMeta = new BigDecimal("0.00");	
		}
		
		try {
			log.debug("Preparando entidade Configuracao");

			List<Configuracao> configList = configuracaoRepository.findAll();
			
			if(configList.isEmpty()) {
				configuracao.setValorBrutoMeta(valorBrutoMeta);
				configuracao.setAtivoUm((((String) dados[1]).equals(""))?"":(String) dados[2]);
				configuracao.setAtivoDois((((String) dados[2]).equals(""))?"":(String) dados[2]);
				configuracaoRepository.save(configuracao);
			}else {
				configList.get(0).setValorBrutoMeta(valorBrutoMeta);
				configList.get(0).setAtivoUm((((String) dados[1]).equals(""))?"":(String) dados[1]);
				configList.get(0).setAtivoDois((((String) dados[2]).equals(""))?"":(String) dados[2]);
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

}
