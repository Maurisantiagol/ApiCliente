package com.ipn.mx.apiclientes.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(name = "nombre", length = 50, nullable = false )
    private String nombre;

    @Column(name = "paterno", length = 50, nullable = false )
    private String paterno;

    @Column(name = "materno", length = 50, nullable = false )
    private String materno;
    @Column(name = "email", length = 150, nullable = false, unique = true )
    private String email;
}
