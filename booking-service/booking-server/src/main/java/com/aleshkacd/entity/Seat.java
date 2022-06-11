package com.aleshkacd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    public enum State{
        FREE,
        OCCUPIED,
        BOOKED
    };

    private Integer number;
    private State state;

}
