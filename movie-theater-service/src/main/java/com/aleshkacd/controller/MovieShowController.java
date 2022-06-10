package com.aleshkacd.controller;

import com.aleshkacd.entity.MovieShow;
import com.aleshkacd.service.MovieShowService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String bookSeat(@PathVariable("id") Integer movieId,
                            @RequestBody BookingData bookingData){
        movieShowService.bookSeat(movieId, bookingData.getUserPhone(), bookingData.getSeatNum());
        return "Place was booked";
    }

    @GetMapping
    public Collection<MovieShow> getUpcomingMovieShows(){
        return movieShowService.getUpcomingMovieShows();
    }

    @Data
    @NoArgsConstructor
    private static class BookingData {
        private String userPhone;
        private Integer seatNum;
    }
}
