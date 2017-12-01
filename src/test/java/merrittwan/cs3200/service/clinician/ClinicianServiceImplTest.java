package merrittwan.cs3200.service.clinician;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import merrittwan.cs3200.entity.Clinician;
import merrittwan.cs3200.entity.StudyClinician;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

import static org.junit.Assert.assertEquals;

/**
 * Created by olivi on 11/28/2017.
 */
public class ClinicianServiceImplTest {
  @Tested
  private ClinicianService clinicianService;

  @Injectable
  private JdbcTemplate jdbcTemplate;

  @Before
  public void setup() throws Exception {
    clinicianService = new ClinicianServiceImpl();
  }

  /**
   * Assert that the list of clinicians returned by the service matches the values returned
   * from the query.
   */
  @Test
  public void testGetAllClinicians() throws Exception {
    final List<Clinician> expected = new ArrayList<>();
    final Clinician drug = createMockedClinician();
    expected.add(drug);

    new Expectations() {{
      jdbcTemplate.query(anyString, (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<Clinician> actual = clinicianService.getAllClinicians();
    assertEquals(1, actual.size());
    assertEquals(actual.get(0), expected.get(0));
  }

  /**
   * Assert that the list of StudyClinicians returned by the service matches the values returned
   * from the query.
   */
  @Test
  public void testGetCliniciansByStudy() throws Exception {
    final List<StudyClinician> expected = new ArrayList<>();
    final Clinician drug = createMockedClinician();
    final StudyClinician studyClinician = createMockedStudyClinician(drug);
    expected.add(studyClinician);

    final int studyId = 1;

    new Expectations() {{
      jdbcTemplate.query(anyString, new Object[]{studyId}, (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<StudyClinician> actual = clinicianService.getCliniciansByStudy(studyId);
    assertEquals(1, actual.size());
    assertEquals(actual.get(0), expected.get(0));
    assertEquals(actual.get(0).getClinician(), expected.get(0).getClinician());
  }

  /**
   * Create a mocked instance of a clinician.
   *
   * @return mocked Clinician instance.
   */
  private Clinician createMockedClinician() {
    Clinician clinician = new Clinician();
    clinician.setClinicianId(1);
    clinician.setFirstName("first name");
    clinician.setLastName("last name");
    return clinician;
  }

  /**
   * Create a mocked instance of a StudyClinician object with the given clinician.
   *
   * @param clinician clinician to create mocked studyClinician for.
   * @return mocked StudyClinician instance.
   */
  private StudyClinician createMockedStudyClinician(Clinician clinician) {
    StudyClinician studyClinician = new StudyClinician();
    studyClinician.setStudyId(1);
    studyClinician.setClinician(clinician);
    return studyClinician;
  }
}