package com.PFC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

		@GetMapping("/")
	    public String home() {
	        return "sesionRegistro/index";
	    }
	}

