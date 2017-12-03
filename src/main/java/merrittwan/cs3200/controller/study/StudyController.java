package merrittwan.cs3200.controller.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

  @RequestMapping(value = "/patient", method = RequestMethod.PUT)
  @ResponseBody
  public void updatePatientInformation(@RequestBody Patient patient) {
    studyService.updatePatientInfo(patient);
  }

  @RequestMapping(value = "/clinician", method = RequestMethod.POST)
  @ResponseBody
  public void addClinicianToStudy(@RequestBody StudyClinician studyClinician) {
    studyService.addClinicianToStudy(studyClinician);
  }

  @RequestMapping(value = "/principalinvestigator", method = RequestMethod.POST)
  @ResponseBody
  public void addNewPrincipalInvestigator(@RequestBody PrincipalInvestigator pi) {
    studyService.addPrincipalInvestigator(pi);
  }

  @RequestMapping(value = "/new", method = RequestMethod.POST)
  @ResponseBody
  public void createStudy(@RequestBody Study study) {
    studyService.createStudy(study);
  }

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
  public Map<String, Object> getOutcomesByTreatmentType(@RequestParam(name = "studyId") int studyId,
                                                        @RequestParam(name = "placebo") boolean placebo) {
    return studyService.getOutcomesByTreatmentType(studyId, placebo);
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, Object> getAllStudies() {
    return studyService.getAllStudies();
  }
}
