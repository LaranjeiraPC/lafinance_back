package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.lafinance.dashboard.domain.model.Acao;
import com.lafinance.dashboard.exception.BusinessException;
import com.lafinance.dashboard.exception.NenhumRegistroEncontradoException;
import com.lafinance.dashboard.service.AcaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.domain.model.CompraVenda;
import com.lafinance.dashboard.repository.CompraVendaRepository;
import com.lafinance.dashboard.service.CompraVendaService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Service
@Singleton
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class CompraVendaServiceImpl implements CompraVendaService {

    public static final String ID_VENDA_NAO_INFORMADO = "Id venda não informado";
    public static final String ID_NAO_INFORMADO = "ID não informado";
    public static final String NENHUM_REGISTRO_ENCONTRADO_PELO_ID_INFORMADO = "Nenhum registro encontrado pelo id informado";
    public static final String DADOS_DE_COMPRA_E_VENDA_NAO_INFORMADO = "Dados de compra e venda não informado";
    public static final String ANO_NAO_INFORMADO = "Ano não informado";

    private final CompraVendaRepository repository;

    @Autowired
    private AcaoService acaoService;

    public void salvarRegistro(List<CompraVenda> compraVenda) throws Exception {
        if (compraVenda.isEmpty())
            throw new BusinessException(DADOS_DE_COMPRA_E_VENDA_NAO_INFORMADO);

        repository.saveAll(compraVenda);
        repository.flush();
    }

    public void excluirCompraVendaPeloIdVenda(Integer id) throws Exception {
        if (Objects.isNull(id))
            throw new BusinessException(ID_NAO_INFORMADO);

        List<Acao> acaoList = this.acaoService.consultarAcoesPeloIdVenda(id);

        if (acaoList.isEmpty())
            throw new NenhumRegistroEncontradoException(NENHUM_REGISTRO_ENCONTRADO_PELO_ID_INFORMADO);

        this.acaoService.ativarAcoes(acaoList);

        List<CompraVenda> registros = this.repository.findByVendaId(id);

        this.repository.deleteInBatch(registros);
    }

    public BigDecimal calcularLucroBrutoTotalPeloIdVenda(Integer idVenda) throws Exception {
        if (Objects.isNull(idVenda))
            throw new BusinessException(ID_VENDA_NAO_INFORMADO);

        var lucroBrutoTotal = this.repository.calculoLucroBrutoPeloIdVenda(idVenda);

        return lucroBrutoTotal;
    }

    public BigDecimal calcularLucroBrutoAno(Integer ano) {
        return this.repository.calculoLucroAno(ano);
    }

    public List<CompraVenda> consultarCompraVendaPeloIdVenda(Integer id) {
        return repository.findByVendaId(id);
    }

    public List<Integer> listarDataVendaSomenteAno() {
        var dataVenda = this.repository.listarAnos();
        var anosAgrupado = dataVenda.stream().collect(Collectors.groupingBy(LocalDate::getYear,
                Collectors.mapping(LocalDate::getYear, Collectors.toCollection(ArrayList::new))));

        var anos = anosAgrupado.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());

        return anos;
    }

    @Override
    public List<Integer> listarDataVendaSomenteMes(Integer ano) throws BusinessException {
        if (Objects.isNull(ano))
            throw new BusinessException(ANO_NAO_INFORMADO);
        var dataVenda = this.repository.listarMeses(ano);
        var meses = dataVenda.stream().map(LocalDate::getMonthValue).collect(Collectors.toList());
        return meses;
    }

    public List<String> listarAtivosAgrupandoByNomeAtivo() {
        var ativo = this.repository.listarAtivosAgrupandoByNomeAtivo();
        return ativo;
    }

}
