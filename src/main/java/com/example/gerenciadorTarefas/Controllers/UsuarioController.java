package com.example.gerenciadorTarefas.Controllers;

import com.example.gerenciadorTarefas.Entities.Usuario;
import com.example.gerenciadorTarefas.Repositories.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;
    
    @PostMapping
    public ResponseEntity<Usuario> postUsuario(@RequestBody @Valid Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
    }
    
    @GetMapping(value ="/{id}")
    public ResponseEntity<Object> getUsuario(@PathVariable int id){
      Optional<Usuario> usuario= repository.findById(id);
      if(usuario.isEmpty()){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("Usuário não encontrado").getMessage());
      }
      return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
    }
    
    
    @GetMapping(value ="")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
    
    //alteracao do usuario completo (Mesmo id, valores diferentes)
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> putUsuario(@RequestBody @Valid Usuario usuario, @PathVariable int id){
        
        Optional<Usuario> usuariobd = repository.findById(id);
        if(usuariobd.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("Usuário não localizado").getMessage());
        }
        
        usuario.setId(id);
        BeanUtils.copyProperties(usuario, usuariobd.get());
        
        repository.save(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") int id){
        
        Optional<Usuario> usuariobd = repository.findById(id);
        if(usuariobd.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        repository.delete(usuariobd.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado Corretamente.");
    }
    
}
