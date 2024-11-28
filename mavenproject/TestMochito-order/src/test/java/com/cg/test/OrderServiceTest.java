package com.cg.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.cg.OrderService;
import com.cg.UserController;
import com.cg.UserService;
 
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
		@Mock
		private UserService userService;
		@InjectMocks
		private OrderService orderService;
		@Test
		void testGetOrderEligible()
		{
			int id=1;
			when(userService.isUserEligible(id)).thenReturn(true);
			String result=orderService.placeOrder(id);
			assertEquals("order placed",result);
			verify(userService).isUserEligible(id);
		}
		@Test
		void testGetOrderNoEligible()
		{
			int id=2;
			when(userService.isUserEligible(id)).thenReturn(false);
			IllegalArgumentException exception=assertThrows(IllegalArgumentException.class,()->
			orderService.placeOrder(id));
			assertEquals("user is not eligible to place order",exception.getMessage());
			verify(userService).isUserEligible(id);
		}
}
