package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lafinance.dashboard.service.AlphaVantageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.dto.AcaoDTO;
import com.lafinance.dashboard.model.Acao;
import com.lafinance.dashboard.repository.AcaoRepository;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.util.Response;
import com.lafinance.dashboard.util.Response.TipoResponse;

@Slf4j
@Service
@Transactional
public class AcaoServiceImpl implements AcaoService {

    @Autowired
    private AcaoRepository repository;

    @Autowired
    private AlphaVantageService alphaVantageService;

    @Override
    public List<AcaoDTO> consultarAcoesAtivos() {
        try{
            log.debug("Consultando registros ativos - Tela ação");
            List<AcaoDTO> dtoList = new ArrayList<>();
            List<Acao> acao = repository.findByAllAndStatus();

            log.info("Total de registros encontrados {}", acao.size());
            acao.forEach(a -> dtoList.add(new AcaoDTO(a)));
            return dtoList;
        }catch (Exception e){
            log.error("Erro ao consultar registros - Tela Ação");
            throw e;
        }
    }

    @Override
    public Response cadastrarAcao(Acao acao) throws Exception {
        try{
            if(acao.getAtivo().getNome().isEmpty()) {
                log.error("Ação não possui ativo");
                throw new Exception();
            }

            log.debug("Salvando registro - Tela Ação");
            acao = repository.save(acao);
            log.info("Registro armazenado. ID {}", acao.getId());

            atualizarPrecoAtual();

            return new Response("Registro salvo com sucesso",TipoResponse.SUCESSO, null);
        }catch (Exception e){
            log.error("Erro ao salvar registro - Tela Ação");
            throw e;
        }
    }

    @Override
    public Response editarAcao(AcaoDTO acaoDTO) throws Exception {
        try{
            Acao acao = repository.getOne(acaoDTO.getId());

            if(acao == null){
                log.error("Consulta não retornou nenhum registro. ID: {}", acaoDTO.getId());
                throw new Exception();
            }

            log.debug("Editando registro {} - Tela Ação", acaoDTO.getId());
            acao.setQuantidade(acaoDTO.getQuantidade());
            acao.setDataCompra(acaoDTO.getDataCompra());
            acao.setValorAtivoPago(acaoDTO.getValorAtivoPago());
            acao.setValorBrutoPago(acaoDTO.getValorBrutoPago());
            acao.setStatus(acaoDTO.getStatus());
            acao.setPrecoAlvo(acaoDTO.getPrecoAlvo());

            repository.save(acao);
            log.info("Registro editado. ID {}", acao.getId());

            return new Response("Registro Editado com sucesso",TipoResponse.SUCESSO, null);
        }catch (Exception e){
            log.error("Erro ao editar registro - Tela Ação");
            throw e;
        }
    }

    @Override
    public Response inativarAcoes(List<Acao> acoes) {
        try{
            log.debug("Inativando registros - Tela Ação");
            log.info("Quantidade de registros desabilitados {}", acoes.size());
            acoes.forEach(a -> a.setStatus("N"));

            this.repository.saveAll(acoes);
            log.debug("Registros inativados - Tela Ação");

            return new Response("Registro salvo com sucesso",TipoResponse.SUCESSO, acoes);
        }catch (Exception e){
            log.warn("Erro ao inativar registros");
            throw e;
        }
    }

    @Override
    public void ativarAcoes(List<Acao> acoes) {
        try{
            log.debug("Consultando ações para habilitar - Tela ação");
            log.info("Quantidade de registro para habilitar {}", acoes);
            acoes.forEach(a -> {
                Acao acao = this.repository.getOne(a.getId());
                acao.setStatus("S");
                log.debug("Registro {} habilitado - Tela ação", acao.getId());
            });
            log.debug("Finalizado habilitação dos registros - Tela ação");
            this.repository.saveAll(acoes);
        }catch (Exception e){
            log.warn("Erro ao ativar registros");
            throw e;
        }
    }

    @Override
    public List<Acao> consultarAcoesId(List<Integer> id) {
        try{
            log.debug("Consultando ações - Tela ação");
            log.info("Ids utilizado para consulta: {} - Tela ação", id);
            return this.repository.consultarAcoesId(id);
        }catch (Exception e){
            log.warn("Erro ao consultar registros");
            throw e;
        }

    }

    @Override
    public BigDecimal calcularLucroBruto(List<Integer> idsCompra) {
        try{
            log.debug("Calculando registros. IDs {} - Tela ação", idsCompra);
            return this.repository.calcularValorBrutoPago(idsCompra);
        }catch (Exception e){
            log.warn("Erro ao calcular registros");
            throw e;
        }
    }

    @Override
    public Response excluirAcao(Integer id) {
        try{
            log.debug("Excluindo registro de id {} - Tela ação", id);
            repository.delete(repository.getOne(id));
            log.debug("Registro de id {} excluído com sucesso - Tela ação", id);

            return new Response("Registro excluido sucesso",TipoResponse.SUCESSO, null);
        }catch (Exception e){
            log.warn("Erro ao excluir registro");
            throw e;
        }
    }

    @Override
    public List<AcaoDTO> consultarAcoesAtivosVenda(String nome) {
        try{
            log.debug("Consultando ações ativas para venda pelo nome da ação: {} - Tela ação", nome);
            List<AcaoDTO> dtos = new ArrayList<>();
            this.repository.findByAtivoNome(nome).forEach(a -> dtos.add(new AcaoDTO(a)));
            return dtos;
        }catch (Exception e){
            log.warn("Erro ao consultar registro");
            throw e;
        }
    }

    @Override
    public List<Acao> consultarAcoesPeloIdVenda(Integer idVenda) {
        try{
            log.debug("Consultando registros pelo id {} venda - Tela ação", idVenda);
            return this.repository.findByVenda(idVenda);
        }catch (Exception e){
            log.warn("Erro ao consultar registro");
            throw e;
        }
    }

    @Override
    public Response atualizarPrecoAtual() {
        try{
            log.debug("Consultando registros ativos - Tela Home");
            List<Acao> acao = repository.findByAllAndStatus();

            log.info("Total de registros encontrados {} - Tela Home", acao.size());
            acao.forEach(a -> {
                if(!a.getMesAtualizacao().isEqual(LocalDate.now())){
                    log.debug("Atualizando preço atual - Tela Home");
                    var atualizado = alphaVantageService.consultarPrecoAlvo(a.getAtivo().getNome());
                    a.setPrecoHoje(atualizado);
                    a.setMesAtualizacao(LocalDate.now());
                    log.info("Registro atualizado {} - Tela Home", a.getAtivo().getNome());
                }
            });

            return new Response("Preço atual dos ativos atualizados", TipoResponse.SUCESSO, null);
        }catch (Exception e){
            log.error("Erro ao atualizar registros - Tela Ação");
            throw e;
        }
    }

}
