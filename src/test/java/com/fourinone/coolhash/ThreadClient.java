package com.fourinone.coolhash;

import com.fourinone.BeanContext;
import com.fourinone.CoolHashClient;

public class ThreadClient implements Runnable {
	public CoolHashClient chc;

	public ThreadClient(CoolHashClient chc) {
		this.chc = chc;
	}

	// 多个线程争抢修改threadname, 将自己线程名设置为新value，并将旧的value值输出
	public void putTest() {
		try {
			long begintime = System.currentTimeMillis();
			String threadname = (String) chc.put("threadname", Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getName() + " put time:"
					+ (System.currentTimeMillis() - begintime) + " old:" + threadname);

			// chc.exit();//多线程操作未结束不要关闭客户端
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 写入过程中，同时有多个线程争抢读取threadname并输出
	public void getTest() {
		try {
			long begintime = System.currentTimeMillis();
			String threadname = (String) chc.get("threadname");
			System.out.println(Thread.currentThread().getName() + " get time:"
					+ (System.currentTimeMillis() - begintime) + " get:" + threadname);

			// chc.exit();//多线程操作未结束不要关闭客户端
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void run() {
		putTest();// 线程中写入
		getTest();// 线程中读取
	}

	// 运行命令：java -cp fourinone.jar; ThreadPutGet localhost 2014 1000
	// 为了清晰查看多线程输出结果，可以关闭一些系统输出，将客户端的config.xml里LOG部分的DESC="LOGLEVEL"设置到OFF级别
	public static void main(String[] args) {
		String host = args[0];
		int ip = Integer.parseInt(args[1]);
		int threadcount = Integer.parseInt(args[2]);

		for (int i = 0; i < threadcount; i++) {
			CoolHashClient chc = BeanContext.getCoolHashClient(host, ip);
			ThreadClient tc = new ThreadClient(chc);
			new Thread(tc).start();
		}
	}
}