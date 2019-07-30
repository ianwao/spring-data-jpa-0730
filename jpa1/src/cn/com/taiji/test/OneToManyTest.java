package cn.com.taiji.test;

import javax.persistence.EntityManager;

import cn.com.taiji.domain.Father;
import cn.com.taiji.domain.Son;

public class OneToManyTest {

	public static void add(EntityManager entityManager) {
		

	}

	public static void select(EntityManager entityManager) {
		// TODO Auto-generated method stub

	}

	public static void update(EntityManager entityManager) {
		int rs =entityManager.createQuery("update Son s set s.sname = ?1 where sid=?2")
				.setParameter("1","小华")
				.setParameter("2",1).executeUpdate();
		 System.out.println("更新:"+rs);

	}

	public static void delete(EntityManager entityManager) {
		String name="小华";
		int rs = entityManager
				.createQuery("delete from Son s where s.sname = ?1")
				.setParameter("1",name).executeUpdate();
		System.out.println("删除了"+rs);

	}

	public static void persist(EntityManager entityManager) {
		Father father = new Father();
		father.setFname("李四");
		father.setAge(45);

		Son son1 = new Son();
		son1.setSname("李丽");
		son1.setSage(22);
		Son son2 = new Son();
		son2.setSname("李莉");
		son2.setSage(14);

		// 建立关联关系
		father.getSon().add(son1);
		father.getSon().add(son2);

		// 执行保存操作
		// 单项一对多关联关系时，一定会多出update语句，
		// 因为n的一端再插入式不会同时插入外键列
		// 所以先保存哪一端都可以，差别不大
		entityManager.persist(father);
		entityManager.persist(son1);
		entityManager.persist(son2);

	}

	// 默认使用了懒加载，也可以使用@OneToMany的fetch属性来修改默认的加载策略
	public static void find(EntityManager entityManager) {
		Father father = entityManager.find(Father.class, 1);
		System.out.println("father:" + father.getFname());
		System.out.println("son:" + father.getSon().size());

	}

	public static void remove(EntityManager entityManager) {
		//直接删除一的一方，可以删除成功，一的一方数据被删除，多的一方外键变为null
		//想要再删除一的一方的同时也删除多的一方，需要修改@OneToMany的cascade属性来修改默认的删除策略
		Father father = entityManager.find(Father.class, 3);
		entityManager.remove(father);
	}

	public static void update1(EntityManager entityManager) {
		Father father = entityManager.find(Father.class,4);
		father.getSon().iterator().next().setSage(27);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
