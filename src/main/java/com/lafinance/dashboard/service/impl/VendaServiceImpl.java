package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.VendaDTO;
import com.lafinance.dashboard.model.Compra;
import com.lafinance.dashboard.model.Investimento;
import com.lafinance.dashboard.model.Usuario;
import com.lafinance.dashboard.model.Venda;
import com.lafinance.dashboard.repository.InvestimentoRepository;
import com.lafinance.dashboard.repository.VendaRepository;
import com.lafinance.dashboard.service.CompraService;
import com.lafinance.dashboard.service.LogService;
import com.lafinance.dashboard.service.UsuarioService;
import com.lafinance.dashboard.service.VendaService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;
import com.lafinance.dashboard.util.Util;

@Service
@Transactional
public class VendaServiceImpl implements VendaService {

	private final Logger log = LoggerFactory.getLogger(VendaServiceImpl.class);

	private final VendaRepository vendaRepository;
	private final CompraService compraService;
	private final UsuarioService usuarioService;
	private final InvestimentoRepository investimentoRepository;
	private final LogService logService;

	public VendaServiceImpl(VendaRepository vendaRepository, InvestimentoRepository investimentoRepository,
			CompraService compraService, LogService logService, UsuarioService usuarioService) {
		this.vendaRepository = vendaRepository;
		this.compraService = compraService;
		this.investimentoRepository = investimentoRepository;
		this.logService = logService;
		this.usuarioService = usuarioService;
	}

	@Override
	public List<VendaDTO> consultarVendas(String usuario) {
		List<VendaDTO> dtoList = new ArrayList<>();
		vendaRepository.consultarVendasPorUsuario(usuario).forEach(v -> dtoList
				.add(new VendaDTO(v.getAcao(), v.getQuantidade(), v.getValorVenda(), v.getIdInvestimento())));
		return dtoList;
	}

	@Override
	public Response salvarVenda(Object[] objeto) {
		Response response = new Response();

		try {
			Compra compra = compraService.consultarCompra((Integer) objeto[0]);
			
			if(compra != null && !compra.getIndicadorAtivo().equals("N")) {
				Investimento investimentoTemp = compra.getInvestimento();

				compra.setIndicadorAtivo("N");
				compraService.atualizarCompra(compra);

				Integer quantidadeVenda = (Integer) objeto[1];

				if (investimentoTemp.getQuantidade() >= quantidadeVenda) {
					Integer quantidade = investimentoTemp.getQuantidade() - quantidadeVenda;
					investimentoTemp.setQuantidade(quantidade);
				}

				investimentoRepository.save(investimentoTemp);

				montarVendar(objeto, investimentoTemp, compra);

				response.setTipo(TipoResponse.SUCESSO);
				response.setMensagem("Registro salvo com sucesso!");
			}else {
				response.setTipo(TipoResponse.ERRO);
				response.setMensagem("Registro já vendido!");
			}
			
		} catch (Exception e) {
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Registro já cadastrado!");
		}
		return response;
	}

	private void montarVendar(Object[] dados, Investimento investimento, Compra compra) {
		Venda venda = new Venda();

		BigDecimal vlrVendaTemp = Util.converterParaBigDecimalSemReplace((String) dados[2]);

		BigDecimal totalCompra = compra.getTotalCompra();

		BigDecimal quantidade = new BigDecimal((Integer) dados[1]);

		BigDecimal totalVenda = vlrVendaTemp.multiply(quantidade);

		BigDecimal lucroTotalVenda = totalVenda.subtract(totalCompra);

		venda.setQuantidade((Integer) dados[1]);
		venda.setValorVenda(vlrVendaTemp);
		venda.setTotalVenda(totalVenda);
		venda.setDataVenda(Util.montarData((String) dados[3]));
		venda.setLucroBruto(lucroTotalVenda);
		venda.setInvestimento(investimento);
		venda.setCompra(compra);

		vendaRepository.save(venda);
	}

	@Override
	public Response editarVenda(Object[] acao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response excluirVenda(Object[] acao) {
		Response response = new Response();

		try {
			vendaRepository.excluirRegistro((Integer) acao[1]);
			response.setTipo(TipoResponse.SUCESSO);
			response.setMensagem("Registro excluído com sucesso!");
		} catch (Exception e) {
			response.setTipo(TipoResponse.ERRO);
			response.setMensagem("Erro ao excluir o registro!");
		}

		return response;
	}

	@Override
	public List<VendaDTO> consultarVenda(Integer id) {
		log.debug("Consultar lista de venda(s) do investimento: {}", id);

		List<VendaDTO> vendaListDTO = new ArrayList<>();

		vendaRepository.consultarVendasInvestimento(id).forEach(m -> {
			vendaListDTO.add(new VendaDTO(m.getId(), m.getAcao(), m.getQuantidade(), m.getTotalVenda(),
					m.getValorVenda(), m.getLucroBruto(), m.getDataVenda(), m.getCompra(), m.getUsuario()));
		});

		Usuario user = usuarioService.consultarNome(vendaListDTO.get(0).getUsuario());

		logService.salvarLog(user, "Consultar lista de venda(s) do investimento: {}" + id);

		return vendaListDTO;
	}

}
