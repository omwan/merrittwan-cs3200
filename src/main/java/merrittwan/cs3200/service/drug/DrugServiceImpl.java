package merrittwan.cs3200.service.drug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

import merrittwan.cs3200.entity.Drug;
import merrittwan.cs3200.entity.StudyDrug;
import merrittwan.cs3200.rowmap.DrugRowMapper;
import merrittwan.cs3200.rowmap.StudyDrugRowMapper;

/**
 * Created by olivi on 11/28/2017.
 */
@Service
public class DrugServiceImpl implements DrugService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void addNewDrug(Drug drug) {
    String sql = "INSERT INTO DRUG (MARKET_NAME, SCIENTIFIC_NAME, TOXICITY, PREVIOUS_SUCCESS) " +
            "VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(sql, drug.getMarketName(), drug.getScientificName(), drug.getToxicity(),
            drug.getPreviousSuccess());
  }

  @Override
  public void deleteDrug(int drugId) {
    String sql = "DELETE FROM DRUG WHERE DRUG_ID = ?";
    jdbcTemplate.update(sql, drugId);
  }

  @Override
  public List<Drug> getAllDrugs() {
    RowMapper<Drug> rm = new DrugRowMapper();
    RowMapperResultSetExtractor<Drug> rs =  new RowMapperResultSetExtractor<>(rm);
    String sql = "select * from DRUG";
    return jdbcTemplate.query(sql, rs);
  }

  @Override
  public List<StudyDrug> getDrugsByStudy(int studyId) {
    RowMapper<StudyDrug> rm = new StudyDrugRowMapper();
    RowMapperResultSetExtractor<StudyDrug> rs =  new RowMapperResultSetExtractor<>(rm);
    String sql = "SELECT * FROM DRUG JOIN STUDY_DRUG_DETAILS " +
            "ON DRUG.DRUG_ID = STUDY_DRUG_DETAILS.DRUG_DRUG_ID " +
            "WHERE STUDY_DRUG_DETAILS.STUDY_STUDY_ID = ?";
    return jdbcTemplate.query(sql, new Object[] {studyId}, rs);
  }
}
