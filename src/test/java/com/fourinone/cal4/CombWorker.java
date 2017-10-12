package com.fourinone.cal4;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

public class CombWorker extends MigrantWorker {

	private int m = 0, n = 0, total = 0, index = -1;

	@Override
	protected WareHouse doTask(WareHouse inhouse) {
		total = 0;
		n = (Integer) inhouse.getObj("wknum");
		m = (Integer) inhouse.getObj("comb");
		index = getSelfIndex() + 1;
		System.out.println("index:" + index);
		comb(index + "");
		return new WareHouse("total", total);
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
}
