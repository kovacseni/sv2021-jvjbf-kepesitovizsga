package training360.guinessapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training360.guinessapp.dto.RecorderDto;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeatWorldRecordDto {

    private String description;
    private Double oldRecordValue;
    private Double newRecordValue;
    private String unitOfMeasure;
    private LocalDate dateOfRecord;
    private RecorderDto oldRecorder;
    private RecorderDto newRecorder;

    public String getOldRecorderName() {
        return oldRecorder.getName();
    }

    public String getNewRecorderName() {
        return newRecorder.getName();
    }

    public Double getRecordDifference() {
        return Math.abs(oldRecordValue - newRecordValue);
    }
}
