package cn.com.taiji.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cn.com.taiji.domain.Employee;


/**
 * @Description:
 * @author:
 * @date: 2019/7/29 10:36
 */
public class Main {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa1");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		// System.out.println(findUsers(manager, "user1", "aaa"));
		Employee employee = manager.find(Employee.class, 1);
		// employee.getDepartment().setName("111");
		employee.setAccount("aaaaaaaaaaaa");
		manager.getTransaction().commit();
		manager.close();
		factory.close();

	}

}
