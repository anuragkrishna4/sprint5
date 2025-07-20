//package com.kiit.loginaggregation;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.kafka.common.serialization.Deserializer;
//
//import java.util.Map;
//
//public class JsonPOJODeserializer<T> implements Deserializer<T> {
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//    private final Class<T> targetClass;
//
//    public JsonPOJODeserializer(Class<T> targetClass) {
//        this.targetClass = targetClass;
//    }
//
//    @Override
//    public void configure(Map<String, ?> configs, boolean isKey) {}
//
//    @Override
//    public T deserialize(String topic, byte[] data) {
//        try {
//            if (data == null || data.length == 0)
//                return null;
//            return objectMapper.readValue(data, targetClass);
//        } catch (Exception e) {
//            throw new RuntimeException("Json deserialization failed", e);
//        }
//    }
//
//    @Override
//    public void close() {}
//}
