package br.com.senac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.dominio.Usuario;
import br.com.senac.repositorio.UsuarioRepositorio;

@Service
public class UsuarioService {

	@Autowired // injeção de dependências
	private UsuarioRepositorio usuRepo;
	
	public Usuario buscaPorEmail(String email) {
		Usuario usu = usuRepo.findByEmail(email);
		return usu;
	}
	
	
	
	
	
}
