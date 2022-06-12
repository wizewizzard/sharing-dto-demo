package com.aleshkacd.config;

import com.aleshkacd.booking.client.BookingServiceClientImpl;
import com.aleshkacd.exception.BookingResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    @Bean(name = "bookingRestTemplate")
    public RestTemplate bookingRestTemplate(@Autowired BookingResponseErrorHandler bookingResponseErrorHandler){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(bookingResponseErrorHandler);
        return restTemplate;
    }

    @Bean
    public BookingServiceClientImpl bookingService(@Qualifier("bookingRestTemplate") RestTemplate bookingRestTemplate){
        return new BookingServiceClientImpl(bookingRestTemplate);
    }
}
