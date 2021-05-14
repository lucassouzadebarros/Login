package br.com.senac;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.senac.dominio.Permissao;
import br.com.senac.dominio.Role;
import br.com.senac.dominio.Usuario;
import br.com.senac.repositorio.PermissaoRepositorio;
import br.com.senac.repositorio.RoleRepositorio;
import br.com.senac.repositorio.UsuarioRepositorio;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	UsuarioRepositorio usuRepo;
	
	@Autowired
	RoleRepositorio roleRepo;
	
	@Autowired
	PermissaoRepositorio permissaoRepo;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setNomeCompleto("Marcelo admin");
		usuario.setEmail("marceloestruc@uol.com.br");
		usuario.setSenha(new BCryptPasswordEncoder().encode("123456"));
		
		Role roleAdmin = new Role();
		roleAdmin.setNomeRole("ROLE_ADMIN");
		roleRepo.save(roleAdmin);
		
		usuario.setRoles(Arrays.asList(roleAdmin));
		usuRepo.save(usuario);
		
		Usuario usuario2 = new Usuario();
		usuario2.setLogin("estruc");
		usuario2.setNomeCompleto("Marcelo estruc");
		usuario2.setSenha(new BCryptPasswordEncoder().encode("123456"));
		
		Role roleUser = new Role();
		roleUser.setNomeRole("ROLE_USER");
		
		Permissao permissaoUser = new Permissao();
		permissaoUser.setNomePermissao("insert");
		
		permissaoRepo.save(permissaoUser);
		
		roleRepo.save(roleUser);
		
		usuario2.setRoles(Arrays.asList(roleUser));
		
		usuario2.setPermissoes(Arrays.asList(permissaoUser));
		
		usuRepo.save(usuario2);
		
		Usuario usuario3 = new Usuario();
		usuario3.setLogin("Salvato");
		usuario3.setNomeCompleto("Sara");
		usuario3.setSenha(new BCryptPasswordEncoder().encode("123456"));
		
		usuario3.setRoles(Arrays.asList(roleUser, roleAdmin));
		
		usuRepo.save(usuario3);
	}

}
