package merrittwan.cs3200.service.principal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

import static org.junit.Assert.assertEquals;

/**
 * Created by olivi on 12/04/2017.
 */
public class PrincipalInvestigatorServiceImplTest {

  @Tested
  private PrincipalInvestigatorService principalInvestigatorService;

  @Injectable
  private JdbcTemplate jdbcTemplate;

  @Before
  public void setup() throws Exception {
    principalInvestigatorService = new PrincipalInvestigatorServiceImpl();
  }

  /**
   * Assert that the resultset returned by the service matches the values returned
   * from the query.
   */
  @Test
  public void testGetAllPrincipalInvestigators() throws Exception {
    final Map<String, Object> expected = new HashMap<>();
    expected.put("last_name", "name");
    expected.put("first_name", "name");

    new Expectations() {{
      jdbcTemplate.call((CallableStatementCreator) any, (List<SqlParameter>) any);
      returns(expected);
    }};

    Map<String, Object> actual = principalInvestigatorService.getAllPrincipalInvestigators();
    for (String key : actual.keySet()) {
      assertEquals(actual.get(key), expected.get(key));
    }
  }
}