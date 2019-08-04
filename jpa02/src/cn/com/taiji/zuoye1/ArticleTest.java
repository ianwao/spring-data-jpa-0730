package cn.com.taiji.zuoye1;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;



        //多对一
public class ArticleTest {
	
	

	public static void main(String[] args) {
		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaaa");

		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();
		//3.开启事务
		EntityTransaction entityTransaction= entityManager.getTransaction();
		entityTransaction.begin();
		
		//4.进行持久化操作
		Article article=new Article();
	
		article.setTitle("测试题目");
		article.setContent("测试内容");
		//由临时状态变为持久状态，对象有id，则不能执行insert操作，会抛出异常
		entityManager.persist(article);
		//把对象对应的记录从数据库中移除，但是只能移除持久化对象，不能移除游离对象
		//entityManager.remove(article);
/*		entityManager.merge(article);

*/		
		//单向多对一：@ManyToOne,@JoinColumn(name="外键名")，使用persist保存n-1关系时，新保存1，再保存n
		//使用
		//5.提交事务
		entityTransaction.commit();

//		add(entityManager);	 
//		search(entityManager,"tom");
		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}
}
