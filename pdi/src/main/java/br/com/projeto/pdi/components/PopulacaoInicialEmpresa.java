package br.com.projeto.pdi.components;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.projeto.pdi.model.Funcionario;
import br.com.projeto.pdi.repository.FuncionarioRepository;

@Component
@Transactional
public class PopulacaoInicialEmpresa implements CommandLineRunner {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public void run(String... args) throws Exception {
		Funcionario funcionario1 = new Funcionario("Bruno");
		funcionario1.setEmail("brunnoaraujoc@gmail.com");
		funcionario1.setCargo("Desenvolvedor back-end");
		Funcionario funcionario2 = new Funcionario("Carlos");
		funcionario2.setEmail("Vianacarlos32@gmail.com");
		funcionario2.setCargo("Analista de Banco de dados");
		
		funcionarioRepository.save(funcionario1);
		funcionarioRepository.save(funcionario2);

	}

}
