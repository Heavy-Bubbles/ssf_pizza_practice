package tan.chelsea.ssf_pizza_practice.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import tan.chelsea.ssf_pizza_practice.model.Delivery;
import tan.chelsea.ssf_pizza_practice.model.Order;
import tan.chelsea.ssf_pizza_practice.model.Pizza;
import tan.chelsea.ssf_pizza_practice.repository.PizzaRepository;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public static final String[] PIZZA_NAMES = {
        "bella",
        "margherita",
        "marinara",
        "spianatacalabrese",
        "trioformaggio"
    };

    public static final String[] PIZZA_SIZES = {
        "sm",
        "md",
        "lg"
    };

    private final Set<String> pizzaNames;
    private final Set<String> pizzaSizes;

    public PizzaService(){
        pizzaNames = new HashSet<String>(Arrays.asList(PIZZA_NAMES));
        pizzaSizes = new HashSet<String>(Arrays.asList(PIZZA_SIZES));
    }

    public List<ObjectError> validatePizza(Pizza pizza){
        List<ObjectError> errors = new LinkedList<>();
        FieldError error;
        if (!pizzaNames.contains(pizza.getPizza().toLowerCase())){
            error = new FieldError("pizza", "pizza", "We do not have %s pizza".formatted(pizza.getPizza()));
            errors.add(error);
        }

        if (!pizzaSizes.contains(pizza.getSize().toLowerCase())){
            error = new FieldError("pizza", "size", "We do not have %s size".formatted(pizza.getSize()));
            errors.add(error);
        }
        
        return errors;
    }

    private String generateID(){
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private Order createOrder(Pizza pizza, Delivery delivery){
        Order order = new Order(pizza, delivery);
        order.setId(generateID());
        return order;
    }

    private double calculateTotalCost(Order order){
        double total = 0;
        switch(order.getPizza().getPizza()){
            case "margherita" : total += 22;
            break;
            case "trioformaggio" : total += 25;
            break;
            case "bella", "marinara", "spianatacalabrese" : total += 30;
            break;
        }

        switch (order.getPizza().getSize()){
            case "sm" : total *= 1;
            break;
            case "md" : total *= 1.2;
            break;
            case "lg" : total *= 1.5;
            break;
        }

        total *= order.getPizza().getQuantity();
        if (order.getDelivery().isRush()){
            total += 2;
        }
        return total;
    }

    public Order savePizza(Pizza pizza, Delivery delivery){
        Order order = createOrder(pizza, delivery);
        order.setTotalCost(calculateTotalCost(order));
        pizzaRepository.save(order);
        return order;
    }
    
}
