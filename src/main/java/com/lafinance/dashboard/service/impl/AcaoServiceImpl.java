package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lafinance.dashboard.domain.enums.StatusEnum;
import com.lafinance.dashboard.exception.BusinessException;
import com.lafinance.dashboard.exception.NenhumRegistroEncontradoException;
import com.lafinance.dashboard.service.AlphaVantageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.domain.dto.AcaoDTO;
import com.lafinance.dashboard.domain.model.Acao;
import com.lafinance.dashboard.repository.AcaoRepository;
import com.lafinance.dashboard.service.AcaoService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Service
@Singleton
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AcaoServiceImpl implements AcaoService {

    public static final String NOME_DO_ATIVO_OBRIGATORIO = "Nome do Ativo obrigatório";
    public static final String QUANTIDADE_PREENCHIMENTO_OBRIGATORIO = "Quantidade preenchimento obrigatório";
    public static final String VALOR_BRUTO_PAGO_PREENCHIMENTO_OBRIGATORIO = "Valor bruto pago preenchimento obrigatório";
    public static final String VALOR_ATIVO_PAGO_PREENCHIMENTO_OBRIGATORIO = "Valor ativo pago preenchimento obrigatório";
    public static final String DATA_CRIACAO_PREENCHIMENTO_OBRIGATORIO = "Data Criação preenchimento obrigatório";
    public static final String ENTIDADE_ACAO_NAO_ENCONTRADO = "Entidade Ação não encontrado";
    public static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado";

    private final AcaoRepository repository;

    @Autowired
    private AlphaVantageService alphaVantageService;

    public List<AcaoDTO> listarAcoesAtivosOutrosMeses(List<Acao> ids) {
        List<AcaoDTO> dtoList = new ArrayList<>();

        var idsFormatado = ids.stream().map(Acao::getId).collect(Collectors.toList());
        if (idsFormatado.isEmpty())
            new NenhumRegistroEncontradoException(NENHUM_REGISTRO_ENCONTRADO);

        var acao = this.repository.findByIdNotInAndStatus(idsFormatado, "S");

        acao.forEach(a -> dtoList.add(new AcaoDTO(a)));
        return dtoList;
    }

    public AcaoDTO cadastrarAcao(AcaoDTO acaoDTO) throws Exception {
        if (acaoDTO.getAtivo().getNome().isEmpty())
            throw new BusinessException(NOME_DO_ATIVO_OBRIGATORIO);

        if (acaoDTO.getQuantidade() == null)
            throw new BusinessException(QUANTIDADE_PREENCHIMENTO_OBRIGATORIO);
        if (acaoDTO.getValorBrutoPago() == null)
            throw new BusinessException(VALOR_BRUTO_PAGO_PREENCHIMENTO_OBRIGATORIO);
        if (acaoDTO.getValorAtivoPago() == null)
            throw new BusinessException(VALOR_ATIVO_PAGO_PREENCHIMENTO_OBRIGATORIO);
        if (acaoDTO.getMesCriacao() == null)
            throw new BusinessException(DATA_CRIACAO_PREENCHIMENTO_OBRIGATORIO);

        Acao acao = repository.save(criarAcao(acaoDTO));
        return new AcaoDTO(acao);
    }

    public void editarAcao(AcaoDTO acaoDTO) throws Exception {
        var acaoConsultado = this.repository.findById(acaoDTO.getId())
                .orElseThrow(() -> new NenhumRegistroEncontradoException(ENTIDADE_ACAO_NAO_ENCONTRADO));

        acaoConsultado.setQuantidade(acaoDTO.getQuantidade());
        acaoConsultado.setDataCompra(acaoDTO.getDataCompra());
        acaoConsultado.setValorAtivoPago(acaoDTO.getValorAtivoPago());
        acaoConsultado.setValorBrutoPago(acaoDTO.getValorBrutoPago());
        acaoConsultado.setStatus(acaoDTO.getStatus());
        acaoConsultado.setPrecoAlvo(acaoDTO.getPrecoAlvo());
        acaoConsultado.setMesAtualizacao(LocalDate.now());
        repository.save(acaoConsultado);
    }

    public List<AcaoDTO> inativarAcoes(List<AcaoDTO> acaoDTO) {
        List<AcaoDTO> acaoDTOS = new ArrayList<>();
        List<Acao> acao = acaoDTO.stream().map(a -> {
            a.setStatus(StatusEnum.INATIVO.getDescricao());
            var dto = this.criarAcao(a);
            dto.setId(a.getId());
            dto.setMesAtualizacao(LocalDate.now());
            return dto;
        }).collect(Collectors.toList());

        acao = this.repository.saveAll(acao);

        acao.forEach(a -> acaoDTOS.add(new AcaoDTO(a)));
        return acaoDTOS;
    }

    public void ativarAcoes(List<Acao> acoes) throws Exception {
        var idsFormatado = acoes.stream().map(Acao::getId).collect(Collectors.toList());

        var acaoConsultado = this.repository.findByIdIn(idsFormatado).stream()
                .peek(a -> a.setStatus(StatusEnum.ATIVO.getDescricao())).collect(Collectors.toList());

        if (acaoConsultado.isEmpty())
            throw new BusinessException(NENHUM_REGISTRO_ENCONTRADO);

        this.repository.saveAll(acoes);
    }

    public void excluirAcao(Integer id) throws Exception {
        var acaoConsultado = this.repository.findById(id)
                .orElseThrow(() -> new NenhumRegistroEncontradoException(ENTIDADE_ACAO_NAO_ENCONTRADO));
        repository.delete(acaoConsultado);
    }

    public List<AcaoDTO> listarAcoesAtivosVenda(String nome) {
        List<AcaoDTO> dtos = new ArrayList<>();
        this.repository.findByAtivoNome(nome).forEach(a -> dtos.add(new AcaoDTO(a)));
        return dtos;
    }

    public List<Acao> consultarAcoesPeloIdVenda(Integer idVenda) throws Exception {
        var acao = this.repository.findByVenda(idVenda);
        if (acao.isEmpty())
            throw new BusinessException(NENHUM_REGISTRO_ENCONTRADO);

        return acao;
    }

    public List<AcaoDTO> listarAcoesAtivosMesCorrente(Integer mes, Integer ano) throws Exception {
        List<Acao> acaoConsultado = repository.consultarAcoesAtivosMesCorrente(ano, mes);
        if (acaoConsultado.isEmpty())
            throw new BusinessException(NENHUM_REGISTRO_ENCONTRADO);

        List<AcaoDTO> dtos = new ArrayList<>();
        acaoConsultado.forEach(a -> dtos.add(new AcaoDTO(a)));
        return dtos;
    }

    public List<AcaoDTO> listarAcoesAtivos() throws Exception {
        List<AcaoDTO> acaoDTO = new ArrayList<>();
        List<Acao> acao = repository.findByAllAndStatus();

        if (acao.isEmpty())
            throw new BusinessException(NENHUM_REGISTRO_ENCONTRADO);

        acao.forEach(a -> acaoDTO.add(new AcaoDTO(a)));
        return acaoDTO;
    }

    private Acao criarAcao(AcaoDTO acaoDTO) {
        Acao acao = new Acao();
        acao.setAtivo(acaoDTO.getAtivo());
        acao.setQuantidade(acaoDTO.getQuantidade());
        acao.setValorBrutoPago(acaoDTO.getValorBrutoPago());
        acao.setValorAtivoPago(acaoDTO.getValorAtivoPago());
        acao.setDataCompra(acaoDTO.getDataCompra());
        acao.setMesCriacao(acaoDTO.getMesCriacao());
        acao.setStatus(acaoDTO.getStatus());
        acao.setPrecoAlvo(acaoDTO.getPrecoAlvo());
        return acao;
    }

    public BigDecimal consultarInvestimentoTotal() {
        return this.repository.consultarValorInvestimentoTotal();
    }

    public Integer consultaQuantidadeCota() {
        return this.repository.consultarQuantidadeCotas();
    }

    public List<String> listarAtivosAgrupandoByNomeAtivo() {
        return this.repository.listarAtivosAgrupandoByNomeAtivo();
    }

    public void atualizarUltimaCotacao(List<Map<String, BigDecimal>> cotacoes) {
        List<Acao> acoes = this.repository.findByAllAndStatusAndDataAtualizacaoNotEqualsNow();
        for (Map<String, BigDecimal> cotacao : cotacoes) {
            cotacao.entrySet().forEach(cot -> acoes.stream().forEach(ac -> {
                if (ac.getAtivo().getNome().equals(cot.getKey())) {
                    ac.setPrecoHoje(cot.getValue());
                    ac.setMesAtualizacao(LocalDate.now());
                }
            }));
        }
        this.repository.saveAll(acoes);
    }
}
