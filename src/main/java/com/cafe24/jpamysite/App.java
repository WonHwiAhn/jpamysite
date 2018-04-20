package com.cafe24.jpamysite;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cafe24.mysite.domain.User;

public class App {
    public static void main( String[] args ) {
        EntityManagerFactory emf =
        		Persistence.createEntityManagerFactory("jpamysite");
        
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        
        try {
        	insertTest(em);
        	//findListTest(em);
        	findOneTest(em);
        }catch(Exception e) {
        	tx.rollback();
        	e.printStackTrace();
        }
        
        tx.commit();
        em.close();
        emf.close();
    }
    
    public static void insertTest(EntityManager em) {
    	User user = new User();
    	user.setName("WONHWI");
    	user.setEmail("myroom9@naver.com");
    	user.setGender("Man");
    	user.setPassword("123");
    	user.setJoinDate(new Date());
    	
    	em.persist(user);
    }
    
    public static void findListTest(EntityManager em) {
    	TypedQuery<User> query = em.createQuery("SELECT no FROM User a", User.class);
    	
    	java.util.List<User> list = query.getResultList();
    	
    	for(int i=0;i<list.size();i++) {
    		System.out.println(list.get(i));
    	}
    }
    
    public static void findOneTest(EntityManager em) {
    	User book = em.find(User.class, 1L);
    	System.out.println(book);
    	
    	//book.setPrice(0);
    }
}
