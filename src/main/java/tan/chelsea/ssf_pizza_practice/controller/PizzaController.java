package tan.chelsea.ssf_pizza_practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import tan.chelsea.ssf_pizza_practice.model.Delivery;
import tan.chelsea.ssf_pizza_practice.model.Order;
import tan.chelsea.ssf_pizza_practice.model.Pizza;
import tan.chelsea.ssf_pizza_practice.service.PizzaService;

@Controller
@RequestMapping (path = "/")
public class PizzaController {

    @Autowired 
    private PizzaService pizzaService;

    @GetMapping
    public String getIndex(Model model){
        model.addAttribute("pizza", new Pizza());
        return "index";
    }

    @PostMapping (path = "/pizza", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postPizza(@Valid Pizza pizza, BindingResult bindingResult, Model model, HttpSession session){
    

        if (bindingResult.hasErrors()){
            return "index";
        }

        List<ObjectError> errors = pizzaService.validatePizza(pizza);
        if (!errors.isEmpty()){
            for (ObjectError error : errors){
                bindingResult.addError(error);
                return "index";
            }
        }

        session.setAttribute("pizza", pizza);
        model.addAttribute("delivery", new Delivery());
        return "delivery";
    }

    @PostMapping (path = "/pizza/order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postDelivery(@Valid Delivery delivery, BindingResult bindingResult, Model model, HttpSession session){

        if (bindingResult.hasErrors()){
            return "delivery";
        }

        Pizza pizza = (Pizza) session.getAttribute("pizza");
        Order order = pizzaService.savePizza(pizza, delivery);
        model.addAttribute("order", order);
        return "order";
    }
    
}
