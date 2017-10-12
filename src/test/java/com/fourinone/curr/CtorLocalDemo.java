package com.fourinone.curr;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

public class CtorLocalDemo extends Contractor {
	public WareHouse giveTask(WareHouse inhouse) {
		WorkerLocal[] wks = getLocalWorkers(3);
		for (int j = 0; j < wks.length; j++)
			wks[j].setWorker(new WorkerLocalDemo("worker" + j));

		WareHouse[] tasks = new WareHouse[20];
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = new WareHouse("task", i + "");
		}

		WareHouse[] result = doTaskCompete(wks, tasks);
		System.out.println("result:");
		for (WareHouse r : result)
			System.out.println(r);

		return inhouse;
	}

	public static void main(String[] args) {
		CtorLocalDemo cd = new CtorLocalDemo();
		cd.giveTask(null);
		cd.exit();
	}
}