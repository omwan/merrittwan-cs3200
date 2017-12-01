package merrittwan.cs3200.service.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by olivi on 11/28/2017.
 */

@Service
public class PrincipalInvestigatorServiceImpl implements PrincipalInvestigatorService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Map<String, Object> getAllPrincipalInvestigators() {
    CallableStatementCreator csc = con -> con.prepareCall("{ call get_all_pis() }");
    return jdbcTemplate.call(csc, new ArrayList<>());
  }
}
