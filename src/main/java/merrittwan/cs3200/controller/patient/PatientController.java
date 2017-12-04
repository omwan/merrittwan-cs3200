package merrittwan.cs3200.controller.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import merrittwan.cs3200.entity.Patient;
import merrittwan.cs3200.service.patient.PatientService;

/**
 * Created by olivi on 11/30/2017.
 */
@Controller
@CrossOrigin
@RequestMapping("/api/patient")
public class PatientController {

  @Autowired
  private PatientService patientService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public List<Patient> getAllPatients() {
    return patientService.getAllPatients();
  }

  @RequestMapping(value = "/study", method = RequestMethod.GET)
  @ResponseBody
  public List<Patient> getPatientsByStudy(@RequestParam(name = "studyId") int studyId) {
    return patientService.getPatientsByStudy(studyId);
  }
}
