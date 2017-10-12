package com.fourinone.cal3;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

public class CtorMul extends Contractor {
	public WareHouse giveTask(WareHouse inhouse) {
		WorkerLocal[] wks = getWaitingWorkers("WorkerMul");
		System.out.println("wks.length:" + wks.length);

		WareHouse[] tasks = new WareHouse[15];
		for (int i = 0; i < 15; i++) {
			tasks[i] = new WareHouse("taskId", i + "");
		}
		WareHouse[] result = doTaskCompete(wks, tasks);
		for (int i = 0; i < result.length; i++) {
			System.out.println(i + ":" + result[i]);
		}

		return inhouse;
	}

	public static void main(String[] args) {
		CtorMul cd = new CtorMul();
		cd.giveTask(null);
		cd.exit();
	}
}