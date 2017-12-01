package merrittwan.cs3200.service.measuredvalue;

import java.util.List;

import merrittwan.cs3200.entity.ClinicianPatientMeasuredValue;
import merrittwan.cs3200.entity.MeasuredValue;

/**
 * Created by olivi on 11/28/2017.
 */
public interface MeasuredValueService {

  void recordMeasuredValue(ClinicianPatientMeasuredValue recordedValue);

  List<ClinicianPatientMeasuredValue> getRecordedValuesForPatient(int patientId);
}
