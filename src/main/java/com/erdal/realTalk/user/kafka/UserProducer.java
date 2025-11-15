package com.erdal.realTalk.user.kafka;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class UserProducer {

    private final KafkaProducer<String, String> producer;
    private final String topic;

    public UserProducer(String bootstrapServers, String topic) {
        this.topic = topic;

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        producer = new KafkaProducer<>(props);
    }

    // Mesaj gönderme metodu (TEK parametre)
    public void sendMessage(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);

        try {
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata metadata = future.get(); // senkron (zorunlu değil)
            System.out.println("Sent message → topic: " + metadata.topic() +
                    ", partition: " + metadata.partition());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        producer.close();
    }
}
