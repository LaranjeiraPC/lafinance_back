package com.lafinance.dashboard.service;

import com.lafinance.dashboard.util.Response;

public interface CommonService<K>{
    Response cadastrar(K type);
    Response excluir(Integer id);
}
