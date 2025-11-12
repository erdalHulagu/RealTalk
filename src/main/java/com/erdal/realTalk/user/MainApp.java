package com.erdal.realTalk.user;


import org.hibernate.Session;

import com.erdal.realTalk.user.demo.User;
import com.erdal.realTalk.user.role.Role;
import com.erdal.realTalk.user.status.Status;
import com.erdal.realTalk.user.util.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {

        System.out.println("Hibernate User Test Başlatıldı...");

        // 1 Hibernate session açıyoruz
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            // 2️ Yeni bir kullanıcı oluştur
            User user = new User(
                    "erdal",
                    "erdal@example.com",
                    "12345",
                    Role.ADMIN,
                    Status.ACTIVE
            );

            // 3️⃣ Veritabanına kaydet
            session.persist(user);
            session.getTransaction().commit();

            System.out.println("Kullanıcı başarıyla kaydedildi: " + user);

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // 4️⃣ Kaynakları kapat
            session.close();
            HibernateUtil.shutdown();
        }
    }
}
