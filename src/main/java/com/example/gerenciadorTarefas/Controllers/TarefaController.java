package com.example.gerenciadorTarefas.Controllers;

import com.example.gerenciadorTarefas.DTO.TarefaDTO;
import com.example.gerenciadorTarefas.Entities.Tarefa;
import com.example.gerenciadorTarefas.Entities.Usuario;
import com.example.gerenciadorTarefas.Repositories.TarefaRepository;
import com.example.gerenciadorTarefas.Repositories.UsuarioRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TarefaController {
    
    @Autowired
    private TarefaRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @PostMapping(value="/usuario/{id}/tarefa")
    public ResponseEntity<Object> criarTarefa(@PathVariable(value="id") int id, @RequestBody @Valid TarefaDTO tarefaDTO){
        
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não localizado");
        }
        Tarefa tarefa = tarefaDTO.criarTarefa(usuario.get());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tarefa));
    }
    
    @GetMapping(value="/usuario/{id}/tarefa")
    public ResponseEntity<List<Tarefa>> getTodasTarefasDoUsuario(@PathVariable(value="id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(repository.getTarefaByUsuario(id));
    }
    
    @DeleteMapping(value="/usuario/{id}/tarefa/{tarefa}")
    public ResponseEntity<Object> removerTarefa(@PathVariable(value="id") int id, @PathVariable(value="tarefa") int tarefaid){
        
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não localizado!");
        }
        
        Optional<Tarefa> tarefa = repository.findById(tarefaid);
        if(tarefa.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não localizada!");
        }
        
        repository.delete(tarefa.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa excluída!");
        
        }
    
    @PutMapping(value="/usuario/{id}/tarefa/{tarefa}")
    public ResponseEntity<Object> alterarTarefa(@PathVariable(value="id") int id, @PathVariable(value="tarefa") int tarefaid, @RequestBody @Valid TarefaDTO tarefaDTO){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        Optional<Tarefa> tarefa = repository.findById(tarefaid);
        if(usuario.isEmpty() || tarefa.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário ou tarefa não localizado");
        }
        
        Tarefa tarefaBD = repository.getTarefaByUsuarioETarefa(usuario.get().getId(), tarefa.get().getId());
        if(tarefaBD == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não pertence ao usuário informado");
        }
        
        BeanUtils.copyProperties(tarefaDTO, tarefa.get());
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(tarefa.get()));
        
        
    }
    
}
