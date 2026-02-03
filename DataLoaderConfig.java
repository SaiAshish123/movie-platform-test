package com.movie.booking.config;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

import com.movie.booking.entity.*;
import com.movie.booking.repository.*;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderConfig {


    @Autowired
    private TheatreReporistory theatreRepository;


    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SeatMappingRepository seatMappingRepository;

    @PostConstruct
    public void loadData() {

        Movie movie = new Movie();
        movie.setTitle("Bahubali");
        movie.setLanguage("Telugu");
        movie.setGenre("History");
        movieRepository.save(movie);

        Theatre theatre = new Theatre();
        theatre.setName("PVR Forum");
        theatre.setCity("Bangalore");
        theatre = theatreRepository.save(theatre);
        Show show = new Show();
        show.setMovie(movie);
        show.setTheatre(theatre);
        show.setShowDate(LocalDate.now());
        show.setShowTime(LocalTime.of(14, 30)); // Afternoon show
        showRepository.save(show);

        for (int i = 1; i <= 10; i++) {
            Seat seat = new Seat();
            seat.setShow(show);
            seat.setSeatNumber("A" + i);
            seat.setBooked(false);
            seatMappingRepository.save(seat);
        }
    }
}
