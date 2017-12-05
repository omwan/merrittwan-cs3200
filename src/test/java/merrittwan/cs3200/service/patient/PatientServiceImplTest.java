package merrittwan.cs3200.service.patient;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import merrittwan.cs3200.entity.Patient;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

import static org.junit.Assert.*;

/**
 * Class to test methods from service for patients.
 * Created by olivi on 12/04/2017.
 */
public class PatientServiceImplTest {

  @Tested
  private PatientService PatientService;

  @Injectable
  private JdbcTemplate jdbcTemplate;

  @Before
  public void setup() throws Exception {
    PatientService = new PatientServiceImpl();
  }

  /**
   * Assert that the list of Patients returned by the service matches the values returned
   * from the query.
   */
  @Test
  public void testGetAllPatients() throws Exception {
    final List<Patient> expected = new ArrayList<>();
    final Patient patient = createMockedPatient();
    expected.add(patient);

    new Expectations() {{
      jdbcTemplate.query(anyString, (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<Patient> actual = PatientService.getAllPatients();
    assertEquals(1, actual.size());
    assertEquals(actual.get(0), patient);
  }

  /**
   * Assert that the list of patients returned by the service matches the values returned
   * from the query.
   */
  @Test
  public void testGetPatientsByStudy() throws Exception {
    final List<Patient> expected = new ArrayList<>();
    final Patient patient = createMockedPatient();
    expected.add(patient);

    final int studyId = 1;

    new Expectations() {{
      jdbcTemplate.query(anyString, new Object[]{studyId}, (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<Patient> actual = PatientService.getPatientsByStudy(studyId);
    assertEquals(1, actual.size());
    assertEquals(actual.get(0), patient);
    assertEquals(actual.get(0).getStudyId(), patient.getStudyId());
  }

  /**
   * Create a mocked instance of a Patient.
   *
   * @return mocked Patient instance.
   */
  private Patient createMockedPatient() {
    Patient Patient = new Patient();
    Patient.setPatientId(1);
    Patient.setFirstName("first name");
    Patient.setLastName("last name");
    return Patient;
  }
}