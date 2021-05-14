package br.com.senac.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.senac.dominio.SecureToken;

@Repository
public interface SecureTokenRepository extends JpaRepository <SecureToken, Long> {

	
	
}
