package com.fourinone.cal5;

public class PITest {

	public static void main(String[] args) {
		double pi = 0.0;
		for (double i = 1.0; i < 100000001d; i++) {
			pi += Math.pow(-1, i + 1) / (2 * i - 1);
		}
		System.out.println(4 * pi);
	}

}
