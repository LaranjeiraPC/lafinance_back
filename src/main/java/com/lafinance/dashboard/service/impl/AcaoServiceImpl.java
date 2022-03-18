package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lafinance.dashboard.service.AlphaVantageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.AcaoDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.repository.AcaoRepository;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;

@Service
@Transactional
public class AcaoServiceImpl implements AcaoService {

    @Autowired
    private AcaoRepository repository;

    @Autowired
    private AlphaVantageService alphaVantageService;

    @Override
    public List<AcaoDTO> consultarAcoesAtivosOutrosMeses(List<Acao> ids) {
        try{
            List<AcaoDTO> dtoList = new ArrayList<>();
            var idsFormatado = ids.stream().map(acao -> acao.getId()).collect(Collectors.toList());
            repository.findByIdNotInAndStatus(idsFormatado, "S").forEach(a -> dtoList.add(new AcaoDTO(a)));
            return dtoList;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Response cadastrarAcao(Acao acao) throws Exception {
        try{
            if(acao.getAtivo().getNome().isEmpty()) {
                throw new Exception();
            }
            repository.save(acao);

            atualizarPrecoAtual();

            return new Response("Registro salvo com sucesso",TipoResponse.SUCESSO, null);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Response editarAcao(AcaoDTO acaoDTO) throws Exception {
        try{
            Acao acao = repository.getOne(acaoDTO.getId());

            if(acao == null){
                throw new Exception();
            }

            acao.setQuantidade(acaoDTO.getQuantidade());
            acao.setDataCompra(acaoDTO.getDataCompra());
            acao.setValorAtivoPago(acaoDTO.getValorAtivoPago());
            acao.setValorBrutoPago(acaoDTO.getValorBrutoPago());
            acao.setStatus(acaoDTO.getStatus());
            acao.setPrecoAlvo(acaoDTO.getPrecoAlvo());

            repository.save(acao);
            return new Response("Registro Editado com sucesso",TipoResponse.SUCESSO, null);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Response inativarAcoes(List<Acao> acoes) {
        try{
            acoes.forEach(a -> a.setStatus("N"));
            this.repository.saveAll(acoes);
            return new Response("Registro salvo com sucesso",TipoResponse.SUCESSO, acoes);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void ativarAcoes(List<Acao> acoes) {
        try{
            acoes.forEach(a -> {
                Acao acao = this.repository.getOne(a.getId());
                acao.setStatus("S");
            });
            this.repository.saveAll(acoes);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Acao> consultarAcoesId(List<Integer> id) {
        try{
            return this.repository.consultarAcoesId(id);
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public BigDecimal calcularLucroBruto(List<Integer> idsCompra) {
        try{
            return this.repository.calcularValorBrutoPago(idsCompra);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Response excluirAcao(Integer id) {
        try{
            repository.delete(repository.getOne(id));
            return new Response("Registro excluido sucesso",TipoResponse.SUCESSO, null);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<AcaoDTO> consultarAcoesAtivosVenda(String nome) {
        try{
            List<AcaoDTO> dtos = new ArrayList<>();
            this.repository.findByAtivoNome(nome).forEach(a -> dtos.add(new AcaoDTO(a)));
            return dtos;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Acao> consultarAcoesPeloIdVenda(Integer idVenda) {
        try{
            return this.repository.findByVenda(idVenda);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Response atualizarPrecoAtual() {
        try{
            List<Acao> acao = repository.findByAllAndStatus();
            acao.forEach(a -> {
                if(!a.getMesAtualizacao().isEqual(LocalDate.now())){
                    var atualizado = alphaVantageService.consultarPrecoAlvo(a.getAtivo().getNome());
                    a.setPrecoHoje(atualizado);
                    a.setMesAtualizacao(LocalDate.now());
                }
            });

            return new Response("Preço atual dos ativos atualizados", TipoResponse.SUCESSO, null);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Acao> consultarAcoesAtivosMesCorrente(Integer mes, Integer ano) {
        try{
            List<Acao> acao = repository.consultarAcoesAtivosMesCorrente(ano, mes);
            return acao;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<AcaoDTO> consultarAcoesAtivos() {
        try{
            List<AcaoDTO> acaoDTO = new ArrayList<>();
            List<Acao> acaoList = repository.findByAllAndStatus();
            acaoList.forEach(a -> acaoDTO.add(new AcaoDTO(a)));
            return acaoDTO;
        }catch (Exception e){
            throw e;
        }
    }
}
