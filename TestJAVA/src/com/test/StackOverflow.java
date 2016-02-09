package com.test;

public class StackOverflow {

	public static void main(String[] args) {
		C c1 = new C();
	}
}

class A {
	A() {
		new B();
		System.out.println("A");
	}
}

class B {
	B() {
		new C();
		System.out.println("B");
	}
}

class C {
	C() {
		new A();
		System.out.println("");
	}
}