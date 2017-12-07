package merrittwan.cs3200.service.measuredvalue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import merrittwan.cs3200.entity.ClinicianPatientMeasuredValue;
import merrittwan.cs3200.entity.MeasuredValue;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;

import static org.junit.Assert.assertEquals;

/**
 * Class to test methods from service for measured values.
 * Created by olivi on 12/04/2017.
 */
public class MeasuredValueServiceImplTest {

  @Tested
  private MeasuredValueService measuredValueService;

  @Injectable
  private JdbcTemplate jdbcTemplate;

  @Injectable
  private PlatformTransactionManager platformTransactionManager;

  @Injectable
  private TransactionStatus transactionStatus;

  @Before
  public void setup() throws Exception {
    measuredValueService = new MeasuredValueServiceImpl();
  }

  /**
   * Assert that service to record measured value inserts on the correct table and that
   * a commit is made following a successful insert.
   */
  @Test
  public void testRecordMeasuredValue() throws Exception {
    final ClinicianPatientMeasuredValue recordedValue = createMockedClinicianPatientMeasuredValue(1);
    mockMeasuredValueId();

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.update(anyString, recordedValue.getPatientId(), 1, recordedValue.getClinicianId(),
              recordedValue.getValueMeasure(), recordedValue.getDate());

      platformTransactionManager.commit(transactionStatus);
    }};

    measuredValueService.recordMeasuredValue(recordedValue);
  }

  /**
   * Asserts that if an error is thrown while recording a measured value, the transaction
   * is rolled back.
   */
  @Test
  public void testRecordMeasuredValueInsertionError() throws Exception {
    final ClinicianPatientMeasuredValue recordedValue = createMockedClinicianPatientMeasuredValue(1);
    mockMeasuredValueId();
    final Exception e = new Exception("error in insertion");

    new Expectations() {{
      platformTransactionManager.getTransaction((DefaultTransactionDefinition) any);
      returns(transactionStatus);

      jdbcTemplate.update(anyString, recordedValue.getPatientId(), 1, recordedValue.getClinicianId(),
              recordedValue.getValueMeasure(), recordedValue.getDate());
      result = e;

      platformTransactionManager.rollback(transactionStatus);
    }};

    try {
      measuredValueService.recordMeasuredValue(recordedValue);
    } catch (Exception ex) {
      assertEquals(e, ex);
    }
  }

  /**
   * Asserts that service to retrieve recorded values for a given patient returns a list
   * of recorded values matching the given patient id.
   */
  @Test
  public void testGetRecordedValuesForPatient() throws Exception {
    final int patientId = 1;
    final List<ClinicianPatientMeasuredValue> expected = new ArrayList<>();
    final ClinicianPatientMeasuredValue recordedValue = createMockedClinicianPatientMeasuredValue(patientId);
    expected.add(recordedValue);

    new Expectations() {{
      jdbcTemplate.query(anyString, new Object[]{patientId}, (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<ClinicianPatientMeasuredValue> actual = measuredValueService
            .getRecordedValuesForPatient(patientId);
    assertEquals(1, actual.size());
    assertEquals(actual.get(0), expected.get(0));
    assertEquals(actual.get(0).getPatientId(), patientId);
  }

  private ClinicianPatientMeasuredValue createMockedClinicianPatientMeasuredValue(int patientId) {
    ClinicianPatientMeasuredValue measuredValue = new ClinicianPatientMeasuredValue();
    measuredValue.setClinicianId(1);
    measuredValue.setDate(new Date(0));
    measuredValue.setValueMeasure(1);
    measuredValue.setMeasuredValue(new MeasuredValue());
    measuredValue.setPatientId(patientId);
    return measuredValue;
  }

  private MockUp<MeasuredValueServiceImpl> mockMeasuredValueId() {
    return new MockUp<MeasuredValueServiceImpl>() {
      @Mock
      int getMeasuredValueId(MeasuredValue measuredValue) {
        return 1;
      }
    };
  }

}