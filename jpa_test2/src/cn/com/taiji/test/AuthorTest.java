package cn.com.taiji.test;

import cn.com.taiji.pojo.Article;
import cn.com.taiji.pojo.Author;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AuthorTest {
    public static void main(String[] args) {
        // 1. 创建EntityManagerFactory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa_test1");

        // 2. 创建EntityManager
        EntityManager entityManager = factory.createEntityManager();

        //添加
//		addAuthor(entityManager);
        //更新
		updateAuthor(entityManager);
        //删除
//		delectAuthor(entityManager);
        //查询
//        selectAuthor(entityManager);

        // 6. 关闭EntityManager
        entityManager.close();

        // 7. 关闭EntityManagerFactory
        factory.close();
    }

    private static void delectAuthor(EntityManager entityManager) {
        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4. 持久化操作
//        Author author = entityManager.find(Author.class,2);
//        entityManager.remove(author);

        Article article = entityManager.find(Article.class,1);
        entityManager.remove(article);
        // 5. 提交事务
        transaction.commit();
    }

    private static void selectAuthor(EntityManager entityManager) {
        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4. 持久化操作
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> c = cb.createQuery(Author.class);
        Root<Author> authorRoot = c.from(Author.class);
        c.select(authorRoot)
                .where(cb.like(authorRoot.get("name"), "%白%"));
        TypedQuery query = entityManager.createQuery(c);
        List<Author> userList = query.getResultList();

        System.out.println(userList.size());

        // 5. 提交事务
        transaction.commit();
    }

    private static void updateAuthor(EntityManager entityManager) {
        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4. 持久化操作
        Article article = entityManager.find(Article.class,3);
        article.setContent("早上起床");
        // 5. 提交事务
        transaction.commit();
    }

    private static void addAuthor(EntityManager entityManager) {
        // 3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4. 持久化操作
        Author author = new Author();
        author.setName("杜甫");

        List<Article> list = new ArrayList<>();
        Article article = new Article();
        article.setTitle("朝辞白帝");
        article.setContent("艰难程度");
        article.setAuthor(author);
        list.add(article);

        author.setArticleList(list);
        // 添加user到数据库，相当于hibernate的save();
        entityManager.persist(author);
        entityManager.persist(article);
        // 5. 提交事务
        transaction.commit();
    }
}
