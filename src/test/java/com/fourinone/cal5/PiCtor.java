package com.fourinone.cal5;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

public class PiCtor extends Contractor {

	@Override
	public WareHouse giveTask(WareHouse inhouse) {
		WorkerLocal[] wks = getWaitingWorkers("PiWorker");
		System.out.println("wks.length:" + wks.length);
		WareHouse[] hmarr = doTaskBatch(wks, inhouse);
		double pi = 0.0;
		for (WareHouse result : hmarr) {
			pi = pi + (Double) result.getObj("pi");
		}
		System.out.println("Pi:" + pi);
		return inhouse;
	}

}
