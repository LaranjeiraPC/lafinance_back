package com.lafinance.dashboard.domain.enums;

public enum StatusEnum {
    ATIVO("S"),
    INATIVO("N");

    private String descricao;

    StatusEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
