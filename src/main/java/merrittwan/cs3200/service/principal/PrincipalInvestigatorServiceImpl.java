package merrittwan.cs3200.service.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * Service to manage data in PRINCIPAL_INVESTIGATOR table.
 * Created by olivi on 11/28/2017.
 */
@Service
public class PrincipalInvestigatorServiceImpl implements PrincipalInvestigatorService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * Get all principal investigators in the database.
   * @return list of principal investigators.
   */
  @Override
  public Map<String, Object> getAllPrincipalInvestigators() {
    CallableStatementCreator csc = con -> con.prepareCall("{ call get_all_pis() }");
    return jdbcTemplate.call(csc, new ArrayList<>());
  }
}
