package com.example.gerenciadorTarefas.Repositories;

import com.example.gerenciadorTarefas.Entities.SubTarefa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubTarefaRepository extends JpaRepository<SubTarefa, Integer>{
    
    @Query(nativeQuery = true, value="select subtarefa.id, subtarefa.acao, subtarefa.status, subtarefa.tarefa from subtarefa inner join tarefa on subtarefa.tarefa = :tarefa and tarefa.usuario=:usuario")
    List<SubTarefa> getTodasSubtarefasDaTarefaUsuario(int tarefa, int usuario); 
    
    @Query(nativeQuery=true, value="select subtarefa.id, subtarefa.acao, subtarefa.status, subtarefa.tarefa from subtarefa inner join tarefa on subtarefa.tarefa = :tarefa and tarefa.usuario=:usuario where subtarefa.id=:subtarefa")
    SubTarefa getSubtarefa(int tarefa, int usuario, int subtarefa);
}
