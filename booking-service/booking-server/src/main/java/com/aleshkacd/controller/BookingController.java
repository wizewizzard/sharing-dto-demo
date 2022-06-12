package com.aleshkacd.controller;

import com.aleshkacd.booking.client.dto.BookingRequest;
import com.aleshkacd.booking.client.dto.BookingResponse;
import com.aleshkacd.booking.client.dto.SeatsStatusResponse;
import com.aleshkacd.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BookingResponse> bookSeat(@RequestBody BookingRequest bookingRequestData){
        return bookingService.bookSeat(bookingRequestData);
    }
}
