package merrittwan.cs3200.controller.institution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import merrittwan.cs3200.entity.Institution;
import merrittwan.cs3200.service.institution.InstitutionService;

/**
 * Controller for API endpoints for institutions.
 * Created by olivi on 11/29/2017.
 */
@Controller
@RequestMapping("/api/institution")
public class InstitutionController {

  @Autowired
  private InstitutionService institutionService;

  /**
   * Get all institutions in database.
   * @return list of institutions.
   */
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public List<Institution> getAllInstitutions() {
    return institutionService.getAllInstitutions();
  }

}
