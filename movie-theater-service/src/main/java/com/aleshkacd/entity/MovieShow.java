package com.aleshkacd.entity;

import java.time.LocalDateTime;

public record MovieShow (
        Integer movieId,
        Integer hallId,
        LocalDateTime startsAt
) {

}
