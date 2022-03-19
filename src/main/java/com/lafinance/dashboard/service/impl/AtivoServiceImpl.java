package com.lafinance.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.lafinance.dashboard.domain.enums.StatusEnum;
import com.lafinance.dashboard.exception.BusinessException;
import com.lafinance.dashboard.exception.NenhumRegistroEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lafinance.dashboard.domain.dto.AtivoDTO;
import com.lafinance.dashboard.domain.model.Ativo;
import com.lafinance.dashboard.repository.AtivoRepository;
import com.lafinance.dashboard.service.AtivoService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Service
@Singleton
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AtivoServiceImpl implements AtivoService {

    public static final String ENTIDADE_ATIVO_NAO_ENCONTRADO = "Entidade Ativo não encontrado";
    public static final String NOME_DO_ATIVO_OBRIGATORIO = "Nome do ativo obrigatório";
    public static final String ENTIDADE_ATIVO_JA_CADASTRADO = "Entidade Ativo já cadastrado";

    private final AtivoRepository ativoRepository;

    public AtivoDTO consultarNomeAtivo(String nome) throws Exception {
        if (nome.isEmpty())
            throw new BusinessException(NOME_DO_ATIVO_OBRIGATORIO);

        var ativo = this.ativoRepository.findByNome(nome);

        if (Objects.isNull(ativo))
            throw new NenhumRegistroEncontradoException(ENTIDADE_ATIVO_NAO_ENCONTRADO);

        return new AtivoDTO(ativo);
    }

    public AtivoDTO salvarAtivo(AtivoDTO ativoDTO) throws Exception {
        var ativoConsultado = this.ativoRepository.findByNome(ativoDTO.getNome());
        if (Objects.nonNull(ativoConsultado))
            throw new BusinessException(ENTIDADE_ATIVO_JA_CADASTRADO);

        Ativo ativo = new Ativo();
        ativo.setNome(ativoDTO.getNome());
        ativo.setStatus(StatusEnum.ATIVO.getDescricao());
        ativo = this.ativoRepository.save(ativo);

        return new AtivoDTO(ativo);
    }

    public void editarAtivo(AtivoDTO ativoDTO) throws Exception {
        var ativoConsultado = this.ativoRepository.findById(ativoDTO.getId())
                .orElseThrow(() -> new NenhumRegistroEncontradoException(ENTIDADE_ATIVO_NAO_ENCONTRADO));

        ativoConsultado.setNome(ativoDTO.getNome());
        ativoConsultado.setStatus(ativoDTO.getStatus());

        this.ativoRepository.save(ativoConsultado);
    }

    public void excluirAtivo(Integer id) throws Exception {
        var ativoConsultado = ativoRepository.findById(id)
                .orElseThrow(() -> new NenhumRegistroEncontradoException(ENTIDADE_ATIVO_NAO_ENCONTRADO));
        this.ativoRepository.delete(ativoConsultado);
    }

    public List<AtivoDTO> listarAtivo() {
        List<Ativo> ativos = this.ativoRepository.findAll();
        List<AtivoDTO> dto = new ArrayList<>();
        ativos.forEach(a -> dto.add(new AtivoDTO(a)));
        return dto;
    }
}
