import com.tradeteam.entities.Order;
import com.tradeteam.repositories.OrderRepository;
import com.tradeteam.services.OrderService;
import com.tradeteam.services.OrderServiceImpl;
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
        Order order1 = new Order(1, LocalDateTime.now(), 100, 0, 99.01, true, Order.OrderType.BUY, userId);
        Order order2 = new Order(2, LocalDateTime.now(), 100, 0, 99.03, true, Order.OrderType.SELL, userId);
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
