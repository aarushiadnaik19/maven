package com.cg;

//public  class OrderController {
//	private OrderService orderService;
//	public OrderController (UserService userService) {
//		this.orderService=orderService;
//	}
//	public String placeOrder(int id) {
//		return orderService.placeOrder(id);
//	}
//}

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public String placeOrder(int id) {
        return orderService.placeOrder(id);
    }
}

