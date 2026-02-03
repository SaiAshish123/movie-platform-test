
package com.movie.booking.controller;

import com.movie.booking.dto.BookingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.movie.booking.service.MovieBookingService;
import com.movie.booking.dto.BookingRequest;
import com.movie.booking.entity.Booking;


@Tag(name = "Movie Booking API", description = "Operations related to ticket booking")
@RestController
@RequestMapping("/api/v1/movie/booking")
public class MovieBookingController {

    @Autowired
    private MovieBookingService movieBookingService;


    @Operation(summary = "Book movie tickets",
            description = "Books seats for a given show and user")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Booking created successfully"),
            @ApiResponse(responseCode = "404", description = "Show not found"),
            @ApiResponse(responseCode = "409", description = "Seats already booked"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<BookingResponse> bookTicket(
            @Valid
            @RequestBody BookingRequest request) {

        BookingResponse response = movieBookingService.bookTickets(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)   // 201
                .body(response);
    }
}
