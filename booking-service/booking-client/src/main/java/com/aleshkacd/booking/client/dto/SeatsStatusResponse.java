package com.aleshkacd.booking.client.dto;

import java.util.List;

public record SeatsStatusResponse (
        List<SeatDTO> seats
) {
    public record SeatDTO (Integer number, String status){
    }
}
