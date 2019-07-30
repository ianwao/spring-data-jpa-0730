package cn.com.taiji.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
//作者--》文章    1-n
public class AuthorAndArticleTest {
	public static void main(String[] args) {
		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaaa");

		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();
		// addAuthor(entityManager);
		// addArticle(entityManager);
		// delAuthor(entityManager);
		// delArticle(entityManager);
		// updateArticle(entityManager);
		testmerge(entityManager);
		// searchAuthor(entityManager, "张三");//使用The Criteria API查詢
		// searchByNameQuery(entityManager);//使用命名sql查询

		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}
	
	/*		entityManager.merge(article);
	1.若传入的是一个对象，会创建一个新的对象，把临时对象的属性复制到新的对象中，
	然后对新的对象执行持久化操作，所以心得对象中有id，但以前的临时对象中没有id
	2.若传入的对象是一个游离对象，即传入的对象有id,若在entityManager缓存中没有该对象，若在数据库表中也没有相应记录，
	jpa会创建一个对象，把临时对象的属性复制到新的对象中，对新创建的对象执行insert操作
*/	

	private static void testmerge(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作

		Article article = new Article();
		article.setTitle("测试三");
		article.setContent("测试三内容");
		Article article2 = entityManager.merge(article);
		System.out.println("article#id" + article.getId());
		System.out.println("article2#id" + article2.getId());
		// 5. 提交事务
		transaction.commit();

	}

	private static void delArticle(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		Article article = entityManager.find(Article.class, 1);
		entityManager.remove(article);
		// 5. 提交事务
		transaction.commit();

	}

	private static void updateArticle(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		Article article = (Article) entityManager.find(Article.class, 1);
		article.setTitle("测试一 一");

		// 5. 提交事务
		transaction.commit();

	}

	private static void searchByNameQuery(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作

		List<String> listtitle = entityManager.createNamedQuery("Article.search").getResultList();
		System.out.println(listtitle);

		// 5. 提交事务
		transaction.commit();

	}

	private static void delAuthor(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		Author address = entityManager.find(Author.class, 1);
		entityManager.remove(address);
		// 5. 提交事务
		transaction.commit();

	}

	private static void searchAuthor(EntityManager entityManager, String string) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Author> c = cb.createQuery(Author.class);

		Root<Author> root = c.from(Author.class);
		c.select(root).where(cb.equal(root.get("name"), string));
		TypedQuery query = entityManager.createQuery(c);
		Author author = (Author) query.getSingleResult();
		System.out.println(author.getName() + ";" + author.getId() + ";" + author.getArticleList().get(0).getTitle()
				+ ";" + author.getArticleList().get(1).getTitle());

		// 5. 提交事务
		transaction.commit();

	}

	private static void addArticle(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作

		Author author = entityManager.find(Author.class, 2);

		Article article1 = new Article();
		article1.setAuthor(author);
		article1.setTitle("测试一");
		article1.setContent("测试内容一");

		Article article2 = new Article();
		article2.setAuthor(author);
		article2.setTitle("测试二");
		article2.setContent("测试内容二");

		entityManager.persist(author);
		entityManager.persist(article1);
		entityManager.persist(article2);

		// 5. 提交事务
		transaction.commit();

	}

	private static void addAuthor(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		Author author = new Author();
		author.setName("张三");
		entityManager.persist(author);

		// 5. 提交事务
		transaction.commit();

	}

}
