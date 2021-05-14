package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.dto.ResetPasswordDTO;
import br.com.senac.service.PasswordService;

@Controller
@RequestMapping("password")
public class PasswordController {
	
	
	@Autowired
	PasswordService passwordService;
	
	
	@GetMapping("/forgot")
	public ModelAndView esqueciMinhaSenhaPrincipal() {
		ModelAndView mv = new ModelAndView("forgotPassword");
		mv.addObject("forgotPassword", new ResetPasswordDTO());
		return mv;
	}
	
	@PostMapping("/request")
	public ModelAndView resetPassword(ResetPasswordDTO forgotPassword) {
		System.out.println("forgotPassword:" + forgotPassword.getEmail());
		passwordService.esqueciSenha(forgotPassword.getEmail());
		
		ModelAndView mv = new ModelAndView("emailEnviado");
		return mv;
		
	}

}
