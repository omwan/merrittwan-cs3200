package merrittwan.cs3200.rowmap;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import merrittwan.cs3200.entity.Drug;
import merrittwan.cs3200.entity.StudyDrug;

/**
 * RowMapper for STUDY_DRUG_DETAILS table.
 * Created by olivi on 11/28/2017.
 */
public class StudyDrugRowMapper implements RowMapper<StudyDrug> {

  /**
   * Map a ResultSet to a StudyDrug POJO.
   *
   * @param rs     query results
   * @param rowNum row number
   * @return StudyDrug object with fields filled in from query results.
   */
  @Override
  public StudyDrug mapRow(ResultSet rs, int rowNum) {
    StudyDrug studyDrug = new StudyDrug();

    try {
      RowMapper<Drug> drugRowMapper = new DrugRowMapper();
      Drug drug = drugRowMapper.mapRow(rs, rowNum);
      studyDrug.setDrug(drug);

      studyDrug.setStudyId(rs.getInt("study_study_id"));
      studyDrug.setDosageAmount(rs.getInt("dosage_amount"));
      studyDrug.setDosageUnit(rs.getString("dosage_unit"));
      studyDrug.setTreatmentIntervalTime(rs.getInt("treatment_interval_time"));
      studyDrug.setTreatmentIntervalType(Enum.valueOf(StudyDrug.IntervalType.class,
              rs.getString("treatment_interval_type").toUpperCase()));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return studyDrug;
  }
}
