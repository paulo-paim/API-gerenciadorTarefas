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
    public Usuario postUsuario(@RequestBody @Valid Usuario usuario){
        return repository.save(usuario);
    }
    
    @GetMapping(value ="/{id}")
    public Usuario getUsuario(@PathVariable int id){
      Usuario usuario= repository.findById(id).get();
      return usuario;
    }
    
    @GetMapping(value ="")
    public List<Usuario> getUsuarios(){
        System.out.println("BATEU");
        return repository.findAll();
    }
    
    //alteracao do usuario completo (Mesmo id, valores diferentes)
    @PutMapping(value = "/{id}")
    public Usuario putUsuario(@RequestBody @Valid Usuario usuario, @PathVariable int id){
        
        Optional<Usuario> usuariobd = repository.findById(usuario.getId());
        if(usuariobd.isEmpty()){
            return new Usuario();
        }
        
        
        BeanUtils.copyProperties(usuario, usuariobd.get());
        repository.save(usuario);
        return usuario;
    }
    
    @DeleteMapping(value = "/{id}")
    public String deleteUsuario(@PathVariable(value = "id") int id){
        
        Optional<Usuario> usuariobd = repository.findById(id);
        if(usuariobd.isEmpty()){
            return "USUARIO NAO ENCONTRADO";
        }
        repository.delete(usuariobd.get());
        return "USUARIO DELETADO!!!";
    }
    
}
