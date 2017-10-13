package com.fourinone.cal5;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

public class PiWorker extends MigrantWorker {

	public double m = 0.0, n = 0.0;

	public PiWorker(double m, double n) {
		this.m = m;
		this.n = n;
	}

	@Override
	protected WareHouse doTask(WareHouse inhouse) {
		double pi = 0.0;
		for (double i = m; i < n; i++) {
			pi += Math.pow(-1, i + 1) / (2 * i - 1);
		}
		System.out.println(4 * pi);
		inhouse.setObj("pi", 4 * pi);
		return inhouse;
	}

	public static void main(String[] args) {
		PiWorker piWorker = new PiWorker(Double.parseDouble(args[2]), Double.parseDouble(args[3]));
		piWorker.waitWorking(args[0], Integer.parseInt(args[1]), "PiWorker");
	}

}
