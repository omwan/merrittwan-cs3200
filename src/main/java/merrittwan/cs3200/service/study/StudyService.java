package merrittwan.cs3200.service.study;

import java.util.Map;

import merrittwan.cs3200.entity.Patient;
import merrittwan.cs3200.entity.PrincipalInvestigator;
import merrittwan.cs3200.entity.Study;
import merrittwan.cs3200.entity.StudyClinician;

/**
 * Service to perform operations on database relating to studies.
 * Created by olivi on 11/28/2017.
 */
public interface StudyService {

  /**
   * Add a given patient to their study.
   *
   * @param patient patient to add to study
   */
  void addPatientToStudy(Patient patient);

  void updatePatientInfo(Patient patient);

  void addClinicianToStudy(StudyClinician studyClinician);

  void addPrincipalInvestigator(PrincipalInvestigator pi);

  void createStudy(Study study);

  void closeStudy(int studyId);

  /**
   * Retrieve the patient outcomes for a study by the treatment type.
   *
   * @param studyId study to retrieve outcomes for
   * @param placebo treatment type (either placebo or drug)
   * @return resultset containing patient outcomes matching the given parameters.
   */
  Map<String, Object> getOutcomesByTreatmentType(int studyId, boolean placebo);

  Map<String, Object> getAllStudies();

}
