package merrittwan.cs3200.controller.drug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import merrittwan.cs3200.entity.Drug;
import merrittwan.cs3200.entity.StudyDrug;
import merrittwan.cs3200.service.drug.DrugService;

/**
 * Controller for API endpoints for drugs.
 * Created by olivi on 11/28/2017.
 */
@Controller
@CrossOrigin
@RequestMapping("/api/drug")
public class DrugController {

  @Autowired
  private DrugService drugService;

  /**
   * Add a new drug to the database.
   *
   * @param drug values to add to drug table
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  @ResponseBody
  public void addNewDrug(@RequestBody Drug drug) {
    drugService.addNewDrug(drug);
  }

  /**
   * Delete a given drug from the database.
   *
   * @param drugId primary key of drug to be deleted
   */
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  @ResponseBody
  public void deleteDrug(@RequestParam(name = "drugId") int drugId) {
    drugService.deleteDrug(drugId);
  }

  /**
   * Get all drugs in the database.
   *
   * @return list of drugs
   */
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public List<Drug> getAllDrugs() {
    return drugService.getAllDrugs();
  }

  /**
   * Get all drugs associated with a given study.
   *
   * @param studyId primary key of study to query on
   * @return list of drugs associated with the given study id
   */
  @RequestMapping(value = "/study", method = RequestMethod.GET)
  @ResponseBody
  public List<StudyDrug> getDrugsByStudy(@RequestParam(name = "studyId") int studyId) {
    return drugService.getDrugsByStudy(studyId);
  }
}
