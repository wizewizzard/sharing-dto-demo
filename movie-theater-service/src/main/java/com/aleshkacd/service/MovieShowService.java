package com.aleshkacd.service;

import com.aleshkacd.booking.client.ISeatBookingClient;
import com.aleshkacd.booking.client.dto.BookingRequest;
import com.aleshkacd.booking.client.dto.BookingResponse;
import com.aleshkacd.booking.client.dto.SeatsStatusResponse;
import com.aleshkacd.entity.MovieShow;
import com.aleshkacd.exception.MovieShowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieShowService {

    private final List<MovieShow> movieShows = new ArrayList<>();

    private final ISeatBookingClient seatBooker;

    @Autowired
    public MovieShowService(ISeatBookingClient seatBooker) {
        this.seatBooker = seatBooker;
    }

    @PostConstruct
    public void fillData(){
        LocalDateTime currentDT = LocalDateTime.now();
        movieShows.add( new MovieShow(1,236, 22, currentDT.minusHours(10)));
        movieShows.add( new MovieShow(2,897, 22, currentDT.plusDays(1)));
        movieShows.add( new MovieShow(3,741,4, currentDT.plusHours(2)));
        movieShows.add( new MovieShow(4,77, 8, currentDT.plusMinutes(55)));
    }

    public ResponseEntity<BookingResponse> bookSeat(Integer movieShowId, String userPhone, Integer seatNum){
        MovieShow movieShow = movieShows.get(movieShowId);
        if(movieShow != null){
            //Booking for a show is allowed if there's more than one hour left until the show starts
            if(movieShow.startsAt().isAfter(LocalDateTime.now().plusHours(1))){
                log.info("Trying to book the seat... Calling to another service");
                BookingRequest requestDTO = new BookingRequest(movieShow.hallId(), userPhone, seatNum);
                return seatBooker.bookSeat(requestDTO);
            }
            else {
                throw new MovieShowException("It is too late to book seats for this movie show");
            }
        }
        else{
            throw new MovieShowException("Movie show with this id does not exist");
        }
    }

    public ResponseEntity<SeatsStatusResponse> getSeatsStatusForShow(Integer movieShowId){
        MovieShow movieShow = movieShows.get(movieShowId);
        if(movieShow != null){
            if(movieShow.startsAt().isAfter(LocalDateTime.now()))
                return seatBooker.getSeatsStatus(movieShow.hallId());
            else
                throw new MovieShowException("Data for this show is not available");
        }
        else{
            throw new MovieShowException("Movie show with this id does not exist");
        }
    }

    public Collection<MovieShow> getUpcomingMovieShows(){
        return movieShows
                .stream()
                .filter(e -> e.startsAt().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
