package com.projetoprincipal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoprincipal.entities.Funcionario;
import com.projetoprincipal.services.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioController {
    private final FuncionarioService FuncionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService FuncionarioService) {
        this.FuncionarioService = FuncionarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscaFuncionarioControlId(@PathVariable long id) {
        Funcionario Funcionario = FuncionarioService.buscaFuncionarioPeloId(id);
        if (Funcionario != null) {
            return ResponseEntity.ok(Funcionario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> buscaTodasFuncionariosControl() {
        List<Funcionario> Funcionarios = FuncionarioService.buscaTodosFuncionarios();
        return ResponseEntity.ok(Funcionarios);
    }

    @PostMapping
    public ResponseEntity<Funcionario> salvaFuncionarioControl(@RequestBody Funcionario Funcionario) {
        Funcionario salvaFuncionario = FuncionarioService.salvaFuncionario(Funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaFuncionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> alteraFuncionarioControl(@PathVariable Long id, @RequestBody Funcionario Funcionario) {
        Funcionario alteraFuncionario = FuncionarioService.alterarFuncionario(id, Funcionario);
        if (alteraFuncionario != null) {
            return ResponseEntity.ok(Funcionario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagaFuncionarioControl(@PathVariable Long id) {
        boolean apagar = FuncionarioService.apagarFuncionario(id);
        if (apagar) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
