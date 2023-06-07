package tan.chelsea.ssf_pizza_practice.model;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class Pizza implements Serializable{

    @NotBlank(message = "You must select a pizza!")
    private String pizza;

    @NotBlank(message = "You must select a size!")
    private String size;

    private int quantity;
}
