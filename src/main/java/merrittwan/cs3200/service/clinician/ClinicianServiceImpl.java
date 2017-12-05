package merrittwan.cs3200.service.clinician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

import merrittwan.cs3200.entity.Clinician;
import merrittwan.cs3200.entity.StudyClinician;
import merrittwan.cs3200.rowmap.ClinicianRowMapper;
import merrittwan.cs3200.rowmap.StudyClinicianRowMapper;

/**
 * Service to manage data in CLINICIAN table.
 * Created by olivi on 11/28/2017.
 */
@Service
public class ClinicianServiceImpl implements ClinicianService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * Get all clinicians in database.
   *
   * @return list of clinicians
   */
  @Override
  public List<Clinician> getAllClinicians() {
    RowMapper<Clinician> rm = new ClinicianRowMapper();
    RowMapperResultSetExtractor<Clinician> rs =  new RowMapperResultSetExtractor<>(rm);
    String sql = "select * from CLINICIAN";
    return jdbcTemplate.query(sql, rs);
  }

  /**
   * Get all clinicians associated with a given study.
   *
   * @param studyId primary key of study to query on
   * @return list of clinicians associated with the given study id
   */
  @Override
  public List<StudyClinician> getCliniciansByStudy(int studyId) {
    RowMapper<StudyClinician> rm = new StudyClinicianRowMapper();
    RowMapperResultSetExtractor<StudyClinician> rs =  new RowMapperResultSetExtractor<>(rm);
    String sql = "SELECT * FROM CLINICIAN JOIN STUDY_has_CLINICIAN " +
            "ON CLINICIAN.CLINCIAN_ID = STUDY_has_CLINICIAN.CLINICIAN_CLINICIAN_ID " +
            "WHERE STUDY_has_CLINICIAN.STUDY_STUDY_ID = ?";
    return jdbcTemplate.query(sql, new Object[] {studyId}, rs);
  }
}
