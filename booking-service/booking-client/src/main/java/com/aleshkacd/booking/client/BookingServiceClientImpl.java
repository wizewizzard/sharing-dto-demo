package com.aleshkacd.booking.client;

import com.aleshkacd.booking.client.dto.BookingRequest;
import com.aleshkacd.booking.client.dto.BookingResponse;
import com.aleshkacd.booking.client.dto.SeatsStatusResponse;
import com.aleshkacd.booking.client.exception.BookingServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BookingServiceClientImpl implements ISeatBookingClient {

    private final RestTemplate restTemplate;

    public BookingServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<BookingResponse> bookSeat(BookingRequest bookingData) {
        validate(bookingData);
        String url = "http://localhost:8081/api/booking";
        return restTemplate.postForEntity(url, bookingData, BookingResponse.class);
    }

    @Override
    public ResponseEntity<SeatsStatusResponse> getSeatsStatus(Integer hallId) throws BookingServiceException {
        if(hallId == null)
            throw new BookingServiceException("Hall id must not be null!");
        String url = "http://localhost:8081/api/booking/{hallId}";
        Map<String, Integer> params = new HashMap<>();
        params.put("hallId", hallId);
        return restTemplate.getForEntity(url,
                SeatsStatusResponse.class,
                params);
    }

    public void validate(BookingRequest bookingData){
        try {
            Objects.requireNonNull(bookingData.userPhone());
            Objects.requireNonNull(bookingData.hallId());
            Objects.requireNonNull(bookingData.seatNum());
        }
        catch (NullPointerException nullPointerException){
            throw new BookingServiceException("User phone/movie show/seat number must not be null!");
        }
    }
}
