package br.com.projeto.pdi.components;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.projeto.pdi.repository.FuncionarioRepository;

@Component
@Transactional
public class PopulacaoInicialEmpresa implements CommandLineRunner {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public void run(String... args) throws Exception {

		
	}

}
