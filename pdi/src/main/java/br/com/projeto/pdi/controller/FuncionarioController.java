package br.com.projeto.pdi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.pdi.model.Funcionario;
import br.com.projeto.pdi.model.Pdi;
import br.com.projeto.pdi.repository.FuncionarioRepository;
import br.com.projeto.pdi.repository.PdiRepository;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private PdiRepository pdiRepository;

	@GetMapping("/lista")
	public String listarFuncionarios(Model model) {
		model.addAttribute("listaFuncionarios", funcionarioRepository.findAll());
		return "funcionario/listaFuncionario";
	}

	@GetMapping("/novo")
	public String novoFuncionario(@ModelAttribute("funcionario") Funcionario funcionario) {
		return "funcionario/cadastroFuncionarios";
	}

	@PostMapping("/salvar")
	public String salvarFuncionario(@ModelAttribute("funcionario") Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
		return "redirect:/funcionarios/lista";
	}

	@GetMapping("/{id}")
	public String alterarFuncionario(@PathVariable("id") long id, Model model) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
		if (funcionarioOptional.isEmpty()) {
			throw new IllegalArgumentException("Funcionario invalido");
		}

		model.addAttribute("funcionario", funcionarioOptional.get());

		return "funcionario/cadastroFuncionarios";
	}

	@GetMapping("/excluir/{id}")
	public String excluirFuncionario(@PathVariable("id") long id) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
		if (funcionarioOptional.isEmpty()) {
			throw new IllegalArgumentException("Funcionario invalido");
		}

		List<Pdi> pdis = funcionarioOptional.get().getPdis();
		
		if(!pdis.isEmpty()) {
			pdiRepository.deleteAll(pdis);
		}
		
		funcionarioOptional = funcionarioRepository.findById(id);
		funcionarioRepository.delete(funcionarioOptional.get());
		
		return "redirect:/funcionarios/lista";
	}

}
