package merrittwan.cs3200.service.study;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import merrittwan.cs3200.entity.Address;
import merrittwan.cs3200.entity.Clinician;
import merrittwan.cs3200.entity.Institution;
import merrittwan.cs3200.entity.MedicalCondition;
import merrittwan.cs3200.entity.Patient;
import merrittwan.cs3200.entity.PrincipalInvestigator;
import merrittwan.cs3200.entity.Study;
import merrittwan.cs3200.entity.StudyClinician;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;

import static org.junit.Assert.assertEquals;

/**
 * Class to test methods from service for studies.
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

  /**
   * Assert that adding a patient to study calls the appropriate jdbcTemplate methods,
   * and that a commit is made.
   */
  @Test
  public void testAddPatientToStudy() throws Exception {
    final Patient patient = createMockedPatient();
    mockGetAddressId();

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.update(anyString, patient.getFirstName(), patient.getLastName(), patient.getDob(),
              patient.getSex().toString(), patient.getHometown(), patient.getNationality(),
              patient.getRace(), patient.getEthnicity(), patient.getStudyId(), patient.isPlacebo(),
              1, patient.isHealthy());

      platformTransactionManager.commit(transactionStatus);
    }};

    studyService.addPatientToStudy(patient);
  }

  /**
   * Asserts that if an insert throws an exception, the transaction is rolled back.
   */
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
              patient.getRace(), patient.getEthnicity(), patient.getStudyId(), patient.isPlacebo(),
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

  /**
   * Assert that updating patient information calls the appropriate jdbcTemplate methods,
   * and that a commit is made.
   */
  @Test
  public void testUpdatePatientInfo() throws Exception {
    final Patient patient = createMockedPatient();
    mockGetAddressId();

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.update(anyString, patient.getFirstName(), patient.getLastName(), patient.getDob(),
              patient.getSex().toString(), patient.getHometown(), patient.getNationality(),
              patient.getRace(), patient.getEthnicity(), 1, patient.getPatientId());

      platformTransactionManager.commit(transactionStatus);
    }};

    studyService.updatePatientInfo(patient);
  }

  /**
   * Asserts that if the update throws an exception, the transaction is rolled back.
   */
  @Test
  public void testUpdatePatientInfoUpdateError() throws Exception {
    final Patient patient = createMockedPatient();
    mockGetAddressId();

    final Exception e = new Exception("update error");

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.update(anyString, patient.getFirstName(), patient.getLastName(), patient.getDob(),
              patient.getSex().toString(), patient.getHometown(), patient.getNationality(),
              patient.getRace(), patient.getEthnicity(), 1, patient.getPatientId());
      result = e;

      platformTransactionManager.rollback(transactionStatus);
    }};

    try {
      studyService.updatePatientInfo(patient);
    } catch (Exception ex) {
      assertEquals(e, ex);
    }
  }

  /**
   * Assert that adding a patient to study calls the appropriate jdbcTemplate methods,
   * and that a commit is made.
   */
  @Test
  public void testAddClinicianToStudy() throws Exception {
    final StudyClinician studyClinician = createMockedStudyClinician();
    mockGetClinicianId();

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.update(anyString, 1, studyClinician.getStudyId());

      platformTransactionManager.commit(transactionStatus);
    }};

    studyService.addClinicianToStudy(studyClinician);
  }

  /**
   * Asserts that if an insert throws an exception, the transaction is rolled back.
   */
  @Test
  public void testAddClinicianToStudyUpdateError() throws Exception {
    final StudyClinician studyClinician = createMockedStudyClinician();
    mockGetClinicianId();
    final Exception e = new Exception("error in insertion");

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.update(anyString, 1, studyClinician.getStudyId());
      result = e;

      platformTransactionManager.rollback(transactionStatus);
    }};

    try {
      studyService.addClinicianToStudy(studyClinician);
    } catch (Exception ex) {
      assertEquals(e, ex);
    }
  }

  /**
   * Assert that adding a pi to the database calls the appropriate jdbcTemplate methods,
   * and that a commit is made.
   */
  @Test
  public void testAddPrincipalInvestigator() throws Exception {
    final PrincipalInvestigator pi = createMockedPrincipalInvestigator();
    mockGetAddressId();
    mockGetInstitutionId();

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.update(anyString, pi.getFirstName(), pi.getLastName(), pi.getPhone(),
              pi.getEmail(), 1, 1);

      platformTransactionManager.commit(transactionStatus);
    }};

    studyService.addPrincipalInvestigator(pi);
  }

  /**
   * Asserts that if an insert throws an exception, the transaction is rolled back.
   */
  @Test
  public void testAddPrincipalInvestigatorInsertionError() throws Exception {
    final PrincipalInvestigator pi = createMockedPrincipalInvestigator();
    mockGetAddressId();
    mockGetInstitutionId();
    final Exception e = new Exception("error in insertion");

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.update(anyString, pi.getFirstName(), pi.getLastName(), pi.getPhone(),
              pi.getEmail(), 1, 1);
      result = e;

      platformTransactionManager.rollback(transactionStatus);
    }};
    try {
      studyService.addPrincipalInvestigator(pi);
    } catch (Exception ex) {
      assertEquals(e, ex);
    }
  }

  /**
   * Asserts that creating a study calls the appropriate jdbcTemplate methods and
   * that a commit is made.
   */
  @Test
  public void testCreateStudy() throws Exception {
    final Study study = createMockedStudy();
    mockGetConditionId();
    mockInsertStudy();

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.batchUpdate(anyString, (BatchPreparedStatementSetter) any);

      platformTransactionManager.commit(transactionStatus);
    }};

    studyService.createStudy(study);
  }

  /**
   * Asserts that if an insert throws an exception, the transaction is rolled back.
   */
  @Test
  public void testCreateStudyInsertionError() throws Exception {
    final Study study = createMockedStudy();
    mockGetConditionId();
    mockInsertStudy();

    final Exception e = new Exception("insertion error");

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.batchUpdate(anyString, (BatchPreparedStatementSetter) any);
      result = e;

      platformTransactionManager.rollback(transactionStatus);
    }};

    try {
      studyService.createStudy(study);
    } catch (Exception ex) {
      assertEquals(e, ex);
    }
  }

  /**
   * Asserts that service to close a study calls the appropriate jdbctemplate method
   * with the appropriate parameters.
   */
  @Test
  public void testCloseStudy() throws Exception {
    final int studyId = 1;

    new Expectations() {{
      jdbcTemplate.update(anyString, studyId);
    }};

    studyService.closeStudy(1);
  }

  /**
   * Asserts that the service to retrieve patient outcomes by characteristics calls
   * the appropriate jdbcTemplate method with the appropriate parameters.
   */
  @Test
  public void testGetOutcomesByTreatmentType() throws Exception {
    final List<Patient> expected = new ArrayList<>();
    final Patient patient = createMockedPatient();
    expected.add(patient);

    final int studyId = 1;
    final boolean placebo = true;

    new Expectations() {{
      jdbcTemplate.query(anyString, new Object[] {studyId, placebo},
              (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<Patient> actual = studyService.getOutcomesByTreatmentType(studyId, placebo);
    assertEquals(1, actual.size());
    assertEquals(actual.get(0), patient);
  }

  /**
   * Asserts that the service to retrieve patient outcomes by characteristics calls
   * the appropriate jdbcTemplate method with the appropriate parameters.
   */
  @Test
  public void testGetOutcomesByPatientCharacteristics() throws Exception {
    final List<Patient> expected = new ArrayList<>();
    final Patient patient = createMockedPatient();
    expected.add(patient);

    final int studyId = 1;
    final Map<String, Object> characteristics = new HashMap<>();
    characteristics.put("race", "White");

    new Expectations() {{
      jdbcTemplate.query((PreparedStatementCreator) any, (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<Patient> actual = studyService.getOutcomesByPatientCharacteristics(studyId,
            characteristics);
    assertEquals(1, actual.size());
    assertEquals(actual.get(0), expected.get(0));
  }

  /**
   * Assert that the resultset returned by the service matches the values returned
   * from the query.
   */
  @Test
  public void testGetAllStudies() throws Exception {
    final Map<String, Object> expected = new HashMap<>();
    expected.put("start_date", new Date());
    expected.put("end_date", new Date());

    new Expectations() {{
      jdbcTemplate.call((CallableStatementCreator) any, (List<SqlParameter>) any);
      returns(expected);
    }};

    Map<String, Object> actual = studyService.getAllStudies();
    for (String key : actual.keySet()) {
      assertEquals(actual.get(key), expected.get(key));
    }
  }

  private MockUp<StudyServiceImpl> mockGetAddressId() {
    return new MockUp<StudyServiceImpl>() {
      @Mock
      int getAddressId(Address address) {
        return 1;
      }
    };
  }

  private MockUp<StudyServiceImpl> mockGetClinicianId() {
    return new MockUp<StudyServiceImpl>() {
      @Mock
      int getClinicianId(Clinician clinician) {
        return 1;
      }
    };
  }

  private MockUp<StudyServiceImpl> mockGetInstitutionId() {
    return new MockUp<StudyServiceImpl>() {
      @Mock
      int getInstitutionId(Institution clinician) {
        return 1;
      }
    };
  }

  private MockUp<StudyServiceImpl> mockGetConditionId() {
    return new MockUp<StudyServiceImpl>() {
      @Mock
      int getConditionId(MedicalCondition medicalCondition) {
        return 1;
      }
    };
  }

  private MockUp<StudyServiceImpl> mockInsertStudy() {
    return new MockUp<StudyServiceImpl>() {
      @Mock
      int insertStudy(Study study) {
        return 1;
      }
    };
  }

  private StudyClinician createMockedStudyClinician() {
    StudyClinician studyClinician = new StudyClinician();
    Clinician clinician = new Clinician();
    studyClinician.setClinician(clinician);
    studyClinician.setStudyId(1);
    return studyClinician;
  }

  private PrincipalInvestigator createMockedPrincipalInvestigator() {
    PrincipalInvestigator pi = new PrincipalInvestigator();
    pi.setFirstName("first name");
    pi.setLastName("last name");
    return pi;
  }

  /**
   * Create a mocked instance of a patient object.
   *
   * @return mocked patient object.
   */
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
    patient.setStudyId(1);
    patient.setPlacebo(true);
    Address address = new Address();
    patient.setAddress(address);
    patient.setHealthy(true);
    return patient;
  }

  private Study createMockedStudy() {
    Study study = new Study();
    PrincipalInvestigator pi = new PrincipalInvestigator();
    pi.setPrincipalInvestigatorId(1);
    study.setPrincipalInvestigator(pi);
    return study;
  }

}