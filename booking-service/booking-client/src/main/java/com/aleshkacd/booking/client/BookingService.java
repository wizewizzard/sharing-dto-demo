package com.aleshkacd.booking.client;

import com.aleshkacd.booking.client.dto.BookingRequestDTO;
import com.aleshkacd.booking.client.dto.BookingResponseDTO;
import com.aleshkacd.booking.client.exception.BookingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class BookingService implements IBookSeat{

    private final RestTemplate restTemplate;

    public BookingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<BookingResponseDTO> bookSeat(BookingRequestDTO bookingData) {
        validate(bookingData);
        String url = "http://localhost:8081/api/booking";
        return restTemplate.postForEntity(url, bookingData, BookingResponseDTO.class);
    }

    public void validate(BookingRequestDTO bookingData){
        try {
            Objects.requireNonNull(bookingData.userPhone());
            Objects.requireNonNull(bookingData.hallId());
            Objects.requireNonNull(bookingData.seatNum());
        }
        catch (NullPointerException nullPointerException){
            throw new BookingException("User phone/movie show/seat number must not be null!");
        }
    }
}
