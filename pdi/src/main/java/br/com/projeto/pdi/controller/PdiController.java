package br.com.projeto.pdi.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.pdi.model.Funcionario;
import br.com.projeto.pdi.model.Pdi;
import br.com.projeto.pdi.repository.FuncionarioRepository;
import br.com.projeto.pdi.repository.PdiRepository;

@Controller
@RequestMapping("/pdis")
public class PdiController {

	@Autowired
	private PdiRepository pdiRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping("/novo")
	public ModelAndView novoPdi(@ModelAttribute("pdi") Pdi pdi) {
		ModelAndView mv = new ModelAndView("pdi/cadastroPdi");
		mv.addObject("funcionarios", this.funcionarioRepository.findAll());

		return mv;
	}

	@PostMapping("/salvar")
	public String salvarPdi(@ModelAttribute("pdi") Pdi pdi) {
		pdi.setDataCriacao(LocalDate.now());
		pdiRepository.save(pdi);
		return "redirect:/funcionarios/lista";
	}

	@GetMapping("/lista")
	public String listarPdis(Model model) {
		model.addAttribute("listaPdis", pdiRepository.findAll());
		return "pdi/listaPdi";
	}

	@GetMapping("/lista/{idFuncionario}")
	public ModelAndView listarPdisPorFuncionario(@PathVariable("idFuncionario") Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("pdi/listaPdi");

		mv.addObject("listaPdis", pdiRepository.findByFuncionario(funcionario));

		return mv;
	}

	@GetMapping("/excluirPdi/{idPdi}")
	public String excluir(@PathVariable("idPdi") long id) {
		Optional<Pdi> pdiOptional = pdiRepository.findById(id);
		if (pdiOptional.isEmpty()) {
			throw new IllegalArgumentException("Funcionario invalido");
		}

		pdiRepository.delete(pdiOptional.get());
		return "redirect:/funcionarios/lista";
	}

}