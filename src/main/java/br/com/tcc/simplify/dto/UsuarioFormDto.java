package br.com.tcc.simplify.dto;

import java.util.List;

import br.com.tcc.simplify.entities.Usuario;
import br.com.tcc.simplify.repository.UsuarioRepository;

public class UsuarioFormDto {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario converter(UsuarioRepository usuarioRepository) {
		List<Usuario> usuario = usuarioRepository.findByEmail(email);
		return new Usuario(id, nome, email, senha);
	}
}
