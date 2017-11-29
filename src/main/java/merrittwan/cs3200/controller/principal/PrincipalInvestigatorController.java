package merrittwan.cs3200.controller.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import merrittwan.cs3200.service.principal.PrincipalInvestigatorService;

/**
 * Created by olivi on 11/28/2017.
 */
@Controller
@RequestMapping("/api/principal")
public class PrincipalInvestigatorController {

  @Autowired
  private PrincipalInvestigatorService principalInvestigatorService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, Object> getAllPrincipalInvestigators() {
    return principalInvestigatorService.getAllPrincipalInvestigators();
  }

}
