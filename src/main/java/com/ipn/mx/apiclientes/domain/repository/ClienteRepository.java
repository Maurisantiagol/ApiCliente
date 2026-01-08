package com.ipn.mx.apiclientes.domain.repository;

import com.ipn.mx.apiclientes.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
