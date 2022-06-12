package com.aleshkacd.controller;

import com.aleshkacd.booking.client.dto.BookingRequestDTO;
import com.aleshkacd.booking.client.dto.BookingResponseDTO;
import com.aleshkacd.booking.client.dto.SeatsStatusResponse;
import com.aleshkacd.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/booking")
@Slf4j
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatsStatusResponse> getSeatsStatus(@PathVariable("id") Integer hallId){
        return bookingService.getSeats(hallId);
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> bookSeat(@RequestBody BookingRequestDTO bookingRequestData){
        return bookingService.bookSeat(bookingRequestData);
    }
}
