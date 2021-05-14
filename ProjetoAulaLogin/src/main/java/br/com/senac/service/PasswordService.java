package br.com.senac.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.senac.context.ForgotPasswordEmailContext;
import br.com.senac.dominio.SecureToken;
import br.com.senac.dominio.Usuario;

@Service
public class PasswordService {

	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	SecureTokenService secureTokenService;
	
	@Value("${site.base.url.https}")
    private String baseURL;
	
	@Autowired
	private EmailService emailService;
	
	
	public void esqueciSenha(String email) {
		Usuario usu = usuarioService.buscaPorEmail(email);
		sendResetPasswordEmail(usu);
	}
	
	public void sendResetPasswordEmail(Usuario usuario) {
		SecureToken secureToken = secureTokenService.createSecureToken();
		secureToken.setUsuario(usuario);
		secureTokenService.salvarToken(secureToken);
		
		ForgotPasswordEmailContext emailContext = new ForgotPasswordEmailContext();
		emailContext.init(usuario);
		emailContext.setToken(secureToken.getToken());
		emailContext.buildVerificationUrl(baseURL,secureToken.getToken());
		
		try {
			emailService.sendMail(emailContext);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
}
