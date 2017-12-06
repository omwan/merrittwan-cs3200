package merrittwan.cs3200.controller.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import merrittwan.cs3200.entity.Patient;
import merrittwan.cs3200.entity.PrincipalInvestigator;
import merrittwan.cs3200.entity.Study;
import merrittwan.cs3200.entity.StudyClinician;
import merrittwan.cs3200.service.study.StudyService;

/**
 * Controller for endpoints to perform operations relating to studies.
 * Created by olivi on 11/28/2017.
 */
@Controller
@CrossOrigin
@RequestMapping("/api/study")
public class StudyController {

  @Autowired
  private StudyService studyService;

  /**
   * Add a given patient to their study.
   *
   * @param patient patient to add to study
   */
  @RequestMapping(value = "/patient", method = RequestMethod.POST)
  @ResponseBody
  public void addPatientToStudy(@RequestBody Patient patient) {
    studyService.addPatientToStudy(patient);
  }

  /**
   * Update the information for a given patient.
   *
   * @param patient patient object with modified values.
   */
  @RequestMapping(value = "/patient", method = RequestMethod.PUT)
  @ResponseBody
  public void updatePatientInformation(@RequestBody Patient patient) {
    studyService.updatePatientInfo(patient);
  }

  /**
   * Add a given clinician to a study.
   *
   * @param studyClinician object containing study and clinician information.
   */
  @RequestMapping(value = "/clinician", method = RequestMethod.POST)
  @ResponseBody
  public void addClinicianToStudy(@RequestBody StudyClinician studyClinician) {
    studyService.addClinicianToStudy(studyClinician);
  }

  /**
   * Add a principal investigator to the database.
   *
   * @param pi principal investigator object to add
   */
  @RequestMapping(value = "/principalinvestigator", method = RequestMethod.POST)
  @ResponseBody
  public void addNewPrincipalInvestigator(@RequestBody PrincipalInvestigator pi) {
    studyService.addPrincipalInvestigator(pi);
  }

  /**
   * Create a new study.
   *
   * @param study object containing study information
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  @ResponseBody
  public void createStudy(@RequestBody Study study) {
    studyService.createStudy(study);
  }

  /**
   * Close a study.
   *
   * @param studyId primary key of study to close.
   */
  @RequestMapping(value = "/new", method = RequestMethod.PUT)
  @ResponseBody
  public void closeStudy(@RequestBody int studyId) {
    studyService.closeStudy(studyId);
  }

  /**
   * Retrieve the patient outcomes for a study by the treatment type.
   *
   * @param studyId study to retrieve outcomes for
   * @param placebo treatment type (either placebo or drug)
   * @return resultset containing patient outcomes matching the given parameters.
   */
  @RequestMapping(value = "/treatment", method = RequestMethod.GET)
  @ResponseBody
  public List<Patient> getOutcomesByTreatmentType(@RequestParam(name = "studyId") int studyId,
                                                  @RequestParam(name = "placebo") boolean placebo) {
    return studyService.getOutcomesByTreatmentType(studyId, placebo);
  }

  /**
   * Get a list of patients matching the given parameters for the given study.
   *
   * @param studyId         primary key of study to query on
   * @param characteristics column names and values to query on
   * @return list of patients matching the given parameters.
   */
  @RequestMapping(value = "/characteristics", method = RequestMethod.POST)
  @ResponseBody
  public List<Patient> getOutcomesByPatientCharacteristics(@RequestParam(name = "studyId") int studyId,
                                                           @RequestBody Map<String, Object> characteristics) {
    return studyService.getOutcomesByPatientCharacteristics(studyId, characteristics);
  }

  /**
   * Retrieve a list of all studies in the database.
   *
   * @return list of studies.
   */
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, Object> getAllStudies() {
    return studyService.getAllStudies();
  }
}
