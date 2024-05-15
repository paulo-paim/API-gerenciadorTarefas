package com.example.gerenciadorTarefas.Controllers;

import com.example.gerenciadorTarefas.Repositories.SubTarefaRepository;
import com.example.gerenciadorTarefas.Repositories.TarefaRepository;
import com.example.gerenciadorTarefas.Repositories.UsuarioRepository;
import com.example.gerenciadorTarefas.Entities.SubTarefa;
import com.example.gerenciadorTarefas.Entities.Tarefa;
import com.example.gerenciadorTarefas.Entities.Usuario;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Object> criarSubTarefa(@PathVariable(value="id") int id,@PathVariable(value="tar") int tar,@RequestBody @Valid SubTarefa subTarefa){
        
        Tarefa tarefa = tarefaRepository.getTarefaByUsuarioETarefa(id, tar);
        
        if(tarefa==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário ou tarefa não localizado");
        }
        
        subTarefa.setTarefa(tarefa);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(subTarefa));
        
        
    }
    
    @GetMapping(value="/usuario/{id}/tarefa/{tar}")
    public List<SubTarefa> getTodasSubTarefas(@PathVariable(value="id") int id, @PathVariable(value="tar") int tar){
        
        List<SubTarefa> subtarefas = repository.getTodasSubtarefasDaTarefaUsuario(tar, id);
        
        return subtarefas;
    }
    
    @DeleteMapping(value="/usuario/{id}/tarefa/{tarefa}/subtarefa/{subtarefa}")
    public ResponseEntity<Object> deletarSubtarefa(@PathVariable(value="id") int id, @PathVariable(value="tarefa") int tarefa, @PathVariable(value="subtarefa") int sub){
        
        SubTarefa subtarefa = repository.getSubtarefa(tarefa, id, sub);
        if(subtarefa == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sub-Tarefa não localizada na tarefa ou usuário informado");
        }
        repository.delete(subtarefa);
        return ResponseEntity.status(HttpStatus.OK).body("SubTarefa excluída com sucesso!");
    }
    
    
    @PutMapping(value="/usuario/{id}/tarefa/{tarefa}/subtarefa/{subtarefa}")
    public ResponseEntity<Object> alterarSubTarefa(@PathVariable(value="id") int id, @PathVariable(value="tarefa") int tarefa, @PathVariable(value="subtarefa") int subtarefa, @RequestBody @Valid SubTarefa subTarefaAtualizada){
        SubTarefa subBD = repository.getSubtarefa(tarefa, id, subtarefa);
        if(subBD==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Algo não foi localizado");
        }
        
        BeanUtils.copyProperties(subTarefaAtualizada, subBD);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(subBD));
        
    }
}
