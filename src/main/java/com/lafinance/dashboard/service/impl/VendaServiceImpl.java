package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.service.AtivoService;
import com.lafinance.dashboard.service.CompraVendaService;
import com.lafinance.dashboard.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.domain.dto.VendaDTO;
import com.lafinance.dashboard.domain.model.Venda;
import com.lafinance.dashboard.repository.VendaRepository;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;
import com.lafinance.dashboard.util.Util;

@Service
@Transactional
public class VendaServiceImpl implements VendaService {

	@Autowired
	private VendaRepository repository;

	@Autowired
	private AcaoService acaoService;

	@Autowired
	private AtivoService ativoService;

	@Autowired
	private CompraVendaService compraVendaService;

	@Override
	public List<VendaDTO> consultarVendasPeloAnoMesSelecionado(String ano, String mes) {
        try{
            List<VendaDTO> dtoList = new ArrayList<>();
            repository.findByDataVenda(Integer.parseInt(ano),
                    Integer.parseInt(Util.converterNomeMesParaInteiro(mes))).forEach(a -> dtoList.add(new VendaDTO(a)));
            return dtoList;
        }catch (Exception e){
            throw e;
        }
	}

	@Override
	public List<VendaDTO> consultarVendasPeloAnoMesSelecionadoInteiro(String ano, String mes) {
		List<VendaDTO> dtoList = new ArrayList<>();
		repository.findByDataVenda(Integer.parseInt(ano),
				Integer.parseInt(Util.ajustarNumeroMes(mes))).forEach(a -> dtoList.add(new VendaDTO(a)));
		return dtoList;
	}

	@Override
	public BigDecimal calcularLucroBruto(List<Integer> idVenda) {
        try{
            return this.repository.calcularLucroBruto(idVenda);
        }catch(Exception e){
            throw e;
        }
	}

	@Override
	public Response cadastrar(Venda venda) {
		try{
            Response response = new Response();
			List<Venda> vendas = new ArrayList<>();
			if(!venda.getAtivo().getNome().isEmpty()){
                venda.setMesCriacao(LocalDate.now());
                venda.setMesAtualizacao(LocalDate.now());
				venda = repository.saveAndFlush(venda);
				vendas.add(venda);
			}

			response.setMensagem("Registro salvo com sucesso");
			response.setTipo(TipoResponse.SUCESSO);
			response.setDtos(vendas);
			return response;
		}catch (Exception e){
			throw e;
		}
	}

	@Override
	public Response excluir(Integer id) throws Exception {
		Response response = new Response();
		try{
			this.compraVendaService.excluirCompraVendaPeloIdVenda(id);
			this.repository.delete(repository.getOne(id));
			response.setMensagem("Registro excluido com sucesso");
			response.setTipo(TipoResponse.SUCESSO);
            return response;
		}catch (Exception e){
            throw e;
		}
	}

    @Override
    public Response editar(Venda venda) {
        Response response = new Response();
        try{
            venda.setMesAtualizacao(LocalDate.now());

            this.repository.save(venda);
            response.setMensagem("Registro salvo com sucesso");
            response.setTipo(TipoResponse.SUCESSO);
            return response;
        }catch (Exception e){
            throw e;
        }
    }
}
