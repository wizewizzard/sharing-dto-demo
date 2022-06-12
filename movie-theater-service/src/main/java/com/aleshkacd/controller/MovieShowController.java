package com.aleshkacd.controller;

import com.aleshkacd.booking.client.dto.BookingResponse;
import com.aleshkacd.booking.client.dto.SeatsStatusResponse;
import com.aleshkacd.entity.MovieShow;
import com.aleshkacd.service.MovieShowService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/movie-show")
public class MovieShowController {
    private  final MovieShowService movieShowService;

    @Autowired
    public MovieShowController(MovieShowService movieShowService) {
        this.movieShowService = movieShowService;
    }

    @PostMapping("/{id}/book")
    public ResponseEntity<BookingResponse> bookSeat(@PathVariable("id") Integer movieShowId,
                                                    @RequestBody BookingData bookingData) {
        return movieShowService.bookSeat(movieShowId, bookingData.getUserPhone(), bookingData.getSeatNum());
    }

    @GetMapping
    public Map<Integer, MovieShow> getUpcomingMovieShows(){
        return movieShowService.getUpcomingMovieShows();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatsStatusResponse> getMovieShowSeatsStatus(@PathVariable("id") Integer movieShowId){
        return movieShowService.getSeatsStatusForShow(movieShowId);
    }

    @Data
    @NoArgsConstructor
    private static class BookingData {
        private String userPhone;
        private Integer seatNum;
    }
}
