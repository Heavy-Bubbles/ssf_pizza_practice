package tan.chelsea.ssf_pizza_practice.repository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import tan.chelsea.ssf_pizza_practice.model.Order;

@Repository
public class PizzaRepository {

    @Autowired
    @Qualifier("Pizza")
    private RedisTemplate<String, Object> template;

    public void save (Order order){
        template.opsForValue()
            .set(order.getId(), order.toJson().toString());
    }
    
    public Order findById(final String orderId) throws IOException{
        String jsonStringVal = (String) template.opsForValue()
            .get(orderId);
        Order order = Order.createOrderJson(jsonStringVal);
        return order;

    }
}
