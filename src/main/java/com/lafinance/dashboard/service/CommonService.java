package com.lafinance.dashboard.service;

import com.lafinance.dashboard.util.Response;

public interface CommonService<K>{
    Response cadastrar(K object);
    Response excluir(Integer id);
    Response editar(K object);
}
