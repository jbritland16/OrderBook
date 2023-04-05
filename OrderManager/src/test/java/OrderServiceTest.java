import com.tradeteam.entities.Order;
import com.tradeteam.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderService orderService;

    @Test
    public void testFindByUserId() {
        // Given
        int userId = 1;
        Order order1 = new Order(100, 99.02, "BUY", 1, "AAPL", "NYSE");
        Order order2 = new Order(100, 99.02, "SELL", 1, "AAPL", "NYSE");;
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        when(orderService.findByUserId(userId)).thenReturn(orders);

        // When
        List<Order> result = orderService.findByUserId(userId);

        // Then
        assertNotNull(result);
        assertEquals(result, orders);
    }
}
