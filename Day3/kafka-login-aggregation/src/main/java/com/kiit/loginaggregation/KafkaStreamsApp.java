package com.kiit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;

import java.util.Properties;

public class KafkaStreamsApp {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "login-count-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.STATE_DIR_CONFIG, "C:/kafka-state-store/login-count-app"); // custom state dir

        ObjectMapper objectMapper = new ObjectMapper();

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> loginEvents = builder.stream("user-logins");

        KTable<String, Long> loginCounts = loginEvents
                .map((key, value) -> {
                    try {
                        JsonNode jsonNode = objectMapper.readTree(value);
                        String userId = jsonNode.get("userId").asText();
                        return KeyValue.pair(userId, "1");
                    } catch (Exception e) {
                        return KeyValue.pair("unknown", "1");
                    }
                })
                .groupByKey()
                .count(Materialized.as("login-count-store"));

        // Write the results to another topic
        loginCounts.toStream().to("user-login-counts", Produced.with(Serdes.String(), Serdes.Long()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);

        // Add shutdown hook for graceful exit
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

        streams.start();
    }
}
