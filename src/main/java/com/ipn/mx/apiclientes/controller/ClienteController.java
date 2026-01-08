package com.ipn.mx.apiclientes.controller;

import ch.qos.logback.core.net.server.Client;
import com.ipn.mx.apiclientes.domain.entities.Cliente;
import com.ipn.mx.apiclientes.domain.repository.ClienteRepository;
import com.ipn.mx.apiclientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/apiClientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> findAll(){
        return clienteService.findAll();
    }

    @GetMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente findById(@PathVariable Long id){
        return clienteService.findById(id);
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente)
    {
    if(cliente.getIdCliente()!=null){
        cliente.setIdCliente(null);
    }
        return clienteService.save(cliente);
    }

    @PutMapping("/clientes/{id}")
    public  Cliente update(@PathVariable Long id, @RequestBody Cliente cliente){
        Cliente clienteOriginal = clienteService.findById(id);
        if(clienteOriginal==null){
            Cliente clienteNuevo = new Cliente();
            clienteNuevo.setNombre(cliente.getNombre());
            clienteNuevo.setPaterno(cliente.getPaterno());
            clienteNuevo.setMaterno(cliente.getMaterno());
            clienteNuevo.setEmail(cliente.getEmail());
            return  clienteService.save(clienteNuevo);
        } else {
            clienteOriginal.setNombre(cliente.getNombre());
            clienteOriginal.setPaterno(cliente.getPaterno());
            clienteOriginal.setMaterno(cliente.getMaterno());
            clienteOriginal.setEmail(cliente.getEmail());
            return clienteService.save(clienteOriginal);
        }
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        clienteService.deleteById(id);
    }
}
