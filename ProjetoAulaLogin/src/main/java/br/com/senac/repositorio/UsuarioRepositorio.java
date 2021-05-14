package br.com.senac.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.dominio.Usuario;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer>{
	
	Usuario findByLogin(String login);
	Usuario findByEmail(String email);

}
