package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.entity.Kh_pizza;
import com.kh.repository.PizzaRepository;
import com.kh.service.PizzaService;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@PostMapping
	public Kh_pizza savePizza(@RequestBody Kh_pizza pizza) {
		return pizzaService.createPizza(pizza);
	}
	
	@GetMapping
	public List<Kh_pizza> getAllPizza() {
		return pizzaService.getAllPizza();
	}
	
	@GetMapping("/search")
	public List<Kh_pizza> searchPizza(@RequestParam("query") String query) {
		return pizzaService.searchPizza(query);
	}
	
}
