package com.aleshkacd.controller;

import com.aleshkacd.booking.client.dto.BookingResponseDTO;
import com.aleshkacd.entity.MovieShow;
import com.aleshkacd.service.MovieShowService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/movie-show")
public class MovieShowController {
    private  final MovieShowService movieShowService;

    @Autowired
    public MovieShowController(MovieShowService movieShowService) {
        this.movieShowService = movieShowService;
    }

    @PostMapping("/{id}/book")
    public ResponseEntity<BookingResponseDTO> bookSeat(@PathVariable("id") Integer movieId,
                                                       @RequestBody BookingData bookingData) {
        return movieShowService.bookSeat(movieId, bookingData.getUserPhone(), bookingData.getSeatNum());
    }

    @GetMapping
    public Map<Integer, MovieShow> getUpcomingMovieShows(){
        return movieShowService.getUpcomingMovieShows();
    }

    @Data
    @NoArgsConstructor
    private static class BookingData {
        private String userPhone;
        private Integer seatNum;
    }
}
