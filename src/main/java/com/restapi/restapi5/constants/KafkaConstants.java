package com.restapi.restapi5.constants;

import org.springframework.stereotype.Component;

@Component
public class KafkaConstants {

    public static final String TOPIC="student_update";
    public static final String GROUP_ID="group-student";
    public static final String HOST="localhost:9092";
}