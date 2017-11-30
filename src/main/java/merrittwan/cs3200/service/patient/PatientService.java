package merrittwan.cs3200.service.patient;

import java.util.List;

import merrittwan.cs3200.entity.Patient;

/**
 * Created by olivi on 11/30/2017.
 */
public interface PatientService {

  List<Patient> getAllPatients();

  List<Patient> getPatientsByStudy(int studyId);
}
