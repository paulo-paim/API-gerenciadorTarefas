/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gerenciadorTarefas.Repositories;

import com.example.gerenciadorTarefas.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Paulo
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    
    
}
