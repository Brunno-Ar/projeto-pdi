package br.com.projeto.pdi.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.projeto.pdi.model.Funcionario;
import br.com.projeto.pdi.repository.FuncionarioRepository;

@Controller
public class FuncionarioController {

	private FuncionarioRepository funcionarioRepository;

	public FuncionarioController(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	@GetMapping("/funcionario/lista")
	public String Funcionarios(Model model) {
		model.addAttribute("listaFuncionarios", funcionarioRepository.findAll());
		return "funcionario/listaFuncionario";
	}

	@GetMapping("/funcionario/{id}")
	public String alterarFuncionario(@PathVariable("id") long id, Model model) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
		if(funcionarioOptional.isEmpty()) {
			throw new IllegalArgumentException("Funcionario invalido");
		}
		
		model.addAttribute("funcionario", funcionarioOptional.get());
		
		return "funcionario/cadastroFuncionarios";
	}
	
	@GetMapping("/funcionario/excluir/{id}")
	public String excluirFuncionario(@PathVariable("id") long id) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
		if(funcionarioOptional.isEmpty()) {
			throw new IllegalArgumentException("Funcionario invalido");
		}
		
		funcionarioRepository.delete(funcionarioOptional.get());
		return "redirect:/funcionario/lista";
	}
	

	@GetMapping("/funcionario/novo")
	public String novoFuncionario(@ModelAttribute("funcionario") Funcionario funcionario) {
		return "funcionario/cadastroFuncionarios";
	}

	@PostMapping("/funcionario/salvar")
	public String salvarFuncionario(@ModelAttribute("funcionario") Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
		return "redirect:/funcionario/lista";
	}
}
