package cn.com.taiji.role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

                //多对多 n-n
public class UserAndAuthorityTest {
	public static void main(String[] args) {
		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaaa");

		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();

		// addUserAndAuthor(entityManager);
		deleteUser(entityManager);
		// updateUser(entityManager);
		// search(entityManager);//使用标准API方式查询
		// findUser(entityManager);//使用动态查询
		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}

	private static void deleteUser(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		User user = entityManager.find(User.class, 1);

		entityManager.remove(user);
		// 5. 提交事务
		transaction.commit();

	}

	private static void updateUser(EntityManager entityManager) {

		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		List<Authority> authorityList = new ArrayList<>();
		Authority a = new Authority();
		a.setName("11");
		authorityList.add(a);
		User users = entityManager.find(User.class, 1);
		users.setUsername("cc");
		users.setPassword("1234");
		users.setAuthorityList(authorityList);
		entityManager.persist(a);// 需要保存才可以，不然会报错,而且需将写入cascade=CascadeType.ALL给被维护方
		/*
		 * 抛出java.lang.IllegalStateException: org.hibernate.TransientObjectException:
		 * object references an unsaved transient instance - save the transient instance
		 * beforeQuery flushing 因为a是Transient状态，没保存，所以抛异常，只要保存即可
		 */
		// 5. 提交事务
		transaction.commit();

	}

	private static void findUser(EntityManager entityManager) {
		// TODO Auto-generated method stub
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		String name = "aa";
		String deptName = "123456";
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> c = cb.createQuery(User.class);
		Root<User> emp = c.from(User.class);
		c.select(emp);
		c.distinct(true);

		List<Predicate> criteria = new ArrayList<Predicate>();
		if (name != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "username");
			criteria.add((Predicate) cb.equal(emp.get("username"), p));
		}
		if (deptName != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "password");
			criteria.add((Predicate) cb.equal(emp.get("password"), p));
		}

		if (criteria.size() == 0) {
			throw new RuntimeException("no criteria");
		} else if (criteria.size() == 1) {
			c.where((Expression<Boolean>) criteria.get(0));
		} else {
			c.where(cb.and((Predicate[]) criteria.toArray(new Predicate[0])));
		}
		TypedQuery<User> q = entityManager.createQuery(c);
		if (name != null) {
			q.setParameter("username", name);
		}
		if (deptName != null) {
			q.setParameter("password", deptName);
		}

		System.out.println(q.getResultList().size());

	}

	private static void search(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> c = cb.createQuery(User.class);

		Root<User> root = c.from(User.class);
		c.select(root).where(cb.equal(root.get("username"), "aa"));
		TypedQuery query = entityManager.createQuery(c);
		List<User> list = query.getResultList();
		System.out.println(list.size());

		// 5. 提交事务
		transaction.commit();

	}

	private static void addUserAndAuthor(EntityManager entityManager) {
		// TODO Auto-generated method stub
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// 4. 持久化操作
		List<Authority> authorityList = new ArrayList<Authority>();
		Authority authority = new Authority();
		authority.setName("张三");
		authorityList.add(authority);

		List<User> userList = new ArrayList<User>();
		User user = new User();
		user.setUsername("aa");
		user.setPassword("123456");
		// userList.add(user);
		user.setAuthorityList(authorityList);
		// authority.setUserList(userList);
		entityManager.persist(authority);
		entityManager.persist(user);
		// 5. 提交事务
		transaction.commit();

	}

}
