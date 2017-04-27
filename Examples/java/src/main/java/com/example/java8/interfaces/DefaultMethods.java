package com.example.java8.interfaces;
/**
 * Default method - Helps extend the functionality of already released APIs without breaking existing code.
 * Implementing classes dont have to implement the default method to adapt their software.
 *  
	Different ways of using interface default method (extension methods): 
		1. Invoke default method from implementing class
		2. Override default method and also invoke interface default method from this new method.
		3. How to handle when 2 interfaces have same default method.
	
**/
interface Formula{
	double calculate(int a);
	
	//Default methods in interfaces are also known as Extension Methods.
	default double sqrt(int a){
		return Math.sqrt(a);
	}
}

//Second interface with same method signature
interface Formula2{
	default double sqrt(int a){
		return Math.sqrt(a);
	}
}

public class DefaultMethods{
	
	static void invokeDefaultMethod(){
		Formula f = new Formula(){
			@Override
			public double calculate(int a){
				return sqrt(a * 100);
			}
		};
		System.out.println("invokeDefaultMethod() f.calculate = "+f.calculate(100));
		System.out.println("invokeDefaultMethod() f.sqrt = "+f.sqrt(16));
	}
	
	public static void main(String[] args){
		invokeDefaultMethod();
		overrideAndInvokeDefaultMethod();
		multipleInterfaceImplementor();
	}
	
	static void overrideAndInvokeDefaultMethod(){
		Formula f2 = new Formula(){
			@Override
			public double calculate(int a){
				return Formula.super.sqrt(a * 100);
			}
			
			@Override
			public double sqrt(int a){
				System.out.println("f2: I am overridden sqrt in Java8.");
				return Formula.super.sqrt(a);
			}
		};
		
		System.out.println("overrideAndInvokeDefaultMethod() f2.calculate = "+f2.calculate(100));
		System.out.println("overrideAndInvokeDefaultMethod() f2.sqrt = "+f2.sqrt(16));
	}
	
	static void multipleInterfaceImplementor(){
		MultipleInterfaceImplementor test = new MultipleInterfaceImplementor();
		test.sqrt(100);
	}
}
//class implementing multiple interfaces which have same default method.
class MultipleInterfaceImplementor implements Formula, Formula2{
	public double calculate(int a){
		return 0;
	}
	
	public double sqrt(int a){
		System.out.println("MultipleInterfaceImplementor.sqrt");
		return Math.sqrt(a);
	}
}