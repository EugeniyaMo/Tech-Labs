//package ru.itmo.kotikicontrollers.kafka;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class KafkaService{
//
//    private final KafkaTemplate<Long, Gson> kafkaTemplate;
//    private final ObjectMapper objectMapper;
//
//    @Autowired
//    public KafkaService(KafkaTemplate<Long, Gson> kafkaStarshipTemplate,
//                               ObjectMapper objectMapper) {
//        this.kafkaTemplate = kafkaStarshipTemplate;
//        this.objectMapper = objectMapper;
//    }
//
//    public void send(Gson message) {
//        kafkaTemplate.send("server.starship", message);
//    }
//
//    @KafkaListener(id = "Starship", topics = {"server.starship"}, containerFactory = "singleFactory")
//    public void consume(Gson message) {
//        log.info("=> consumed {}", writeValueAsString(message));
//    }
//
//    private String writeValueAsString(Gson message) {
//        try {
//            return objectMapper.writeValueAsString(message);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Writing value to JSON failed: " + message.toString());
//        }
//    }
//}