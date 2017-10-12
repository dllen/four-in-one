package com.fourinone.cal4;

import java.util.Date;

public class CombTest {

	int m = 0, n = 0, total = 0;

	public CombTest(int n, int m) {
		this.n = n;
		this.m = m;
	}

	public void comb(String str) {
		for (int i = 1; i < n + 1; i++) {
			if (str.length() == m - 1) {
				System.out.println(str + i);
				total++;
			} else {
				comb(str + i);
			}
		}
	}

	public static void main(String[] args) {
		CombTest ct = new CombTest(5, 5);
		long start = (new Date()).getTime();
		ct.comb("");
		System.out.println("total:" + ct.total);
		long end = (new Date()).getTime();
		System.out.println("time : " + (end - start) / 1000 + "s");
	}
}
