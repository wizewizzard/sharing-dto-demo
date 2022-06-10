package com.aleshkacd.config;

import com.aleshkacd.booking.client.BookingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public BookingService bookingService(){
        return new BookingService();
    }
}
