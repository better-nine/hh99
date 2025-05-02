package com.example.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${setting.autocommit}")
    private String autoCommit;

    @Value("${setting.earliest}")
    private String earliest;

    // 기본 ThreadPoolTaskExecutor를 사용
    @Bean
    @Primary
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);  // 최소 스레드 수
        executor.setMaxPoolSize(10);  // 최대 스레드 수
        executor.setQueueCapacity(100);  // 대기 큐의 용량
        executor.setThreadNamePrefix("Kafka-Listener-");
        executor.initialize();
        return executor;
    }

    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, this.autoCommit);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, this.earliest);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 5000);
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    factory(ConsumerFactory<String, String> consumerFactory, ThreadPoolTaskExecutor taskExecutor) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(3);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.getContainerProperties().setListenerTaskExecutor(taskExecutor);  // taskExecutor 사용

        return factory;
    }
}