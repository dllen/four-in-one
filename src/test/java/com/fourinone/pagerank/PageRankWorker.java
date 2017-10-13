package com.fourinone.pagerank;

import com.fourinone.MigrantWorker;
import com.fourinone.WareHouse;

public class PageRankWorker extends MigrantWorker {

	public String page = null;
	public String[] links = null;

	public PageRankWorker(String page, String[] links) {
		this.page = page;
		this.links = links;
	}

	@Override
	protected WareHouse doTask(WareHouse inhouse) {
		Double pr = (Double) inhouse.getObj(page);
		System.out.println(pr);

		WareHouse wareHouse = new WareHouse();
		for (String link : links) {
			wareHouse.setObj(link, pr / links.length);
		}
		return wareHouse;
	}

	public static void main(String[] args) {
		String[] links = null;
		if (args[2].equals("A"))
			links = new String[] { "B", "C" };
		else if (args[2].equals("B"))
			links = new String[] { "C" };
		else if (args[2].equals("C"))
			links = new String[] { "A" };

		PageRankWorker pageRankWorker = new PageRankWorker(args[2], links);
		pageRankWorker.waitWorking(args[0], Integer.parseInt(args[1]), "pageRankWorker");
	}

}
