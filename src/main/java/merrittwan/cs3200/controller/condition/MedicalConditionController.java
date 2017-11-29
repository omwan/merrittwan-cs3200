package merrittwan.cs3200.controller.condition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import merrittwan.cs3200.entity.MedicalCondition;
import merrittwan.cs3200.service.condition.MedicalConditionService;

/**
 * Created by olivi on 11/28/2017.
 */
@Controller
@RequestMapping("/api/condition")
public class MedicalConditionController {

  @Autowired
  private MedicalConditionService medicalConditionService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public List<MedicalCondition> getAllMedicalConditions() {
    return medicalConditionService.getAllMedicalConditions();
  }
}
