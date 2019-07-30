package cn.com.taiji.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import cn.com.taiji.domain.Address;
import cn.com.taiji.domain.People;

public class OneToOneTest {

	public static void addtest(EntityManager entityManager) {
		// 3.��������
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4.���г־û�����
		// 4.1 �������
		// People people1=new People();
		// people1.setName("����");
		// people1.setSex("Ů");
		//
		// Address a1 = new Address();
		// a1.setAddress("�㶫");
		//
		// people1.setAddress(a1);
		//
		// //ִ�б������
		// entityManager.persist(a1);
		// entityManager.persist(people1);

		// 4.2��ѯ����
		// ��ѯ�ķ�ʽ
		// ��ʽһ��
		List<Address> list = entityManager.createQuery("select address from Address").getResultList();
		People people = new People();
		people.setName("����");
		people.setSex("��");
		people.setAddress(list.get(2));
		System.out.println(people.getName());

		// ��ʽ����
		People p = entityManager.find(People.class, 2);
		System.out.println(p.getName());

		// 5.�ύ����
		transaction.commit();
	}

	// ��ʽ����ʹ��ԭ����sql�������ݿ������from����Ҫ��Ӧ����
	public static void searchbyNativeQuery(EntityManager entityManager) {
		// 3.��������
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<People> listPeople = entityManager.createNativeQuery("select * from people").getResultList();
		System.out.println("------listPeople------" + listPeople.size());

		// 5.�ύ����
		transaction.commit();

	}

	// ��ʽһ��ʹ��@NamedQuery(name="address.findAll",query="select a from Address
	// a")��@NamedQuery(name="people.findAll",query="select p from People p")
	// �������ݿ������address.findAllһ��Ҫ��Ӧ������ע�⣬�����sql�����from���Ӧ��������
	public static void searchbyNameQuery(EntityManager entityManager) {
		// 3.��������
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		List<Address> listAddress = entityManager.createNamedQuery("address.findAll").getResultList();
		List<People> listPeople = entityManager.createNamedQuery("people.findAll").getResultList();
		System.out.println("------listAddress-------" + listAddress.size());
		System.out.println("------listPeople-------" + listPeople.size());
		// 4.���г־û�����

		// 5.�ύ����
		transaction.commit();

	}

	public static void addAddress(EntityManager entityManager) {
		// 3.��������
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4.���г־û�����
		// ��ѯ��ʽһ��
		Address address = entityManager.find(Address.class, 4);
		System.out.println("------address-------" + address.getAddress());

		// 5.�ύ����
		transaction.commit();

	}

	public static void addPeople(EntityManager entityManager) {
		// 3.��������
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4.���г־û�����
		// ��ѯ��ʽ����
		List<Address> list = entityManager.createQuery("SELECT a FROM Address a ").getResultList();
		People p = new People();
		p.setName("����");
		p.setSex("��");
		p.setAddress(list.get(0));// 0��Ӧ�����ĵ�һ��
		System.out.println("------list--------" + list.size());

		// 5.�ύ����
		transaction.commit();

	}

	public static void persist(EntityManager entityManager) {
		People people = new People();
		people.setName("����");
		people.setSex("��");

		Address address = new Address();
		address.setAddress("�㶫");

		// ���ù�����ϵ
		people.setAddress(address);

		// ִ�б������,�����ȱ��治ά��������ϵ��һ������û������һ��������������update���
		entityManager.persist(people);
		entityManager.persist(address);

	}

	public static void find(EntityManager entityManager) {
		// ������ȡά��������ϵ��һ��,��ʱ��ͨ���������ӻ�ȡ������Ķ���
		// �粻��ͨ���������ӻ�ȡ��������@OneToOne�н�fecht����ΪLAZY
		// People people = entityManager.find(People.class, 1);
		// System.out.println(people.getName());
		// System.out.println(people.getAddress().getClass().getName());
		//
		// ������ȡ��ά��������ϵ��һ��
		Address address = entityManager.find(Address.class, 1);
		System.out.println(address.getAddress());
		System.err.println(address.getClass().getName());
	}

	public static void remove(EntityManager entityManager) {
		People people = entityManager.find(People.class, 1);
		entityManager.remove(people);

		// ����ͨ����ά����ϵ��һ�������м���ɾ��
		// Address address=entityManager.find(Address.class, 2);
		// entityManager.remove(address);
	}

	public static void update(EntityManager entityManager) {
		People people = entityManager.find(People.class, 2);
		people.getAddress().setAddress("�Ϻ�");
		
	}

}
