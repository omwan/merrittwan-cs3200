package merrittwan.cs3200.controller.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import merrittwan.cs3200.entity.Patient;
import merrittwan.cs3200.service.patient.PatientService;

/**
 * Controller for API endpoints for patients.
 * Created by olivi on 11/30/2017.
 */
@Controller
@CrossOrigin
@RequestMapping("/api/patient")
public class PatientController {

  @Autowired
  private PatientService patientService;

  /**
   * Get all patients in database.
   *
   * @return list of patients
   */
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public List<Patient> getAllPatients() {
    return patientService.getAllPatients();
  }

  /**
   * Get all patients associated with a given study.
   *
   * @param studyId primary key of study to query on
   * @return list of patients matching the given study id
   */
  @RequestMapping(value = "/study", method = RequestMethod.GET)
  @ResponseBody
  public List<Patient> getPatientsByStudy(@RequestParam(name = "studyId") int studyId) {
    return patientService.getPatientsByStudy(studyId);
  }
}
