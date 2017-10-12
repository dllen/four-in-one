package com.fourinone.cal3;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

public class StreamWorkerA extends MigrantWorker {
	public StreamCtorB sc;

	public WareHouse doTask(WareHouse inhouse) {
		System.out.println(inhouse);
		// do something
		if (sc == null)
			sc = new StreamCtorB();
		WareHouse msg = new WareHouse();
		msg.put("msg", inhouse.getString("msg") + ",from StreamWorkerA");
		WareHouse wh = sc.giveTask(msg);
		// sc.exit();
		return wh;
	}

	public static void main(String[] args) {
		StreamWorkerA wd = new StreamWorkerA();
		wd.waitWorking(args[0], Integer.parseInt(args[1]), "StreamWorkerA");
	}
}