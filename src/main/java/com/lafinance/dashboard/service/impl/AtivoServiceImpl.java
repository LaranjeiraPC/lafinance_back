package com.lafinance.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    private final AtivoRepository ativoRepository;

    public AtivoDTO consultarNomeAtivo(String nome) throws Exception {
        if (nome.isEmpty())
            throw new BusinessException("Nome do ativo obrigatório");

        var ativo = ativoRepository.findByNome(nome);

        if (Objects.isNull(ativo))
            throw new NenhumRegistroEncontradoException("Nenhum registro encontrado pra o nome do ativo informado");

        return new AtivoDTO(ativo);
    }

    public AtivoDTO salvarAtivo(AtivoDTO ativoDTO) throws Exception {
        var ativoConsultado = ativoRepository.findByNome(ativoDTO.getNome());
        if (Objects.nonNull(ativoConsultado))
            throw new BusinessException("Entidade Ativo já cadastrado");

        Ativo ativo = new Ativo();
        ativo.setNome(ativoDTO.getNome());
        ativo.setStatus(StatusEnum.ATIVO.getDescricao());
        ativo = ativoRepository.save(ativo);

        return new AtivoDTO(ativo);
    }

    public AtivoDTO editarAtivo(AtivoDTO ativoDTO) throws Exception {
        var ativoConsultado = ativoRepository.findById(ativoDTO.getId())
                .orElseThrow(() -> new NenhumRegistroEncontradoException("Entidade Ativo não encontrado"));

        ativoConsultado.setNome(ativoDTO.getNome());
        ativoConsultado.setStatus(ativoDTO.getStatus());

        ativoConsultado = ativoRepository.save(ativoConsultado);
        return new AtivoDTO(ativoConsultado);
    }

    public void excluirAtivo(Integer id) throws Exception {
        var ativoConsultado = ativoRepository.findById(id)
                .orElseThrow(() -> new NenhumRegistroEncontradoException("Entidade Ativo não encontrado"));
        ativoRepository.delete(ativoConsultado);
    }

    public List<AtivoDTO> listarAtivo() {
        List<Ativo> ativos = ativoRepository.findAll();
        List<AtivoDTO> dto = new ArrayList<>();
        ativos.forEach(a -> dto.add(new AtivoDTO(a)));
        return dto;
    }
}
