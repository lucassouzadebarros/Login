package br.com.senac.context;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.senac.dominio.Usuario;

public class ForgotPasswordEmailContext extends AbstractEmailContext{

	private String token;
	
	public <T> void init(T context) {
		
		Usuario usuario = (Usuario) context;
		put("nome",usuario.getNomeCompleto());
		setSubject("Esqueceu sua senha");
		setFrom("lucassouzadebarros@gmail.com"); 
		setTemplateLocation("email/forgot-password");
		setTo(usuario.getEmail());
		setEmail(usuario.getEmail());
		
	}
	
	public void setToken(String token) {
		this.token = token;
		put("token",token);
	}

	public String getToken() {
		return token;
	}
	
	 public void buildVerificationUrl(final String baseURL, final String token){
	        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
	                .path("/register/verify").queryParam("token", token).toUriString();
	        put("verificationURL", url);
	 }
	
	
}
