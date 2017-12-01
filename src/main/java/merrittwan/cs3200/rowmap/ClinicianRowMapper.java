package merrittwan.cs3200.rowmap;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import merrittwan.cs3200.entity.Clinician;

/**
 * Created by olivi on 11/28/2017.
 */
public class ClinicianRowMapper implements RowMapper<Clinician> {

  @Override
  public Clinician mapRow(ResultSet rs, int rowNum) throws SQLException {
    Clinician clinician = new Clinician();

    try {
      clinician.setClinicianId(rs.getInt("clincian_id"));
      clinician.setFirstName(rs.getString("first_name"));
      clinician.setLastName(rs.getString("last_name"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return clinician;
  }
}
