package merrittwan.cs3200.rowmap;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import merrittwan.cs3200.entity.ClinicianPatientMeasuredValue;
import merrittwan.cs3200.entity.MeasuredValue;

/**
 * Created by olivi on 11/29/2017.
 */
public class ClinicianPatientMeasuredValueRowMapper implements RowMapper<ClinicianPatientMeasuredValue> {

  @Override
  public ClinicianPatientMeasuredValue mapRow(ResultSet rs, int rowNum) {
    ClinicianPatientMeasuredValue value = new ClinicianPatientMeasuredValue();

    try {
      RowMapper<MeasuredValue> measuredValueRowMapper = new MeasuredValueRowMapper();
      MeasuredValue measuredValue = measuredValueRowMapper.mapRow(rs, rowNum);
      value.setMeasuredValue(measuredValue);

      value.setPatientId(rs.getInt("patient_id"));
      value.setValueMeasure(rs.getInt("value_measure"));
      value.setDate(rs.getDate("measured_date"));
      value.setClinicianId(rs.getInt("clinician_id"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return value;
  }
}
