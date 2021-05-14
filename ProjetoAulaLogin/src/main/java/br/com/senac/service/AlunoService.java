package br.com.senac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.dominio.Aluno;
import br.com.senac.repositorio.AlunoRepositorio;

@Service
public class AlunoService {
	
	@Autowired // injeção de dependências
	private AlunoRepositorio alunoRepo;
	
	public boolean login(Aluno aluno) {
		
		Aluno alunoEncontrado = alunoRepo.findByEmailAndNome(aluno.getEmail(), aluno.getNome());
		
		if(alunoEncontrado == null) 
			return false;
		return true;
	}
}
