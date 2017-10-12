package com.fourinone.stop;

import java.util.Random;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

public class CancelWorker extends MigrantWorker {
	public WareHouse doTask(WareHouse inhouse) {
		int n = 0;
		Random rd = new Random();
		while (!isInterrupted()) {
			n = rd.nextInt(100000);
			System.out.println(n);
			if (n == 888)
				break;
		}
		return new WareHouse("result", n);
	}

	public static void main(String[] args) {
		CancelWorker mw = new CancelWorker();
		mw.waitWorking("localhost", Integer.parseInt(args[0]), "cancelworker");
	}
}