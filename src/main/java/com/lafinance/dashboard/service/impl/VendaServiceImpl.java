package com.lafinance.dashboard.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.lafinance.dashboard.exception.BusinessException;
import com.lafinance.dashboard.exception.NenhumRegistroEncontradoException;
import com.lafinance.dashboard.service.AcaoService;
import com.lafinance.dashboard.service.AtivoService;
import com.lafinance.dashboard.service.CompraVendaService;
import com.lafinance.dashboard.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.domain.dto.VendaDTO;
import com.lafinance.dashboard.domain.model.Venda;
import com.lafinance.dashboard.repository.VendaRepository;
import com.lafinance.dashboard.util.Util;

import javax.inject.Inject;
import javax.inject.Singleton;

@Service
@Singleton
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class VendaServiceImpl implements VendaService {

    public static final String NOME_ATIVO_PREENCHIMENTO_OBRIGATORIO = "Nome ativo preenchimento obriqatório";
    public static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado";
    public static final String ID_NAO_INFORMADO = "ID não informado";

    private final VendaRepository repository;

    @Autowired
    private AcaoService acaoService;

    @Autowired
    private AtivoService ativoService;

    @Autowired
    private CompraVendaService compraVendaService;

    public List<VendaDTO> consultarVendasPeloAnoMesSelecionado(String ano, String mes) throws Exception {
        List<VendaDTO> dtoList = new ArrayList<>();

        var vendasConsultado = repository.findByDataVenda(Integer.parseInt(ano),
                Integer.parseInt(Util.converterNomeMesParaInteiro(mes)));

        if (vendasConsultado.isEmpty())
            throw new BusinessException(NENHUM_REGISTRO_ENCONTRADO);

        vendasConsultado.forEach(a -> dtoList.add(new VendaDTO(a)));
        return dtoList;
    }

    public List<VendaDTO> consultarVendasPeloAnoMesSelecionadoInteiro(String ano, String mes) {
        List<VendaDTO> dtoList = new ArrayList<>();
        this.repository.findByDataVenda(Integer.parseInt(ano),
                Integer.parseInt(Util.ajustarNumeroMes(mes))).forEach(a -> dtoList.add(new VendaDTO(a)));
        return dtoList;
    }

    public BigDecimal calcularLucroBruto(List<Integer> idVenda) throws Exception {
        var vendaConsultado = this.repository.findByIdIn(idVenda);

        if (vendaConsultado.isEmpty())
            throw new NenhumRegistroEncontradoException(NENHUM_REGISTRO_ENCONTRADO);

        return this.repository.calcularLucroBruto(idVenda);
    }

    public VendaDTO cadastrar(VendaDTO vendaDTO) throws Exception {
        if (vendaDTO.getAtivo().getNome().isBlank())
            throw new BusinessException(NOME_ATIVO_PREENCHIMENTO_OBRIGATORIO);

        Venda venda = new Venda();
        venda.setAtivo(vendaDTO.getAtivo());
        venda.setQuantidade(vendaDTO.getQuantidade());
        venda.setValorBrutoVenda(vendaDTO.getValorBrutoVenda());
        venda.setValorAtivoVenda(vendaDTO.getValorAtivoVenda());
        venda.setDataVenda(vendaDTO.getDataVenda());
        venda.setMesCriacao(LocalDate.now());
        venda = repository.saveAndFlush(venda);

        return new VendaDTO(venda);
    }

    public void excluir(Integer id) throws Exception {
        if (Objects.isNull(id))
            throw new BusinessException(ID_NAO_INFORMADO);

        this.compraVendaService.excluirCompraVendaPeloIdVenda(id);
        this.repository.delete(repository.getOne(id));
    }

    public void editar(VendaDTO vendaDTO) throws Exception {
        var vendaConsultado = this.repository.findById(vendaDTO.getId())
                .orElseThrow(() -> new NenhumRegistroEncontradoException(NENHUM_REGISTRO_ENCONTRADO));

        vendaConsultado.setAtivo(vendaDTO.getAtivo());
        vendaConsultado.setQuantidade(vendaDTO.getQuantidade());
        vendaConsultado.setValorBrutoVenda(vendaDTO.getValorBrutoVenda());
        vendaConsultado.setValorAtivoVenda(vendaDTO.getValorAtivoVenda());
        vendaConsultado.setDataVenda(vendaDTO.getDataVenda());
        vendaConsultado.setMesAtualizacao(LocalDate.now());
        this.repository.save(vendaConsultado);
    }
}
