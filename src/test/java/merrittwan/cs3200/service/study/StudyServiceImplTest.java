package merrittwan.cs3200.service.study;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import merrittwan.cs3200.entity.Address;
import merrittwan.cs3200.entity.Patient;
import merrittwan.cs3200.entity.Study;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;

import static org.junit.Assert.assertEquals;

/**
 * Created by olivi on 11/28/2017.
 */
public class StudyServiceImplTest {

  @Tested
  private StudyService studyService;

  @Injectable
  private JdbcTemplate jdbcTemplate;

  @Injectable
  private PlatformTransactionManager platformTransactionManager;

  @Injectable
  private TransactionStatus transactionStatus;

  @Before
  public void setup() throws Exception {
    studyService = new StudyServiceImpl();
  }

  @Test
  public void testAddPatientToStudy() throws Exception {
    final Patient patient = createMockedPatient();
    mockGetAddressId();

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.update(anyString, patient.getFirstName(), patient.getLastName(), patient.getDob(),
              patient.getSex().toString(), patient.getHometown(), patient.getNationality(),
              patient.getRace(), patient.getEthnicity(), patient.getStudy().getStudyId(), patient.isPlacebo(),
              1, patient.isHealthy());

      platformTransactionManager.commit(transactionStatus);
    }};

    studyService.addPatientToStudy(patient);
  }

  @Test
  public void testAddPatientToStudyInsertionError() throws Exception {
    final Patient patient = createMockedPatient();
    mockGetAddressId();
    final Exception e = new Exception("error in insertion");

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.update(anyString, patient.getFirstName(), patient.getLastName(), patient.getDob(),
              patient.getSex().toString(), patient.getHometown(), patient.getNationality(),
              patient.getRace(), patient.getEthnicity(), patient.getStudy().getStudyId(), patient.isPlacebo(),
              1, patient.isHealthy());
      result = e;

      platformTransactionManager.rollback(transactionStatus);
    }};
    try {
      studyService.addPatientToStudy(patient);
    } catch (Exception ex) {
      assertEquals(e, ex);
    }
  }

  @Test
  public void testGetOutcomesByTreatmentType() throws Exception {
    final Map<String, Object> expected = new HashMap<>();

    new Expectations() {{
      jdbcTemplate.call((CallableStatementCreator) any, (List<SqlParameter>) any);
      returns(expected);
    }};

    Map<String, Object> actual = studyService.getOutcomesByTreatmentType(1, true);
    assertEquals(actual, expected);
  }

  private MockUp<StudyServiceImpl> mockGetAddressId() {
    return new MockUp<StudyServiceImpl>() {
      @Mock
      int getAddressId(Address address) {
        return 1;
      }
    };
  }

  private Patient createMockedPatient() {
    Patient patient = new Patient();
    patient.setFirstName("firstname");
    patient.setLastName("lastname");
    patient.setDob(new Date());
    patient.setSex(Patient.Sex.FEMALE);
    patient.setHometown("hometown");
    patient.setNationality("nationality");
    patient.setRace("race");
    patient.setEthnicity("ethnicity");
    Study study = new Study();
    study.setStudyId(1);
    patient.setStudy(study);
    patient.setPlacebo(true);
    Address address = new Address();
    patient.setAddress(address);
    patient.setHealthy(true);
    return patient;
  }

}