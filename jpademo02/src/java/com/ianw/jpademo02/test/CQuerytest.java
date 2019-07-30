package com.ianw.jpademo02.test;/**
 * ClassName: CQuerytest <br/>
 * Description: <br/>
 * date: 2019/7/30 15:35<br/>
 *
 * @author 72733<br   />
 * @version
 * @since JDK 1.8
 */

import com.ianw.jpademo02.domain.Authority;
import com.ianw.jpademo02.domain.People;
import com.ianw.jpademo02.domain.User;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: jpademo02
 * @description: Criteria Query
 * @author: tao xujie
 * @create: 2019-07-30 15:35
 */

public class CQuerytest {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaText");
        EntityManager entityManager = factory.createEntityManager();

        //peopleQuery(entityManager);
        // peopleQueryByMsg(entityManager);
        String rs = findUsers(entityManager, "aaa1", "123456");
        System.out.println(rs);

        /*for (User u : list
        ) {
            System.out.println(u.toString());
        }*/

        entityManager.close();
        factory.close();
    }



    /*根据名字进行查询*/
    private static void peopleQuery(EntityManager entityManager) {
        /*开启事务*/
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        /*持久化处理*/
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<People> c = cb.createQuery(People.class);
        Root<People> emp = c.from(People.class);

        c.select(emp).where(cb.equal(emp.get("name"), "小明"));
        //c.select(cb.).
        People people = entityManager.createQuery(c).getSingleResult();
        System.out.println(people.toString());

        /*事务提交*/
        transaction.commit();
    }

    public static String findUsers(EntityManager em, String username,
                                       String password) {
        String sss="sss";

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> c = cb.createQuery(String.class);
        Root<User> emp = c.from(User.class);

        c.select(emp.<String>get("username"));

        c.distinct(true);

        //Join<User,Authority> project =emp.join("authority", JoinType.LEFT);

        List<Predicate> criteria = new ArrayList<Predicate>();

        if (username != null && username != "") {
            ParameterExpression<String> p =
                    cb.parameter(String.class, "username");
            //criteria.add(cb.equal(emp.get("username"), p));
            //criteria.add(cb.like(emp.get("username"), p));
            criteria.add(cb.in(emp.get("username")).value("aaa1"));
        }
        if (password != null && password != "") {
            ParameterExpression<String> p =
                    cb.parameter(String.class, "password");
            criteria.add(cb.equal(emp.get("password"), p));
        }

        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        } else if (criteria.size() == 1) {
            c.where(criteria.get(0));
        } else {
            c.where(cb.and(criteria.toArray(new Predicate[0])));
        }

        TypedQuery<String> q = em.createQuery(c);
        if (username != null) {
            q.setParameter("username", username);
        }
        if (password != null) {
            q.setParameter("password", password);
        }
        return q.getSingleResult();
        //return q.getResultList();
    }

}