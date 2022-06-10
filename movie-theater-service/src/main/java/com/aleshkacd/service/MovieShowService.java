package com.aleshkacd.service;

import com.aleshkacd.booking.client.IBookSeat;
import com.aleshkacd.booking.client.dto.BookingRequestDTO;
import com.aleshkacd.entity.MovieShow;
import com.aleshkacd.exception.MovieShowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class MovieShowService {

    private final Map<Integer, MovieShow> movieShows = new HashMap<>();

    private final IBookSeat seatBooker;

    @Autowired
    public MovieShowService(IBookSeat seatBooker) {
        this.seatBooker = seatBooker;
    }

    @PostConstruct
    public void fillData(){
        LocalDateTime currentDT = LocalDateTime.now();
        movieShows.put(1, new MovieShow(236, currentDT.minusHours(10)));
        movieShows.put(2, new MovieShow(897, currentDT.plusDays(1)));
        movieShows.put(3, new MovieShow(236, currentDT.plusHours(5)));
        movieShows.put(4, new MovieShow(741, currentDT.plusHours(2)));
        movieShows.put(5, new MovieShow(77, currentDT.plusMinutes(55)));
    }

    public void bookSeat(Integer movieShowId, String userPhone, Integer seatNum){
        MovieShow movieShow = movieShows.get(movieShowId);
        if(movieShow != null){
            //Booking for a show is allowed if there's more than one hour left until the show starts
            if(movieShow.startsAt().isAfter(LocalDateTime.now().plusHours(1))){
                throw new UnsupportedOperationException("Not implemented yet");
                //TODO: here goes a call for client part of the booking module
            }
            else {
                throw new MovieShowException("It is too late to book seats for this movie show");
            }
        }
        else{
            throw new MovieShowException("Movie show with this id does not exist");

        }
    }

    public Collection<MovieShow> getUpcomingMovieShows(){
        return movieShows
                .values()
                .stream()
                .filter(movieShow -> movieShow.startsAt().isAfter(LocalDateTime.now()))
                .toList();
    }
}
