package merrittwan.cs3200.service.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

import merrittwan.cs3200.entity.Patient;
import merrittwan.cs3200.rowmap.PatientRowMapper;

/**
 * Service to manage data in PATIENT table.
 * Created by olivi on 11/30/2017.
 */
@Service
public class PatientServiceImpl implements PatientService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * Get all patients in database.
   *
   * @return list of patients
   */
  @Override
  public List<Patient> getAllPatients() {
    RowMapper<Patient> rm = new PatientRowMapper();
    RowMapperResultSetExtractor<Patient> rs =  new RowMapperResultSetExtractor<>(rm);
    String sql = "SELECT * FROM PATIENT JOIN ADDRESS " +
            "ON PATIENT.ADDRESS_ID = ADDRESS.ADDRESS_ID";
    return jdbcTemplate.query(sql, rs);
  }

  /**
   * Get all patients associated with a given study.
   *
   * @param studyId primary key of study to query on
   * @return list of patients matching the given study id
   */
  @Override
  public List<Patient> getPatientsByStudy(int studyId) {
    RowMapper<Patient> rm = new PatientRowMapper();
    RowMapperResultSetExtractor<Patient> rs =  new RowMapperResultSetExtractor<>(rm);
    String sql = "SELECT * FROM PATIENT JOIN ADDRESS " +
            "ON PATIENT.ADDRESS_ID = ADDRESS.ADDRESS_ID " +
            "WHERE STUDY_ID = ?";
    return jdbcTemplate.query(sql, new Object[] {studyId}, rs);
  }
}
