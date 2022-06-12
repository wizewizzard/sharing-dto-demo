package com.aleshkacd.booking.client.dto;

public record BookingRequest(Integer hallId,
                             String userPhone,
                             Integer seatNum){

}
