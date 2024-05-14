package com.example.gerenciadorTarefas.Controllers;

import com.example.gerenciadorTarefas.Entities.Tarefa;
import com.example.gerenciadorTarefas.Entities.Usuario;
import com.example.gerenciadorTarefas.Repositories.TarefaRepository;
import com.example.gerenciadorTarefas.Repositories.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TarefaController {
    
    @Autowired
    private TarefaRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @PostMapping(value="/usuario/{id}/tarefa")
    public Tarefa criarTarefa(@PathVariable(value="id") int id, @RequestBody @Valid Tarefa tarefa){
        Usuario usuario = usuarioRepository.findById(id).get();
        tarefa.setUsuario(usuario);
        return repository.save(tarefa);
    }
    
    @GetMapping(value="/usuario/{id}/tarefa")
    public List<Tarefa> getTodasTarefasDoUsuario(@PathVariable(value="id") int id){
        Usuario usuario = usuarioRepository.findById(id).get();
        
        List<Tarefa> tarefas = repository.getTarefaByUsuario(usuario.getId());
        System.out.println(tarefas);
        return tarefas;
    }
    
}
