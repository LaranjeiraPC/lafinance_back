package com.lafinance.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.AtivoDTO;
import com.lafinance.dashboard.model.Ativo;
import com.lafinance.dashboard.repository.AtivoRepository;
import com.lafinance.dashboard.service.AtivoService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;

@Service
@Transactional
public class AtivoServiceImpl implements AtivoService{
	
	private final Logger log = LoggerFactory.getLogger(AtivoServiceImpl.class);

	private final AtivoRepository ativoRepository;
	
	public AtivoServiceImpl(AtivoRepository repository) {
		this.ativoRepository = repository;
	}
	
	@Override
	public Ativo consultarNomeAtivo(String nome) {
		log.debug("Consultando entidade Ativo pelo nome");
		return ativoRepository.findByNome(nome);
	}

	@Override
	public Response salvarAtivo(Object[] dados) {
		Response response = new Response();
		try {
			log.debug("Preparando entidade Ativo");
			
			String tempNome = (String) dados[0];
			tempNome = tempNome.toUpperCase();
			
			Ativo ativoTemp = ativoRepository.findByNome(tempNome);
			
			if(ativoTemp != null) {
				log.debug("Entidade Ativo já cadastrado");
				response.setTipo(TipoResponse.ERRO);
			}else {
				Ativo ativo = new Ativo();
				ativo.setNome(tempNome);
				ativo.setStatus("S");
				
				ativoRepository.save(ativo);
				
				log.debug("Entidade Ativo armazenado");
				response.setTipo(TipoResponse.SUCESSO);
			}
			
			return response;
		}catch(Exception e) {
			log.debug("Erro ao armazenar entidade Ativo");
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Erro ao salvar registro!");
			return response;
		}
	}

	@Override
	public Response editarAtivo(Object[] dados) {
		Response response = new Response();
		try {
			log.debug("Preparando edição entidade Ativo");
			Ativo ativo = ativoRepository.findByNome((String) dados[0]);
			ativo.setNome((String) dados[1]);
			ativo.setStatus((String) dados[2]);
			
			ativoRepository.save(ativo);
			
			log.debug("Entidade Ativo editado");
			response.setTipo(TipoResponse.SUCESSO);
			return response;
		}catch(Exception e) {
			log.debug("Erro ao editar entidade Ativo");
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Erro ao editar registro!");
			return response;
		}
	}

	@Override
	public Response excluirAtivo(String id) {
		Response response = new Response();
		try {
			log.debug("Preparando exclusão entidade Ativo");
			Ativo ativo = ativoRepository.getOne(Integer.parseInt(id));
			ativoRepository.delete(ativo);
			
			log.debug("Entidade Ativo excluido");
			response.setTipo(TipoResponse.SUCESSO);
			return response;
		}catch(Exception e) {
			log.debug("Erro ao excluir entidade Ativo");
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Erro ao excluir registro!");
			return response;
		}
	}

	@Override
	public List<AtivoDTO> consultarAtivo() {
		log.debug("Consultando registros entidade Ativo");
		List<Ativo> ativos = ativoRepository.findAll();
		List<AtivoDTO> dto = new ArrayList<>();
		ativos.forEach(a -> dto.add(new AtivoDTO(a)));
		return dto;
	}

}
