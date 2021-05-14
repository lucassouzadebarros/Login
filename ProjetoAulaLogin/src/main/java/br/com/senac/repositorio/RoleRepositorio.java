package br.com.senac.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.dominio.Role;

@Repository
public interface RoleRepositorio extends CrudRepository<Role, String>{
	
	

}
