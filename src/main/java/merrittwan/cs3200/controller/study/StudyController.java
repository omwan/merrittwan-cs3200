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
import merrittwan.cs3200.service.study.StudyService;

/**
 * Created by olivi on 11/28/2017.
 */
@Controller
@RequestMapping("/api/study")
public class StudyController {

  @Autowired
  private StudyService studyService;

  @RequestMapping(value = "/patient", method = RequestMethod.POST)
  @ResponseBody
  public void addPatientToStudy(@RequestBody Patient patient) {
    studyService.addPatientToStudy(patient);
  }

  @RequestMapping(value = "/treatment", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, Object> getOutcomesByTreatmentType(@RequestParam(name = "studyId") int studyId,
                                                        @RequestParam(name = "placebo") boolean placebo) {
    return studyService.getOutcomesByTreatmentType(studyId, placebo);
  }
}
