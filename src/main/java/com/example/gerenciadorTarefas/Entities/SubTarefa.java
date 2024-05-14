package com.example.gerenciadorTarefas.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="subtarefa")
public class SubTarefa {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @NotNull
    @NotEmpty
    @NotBlank
    private String acao;
    
        @NotNull
    @NotEmpty
    @NotBlank
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "tarefa")
    private Tarefa tarefa;

    public SubTarefa() {
    }

    public SubTarefa(int id, String acao, String status, Tarefa tarefa) {
        this.id = id;
        this.acao = acao;
        this.status = status;
        this.tarefa = tarefa;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    
    
}
