package com.fourinone.coolhash;
import com.fourinone.BeanContext;
import com.fourinone.CoolHashClient;
import com.fourinone.CoolHashMap;
import com.fourinone.CoolHashResult;
import com.fourinone.Filter;
import com.fourinone.Result;
import com.fourinone.Filter.ValueFilter;

public class AsynClient{
	public static void backupTest(){
		//启动两个CoolHash Server，并建立两个server的客户端；如果在同台机器启动两个CoolHash Server请设置不同端口号
		CoolHashClient c1 = BeanContext.getCoolHashClient("192.168.0.1",2014);
		CoolHashClient c2 = BeanContext.getCoolHashClient("192.168.0.2",2014);
		try{
			//同步串行写入key0到两个server
			c2.put("key0","value0");
			System.out.format("server2 get key0: %s\n", c2.get("key0"));
			c1.put("key0","value0");
			System.out.format("server1 get key0: %s\n", c1.get("key0"));
			
			//异步写入到server2，不等返回马上写入server1
			Result rs = c2.operateAsyn("put", new Class[]{String.class, Object.class}, new Object[]{"key1", "value1"});
			System.out.format("server2 put asyn...\n");//c2.get("key1")
			c1.put("key1","value1");
			System.out.format("server1 get key1: %s\n", c1.get("key1"));
			//堵塞等待异步操作结束，也可以不等待忽略写入成功
			Object obj = rs.getResultWait();
			System.out.format("server2 put ok and get key1: %s\n", obj);
			
			CoolHashMap hm=new CoolHashMap();
			for(long i=0;i<1000;i++){
				hm.put("key"+i, "value"+i);
			}
			//异步批量写入数据到server2，不等返回
			Result rsmap = c2.operateAsyn("put", new Class[]{CoolHashMap.class}, new Object[]{hm});
			System.out.format("server2 put %s keys.\n", rsmap.getResult());
			//批量写入到server1
			int count = c1.put(hm);
			System.out.format("server1 put %s keys.\n", count);
			//堵塞等待异步操作写入成功，由于是并行操作，异步备份等待完成即能确保成功又能比串行备份性能更快
			Object objmap = rsmap.getResultWait();
			System.out.format("server2 put ok %s keys.\n", objmap);
		
		}catch(Exception ex){
			System.out.println(ex);
			
		}
		c1.exit();//如果异步不等待结束，在写入未完成退出会丢失数据，这时可以不关闭客户端
		c2.exit();
	}
	
	public static void findTest(){
		//建立两个server的客户端
		CoolHashClient c1 = BeanContext.getCoolHashClient("192.168.0.1",2014);
		CoolHashClient c2 = BeanContext.getCoolHashClient("192.168.0.2",2014);
		try{
			Result[] rs = new Result[2];
			//异步方式同时查询两个server，server1查询value包含8的数据，server2查询value包含6的数据
			rs[0] = c1.operateAsyn("find", new Class[]{String.class, Filter.class}, new Object[]{"*", ValueFilter.contains("8")});
			rs[1] = c2.operateAsyn("find", new Class[]{String.class, Filter.class}, new Object[]{"*", ValueFilter.contains("6")});
			//等待两个server查询全部结束
			Object[] rsobj = Result.getResultWait(rs);
			CoolHashMap chmb1 = ((CoolHashResult)rsobj[0]).nextBatch(1000);
			CoolHashMap chmb2 = ((CoolHashResult)rsobj[1]).nextBatch(1000);
			//将两个server的查询结果求并集，得到即含有8又含有6的数据
			CoolHashMap andmap = chmb1.and(chmb2);
			System.out.println("and:"+andmap);
		}catch(Exception ex){
			System.out.println(ex);
		}
		c1.exit();
		c2.exit();
	}
	
	public static void main(String[] args){
		backupTest();//异步写入备份
		findTest();//异步查询合并
	}
}