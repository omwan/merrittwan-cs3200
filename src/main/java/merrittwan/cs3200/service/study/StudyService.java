package merrittwan.cs3200.service.study;

import java.util.Map;

import merrittwan.cs3200.entity.Patient;

/**
 * Created by olivi on 11/28/2017.
 */
public interface StudyService {

  void addPatientToStudy(Patient patient);

  Map<String, Object> getOutcomesByTreatmentType(int studyId, boolean placebo);

}
