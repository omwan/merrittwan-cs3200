package merrittwan.cs3200.rowmap;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import merrittwan.cs3200.entity.MedicalCondition;

/**
 * Created by olivi on 11/28/2017.
 */
public class MedicalConditionRowMapper implements RowMapper<MedicalCondition> {

  @Override
  public MedicalCondition mapRow(ResultSet rs, int rowNum) {
    MedicalCondition medicalCondition = new MedicalCondition();

    try {
      medicalCondition.setConditionId(rs.getInt("condition_id"));
      medicalCondition.setName(rs.getString("name"));
      medicalCondition.setDescription(rs.getString("description"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return medicalCondition;
  }
}
