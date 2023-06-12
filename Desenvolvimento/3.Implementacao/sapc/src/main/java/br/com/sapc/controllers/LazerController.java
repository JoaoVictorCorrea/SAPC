package br.com.sapc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lazer")
public class LazerController {

	@GetMapping
	public String reserva() {
		return "lazer/lazer";
	}
}
