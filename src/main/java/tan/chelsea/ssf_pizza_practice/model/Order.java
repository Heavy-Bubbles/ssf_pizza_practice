package tan.chelsea.ssf_pizza_practice.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;


public class Order implements Serializable {

    private String id;
    private Pizza pizza;
    private Delivery delivery;
    private double totalCost;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Pizza getPizza() {
        return pizza;
    }
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Order(Pizza pizza, Delivery delivery) {
        this.pizza = pizza;
        this.delivery = delivery;
    }

    public String getName(){
        return this.getDelivery().getName();
    }

    public String getAddress(){
        return this.getDelivery().getAddress();
    }

    public String getPhone(){
        return this.getDelivery().getPhone();
    }

    public boolean getRush(){
        return this.getDelivery().isRush();
    }

    public String getComments(){
        return this.getDelivery().getComments();
    }

    public String getPizzaName(){
        return this.getPizza().getPizza();
    }

    public String getPizzaSize(){
        return this.getPizza().getSize();
    }

    public int getPizzaQuantity(){
        return this.getPizza().getQuantity();
    }

    public double getPizzaCost(){
        if (getRush()){
            return this.getTotalCost() -2;
        }

        return this.getTotalCost();
    }
    
    public JsonObject toJson(){
        return Json.createObjectBuilder()
            .add("orderId", this.getId())
            .add("name", this.getName())
            .add("address", this.getAddress())
            .add("phone", this.getPhone())
            .add("rush", this.getRush())
            .add("comments", this.getComments())
            .add("pizza", this.getPizzaName())
            .add("size", this.getPizzaSize())
            .add("quantity", this.getPizzaQuantity())
            .add("total", this.getTotalCost())
            .build();
    }
}
