package com.erdal.realTalk.user.service;

import com.erdal.realTalk.common.exception.ErrorMessage;
import com.erdal.realTalk.common.exception.ResourceNotFoundException;
import com.erdal.realTalk.user.kafka.UserProducer;
import com.erdal.realTalk.user.mapper.UserMapper;
import com.erdal.realTalk.user.model.User;
import com.erdal.realTalk.user.repository.UserRepository;
import com.erdal.realTalk.user.requests.UserRequest;

import com.fasterxml.jackson.databind.ObjectMapper; // JSON dönüşümü için

/**
 * UserService
 * ------------------
 * Kullanıcı işlemlerini yönetir:
 * - Yeni kullanıcı oluşturma
 * - Database kaydı (Hibernate)
 * - Kafka event yayını (user_created)
 */
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserProducer kafkaProducer;
    private ObjectMapper objectMapper;

    // Constructor: Repository, Mapper ve Kafka Producer injection
    public UserService(UserRepository userRepository, UserMapper userMapper, UserProducer kafkaProducer) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.kafkaProducer = kafkaProducer;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * createUser
     * ------------------
     * 1. UserRequest -> User entity dönüştürür
     * 2. DB'ye kaydeder
     * 3. Kafka'ya 'user_created' event gönderir
     */
    public void createUser(UserRequest userRequest) {
        // 1. User entity oluştur
        User user = userMapper.UserRequestToUser(userRequest);

        // Eğer mapping başarısızsa hata fırlat
        if (user == null) {
            throw new ResourceNotFoundException(ErrorMessage.BAD_REQUEST);
        }

        // 2. Database kaydı
        userRepository.save(user);

        // 3. Kafka event oluştur ve gönder
        try {
            String userJson = objectMapper.writeValueAsString(user); // Useri-> JSONa ceviriyor objectMapper.writeValueAsString(user)
            kafkaProducer.sendMessage(userJson); // Kafka topic: user-events
            System.out.println("Kafka: user_created event gönderildi");
        } catch (Exception e) {
            e.printStackTrace(); 
            // Kafka gönderim hatası DB kaydını bozmaz, sadece log tutar
        }
    }
}
