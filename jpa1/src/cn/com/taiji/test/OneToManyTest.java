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
				.setParameter("1","С��")
				.setParameter("2",1).executeUpdate();
		 System.out.println("����:"+rs);

	}

	public static void delete(EntityManager entityManager) {
		String name="С��";
		int rs = entityManager
				.createQuery("delete from Son s where s.sname = ?1")
				.setParameter("1",name).executeUpdate();
		System.out.println("ɾ����"+rs);

	}

	public static void persist(EntityManager entityManager) {
		Father father = new Father();
		father.setFname("����");
		father.setAge(45);

		Son son1 = new Son();
		son1.setSname("����");
		son1.setSage(22);
		Son son2 = new Son();
		son2.setSname("����");
		son2.setSage(14);

		// ����������ϵ
		father.getSon().add(son1);
		father.getSon().add(son2);

		// ִ�б������
		// ����һ�Զ������ϵʱ��һ������update��䣬
		// ��Ϊn��һ���ٲ���ʽ����ͬʱ���������
		// �����ȱ�����һ�˶����ԣ���𲻴�
		entityManager.persist(father);
		entityManager.persist(son1);
		entityManager.persist(son2);

	}

	// Ĭ��ʹ���������أ�Ҳ����ʹ��@OneToMany��fetch�������޸�Ĭ�ϵļ��ز���
	public static void find(EntityManager entityManager) {
		Father father = entityManager.find(Father.class, 1);
		System.out.println("father:" + father.getFname());
		System.out.println("son:" + father.getSon().size());

	}

	public static void remove(EntityManager entityManager) {
		//ֱ��ɾ��һ��һ��������ɾ���ɹ���һ��һ�����ݱ�ɾ�������һ�������Ϊnull
		//��Ҫ��ɾ��һ��һ����ͬʱҲɾ�����һ������Ҫ�޸�@OneToMany��cascade�������޸�Ĭ�ϵ�ɾ������
		Father father = entityManager.find(Father.class, 3);
		entityManager.remove(father);
	}

	public static void update1(EntityManager entityManager) {
		Father father = entityManager.find(Father.class,4);
		father.getSon().iterator().next().setSage(27);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
