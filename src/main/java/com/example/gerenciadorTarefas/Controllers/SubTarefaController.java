package com.example.gerenciadorTarefas.Controllers;

import com.example.gerenciadorTarefas.Repositories.SubTarefaRepository;
import com.example.gerenciadorTarefas.Repositories.TarefaRepository;
import com.example.gerenciadorTarefas.Repositories.UsuarioRepository;
import com.example.gerenciadorTarefas.Entities.SubTarefa;
import com.example.gerenciadorTarefas.Entities.Tarefa;
import com.example.gerenciadorTarefas.Entities.Usuario;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SubTarefaController {
    
    @Autowired
    private SubTarefaRepository repository;
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    @PostMapping(value="/usuario/{id}/tarefa/{tar}")
    public SubTarefa criarSubTarefa(@PathVariable(value="id") int id,@PathVariable(value="tar") int tar,@RequestBody @Valid SubTarefa subTarefa){
        Usuario usuario = usuarioRepository.findById(id).get();
        Tarefa tarefa = tarefaRepository.findById(tar).get();
        
        subTarefa.setTarefa(tarefa);
        tarefa.setUsuario(usuario);
        
        return repository.save(subTarefa);
        
        
    }
    
    @GetMapping(value="/usuario/{id}/tarefa/{tar}")
    public List<SubTarefa> getTodasSubTarefas(@PathVariable(value="id") int id, @PathVariable(value="tar") int tar){
        
        List<SubTarefa> subtarefas = repository.getTodasSubtarefasDaTarefaUsuario(tar, id);
        
        return subtarefas;
    }
}
