package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.AcaoDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.repository.AcaoRepository;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.service.AtivoService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;

@Service
@Transactional
public class AcaoServiceImpl implements AcaoService {

    private final Logger log = LoggerFactory.getLogger(AcaoServiceImpl.class);

    private final AcaoRepository repository;
    private final AtivoService ativoService;

    public AcaoServiceImpl(AcaoRepository repository, AtivoService ativoService) {
        this.repository = repository;
        this.ativoService = ativoService;
    }

    @Override
    public List<AcaoDTO> consultarAcoesAtivos() {
        List<AcaoDTO> dtoList = new ArrayList<>();
        List<Acao> acao = repository.findByAllAndStatus();
        acao.forEach(a -> dtoList.add(new AcaoDTO(a)));
        return dtoList;
    }

    @Override
    public Response cadastrarAcao(Acao acao) {
        Response response = new Response();
        if(!acao.getAtivo().getNome().isEmpty()){
            repository.save(acao);
            response.setMensagem("Registro salvo com sucesso");
            response.setTipo(TipoResponse.SUCESSO);
        }else{
            response.setMensagem("Erro ao salvar registro");
            response.setTipo(TipoResponse.ERRO);
        }
        return response;
    }

    @Override
    public Response editarAcao(AcaoDTO acaoDTO) {
        Response response = new Response();
        Acao acao = repository.getOne(acaoDTO.getId());
        if(acao != null){

            acao.setQuantidade(acaoDTO.getQuantidade());
            acao.setDataCompra(acaoDTO.getDataCompra());
            acao.setValorAtivoPago(acaoDTO.getValorAtivoPago());
            acao.setValorBrutoPago(acaoDTO.getValorBrutoPago());
            acao.setStatus(acaoDTO.getStatus());

            repository.save(acao);
            response.setMensagem("Registro salvo com sucesso");
            response.setTipo(TipoResponse.SUCESSO);
        }else{
            response.setMensagem("Erro ao salvar registro");
            response.setTipo(TipoResponse.ERRO);
        }
        return response;
    }

    @Override
    public void inativarAcoes(List<Acao> acoes) {
        acoes.forEach(a -> {
            Acao acao = this.repository.getOne(a.getId());
            acao.setStatus("N");
        });
        this.repository.saveAll(acoes);
    }

    @Override
    public void ativarAcoes(List<Acao> acoes) {
        acoes.forEach(a -> {
            Acao acao = this.repository.getOne(a.getId());
            acao.setStatus("S");
        });
        this.repository.saveAll(acoes);
    }

    @Override
    public List<Acao> consultarAcoesId(List<Integer> id) {
        return this.repository.consultarAcoesId(id);
    }

    @Override
    public BigDecimal calcularLucroBruto(List<Integer> idsCompra) {
        return this.repository.calcularValorBrutoPago(idsCompra);
    }

    @Override
    public Response excluirAcao(Integer id) {
        Response response = new Response();
        try{
            repository.delete(repository.getOne(id));
            response.setMensagem("Registro excluido com sucesso");
            response.setTipo(TipoResponse.SUCESSO);
        }catch (Exception e){
            response.setMensagem("Erro ao excluir registro");
            response.setTipo(TipoResponse.ERRO);
        }
        return response;
    }

}
