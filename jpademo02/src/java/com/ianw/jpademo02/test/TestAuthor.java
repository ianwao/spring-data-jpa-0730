package com.ianw.jpademo02.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import static jdk.nashorn.internal.objects.NativeString.search;

public class TestAuthor {

	@PersistenceContext
	EntityManager em;

	public static void main(String[] args) {
		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-demo");

		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();

         //addAuthor(entityManager);
		 //addArticle(entityManager);
		 //search(entityManager, "xxx");
		 //delAuthor(entityManager);
		 //delAuthor(entityManager);
		 //searchByNameQuery(entityManager);

		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}

	private static void addAuthor(EntityManager entityManager) {


	}


}
