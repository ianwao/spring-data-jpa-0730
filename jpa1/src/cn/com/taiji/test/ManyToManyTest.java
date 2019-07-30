package cn.com.taiji.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.com.taiji.domain.Address;
import cn.com.taiji.domain.Authority;
import cn.com.taiji.domain.People;
import cn.com.taiji.domain.User;

public class ManyToManyTest {

	public static void add(EntityManager entityManager) {

		// 4.进行持久化操作
		List<Authority> listAuthority = new ArrayList<Authority>();
		Authority authority = new Authority();
		authority.setName("六六");
		listAuthority.add(authority);

		List<User> listUser = new ArrayList<User>();
		User user = new User();
		user.setUsername("六六");
		user.setPassword("123");
		listUser.add(user);

		authority.setUserList(listUser);
		// user.setAuthorityList(listAuthority);

		entityManager.persist(user);
		entityManager.persist(authority);

	}

	public static void select(EntityManager entityManager) {

		// 4.进行持久化操作
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> c = cb.createQuery(User.class);
		Root<User> emp = c.from(User.class);
		c.select(emp).where(cb.equal(emp.get("username"), "六六"));
		TypedQuery query = entityManager.createQuery(c);
		List<User> listUser = query.getResultList();

		System.out.println(listUser.size());


	}

	public static void findUser(EntityManager entityManager) {

		String name = "六六";
		String deptName = "123";

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
			c.where(cb.and((javax.persistence.criteria.Predicate[]) criteria.toArray(new Predicate[0])));
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

	public static void update(EntityManager entityManager) {
		
		
	}

	public static void delete(EntityManager entityManager) {
		
		
	}

	public static void persist(EntityManager entityManager) {
		Authority authority1 = new Authority();
		authority1.setName("六六");
		
		Authority authority2 = new Authority();
		authority2.setName("琪琪");
		
		User user1 = new User();
		user1.setUsername("六六");
		user1.setPassword("123");
		
		User user2 = new User();
		user2.setUsername("琪琪");
		user2.setPassword("123");
		
		//设置关联关系
		authority1.getUserList().add(user1);
		authority1.getUserList().add(user2);
		
		authority2.getUserList().add(user1);
		authority2.getUserList().add(user2);
		
		user1.getAuthorityList().add(authority1);
		user1.getAuthorityList().add(authority2);
		
		user2.getAuthorityList().add(authority1);
		user2.getAuthorityList().add(authority2);
		
		//保存
		entityManager.persist(user1);
		entityManager.persist(user2);
		entityManager.persist(authority1);
		entityManager.persist(authority2);
		
	}

	//使用维护关联关系的一方获取，还是使用不维护关联关系的一方获取，sql语句相同
	public static void find(EntityManager entityManager) {
		User user=entityManager.find(User.class, 4);
		System.out.println(user.getUsername());
		System.out.println(user.getAuthorityList().size());
		
		Authority authority=entityManager.find(Authority.class, 3);
		System.out.println(authority.getName());
		System.out.println(authority.getUserList().size());
		
	}

	public static void remove(EntityManager entityManager) {
		User user=entityManager.find(User.class, 4);
		entityManager.remove(user);
		
	}

	public static void update1(EntityManager entityManager) {
		User user=entityManager.find(User.class, 3);
		user.getAuthorityList().iterator().next().setName("lll");
		
	}

}
