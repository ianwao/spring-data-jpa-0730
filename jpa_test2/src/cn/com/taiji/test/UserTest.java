package cn.com.taiji.test;

import cn.com.taiji.pojo.Authority;
import cn.com.taiji.pojo.User;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class UserTest {
    public static void main(String[] args) {
        // 1. 创建EntityManagerFactory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_test1");

        // 2. 创建EntityManager
        EntityManager entityManager = factory.createEntityManager();

        //添加
//        addUser(entityManager);
        //查询
//        selectUser(entityManager);
//        findEmployees(entityManager);

        //更新
        updateUser(entityManager);
        //删除
//        delectUser(entityManager);
        // 6. 关闭EntityManager
        entityManager.close();

        // 7. 关闭EntityManagerFactory
        factory.close();
    }

    private static void delectUser(EntityManager entityManager) {
        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4. 持久化操作
        User user = entityManager.find(User.class,5);
        entityManager.remove(user);

//        Authority authority = entityManager.find(Authority.class,4);
//        entityManager.remove(authority);
        // 5. 提交事务
        transaction.commit();
    }

    private static void updateUser(EntityManager entityManager) {
        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4. 持久化操作
        User user = entityManager.find(User.class,1);
        user.setPassword("1111111");
//        entityManager.flush();
        // 5. 提交事务
        transaction.commit();
    }

    private static void selectUser(EntityManager entityManager) {
        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4. 持久化操作
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> c = cb.createQuery(User.class);
        Root<User> userRoot = c.from(User.class);
        c.select(userRoot)
                .where(cb.like(userRoot.get("username"), "%张三%"));
        TypedQuery query = entityManager.createQuery(c);
        List<User> userList = query.getResultList();

        System.out.println(userList.size());
        // 5. 提交事务
        transaction.commit();
    }

    private static void addUser(EntityManager entityManager) {
        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4. 持久化操作
        List<Authority> authorityList = new ArrayList<>();
        Authority authority = new Authority();
        authority.setName("vip");
        authorityList.add(authority);

        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setPassword("12276");
        user.setUsername("程琪");

        user.setAuthorityList(authorityList);
        userList.add(user);
//        authority.setUserList(userList);
        // 添加user到数据库，相当于hibernate的save();
        entityManager.persist(authority);
        entityManager.persist(user);

        // 5. 提交事务
        transaction.commit();
    }

    public static void findEmployees(EntityManager entityManager) {
        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String name = "张三";
        String deptName = "123456";
        // 4. 持久化操作
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> c = cb.createQuery(User.class);
        Root<User> userRoot = c.from(User.class);
        c.select(userRoot);
        c.distinct(true);//去重
//        Join<User,Authority> project =
//                userRoot.join("projects", JoinType.LEFT);
        List<Predicate> criteria = new ArrayList<Predicate>();
        if (name != null) {
            ParameterExpression<String> p =
                    cb.parameter(String.class, "name");
            criteria.add(cb.equal(userRoot.get("username"), p));
        }
        if (deptName != null) {
            ParameterExpression<String> p =
                    cb.parameter(String.class, "dept");
            criteria.add(cb.equal(userRoot.get("password"), p));
        }
        if (criteria.size() == 0) {
            throw new RuntimeException("no criteria");
        } else if (criteria.size() == 1) {
            c.where(criteria.get(0));
        } else {
            c.where(cb.and(criteria.toArray(new Predicate[0])));
        }
        TypedQuery<User> q = entityManager.createQuery(c);
        if (name != null) { q.setParameter("name", name); }
        if (deptName != null) { q.setParameter("dept", deptName); }
        System.out.println(q.getResultList());

        // 5. 提交事务
        transaction.commit();
    }

}
