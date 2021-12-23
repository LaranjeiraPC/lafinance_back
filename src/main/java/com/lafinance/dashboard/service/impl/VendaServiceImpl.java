package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.service.AtivoService;
import com.lafinance.dashboard.service.CompraVendaService;
import com.lafinance.dashboard.service.VendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.VendaDTO;
import com.lafinance.dashboard.model.Venda;
import com.lafinance.dashboard.repository.VendaRepository;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;
import com.lafinance.dashboard.util.Util;

@Service
@Transactional
public class VendaServiceImpl implements VendaService {

	private final Logger log = LoggerFactory.getLogger(VendaServiceImpl.class);

	private final VendaRepository repository;
	private final AcaoService acaoService;
	private final AtivoService ativoService;
	private final CompraVendaService compraVendaService;
	
	private Venda venda = new Venda();

	public VendaServiceImpl(VendaRepository repository, AtivoService ativoService, AcaoService acaoService,
			CompraVendaService compraVenda) {
		this.repository = repository;
		this.ativoService = ativoService;
		this.acaoService = acaoService;
		this.compraVendaService = compraVenda;
	}

	@Override
	public List<VendaDTO> consultarVendasPeloAnoMesSelecionado(String ano, String mes) {
        try{
            log.debug("Consultar vendas pelo ano e mês");
            List<VendaDTO> dtoList = new ArrayList<>();
            repository.findByDataVenda(Integer.parseInt(ano),
                    Integer.parseInt(Util.converterNomeMesParaInteiro(mes))).forEach(a -> dtoList.add(new VendaDTO(a)));
            return dtoList;
        }catch (Exception e){
            log.warn("Erro ao consultar registros", e);
            throw e;
        }
	}

	@Override
	public List<VendaDTO> consultarVendasPeloAnoMesSelecionadoInteiro(String ano, String mes) {
		log.debug("Consultar vendas pelo ano e mês");
		List<VendaDTO> dtoList = new ArrayList<>();
		repository.findByDataVenda(Integer.parseInt(ano),
				Integer.parseInt(Util.ajustarNumeroMes(mes))).forEach(a -> dtoList.add(new VendaDTO(a)));
		return dtoList;
	}

	@Override
	public BigDecimal calcularLucroBruto(List<Integer> idVenda) {
        try{
            log.info("Quantidade de ids venda: {}", idVenda);
            log.debug("Consultando registros calculo lucro bruto");
            return this.repository.calcularLucroBruto(idVenda);
        }catch(Exception e){
            log.warn("Erro ao calcular registro", e);
            throw e;
        }
	}

	@Override
	public Response cadastrar(Venda venda) {
		try{
            log.info("Cadastrando venda"
            );
			Response response = new Response();
			List<Venda> vendas = new ArrayList<>();
			if(!venda.getAtivo().getNome().isEmpty()){
                venda.setMesCriacao(LocalDate.now());
                venda.setMesAtualizacao(LocalDate.now());
				venda = repository.saveAndFlush(venda);
				vendas.add(venda);
			}
            log.debug("Registro salvo na base {}", venda.getId());
			response.setMensagem("Registro salvo com sucesso");
			response.setTipo(TipoResponse.SUCESSO);
			response.setDtos(vendas);
			return response;
		}catch (Exception e){
			log.warn("Erro ao salvar registro", e);
			throw e;
		}
	}

	@Override
	public Response excluir(Integer id) {
		Response response = new Response();
		try{
			this.compraVendaService.excluirCompraVendaPeloIdVenda(id);
			this.repository.delete(repository.getOne(id));
			response.setMensagem("Registro excluido com sucesso");
			response.setTipo(TipoResponse.SUCESSO);
            return response;
		}catch (Exception e){
            log.warn("Erro ao excluir registro", e);
            throw e;
		}

	}

    @Override
    public Response editar(Venda venda) {
        Response response = new Response();
        try{
            log.info("Atualizando registro id: {}", venda.getId());
            venda.setMesAtualizacao(LocalDate.now());

            this.repository.save(venda);
            log.debug("Registro atualizado: {}", venda.getId());

            response.setMensagem("Registro salvo com sucesso");
            response.setTipo(TipoResponse.SUCESSO);
            return response;
        }catch (Exception e){
            log.warn("Erro ao editar registro", e);
            throw e;
        }
    }
}
