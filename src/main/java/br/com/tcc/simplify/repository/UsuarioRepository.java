package br.com.tcc.simplify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.simplify.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findByEmail(String email);
	
}
