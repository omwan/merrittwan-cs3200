package merrittwan.cs3200.controller.drug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import merrittwan.cs3200.entity.Drug;
import merrittwan.cs3200.entity.StudyDrug;
import merrittwan.cs3200.service.drug.DrugService;

/**
 * Created by olivi on 11/28/2017.
 */
@Controller
@CrossOrigin
@RequestMapping("/api/drug")
public class DrugController {

  @Autowired
  private DrugService drugService;

  @RequestMapping(value = "/new", method = RequestMethod.POST)
  @ResponseBody
  public void addNewDrug(@RequestBody Drug drug) {
    drugService.addNewDrug(drug);
  }

  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  @ResponseBody
  public void deleteDrug(@RequestParam(name = "drugId") int drugId) {
    drugService.deleteDrug(drugId);
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public List<Drug> getAllDrugs() {
    return drugService.getAllDrugs();
  }

  @RequestMapping(value = "/study", method = RequestMethod.GET)
  @ResponseBody
  public List<StudyDrug> getDrugsByStudy(@RequestParam(name = "studyId") int studyId) {
    return drugService.getDrugsByStudy(studyId);
  }
}
