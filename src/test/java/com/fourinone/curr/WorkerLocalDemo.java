package com.fourinone.curr;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

public class WorkerLocalDemo extends MigrantWorker {
	public String name;

	public WorkerLocalDemo(String name) {
		this.name = name;
	}

	public WareHouse doTask(WareHouse inhouse) {
		System.out.println(name + ":" + inhouse);
		inhouse.put("task", inhouse.get("task") + " done.");
		return inhouse;
	}
}