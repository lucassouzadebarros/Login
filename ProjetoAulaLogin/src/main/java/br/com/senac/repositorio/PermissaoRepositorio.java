package br.com.senac.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.dominio.Permissao;

@Repository
public interface PermissaoRepositorio extends CrudRepository<Permissao, String>{

}
