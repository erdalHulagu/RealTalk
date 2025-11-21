package com.erdal.realTalk.user.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.erdal.realTalk.common.exception.ErrorMessage;
import com.erdal.realTalk.common.exception.ResourceNotFoundException;
import com.erdal.realTalk.user.kafka.UserProducer;
import com.erdal.realTalk.user.model.User;
import com.erdal.realTalk.user.repository.UserRepository;
import com.erdal.realTalk.user.role.Role;
import com.erdal.realTalk.user.status.Status;
import com.fasterxml.jackson.databind.ObjectMapper; // JSON dönüşümü için

/**
 * UserService
 * ------------------
 * Kullanıcı işlemlerini yönetir:
 * - Yeni kullanıcı oluşturma
 * - Database kaydı (Hibernate)
 * - Kafka event yayını (user_created)
 */

@Singleton
@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserProducer producer;

    @Inject
    private ObjectMapper objectMapper;
    
   

    public void createUser(User user) {
        // Eğer null ise hata fırlat
        if (user == null) {
            throw new ResourceNotFoundException(ErrorMessage.BAD_REQUEST);
        }

        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);

        // DB kaydı
        userRepository.save(user);
        System.out.println("UserService.createUser end");

        // Kafka kısmını try-catch ile kapattık
        try {
            if (producer != null) {
                String userJson = objectMapper.writeValueAsString(user);
                producer.sendMessage(userJson);
                System.out.println("Kafka: user_created event gönderildi");
            } else {
                System.out.println("Kafka producer null, mesaj gönderilmedi");
            }
        } catch (Exception e) {
            System.out.println("Kafka broker çalışmıyor, mesaj gönderilemedi");
        }
    }
}


