package com.aleshkacd.booking.client;

import com.aleshkacd.booking.client.dto.BookingRequestDTO;
import com.aleshkacd.booking.client.dto.BookingResponseDTO;
import com.aleshkacd.booking.client.exception.BookingException;
import org.springframework.http.ResponseEntity;

public interface IBookSeat {
    public ResponseEntity<BookingResponseDTO> bookSeat(BookingRequestDTO bookingData) throws BookingException;
}
