package merrittwan.cs3200.service.institution;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import merrittwan.cs3200.entity.Address;
import merrittwan.cs3200.entity.Institution;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

import static org.junit.Assert.assertEquals;

/**
 * Class to test methods from service for institutions.
 * Created by olivi on 12/04/2017.
 */
public class InstitutionServiceImplTest {

  @Tested
  private InstitutionService institutionService;

  @Injectable
  private JdbcTemplate jdbcTemplate;

  @Before
  public void setup() throws Exception {
    institutionService = new InstitutionServiceImpl();
  }

  /**
   * Assert that the list of institutions returned by the service matches the values returned
   * from the query.
   */
  @Test
  public void testGetAllInstitutions() throws Exception {
    final List<Institution> expected = new ArrayList<>();
    final Institution institution = createMockedInstitution();
    expected.add(institution);

    new Expectations() {{
      jdbcTemplate.query(anyString, (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<Institution> actual = institutionService.getAllInstitutions();
    assertEquals(1, actual.size());
    assertEquals(actual.get(0), institution);
  }

  /**
   * Create a mocked instance of an institution.
   *
   * @return mocked Institution instance.
   */
  private Institution createMockedInstitution() {
    Institution institution = new Institution();
    institution.setName("name");
    institution.setAddress(new Address());
    institution.setType(Institution.InstitutionType.HOSPITAL);
    return institution;
  }
}