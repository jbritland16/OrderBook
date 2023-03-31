//import com.tradeteam.entities.Order;
//import com.tradeteam.repositories.OrderRepository;
//import com.tradeteam.services.OrderServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class OrderTests {
//
//    @Mock
//    private OrderRepository orderRepository;
//
//    @InjectMocks
//    private OrderServiceImpl orderServiceImpl;
//
//    @Test
//    public void findByUserId() {
//        Order order1 = new Order(1, LocalDateTime.now(), 100, 0, 99.01, true, Order.OrderType.BUY, 1);
//        Order order2 = new Order(2, LocalDateTime.now(), 100, 0, 99.03, true, Order.OrderType.SELL, 1);
//
//        List<Order> orders = new ArrayList<>();
//        orders.add(order1);
//        orders.add(order2);
//
//        when(orderRepository.findByUserId(1)).thenReturn(orders);
//
//        List<Order> result = orderServiceImpl.findByUserId(1);
//
//        assertNotNull(result);
//        assertEquals(result, orders);
//    }
//}
