package merrittwan.cs3200.service.study;

import java.util.List;
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

  /**
   * Update the information for a given patient.
   *
   * @param patient patient object with modified values.
   */
  void updatePatientInfo(Patient patient);

  /**
   * Add a given clinician to a study.
   *
   * @param studyClinician object containing study and clinician information.
   */
  void addClinicianToStudy(StudyClinician studyClinician);

  /**
   * Add a principal investigator to the database.
   *
   * @param pi principal investigator object to add
   */
  void addPrincipalInvestigator(PrincipalInvestigator pi);

  /**
   * Create a new study.
   *
   * @param study object containing study information
   */
  void createStudy(Study study);

  /**
   * Close a study.
   *
   * @param studyId primary key of study to close.
   */
  void closeStudy(int studyId);

  /**
   * Retrieve the patient outcomes for a study by the treatment type.
   *
   * @param studyId study to retrieve outcomes for
   * @param placebo treatment type (either placebo or drug)
   * @return list of patients matching the given parameters.
   */
  List<Patient> getOutcomesByTreatmentType(int studyId, boolean placebo);

  /**
   * Get a list of patients matching the given parameters for the given study.
   *
   * @param studyId         primary key of study to query on
   * @param characteristics column names and values to query on
   * @return list of patients matching the given parameters.
   */
  List<Patient> getOutcomesByPatientCharacteristics(int studyId,
                                                    Map<String, Object> characteristics);

  /**
   * Retrieve a list of all studies in the database.
   *
   * @return list of studies.
   */
  Map<String, Object> getAllStudies();

}
