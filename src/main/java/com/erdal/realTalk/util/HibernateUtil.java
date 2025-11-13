package com.erdal.realTalk.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // hibernate.cfg.xml dosyasını okuyup SessionFactory oluşturuyoruz
            sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println(" Hibernate SessionFactory başarıyla oluşturuldu!");
        } catch (HibernateException ex) {
            System.err.println(" SessionFactory oluşturulurken hata oluştu: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Uygulama kapanırken kaynakları serbest bırakmak için
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
