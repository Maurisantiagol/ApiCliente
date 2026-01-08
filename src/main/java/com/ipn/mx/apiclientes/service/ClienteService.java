package com.ipn.mx.apiclientes.service;

import com.ipn.mx.apiclientes.domain.entities.Cliente;

import java.util.List;

public interface ClienteService {
    public List<Cliente> findAll();
    public Cliente findById(Long id);
    public Cliente save(Cliente cliente);
    public void deleteById(Long id);
}
