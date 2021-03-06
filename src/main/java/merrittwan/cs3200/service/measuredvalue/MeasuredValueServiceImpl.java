package merrittwan.cs3200.service.measuredvalue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import merrittwan.cs3200.entity.ClinicianPatientMeasuredValue;
import merrittwan.cs3200.entity.MeasuredValue;
import merrittwan.cs3200.rowmap.ClinicianPatientMeasuredValueRowMapper;
import merrittwan.cs3200.rowmap.MeasuredValueRowMapper;

/**
 * Service to manage data in MEASURED_VALUE table.
 * Created by olivi on 11/28/2017.
 */
@Service
public class MeasuredValueServiceImpl implements MeasuredValueService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private PlatformTransactionManager platformTransactionManager;

  /**
   * Record a measured value for a patient by a clinician.
   *
   * @param recordedValue values to insert on table
   */
  @Override
  public void recordMeasuredValue(ClinicianPatientMeasuredValue recordedValue) {

    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    definition.setName("record measured value");
    definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = platformTransactionManager.getTransaction(definition);

    try {
      int patientId = recordedValue.getPatientId();
      int clinicianId = recordedValue.getClinicianId();
      int measuredValueId = getMeasuredValueId(recordedValue.getMeasuredValue());

      String sql = "INSERT INTO CLINICIAN_PATIENT_MEASURED_VALUE VALUES (?, ?, ?, ?, ?)";

      jdbcTemplate.update(sql, patientId, measuredValueId, clinicianId,
              recordedValue.getValueMeasure(), recordedValue.getDate());

      platformTransactionManager.commit(status);
    } catch (Exception e) {
      e.printStackTrace();
      platformTransactionManager.rollback(status);
      throw e;
    }
  }

  /**
   * Get all recorded values for a given patient.
   *
   * @param patientId primary key of patient to query on
   * @return list of recorded values for the given patient
   */
  @Override
  public List<ClinicianPatientMeasuredValue> getRecordedValuesForPatient(int patientId) {
    RowMapper<ClinicianPatientMeasuredValue> rm = new ClinicianPatientMeasuredValueRowMapper();
    RowMapperResultSetExtractor<ClinicianPatientMeasuredValue> rs = new RowMapperResultSetExtractor<>(rm);
    String sql = "SELECT * FROM MEASURED_VALUE JOIN CLINICIAN_PATIENT_MEASURED_VALUE ON " +
            "MEASURED_VALUE.MEASURED_VALUE_ID = CLINICIAN_PATIENT_MEASURED_VALUE.MEASURED_VALUE_ID " +
            "WHERE PATIENT_ID = ? ORDER BY MEASURED_DATE";
    return jdbcTemplate.query(sql, new Object[] {patientId}, rs);
  }

  /**
   * Get all measured values in the database.
   *
   * @return list of measured values
   */
  @Override
  public List<MeasuredValue> getAllMeasuredValues() {
    RowMapper<MeasuredValue> rm = new MeasuredValueRowMapper();
    RowMapperResultSetExtractor<MeasuredValue> rs = new RowMapperResultSetExtractor<>(rm);
    String sql = "SELECT * FROM MEASURED_VALUE";
    return jdbcTemplate.query(sql, rs);
  }

  private int getMeasuredValueId(MeasuredValue measuredValue) {
    if (measuredValue.getMeasuredValueId() != null) {
      return measuredValue.getMeasuredValueId();
    } else {
      String sql = "INSERT INTO MEASURED_VALUE (VALUE_NAME, VALUE_DESCRIPTION, MAX_HEALTHY_VALUE, " +
              "MIN_HEALTHY_VALUE, VALUE_UNIT) VALUES (?, ?, ?, ?, ?)";
      GeneratedKeyHolder holder = new GeneratedKeyHolder();
      PreparedStatementCreator psc = con -> {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, measuredValue.getValueName());
        ps.setString(2, measuredValue.getValueDescription());
        ps.setInt(3, measuredValue.getMaxHealthyAmount());
        ps.setInt(4, measuredValue.getMinHealthyAmount());
        ps.setString(5, measuredValue.getValueUnit());
        return ps;
      };

      jdbcTemplate.update(psc, holder);
      return holder.getKey().intValue();
    }
  }
}
