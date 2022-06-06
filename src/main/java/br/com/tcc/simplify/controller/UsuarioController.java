package br.com.tcc.simplify.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.tcc.simplify.dto.UsuarioDto;
import br.com.tcc.simplify.dto.UsuarioFormDto;
import br.com.tcc.simplify.entities.Usuario;
import br.com.tcc.simplify.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> buscar(String nomeUsuario){
        return usuarioRepository.findAll();
    }
    
    @PostMapping
    @Transactional
    public  ResponseEntity<UsuarioDto> inserir(@RequestBody UsuarioFormDto usuarioFormDto ,UriComponentsBuilder uriBilder){
    	Usuario usuario = usuarioFormDto.converter(usuarioRepository);
		usuarioRepository.save(usuario);
		
    	URI uri = uriBilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }
    
    @GetMapping("/{id}")
	public UsuarioDto buscarPeloId(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.getById(id);
		return new UsuarioDto(usuario);
	}
    
    @PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody  UsuarioDto usuarioDto){
		Usuario usuario = usuarioDto.atualizar(id, usuarioRepository);
		return ResponseEntity.ok(new UsuarioDto(usuario));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
    	usuarioRepository.deleteById(id);
    	return ResponseEntity.ok().build();
    	}
}
