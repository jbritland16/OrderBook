
import com.tradeteam.entities.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderTest {
    @Test
    public void getStatusActive(){
        Order order = new Order(100, 99.01, "BUY", 1, "AAPL", "NYSE");

        assertEquals("Active", order.getStatus());
    }

    @Test
    public void getStatusInactive(){
        Order order = new Order(100, 99.01, "BUY", 1, "AAPL", "NYSE");
        order.setOrderActive(false);

        assertEquals("Inactive", order.getStatus());
    }
}
