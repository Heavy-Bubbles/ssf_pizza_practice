package tan.chelsea.ssf_pizza_practice.model;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Pizza implements Serializable{

    @NotBlank(message = "You must select a pizza!")
    private String pizza;

    @NotBlank(message = "You must select a size!")
    private String size;

    @Min(value = 1, message = "You must order at least 1 pizza!")
    @Max(value = 10, message = "You can only order a maximum of 10 pizza")
    private int quantity;

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    

    

}
