//package com.cg.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.cg.OrderController;
//import com.cg.OrderService;
//
//@ExtendWith(MockitoExtension.class)
//public class OrderControllerTest {
//@Mock
//private OrderService orderService;
//
//@InjectMocks
//private OrderController ordercontroller;
//
//@Test
//void testplacedorder()
//{
//	int id=1;
//	when(orderService.placeOrder(id)).thenReturn("order placed");
//	String result=orderService.placeOrder(id);
//	assertEquals("order placed",result);
//	verify(orderService).placeOrder(id);
//}
//
//@Test
//void testOrderNotPlaced() {
//    int id = 2;
//    
//    // Mock the behavior of orderService to throw an exception
//    when(orderService.placeOrder(id)).thenThrow(new IllegalArgumentException("user is not eligible to place order"));
//    
//    // Assert that calling placeOrder with the given ID throws the expected exception
//    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderService.placeOrder(id));
//    
//    // Assert that the exception message matches the expected message
//    assertEquals("user is not eligible to place order", exception.getMessage());
//    
//    // Verify that placeOrder was called with the correct parameter
//    verify(orderService).placeOrder(id);
//}
//
//}


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

import com.cg.OrderController;
import com.cg.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    // Test for successful order placement
    @Test
    void testPlacedOrder() {
        int id = 1;
        
        // Mock the behavior of orderService for successful order placement
        when(orderService.placeOrder(id)).thenReturn("order placed");
        
        // Call the controller method
        String result = orderController.placeOrder(id);
        
        // Verify the result
        assertEquals("order placed", result);
        
        // Verify that the orderService placeOrder was called once
        verify(orderService).placeOrder(id);
    }

    // Test for when the order cannot be placed (invalid user or eligibility)
    @Test
    void testOrderNotPlaced() {
        int id = 2;
        
        // Mock the behavior of orderService to throw an exception
        when(orderService.placeOrder(id)).thenThrow(new IllegalArgumentException("user is not eligible to place order"));
        
        // Assert that calling placeOrder with the given ID throws the expected exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderController.placeOrder(id));
        
        // Assert that the exception message matches the expected message
        assertEquals("user is not eligible to place order", exception.getMessage());
        
        // Verify that placeOrder was called with the correct parameter
        verify(orderService).placeOrder(id);
    }

    // Test for edge case: Test with invalid input or edge values
    @Test
    void testPlaceOrderWithInvalidId() {
        int invalidId = -1; // Assuming negative ID is invalid
        
        // Mock the behavior of orderService to throw an exception for invalid ID
        when(orderService.placeOrder(invalidId)).thenThrow(new IllegalArgumentException("Invalid order ID"));
        
        // Assert that calling placeOrder with the invalid ID throws the expected exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderController.placeOrder(invalidId));
        
        // Assert that the exception message is correct
        assertEquals("Invalid order ID", exception.getMessage());
        
        // Verify that placeOrder was called with the invalid ID
        verify(orderService).placeOrder(invalidId);
    }

    // Test for a null or non-existent user (assuming orderService handles null cases)
    @Test
    void testPlaceOrderWithNullUser() {
        int id = 0; // Assuming 0 or null ID is invalid
        
        // Mock the behavior of orderService to throw an exception when order ID is invalid
        when(orderService.placeOrder(id)).thenThrow(new IllegalArgumentException("Order ID cannot be zero"));
        
        // Assert that calling placeOrder with ID 0 throws the expected exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> orderController.placeOrder(id));
        
        // Assert the exception message
        assertEquals("Order ID cannot be zero", exception.getMessage());
        
        // Verify that placeOrder was called with ID 0
        verify(orderService).placeOrder(id);
    }
}

