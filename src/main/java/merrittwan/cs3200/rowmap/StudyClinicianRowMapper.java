package merrittwan.cs3200.rowmap;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import merrittwan.cs3200.entity.Clinician;
import merrittwan.cs3200.entity.StudyClinician;

/**
 * Created by olivi on 11/28/2017.
 */
public class StudyClinicianRowMapper implements RowMapper<StudyClinician> {

  @Override
  public StudyClinician mapRow(ResultSet rs, int rowNum) throws SQLException {
    StudyClinician studyClinician = new StudyClinician();

    try {
      RowMapper<Clinician> clinicianRowMapper = new ClinicianRowMapper();
      Clinician clinician = clinicianRowMapper.mapRow(rs, rowNum);
      studyClinician.setClinician(clinician);

      studyClinician.setStudyId(rs.getInt("study_study_id"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return studyClinician;
  }
}
