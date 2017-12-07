package merrittwan.cs3200.controller.clinician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import merrittwan.cs3200.entity.Clinician;
import merrittwan.cs3200.entity.StudyClinician;
import merrittwan.cs3200.service.clinician.ClinicianService;

/**
 * Controller for API endpoints for clinicians.
 * Created by olivi on 11/28/2017.
 */
@Controller
@CrossOrigin
@RequestMapping("/api/clinician")
public class ClinicianController {

  @Autowired
  private ClinicianService clinicianService;

  /**
   * Get all clinicians in database.
   *
   * @return list of clinicians
   */
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public List<Clinician> getAllDrugs() {
    return clinicianService.getAllClinicians();
  }

  /**
   * Get all clinicians associated with a given study.
   *
   * @param studyId primary key of study to query on
   * @return list of clinicians associated with the given study id
   */
  @RequestMapping(value = "/study", method = RequestMethod.GET)
  @ResponseBody
  public List<StudyClinician> getDrugsByStudy(@RequestParam(name = "studyId") int studyId) {
    return clinicianService.getCliniciansByStudy(studyId);
  }
}
