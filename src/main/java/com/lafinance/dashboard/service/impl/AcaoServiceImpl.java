package com.lafinance.dashboard.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.Usuario;
import com.lafinance.dashboard.repository.AcaoRepository;
import com.lafinance.dashboard.repository.UsuarioRepository;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;

@Service
@Transactional
public class AcaoServiceImpl implements AcaoService {

	private final AcaoRepository repository;
	private final UsuarioRepository usuarioRepository;
	
	public AcaoServiceImpl(AcaoRepository repository, UsuarioRepository usuarioRepository) {
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public List<Acao> carregarAcoes(String usuario) {
		Usuario user = usuarioRepository.consultarNome(usuario);
		return repository.consultarAcoes(user);
	}

	public Response salvarAcao(Object[] dados) {
		
		Response response = new Response();

		Acao acao = new Acao();
		acao.setAtivoAcao((String) dados[0]);
		acao.setNomeAcao((String) dados[1]);
		
		Usuario user = usuarioRepository.consultarNome((String) dados[2]);
		
		acao.setUsuario(user);

		Acao acaoTemp = repository.consultarAcao(user, acao.getNomeAcao());
		
		if (acaoTemp == null) {
			repository.saveAndFlush(acao);
			
			response.setTipo(TipoResponse.SUCESSO);
			response.setMensagem("Registro salvo com sucesso!");
			
		} else {
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Registro já cadastrado!");
		}
		
		return response;
	}

	@Override
	public Response editarAcao(Object[] dados) {
		Response response = new Response();
		try {
			Acao acaoTemp = repository.getOne((Integer) dados[0]);
			if(acaoTemp != null) {
				acaoTemp.setNomeAcao((String) dados[1]);
				acaoTemp.setAtivoAcao((String) dados[2]);
				response.setTipo(TipoResponse.SUCESSO);
				response.setMensagem("Registro editado com sucesso!");
				return response;
			}else {
				response.setTipo(TipoResponse.ERRO);
				response.setMensagem("Registro não encontrado");
				return response;
			}
			
		}catch(Exception e) {
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Erro ao editar registro");
			return response;
		}
	}

	@Override
	public Response excluirAcao(Object[] dados) {
		Response response = new Response();
		try {
			Acao acaoTemp = repository.getOne((Integer) dados[0]);
			if(acaoTemp != null) {
				repository.delete(acaoTemp);
				response.setTipo(TipoResponse.SUCESSO);
				response.setMensagem("Registro excluído com sucesso!");
				return response;
			}else {
				response.setTipo(TipoResponse.ERRO);
				response.setMensagem("Registro não encontrado!");
				return response;
			}
			
		}catch(Exception e) {
			response.setTipo(TipoResponse.SUCESSO);
			response.setMensagem("Erro ao excluir registro!");
			return response;
		}
	}

}
