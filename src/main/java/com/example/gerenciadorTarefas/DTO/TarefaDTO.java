/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gerenciadorTarefas.DTO;

import com.example.gerenciadorTarefas.Entities.Tarefa;
import com.example.gerenciadorTarefas.Entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Paulo
 */
public class TarefaDTO {
    
    @NotNull
    @NotEmpty
    @NotBlank
    private String titulo;
    
    @NotNull
    @NotEmpty
    @NotBlank
    private String descricao;
    
    @NotNull
    @NotBlank
    private String status;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Tarefa criarTarefa(Usuario usuario){
        
        Tarefa tarefa= new Tarefa();
        tarefa.setDescricao(this.descricao);
        tarefa.setTitulo(this.titulo);
        tarefa.setStatus(this.status);
        tarefa.setUsuario(usuario);
        return tarefa;
        
    }
    
}
