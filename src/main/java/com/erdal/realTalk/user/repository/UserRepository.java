package com.erdal.realTalk.user.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.erdal.realTalk.user.model.User;
import com.erdal.realTalk.user.util.HibernateUtil;

public class UserRepository {
	
	 public void save(User user) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            session.persist(user);
	            transaction.commit();
	        } catch (Exception e) {
	            if(transaction != null) transaction.rollback();
	            e.printStackTrace();
	        }
	    }

}
