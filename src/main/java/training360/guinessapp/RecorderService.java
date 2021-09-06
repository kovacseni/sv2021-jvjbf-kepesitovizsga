package training360.guinessapp;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.RecorderShortDto;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RecorderService {

    private RecorderRepository repository;
    private ModelMapper modelMapper;

    public RecorderDto createRecorder(RecorderCreateCommand command) {
        Recorder recorder = new Recorder(command.getName(), command.getDateOfBirth());
        repository.save(recorder);
        return modelMapper.map(recorder, RecorderDto.class);
    }

    public List<RecorderShortDto> listRecorders() {
        Type targetListType = new TypeToken<List<RecorderDto>>() {
        }.getType();
        List<Recorder> recorders = repository.findRecordersByConditions();
        return modelMapper.map(recorders, targetListType);
    }
}
