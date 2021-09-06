package training360.guinessapp;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;

import javax.transaction.Transactional;
import java.time.LocalDate;

@AllArgsConstructor
@Service
public class WorldRecordService {

    private WorldRecordRepository repository;
    private ModelMapper modelMapper;
    private RecorderRepository recorderRepository;

    public WorldRecordDto createWorldRecord(WorldRecordCreateCommand command) {
        Recorder recorder = recorderRepository.findById(command.getRecorderId())
                .orElseThrow(() -> new IllegalArgumentException("Recorder with id: " + command.getRecorderId() + " not found."));
        WorldRecord worldRecord = new WorldRecord(command.getDescription(), command.getValue(), command.getUnitOfMeasure(), command.getDateOfRecord(), recorder);
        repository.save(worldRecord);
        return modelMapper.map(worldRecord, WorldRecordDto.class);
    }

    @Transactional
    public BeatWorldRecordDto beatRecord(long id, BeatWorldRecordCommand command) {
        WorldRecord worldRecord = repository.findById(id)
                .orElseThrow(() -> new WorldRecordNotFoundException("WorldRecord with id: " + command.getRecorderId() + " not found."));
        Recorder recorder = recorderRepository.findById(command.getRecorderId())
                .orElseThrow(() -> new IllegalArgumentException("Recorder with id: " + command.getRecorderId() + " not found."));
        BeatWorldRecordDto beatWorldRecordDto = new BeatWorldRecordDto(worldRecord.getDescription(), worldRecord.getValue(),
                command.getNewRecord(), worldRecord.getUnitOfMeasure(), LocalDate.now(),
                modelMapper.map(worldRecord.getRecorder(), RecorderDto.class),
                modelMapper.map(recorder, RecorderDto.class)
        );
        if (command.getNewRecord() > worldRecord.getValue()) {
            worldRecord.setRecorder(recorder);
            return beatWorldRecordDto;
        }
        throw new IllegalStateException("Bad world record value.");
    }
}
