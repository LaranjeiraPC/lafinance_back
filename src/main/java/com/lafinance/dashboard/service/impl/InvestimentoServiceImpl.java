package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.model.Compra;
import com.lafinance.dashboard.model.Investimento;
import com.lafinance.dashboard.model.Usuario;
import com.lafinance.dashboard.repository.AcaoRepository;
import com.lafinance.dashboard.repository.CompraRepository;
import com.lafinance.dashboard.repository.InvestimentoRepository;
import com.lafinance.dashboard.repository.UsuarioRepository;
import com.lafinance.dashboard.service.InvestimentoService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;
import com.lafinance.dashboard.util.Util;

@Service
@Transactional
public class InvestimentoServiceImpl implements InvestimentoService {

	private Integer quantidadeTotal = 0;
	private BigDecimal total = BigDecimal.ZERO;

	private final InvestimentoRepository investimentoRepository;
	private final AcaoRepository repositoryAcao;
	private final UsuarioRepository usuarioRepository;
	private final CompraRepository compraRepository;

	public InvestimentoServiceImpl(InvestimentoRepository investimentoRepository, AcaoRepository repositoryAcao,
			CompraRepository compraRepository, UsuarioRepository usuarioRepository) {
		this.investimentoRepository = investimentoRepository;
		this.repositoryAcao = repositoryAcao;
		this.compraRepository = compraRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Response salvarCompra(Object[] dados) {

		Response response = new Response();

		Acao acaoTemp = repositoryAcao.consultarAcaoPeloId((Integer) dados[0]);

		Investimento investimentoTemp = investimentoRepository.consultarInvestimento(acaoTemp.getUsuario(), acaoTemp);

		if (investimentoTemp != null) {
			montarInvestimento(dados, investimentoTemp, acaoTemp, false);
		} else {
			Investimento investimento = new Investimento();
			montarInvestimento(dados, investimento, acaoTemp, true);
			investimentoTemp = investimento;
		}

		try {
			Investimento investimento = investimentoRepository.saveAndFlush(investimentoTemp);
			
			compraRepository.save(novaCompra(dados, investimento));
			
			response.setTipo(TipoResponse.SUCESSO);
			response.setMensagem("Registro salvo com sucesso!");
		} catch (Exception e) {
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Registro já cadastrado!");
		}

		return response;
	}

	private void montarInvestimento(Object[] dados, Investimento investimento, Acao acaoTemp, boolean indicador) {
		investimento.setAcao(acaoTemp);
		investimento.setUsuario(acaoTemp.getUsuario());
		investimento.setUltimaDataAtualizacao(LocalDate.now());
		investimento.setUltimaDataCompra(Util.montarData((String) dados[4]));
		
		atualizarQuantidadeTotalCompra(investimento, dados, indicador);
		
	}
	
	private Investimento atualizarQuantidadeTotalCompra(Investimento investimento, Object[] dados, boolean indicador) {
		Investimento investimentoTemp = investimento;
		
		if(indicador && investimentoTemp.getId() != null) {
			List<Compra> compraTemp = compraRepository.consultarInvestimento(investimentoTemp);
			
			compraTemp.forEach(det -> quantidadeTotal = quantidadeTotal + det.getQuantidade());

			compraTemp.forEach(det -> total = total.add(det.getTotalCompra()));
			
			investimentoTemp.setQuantidade(quantidadeTotal);
			investimentoTemp.setTotalCompra(total);
		}else {
			investimentoTemp.setQuantidade(Integer.parseInt((String) dados[1]));
			investimentoTemp.setTotalCompra(Util.converterParaBigDecimal((String) dados[3]));
		}

		investimentoTemp.setUltimoValorCompra(Util.converterParaBigDecimal((String) dados[2]));
		
		return investimentoTemp;
	}

	private Compra novaCompra(Object[] dados, Investimento investimento) {
		Compra compra = new Compra();
		compra.setQuantidade(Integer.parseInt((String) dados[1]));
		compra.setTotalCompra(Util.converterParaBigDecimal((String) dados[3]));
		compra.setValorCompra(Util.converterParaBigDecimal((String) dados[2]));
		compra.setDataCompra(Util.montarData((String) dados[4]));
		compra.setIndicadorAtivo("S");
		compra.setInvestimento(investimento);
		return compra;
	}

	@Override
	public List<Investimento> consultarInvestimento(String usuario) {
		List<Investimento> compraList;

		Usuario user = usuarioRepository.consultarNome(usuario);

		compraList = investimentoRepository.consultarListaInvestimento(user);

		return compraList;
	}

}
