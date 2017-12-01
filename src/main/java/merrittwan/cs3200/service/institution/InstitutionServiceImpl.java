package merrittwan.cs3200.service.institution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

import merrittwan.cs3200.entity.Institution;
import merrittwan.cs3200.rowmap.InstitutionRowMapper;

/**
 * Created by olivi on 11/29/2017.
 */
@Service
public class InstitutionServiceImpl implements InstitutionService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Institution> getAllInstitutions() {
    RowMapper<Institution> rm = new InstitutionRowMapper();
    RowMapperResultSetExtractor<Institution> rs =  new RowMapperResultSetExtractor<>(rm);
    String sql = "SELECT * FROM INSTITUTION JOIN ADDRESS " +
            "ON INSTITUTION.ADDRESS_ID = ADDRESS.ADDRESS_ID;";
    return jdbcTemplate.query(sql, rs);
  }
}
