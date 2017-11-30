package merrittwan.cs3200.rowmap;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import merrittwan.cs3200.entity.Address;
import merrittwan.cs3200.entity.Patient;

/**
 * Created by olivi on 11/30/2017.
 */
public class PatientRowMapper implements RowMapper<Patient> {

  @Override
  public Patient mapRow(ResultSet rs, int rowNum) {
    Patient patient = new Patient();

    try {
      patient.setPatientId(rs.getInt("patient_id"));
      patient.setFirstName(rs.getString("first_name"));
      patient.setLastName(rs.getString("last_name"));
      patient.setDob(rs.getDate("dob"));
      patient.setSex(Enum.valueOf(Patient.Sex.class, rs.getString("sex").toUpperCase()));
      patient.setHometown(rs.getString("hometown_city"));
      patient.setNationality(rs.getString("nationality"));
      patient.setRace(rs.getString("race"));
      patient.setEthnicity(rs.getString("ethnicity"));
      patient.setStudyId(rs.getInt("study_id"));
      patient.setPlacebo(rs.getBoolean("placebo"));
      patient.setHealthy(rs.getBoolean("healthy"));

      RowMapper<Address> addressRowMapper = new AddressRowMapper();
      Address address = addressRowMapper.mapRow(rs, rowNum);
      patient.setAddress(address);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return patient;
  }
}
