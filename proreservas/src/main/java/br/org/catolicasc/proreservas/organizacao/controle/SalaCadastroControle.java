package br.org.catolicasc.proreservas.organizacao.controle;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.org.catolicasc.proreservas.organizacao.dominio.Sala;
import br.org.catolicasc.proreservas.organizacao.dominio.SalaCadastro;
import br.org.catolicasc.proreservas.organizacao.dominio.SalaCadastroRepositorio;

@Controller
public class SalaCadastroControle {

private SalaCadastroRepositorio salaCadastroRepo;
	
	public SalaCadastroControle(SalaCadastroRepositorio salaRepo) {
		this.salaCadastroRepo = salaRepo;
	}
	
	@GetMapping("/organizacao/salas")
	public String salas(Model model) {
		
		model.addAttribute("listaSalas", salaCadastroRepo.findAll());
		return "organizacao/salas/index";
		
	}
	
	@GetMapping("/organizacao/salas/form")
	public String novaSala(@ModelAttribute("sala") Sala sala) {
		
		return "organizacao/salas/form";
	}
	
	@PostMapping("/organizacao/salas/salvar")
	public String salvarSala(@ModelAttribute("sala") SalaCadastro sala) {
		salaCadastroRepo.save(sala);
		return "redirect:/organizacao/salas";
	}
	
	@GetMapping("/organizacao/salas/{id}")
	public String alterarSala(@PathVariable("id") long id, Model model) {
		
		Optional<SalaCadastro> salaOpt = salaCadastroRepo.findById(id);
		if(salaOpt.isEmpty()) {
			throw new IllegalArgumentException("Sala Inválida!");
		}
		model.addAttribute("sala", salaOpt.get());
		return "organizacao/salas/form";
	}
	
	@GetMapping("/organizacao/salas/excluir/{id}")
	public String excluirSala(@PathVariable("id") long id, Model model) {
		
		Optional<SalaCadastro> salaOpt = salaCadastroRepo.findById(id);
		if(salaOpt.isEmpty()) {
			throw new IllegalArgumentException("Sala Inválida!");
		}
		
		salaCadastroRepo.delete(salaOpt.get());
		return "redirect:/organizacao/salas";
		
	}
	
	
}
