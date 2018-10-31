package com.airbnb.fullstackspring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return new ObjectMapper();
    }

    @Bean
    public ObjectWriter objectWriter(ObjectMapper objectMapper){
        return objectMapper.writerWithDefaultPrettyPrinter();
    }
}
//ObjectMapper - The ObjectMapper will define how JSON strings in the request body are deserialized from requests in POJOs (Plain Old Java Objects),
// which we use to model our data. - (request related)
//ObjectWriter - The ObjectWriter will define how we serialize our Java objects into a JSON string  in the response body. (response related)