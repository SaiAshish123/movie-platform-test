package com.movie.booking.controller;

import com.movie.booking.dto.MovieResponse;
import com.movie.booking.service.GetMoviesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Movie API", description = "Operations related to movie browsing")
@RestController
@RequestMapping("/api/v1/movies")
public class GetMovieController {

    @Autowired
    private GetMoviesService movieService;
    @Operation(
            summary = "Fetch all movies",
            description = "Returns list of movies available on the platform"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<MovieResponse>> fetchMovies() {

        List<MovieResponse> movies = movieService.getAllMovies();

        return ResponseEntity.ok(movies);   // 200 OK
    }
}
