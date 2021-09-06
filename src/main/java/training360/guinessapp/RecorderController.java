package training360.guinessapp;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.RecorderShortDto;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/recorders")
public class RecorderController {

    private RecorderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecorderDto createRecorder(@Valid @RequestBody RecorderCreateCommand command) {
        return service.createRecorder(command);
    }

    @GetMapping
    public List<RecorderShortDto> listRecorders() {
        return service.listRecorders();
    }
}
