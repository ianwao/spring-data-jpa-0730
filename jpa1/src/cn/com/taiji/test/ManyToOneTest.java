package cn.com.taiji.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.com.taiji.domain.Article;
import cn.com.taiji.domain.Customer;

public class ManyToOneTest {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;
	
	@Before
	public void init() {
		entityManagerFactory=Persistence.createEntityManagerFactory("jpa1");
		entityManager=entityManagerFactory.createEntityManager();
		transaction=entityManager.getTransaction();
		transaction.begin();
	}
	
	@After
	public void destroy() {
		//�ύ����
		transaction.commit();
		//�ر�entityManager
		entityManager.close();
		//�ر�entityManagerFactory
		entityManagerFactory.close();
	}
	
	//���һ�����
	@Test
	public void testManyToOnePersist() {
		Customer customer=new Customer();
		customer.setAge(22);
		customer.setCname("hunghung");
		customer.setEmail("qq@163.com");
		
		Article article1=new Article();
		article1.setTitle("pig2");
		article1.setContent("qqqq");
		article1.setBirth(new Date());
		article1.setCreatedTime(new Date());
		
		Article article2=new Article();
		article2.setTitle("dag2");
		article2.setContent("qqqq");
		article2.setBirth(new Date());
		article2.setCreatedTime(new Date());
		
		//���ù�����ϵ
		article1.setCustomer(customer);
		article2.setCustomer(customer);
		
		//ִ�б������
		//ע��������ִ��article���ᱨ�����������ȱ���1��һ�ˣ��󱣴�n��һ�ˣ����������������update��䡣
		entityManager.persist(customer);
		entityManager.persist(article1);
		entityManager.persist(article2);
	}
	
	//���һ�Ļ�ȡ
	//Ĭ������£�ʹ���������ӵķ�ʽ����ȡn��һ�˵Ķ�����������1��һ�˵Ķ���
	@Test
	public void testManyToOneFind() {
		Article article=entityManager.find(Article.class, 5);
		System.out.println(article.getTitle());
		System.out.println(article.getCustomer().getCname());
	}
	
	//���һɾ��
	//ע������ֱ��ɾ��1��һ�ˣ���Ϊ�����Լ��
	@Test
	public void testManyToOneRemove() {
		//ɾ�����һ��
		Article article=entityManager.find(Article.class, 5);
		entityManager.remove(article);
		
		//ɾ��1��һ�ˣ������У���Ϊ�����Լ��
//		Customer customer=entityManager.find(Customer.class, 8);
//		entityManager.remove(customer);
	}
	
	//���һ�޸�,hibernate�ĺ��İ��汾���ܹ��ߣ������ܽ����޸Ĳ���
	@Test
	public void testManyToOneUpdate() {
		Article article=entityManager.find(Article.class,1);
		article.getCustomer().setAge(25);
	}
	
	
	
	
	
	
	
	
	
}
