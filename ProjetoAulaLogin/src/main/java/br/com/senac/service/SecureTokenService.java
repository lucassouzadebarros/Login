package br.com.senac.service;

import org.springframework.stereotype.Service;

import br.com.senac.dominio.SecureToken;
import br.com.senac.repositorio.SecureTokenRepository;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;


@Service
public class SecureTokenService {

	  private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
	  private static final Charset US_ASCII = Charset.forName("US-ASCII");
	
	  @Autowired
	  SecureTokenRepository secureTokenRepository;
	  
	  
	  
	  
	  public SecureToken createSecureToken() {
		  String tokenValue =  new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII); 
		  SecureToken secureToken = new SecureToken();
		  secureToken.setToken(tokenValue);
		  salvarToken(secureToken);
		  return secureToken;
		  
	  }
	  
	  public void salvarToken(SecureToken token) {
		  secureTokenRepository.save(token);
	  }
	
}
