package tan.chelsea.ssf_pizza_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tan.chelsea.ssf_pizza_practice.model.Pizza;

@Controller
@RequestMapping (path = "/")
public class PizzaController {

@GetMapping
public String getIndex(Model model){
    model.addAttribute("pizza", new Pizza());
    return "index";
}
    
}
