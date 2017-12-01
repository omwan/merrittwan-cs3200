package merrittwan.cs3200.rowmap;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import merrittwan.cs3200.entity.Drug;

/**
 * RowMapper for DRUG table.
 * Created by olivi on 11/28/2017.
 */
public class DrugRowMapper implements RowMapper<Drug> {

  /**
   * Map a ResultSet to a Drug POJO.
   *
   * @param rs     query results
   * @param rowNum row number
   * @return Drug object with fields filled in from query results.
   */
  @Override
  public Drug mapRow(ResultSet rs, int rowNum) {
    Drug drug = new Drug();
    try {
      drug.setDrugId(rs.getInt("drug_id"));
      drug.setMarketName(rs.getString("market_name"));
      drug.setScientificName(rs.getString("scientific_name"));
      drug.setToxicity(rs.getString("toxicity"));
      drug.setPreviousSuccess(rs.getInt("previous_success"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return drug;
  }
}
