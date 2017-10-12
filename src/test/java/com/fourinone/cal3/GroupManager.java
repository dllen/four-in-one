package com.fourinone.cal3;

import com.fourinone.BeanContext;

public class GroupManager {
	public static void main(String[] args) {

		// <SERVERS>localhost:1888,localhost:1889,localhost:1890</SERVERS>
		// <HEARTBEAT>500</HEARTBEAT>
		// <MAXDELAY>0</MAXDELAY>
		
		String[][] master = new String[][] { { "localhost", "1888" }, { "localhost", "1889" },
				{ "localhost", "1890" } };
		String[][] slave1 = new String[][] { { "localhost", "1889" }, { "localhost", "1888" },
				{ "localhost", "1890" } };
		String[][] slave2 = new String[][] { { "localhost", "1890" }, { "localhost", "1888" },
				{ "localhost", "1889" } };

		String[][] server = null;
		if (args[0].equals("M"))
			server = master;
		else if (args[0].equals("S1"))
			server = slave1;
		else if (args[0].equals("S2"))
			server = slave2;

		BeanContext.startPark(server[0][0], Integer.parseInt(server[0][1]), server);
	}
}