package com.lafinance.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.AtivoDTO;
import com.lafinance.dashboard.model.Ativo;
import com.lafinance.dashboard.repository.AtivoRepository;
import com.lafinance.dashboard.service.AtivoService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;

@Slf4j
@Service
@Transactional
public class AtivoServiceImpl implements AtivoService{

	@Autowired
	private AtivoRepository ativoRepository;

	@Override
	public Ativo consultarNomeAtivo(String nome) {
		log.debug("Consultando entidade Ativo pelo nome");
		return ativoRepository.findByNome(nome);
	}

	@Override
	public Response salvarAtivo(Ativo ativo) {
		Response response = new Response();
		try {
			Ativo ativoCheck = ativoRepository.findByNome(ativo.getNome());

			if(ativoCheck != null){
				response.setTipo(TipoResponse.ERRO);
				response.setMensagem("Entidade Ativo já cadastrado");
				return response;
			}

			ativo.setId(null);
			ativo.setStatus("S");
			ativoRepository.save(ativo);

			response.setTipo(TipoResponse.SUCESSO);
			response.setMensagem("Entidade Ativo armazenado");
			return response;
		}catch(Exception e) {
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Erro ao armazenar entidade Ativo");
			return response;
		}
	}

	@Override
	public Response editarAtivo(Ativo ativo) {
		Response response = new Response();
		try {
			Ativo ativoUpdate = ativoRepository.findByNome(ativo.getNome());
			ativoUpdate.setNome(ativo.getNome());
			ativoUpdate.setStatus(ativo.getStatus());
			
			ativoRepository.save(ativoUpdate);

			response.setTipo(TipoResponse.SUCESSO);
			response.setMensagem("Entidade Ativo editado");
			return response;
		}catch(Exception e) {
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Erro ao editar registro!");
			return response;
		}
	}

	@Override
	public Response excluirAtivo(Integer id) {
		Response response = new Response();
		try {
			Ativo ativo = ativoRepository.getOne(id);
			ativoRepository.delete(ativo);

			response.setMensagem("Entidade Ativo excluido");
			response.setTipo(TipoResponse.SUCESSO);
			return response;
		}catch(Exception e) {
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
