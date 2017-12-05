package merrittwan.cs3200.service.condition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

import merrittwan.cs3200.entity.MedicalCondition;
import merrittwan.cs3200.rowmap.MedicalConditionRowMapper;

/**
 * Service to manage data in MEDICAL_CONDITION table.
 * Created by olivi on 11/28/2017.
 */
@Service
public class MedicalConditionServiceImpl implements MedicalConditionService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * Get all medical conditions in database.
   *
   * @return list of medical conditions
   */
  @Override
  public List<MedicalCondition> getAllMedicalConditions() {
    RowMapper<MedicalCondition> rm = new MedicalConditionRowMapper();
    RowMapperResultSetExtractor<MedicalCondition> rs =  new RowMapperResultSetExtractor<>(rm);
    String sql = "select * from MEDICAL_CONDITION";
    return jdbcTemplate.query(sql, rs);
  }
}
