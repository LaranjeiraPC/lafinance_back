package com.lafinance.dashboard.service;

public interface CommonService<K>{
    K cadastrar(K object) throws Exception;
    void excluir(Integer id) throws Exception;
    void editar(K object) throws Exception;
}
