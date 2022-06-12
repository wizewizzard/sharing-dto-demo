package com.aleshkacd.booking.client;

import com.aleshkacd.booking.client.dto.BookingRequest;
import com.aleshkacd.booking.client.dto.BookingResponse;
import com.aleshkacd.booking.client.dto.SeatsStatusResponse;
import com.aleshkacd.booking.client.exception.BookingServiceException;
import org.springframework.http.ResponseEntity;

public interface ISeatBookingClient {
    ResponseEntity<BookingResponse> bookSeat(BookingRequest bookingData) throws BookingServiceException;

    ResponseEntity<SeatsStatusResponse> getSeatsStatus(Integer hallId) throws BookingServiceException;
}
