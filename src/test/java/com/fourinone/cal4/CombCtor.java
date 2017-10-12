package com.fourinone.cal4;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

public class CombCtor extends Contractor {

	@Override
	public WareHouse giveTask(WareHouse inhouse) {
		WorkerLocal[] wks = getWaitingWorkers("CombWorker");
		System.out.println("wks.length:" + wks.length + ";" + inhouse);
		inhouse.setObj("wknum", wks.length);
		WareHouse[] hmArr = doTaskBatch(wks, inhouse);
		int total = 0;
		for (WareHouse hm : hmArr) {
			total += (Integer) hm.getObj("total");
			System.out.println("total:" + total);
			return inhouse;
		}
		return null;
	}

	public static void main(String[] args) {
		CombCtor combCtor = new CombCtor();
		WareHouse wh = new WareHouse("comb", 5);
		combCtor.doProject(wh);
		combCtor.exit();
	}

}
