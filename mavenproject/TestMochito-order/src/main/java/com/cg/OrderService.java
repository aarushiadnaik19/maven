package com.cg;

public class OrderService {
	private UserService userService;

	public OrderService(UserService userService) {
		this.userService=userService;
	}
	
	public String placeOrder(int id) {
		if(userService.isUserEligible(id)) {
			return "order placed";
		}
		else
		{
			throw new IllegalArgumentException("user is not eligible to place order");
		}
	}
}
