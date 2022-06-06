package br.com.tcc.simplify.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.tcc.simplify.entities.Usuario;
import br.com.tcc.simplify.repository.UsuarioRepository;


public class UsuarioDto {
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	public UsuarioDto() {
		
	}

	public UsuarioDto(Usuario usuario){
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
	
	public static List<UsuarioDto> converter(List<Usuario> usuarios){
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}
	
	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getById(id);
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		usuario.setSenha(this.senha);
		return usuario;
	}
}