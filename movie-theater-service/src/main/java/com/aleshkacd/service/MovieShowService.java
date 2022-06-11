package com.aleshkacd.service;

import com.aleshkacd.booking.client.IBookSeat;
import com.aleshkacd.booking.client.dto.BookingRequestDTO;
import com.aleshkacd.booking.client.dto.BookingResponseDTO;
import com.aleshkacd.entity.MovieShow;
import com.aleshkacd.exception.MovieShowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
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
        movieShows.put(1, new MovieShow(236, 22, currentDT.minusHours(10)));
        movieShows.put(2, new MovieShow(897, 22, currentDT.plusDays(1)));
        movieShows.put(3, new MovieShow(741,4, currentDT.plusHours(2)));
        movieShows.put(4, new MovieShow(77, 8, currentDT.plusMinutes(55)));
    }

    public ResponseEntity<BookingResponseDTO> bookSeat(Integer movieShowId, String userPhone, Integer seatNum){
        MovieShow movieShow = movieShows.get(movieShowId);
        if(movieShow != null){
            //Booking for a show is allowed if there's more than one hour left until the show starts
            if(movieShow.startsAt().isAfter(LocalDateTime.now().plusHours(1))){
                log.info("Trying to book the seat... Calling to another service");
                BookingRequestDTO requestDTO = new BookingRequestDTO(movieShow.hallId(), userPhone, seatNum);
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

    public Collection<MovieShow> getUpcomingMovieShows(){
        return movieShows
                .values()
                .stream()
                .filter(movieShow -> movieShow.startsAt().isAfter(LocalDateTime.now()))
                .toList();
    }
}
