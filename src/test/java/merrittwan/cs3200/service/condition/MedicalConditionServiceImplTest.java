package merrittwan.cs3200.service.condition;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import merrittwan.cs3200.entity.MedicalCondition;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

import static org.junit.Assert.*;

/**
 * Class to test methods from service for medical conditions.
 * Created by olivi on 11/28/2017.
 */
public class MedicalConditionServiceImplTest {

  @Tested
  private MedicalConditionService medicalConditionService;

  @Injectable
  private JdbcTemplate jdbcTemplate;

  @Before
  public void setup() throws Exception {
    medicalConditionService = new MedicalConditionServiceImpl();
  }

  /**
   * Assert that the list of drugs returned by the service matches the values returned
   * from the query.
   */
  @Test
  public void testGetAllMedicalConditions() throws Exception {
    final List<MedicalCondition> expected = new ArrayList<>();
    final MedicalCondition medicalCondition = createMockedMedicalCondition();
    expected.add(medicalCondition);

    new Expectations() {{
      jdbcTemplate.query(anyString, (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<MedicalCondition> actual = medicalConditionService.getAllMedicalConditions();
    assertEquals(1, actual.size());
    assertEquals(actual.get(0), expected.get(0));
  }

  /**
   * Create a mocked instance of a drug.
   *
   * @return mocked MedicalCondition instance.
   */
  private MedicalCondition createMockedMedicalCondition() {
    MedicalCondition medicalCondition = new MedicalCondition();
    medicalCondition.setConditionId(1);
    medicalCondition.setName("name");
    medicalCondition.setDescription("description");
    return medicalCondition;
  }
}