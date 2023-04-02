
import com.tradeteam.entities.Order;
import org.junit.jupiter.api.Test;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class OrderTest {
    @Test
    public void getStatusActive(){
        Order order = new Order(100, 99.01, "BUY", 1, "AAPL", 1);

        assertEquals("Active", order.getStatus());
    }

    @Test
    public void getStatusInactive(){
        Order order = new Order(100, 99.01, "BUY", 1, "AAPL", 1);
        order.setOrderActive(false);

        assertEquals("Inactive", order.getStatus());
    }
}
