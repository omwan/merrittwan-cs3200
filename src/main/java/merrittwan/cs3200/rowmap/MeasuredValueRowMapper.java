package merrittwan.cs3200.rowmap;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import merrittwan.cs3200.entity.MeasuredValue;

/**
 * Created by olivi on 11/29/2017.
 */
public class MeasuredValueRowMapper implements RowMapper<MeasuredValue> {

  @Override
  public MeasuredValue mapRow(ResultSet rs, int rowNum) {
    MeasuredValue measuredValue = new MeasuredValue();

    try {
      measuredValue.setMeasuredValueId(rs.getInt("measured_value_id"));
      measuredValue.setValueName(rs.getString("value_name"));
      measuredValue.setValueDescription(rs.getString("value_description"));
      measuredValue.setValueUnit(rs.getString("value_unit"));
      measuredValue.setMaxHealthyAmount(rs.getInt("max_healthy_value"));
      measuredValue.setMinHealthyAmount(rs.getInt("min_healthy_value"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return measuredValue;
  }
}
