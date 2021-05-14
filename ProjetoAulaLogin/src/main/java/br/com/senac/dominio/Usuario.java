package br.com.senac.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails, Serializable{
	
	private static final long serialVersionUID = 4336030923014801002L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String login;
	
	private String nomeCompleto;
	
	private String senha;
	
	private String email;
	
	
	
	@ManyToMany
	@JoinTable(
			name="usuarios_roles",
			joinColumns = @JoinColumn(
					name = "usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
			name = "role_id", referencedColumnName = "nomeRole"))
	private List<Role> roles;
	
	@ManyToMany
	@JoinTable(
			name = "usuarios_permissoes",
			joinColumns = @JoinColumn(
					name = "usuario_id", referencedColumnName = "id"),
					inverseJoinColumns = @JoinColumn(
					name = "permissao_id", referencedColumnName = "nomePermissao"))
	private List<Permissao> permissoes;
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<Permissao> getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//return (Collection<? extends GrantedAuthority>) this.roles;
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		this.permissoes.forEach(p ->{
			GrantedAuthority authority = new SimpleGrantedAuthority(p.getNomePermissao());
			authorities.add(authority);
		});
		
		this.roles.forEach(r ->{
			GrantedAuthority authority = new SimpleGrantedAuthority(r.getNomeRole());
			authorities.add(authority);
		});
		
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return this.senha;
	}
	@Override
	public String getUsername() {
		return this.login;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true; // alterado de false para true
	}
	@Override
	public boolean isAccountNonLocked() {
		return true; // alterado de false para true
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true; // alterado de false para true
	}
	@Override
	public boolean isEnabled() {
		return true; // alterado de false para true
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	

}
