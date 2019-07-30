package com.ianw.jpademo02.test;/**
 * ClassName: Test2 <br/>
 * Description: <br/>
 * date: 2019/7/30 14:09<br/>
 *
 * @author 72733<br />
 * @version
 * @since JDK 1.8
 */

import com.ianw.jpademo02.domain.Classes;
import com.ianw.jpademo02.domain.People;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *@program: jpademo02
 *@description: JDPL测试
 *@author: tao xujie
 *@create: 2019-07-30 14:09
 */
public class Test2 {
    public static void main(String[] args) {
        // 1. 创建EntityManagerFactory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpaText");

        // 2. 创建EntityManager
        EntityManager entityManager = factory.createEntityManager();

        //持久化操作

       //查询全部人数
        //peopleByNameQuery(entityManager);
        updatePeople(entityManager);


        // 6. 关闭EntityManager
        entityManager.close();

        // 7. 关闭EntityManagerFactory
        factory.close();
    }

    private static void updatePeople(EntityManager entityManager) {
        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 4. 持久化操作
        List<People> list = new ArrayList<People>();

        /*更新数据*/
        entityManager.createQuery("UPDATE People e " +
                "SET e.name = ?1 " +
                "WHERE e.id = ?2")
                .setParameter(1, "小华")
                .setParameter(2, 2)
                .executeUpdate();

        // 5. 提交事务
        transaction.commit();
    }

    private static void peopleByNameQuery(EntityManager entityManager) {
        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 4. 持久化操作
        List<People> list = new ArrayList<People>();

        /*查询*/
        list = entityManager.createNamedQuery("people.all").getResultList();
        //list = entityManager.createNativeQuery("select * from people",People.class).getResultList();
        //People peo = (People) entityManager.createQuery("select p from People p where p.id =?1").setParameter(1,2).getSingleResult();

        /*修改*/
        //int update = entityManager.find(People.class,0)

        //遍历打印信息
        //System.out.println(peo.toString());
        /*for (People p:list
             ) {
            System.out.println("人员信息："+p.toString());
        }*/
        // 5. 提交事务
        transaction.commit();
    }

}