package merrittwan.cs3200.controller.clinician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import merrittwan.cs3200.entity.Clinician;
import merrittwan.cs3200.entity.StudyClinician;
import merrittwan.cs3200.service.clinician.ClinicianService;

/**
 * Created by olivi on 11/28/2017.
 */
@Controller
@CrossOrigin
@RequestMapping("/api/clinician")
public class ClinicianController {

  @Autowired
  private ClinicianService clinicianService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public List<Clinician> getAllDrugs() {
    return clinicianService.getAllClinicians();
  }

  @RequestMapping(value = "/study", method = RequestMethod.GET)
  @ResponseBody
  public List<StudyClinician> getDrugsByStudy(@RequestParam(name = "studyId") int studyId) {
    return clinicianService.getCliniciansByStudy(studyId);
  }
}
