package tan.chelsea.ssf_pizza_practice.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Delivery implements Serializable {

    @NotBlank(message = "You must enter a name!")
    @Size( min = 3, message = "Your name must be at least 3 characters long!")
    private String name;

    @NotBlank(message = "You must enter an address!")
    private String address;

    @Pattern(regexp = "^[0-9]{8}$", message = "Invalid phone number!")
    private String phone;

    private boolean rush = false;
    private String comments;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    
    public static Delivery createDeliveryJson(JsonObject o){
        Delivery delivery = new Delivery();
        delivery.setName(o.getString("name"));
        delivery.setAddress("address");
        delivery.setPhone(o.getString("phone"));
        delivery.setRush(o.getBoolean("rush"));
        delivery.setComments(o.getString("comments"));
        return delivery;
    }
}
