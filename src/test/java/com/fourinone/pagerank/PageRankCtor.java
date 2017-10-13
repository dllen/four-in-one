package com.fourinone.pagerank;

import java.util.Iterator;

import com.fourinone.Contractor;
import com.fourinone.WareHouse;
import com.fourinone.WorkerLocal;

public class PageRankCtor extends Contractor {

	@Override
	public WareHouse giveTask(WareHouse inhouse) {
		WorkerLocal[] wks = getWaitingWorkers("PageRankWorker");
		System.out.println("wks.length:" + wks.length);

		for (int i = 0; i < 500; i++) {
			WareHouse[] hmarr = doTaskBatch(wks, inhouse);
			WareHouse result = new WareHouse();
			for (WareHouse item : hmarr) {
				Iterator<String> iterator = item.keySet().iterator();
				for (iterator.hasNext();;) {
					String page = iterator.next();
					Double pagePr = (Double) item.getObj(page);
					if (result.containsKey(page))
						pagePr = pagePr + (Double) result.getObj(page);
					result.setObj(page, pagePr);
				}
			}
			inhouse = result;
			System.out.println("No." + i + ":" + inhouse);
		}
		return inhouse;
	}

	public static void main(String[] args) {
		PageRankCtor a = new PageRankCtor();
		WareHouse inhouse = new WareHouse();
		inhouse.setObj("A", 1.00d);
		inhouse.setObj("B", 1.00d);
		inhouse.setObj("C", 1.00d);
		a.giveTask(inhouse);
		a.exit();
	}

}
