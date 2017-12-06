package merrittwan.cs3200.service.patient;

import java.util.List;

import merrittwan.cs3200.entity.Patient;

/**
 * Service to manage data in PATIENT table.
 * Created by olivi on 11/30/2017.
 */
public interface PatientService {

  /**
   * Get all patients in database.
   *
   * @return list of patients
   */
  List<Patient> getAllPatients();

  /**
   * Get all patients associated with a given study.
   *
   * @param studyId primary key of study to query on
   * @return list of patients matching the given study id
   */
  List<Patient> getPatientsByStudy(int studyId);
}
