package com.aleshkacd.exception;

import com.aleshkacd.booking.client.error.BookingServiceError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
@Slf4j
public class BookingResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if(response.getStatusCode().is4xxClientError()){
            ObjectMapper objectMapper = new ObjectMapper();
            throw new MovieShowException(
                    objectMapper.readValue(response.getBody(), BookingServiceError.class).message()
            );
        }
        else{
            throw new RemoteServiceException("Booking service responded with server error");
        }
    }
}
