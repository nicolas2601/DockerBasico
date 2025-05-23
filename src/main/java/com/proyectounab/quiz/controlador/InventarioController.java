package com.proyectounab.quiz.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyectounab.quiz.entidades.Inventario;
import com.proyectounab.quiz.repositorio.InventarioRepositorio;

@Controller
public class InventarioController {
	
	@Autowired
	private InventarioRepositorio inventarioRepositorio;

	@GetMapping("/")
	public String paginaInicio() {
		return "index";
	}

	@GetMapping("/listainventario")
	public String listaInventario(Model model) {
		List<Inventario> listaInventario = inventarioRepositorio.findAll();
		model.addAttribute("listaInventario", listaInventario);
		return "tabla"; 
	}

	@GetMapping("/formularioinventario")
	public String formularioInventario(Model model) {
		model.addAttribute("inventario", new Inventario());
		return "formulario";
	}

	@PostMapping("/guardarinventario")
	public String guardarInventario(@ModelAttribute Inventario inventario) {
		inventarioRepositorio.save(inventario);
		return "redirect:/listainventario";
	}

	@GetMapping("/inventario/editar/{id}")
	public String modificarInventario(@PathVariable("id") String id, Model model) {
		Optional<Inventario> inventario = inventarioRepositorio.findById(id);
		if (inventario.isPresent()) {
			model.addAttribute("inventario", inventario.get());
			return "formulario";
		} else {
			return "redirect:/listainventario";
		}
	}

	@GetMapping("/inventario/eliminar/{id}")
	public String eliminarInventario(@PathVariable("id") String id) {
		inventarioRepositorio.deleteById(id);
		return "redirect:/listainventario";
	}
	
}
