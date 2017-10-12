package com.fourinone.cal3;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

public class StreamWorkerB extends MigrantWorker {
	public WareHouse doTask(WareHouse inhouse) {
		System.out.println(inhouse);
		// do something
		inhouse.put("msg", inhouse.getString("msg") + ",from StreamWorkerB");
		return inhouse;
	}

	public static void main(String[] args) {
		StreamWorkerB wd = new StreamWorkerB();
		wd.waitWorking(args[0], Integer.parseInt(args[1]), "StreamWorkerB");
	}
}