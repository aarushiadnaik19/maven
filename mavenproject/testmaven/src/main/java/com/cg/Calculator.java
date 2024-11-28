package com.cg;

public class Calculator implements ICalculator {

	@Override
	public int sum(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public int sub(int a, int b) {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public int multiplication(int a, int b) {
		// TODO Auto-generated method stub
		return a*b;
	}

	@Override
	public int division(int a, int b) throws Exception{
		if(b==0) {
			throw new Exception("deno cannot be zero");
		}
		return a/b;
	}

	@Override
	public boolean equalInteger(int a, int b) {
		// TODO Auto-generated method stub
		if(a==b) {
			return true;
		}
		return false;
	}

	
}
