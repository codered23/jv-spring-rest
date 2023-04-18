package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.CinemaHallRequest;
import mate.academy.spring.dto.CinemaHallResponse;
import mate.academy.spring.mapper.CinemaHallMapper;
import mate.academy.spring.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final CinemaHallMapper cinemaHallMapper;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @GetMapping
    List<CinemaHallResponse> getAll() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallMapper::parse)
                .collect(Collectors.toList());
    }

    @PostMapping
    CinemaHallResponse add(@RequestBody CinemaHallRequest cinemaHallRequest) {
        return cinemaHallMapper.parse(cinemaHallService.add(cinemaHallMapper
                .toModel(cinemaHallRequest)));
    }
}
