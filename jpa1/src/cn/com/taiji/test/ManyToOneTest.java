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
		//提交事务
		transaction.commit();
		//关闭entityManager
		entityManager.close();
		//关闭entityManagerFactory
		entityManagerFactory.close();
	}
	
	//多对一的添加
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
		
		//设置关联关系
		article1.setCustomer(customer);
		article2.setCustomer(customer);
		
		//执行保存操作
		//注：不能先执行article，会报错？？？建议先保存1的一端，后保存n的一端，这样不会多出额外的update语句。
		entityManager.persist(customer);
		entityManager.persist(article1);
		entityManager.persist(article2);
	}
	
	//多对一的获取
	//默认情况下，使用左外连接的方式来获取n的一端的对象和其关联的1的一端的对象。
	@Test
	public void testManyToOneFind() {
		Article article=entityManager.find(Article.class, 5);
		System.out.println(article.getTitle());
		System.out.println(article.getCustomer().getCname());
	}
	
	//多对一删除
	//注：不能直接删除1的一端，因为有外键约束
	@Test
	public void testManyToOneRemove() {
		//删除多的一端
		Article article=entityManager.find(Article.class, 5);
		entityManager.remove(article);
		
		//删除1的一端，不可行，因为有外键约束
//		Customer customer=entityManager.find(Customer.class, 8);
//		entityManager.remove(customer);
	}
	
	//多对一修改,hibernate的核心包版本不能过高，否则不能进行修改操作
	@Test
	public void testManyToOneUpdate() {
		Article article=entityManager.find(Article.class,1);
		article.getCustomer().setAge(25);
	}
	
	
	
	
	
	
	
	
	
}
