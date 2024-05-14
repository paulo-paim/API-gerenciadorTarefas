/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gerenciadorTarefas.Repositories;

import com.example.gerenciadorTarefas.Entities.Tarefa;
import com.example.gerenciadorTarefas.Entities.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Paulo
 */
public interface TarefaRepository extends JpaRepository<Tarefa, Integer>{
    
    @Query(nativeQuery = true, value="SELECT * FROM Tarefa WHERE usuario=:usuario")
    List<Tarefa> getTarefaByUsuario(int usuario);
}