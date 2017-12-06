package merrittwan.cs3200.service.measuredvalue;

import java.util.List;

import merrittwan.cs3200.entity.ClinicianPatientMeasuredValue;
import merrittwan.cs3200.entity.MeasuredValue;

/**
 * Service to manage data in MEASURED_VALUE table.
 * Created by olivi on 11/28/2017.
 */
public interface MeasuredValueService {

  /**
   * Record a measured value for a patient by a clinician.
   *
   * @param recordedValue values to insert on table
   */
  void recordMeasuredValue(ClinicianPatientMeasuredValue recordedValue);

  /**
   * Get all recorded values for a given patient.
   *
   * @param patientId primary key of patient to query on
   * @return list of recorded values for the given patient
   */
  List<ClinicianPatientMeasuredValue> getRecordedValuesForPatient(int patientId);
}
