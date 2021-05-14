package br.com.senac.dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Permissao implements GrantedAuthority{

	private static final long serialVersionUID = -8562266496648719880L;

	@Id
	private String nomePermissao;
	
	@ManyToMany(mappedBy = "permissoes")
	private List<Usuario> usuarios;

	public String getNomePermissao() {
		return nomePermissao;
	}

	public void setNomePermissao(String nomePermissao) {
		this.nomePermissao = nomePermissao;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String getAuthority() {
		return this.nomePermissao;
	}
	

	
}
