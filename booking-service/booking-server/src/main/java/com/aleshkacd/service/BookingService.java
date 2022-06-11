package com.aleshkacd.service;

import com.aleshkacd.booking.client.dto.BookingRequestDTO;
import com.aleshkacd.booking.client.dto.BookingResponseDTO;
import com.aleshkacd.booking.client.exception.BookingException;
import com.aleshkacd.entity.Seat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class BookingService {
    private final Map<Integer, List<Seat>> seatsStatus = new HashMap<>();

    @PostConstruct
    public void fillData(){
        seatsStatus.put(22, List.of(
                new Seat(1, Seat.State.FREE),
                new Seat(2, Seat.State.OCCUPIED),
                new Seat(3, Seat.State.BOOKED)
        ));


        seatsStatus.put(4, List.of(
                new Seat(1, Seat.State.BOOKED),
                new Seat(2, Seat.State.FREE)
        ));

        seatsStatus.put(8, List.of(
                new Seat(1, Seat.State.OCCUPIED),
                new Seat(2, Seat.State.BOOKED),
                new Seat(3, Seat.State.BOOKED),
                new Seat(4, Seat.State.FREE)
        ));
    }

    public ResponseEntity<BookingResponseDTO> bookSeat(BookingRequestDTO bookingData){
        Integer hallId = bookingData.hallId();
        Integer seatNum = bookingData.seatNum();
        String phoneNumber = bookingData.userPhone();

        Seat seat = seatsStatus.get(hallId)
                .stream()
                .filter(s -> Objects.equals(s.getNumber(), seatNum))
                .filter(s -> s.getState().equals(Seat.State.FREE))
                .findFirst()
                .orElseThrow(() -> new BookingException("Seat with number %d in hall %d can not be booked".formatted(seatNum, hallId )));

        log.info("Saving user phone {}...", phoneNumber);
        seat.setState(Seat.State.BOOKED);
        return new ResponseEntity<>(new BookingResponseDTO(
                "Seat in hall %d with number %d was booked".formatted(hallId, seatNum)
        ), HttpStatus.ACCEPTED);


    }

}
