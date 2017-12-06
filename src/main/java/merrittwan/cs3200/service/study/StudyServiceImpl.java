package merrittwan.cs3200.service.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import merrittwan.cs3200.entity.Address;
import merrittwan.cs3200.entity.Clinician;
import merrittwan.cs3200.entity.Drug;
import merrittwan.cs3200.entity.Institution;
import merrittwan.cs3200.entity.MedicalCondition;
import merrittwan.cs3200.entity.Patient;
import merrittwan.cs3200.entity.PrincipalInvestigator;
import merrittwan.cs3200.entity.Study;
import merrittwan.cs3200.entity.StudyClinician;
import merrittwan.cs3200.entity.StudyDrug;
import merrittwan.cs3200.rowmap.PatientRowMapper;

/**
 * Implementation of service to perform operations on database relating to studies.
 * Created by olivi on 11/28/2017.
 */
@Service
public class StudyServiceImpl implements StudyService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private PlatformTransactionManager platformTransactionManager;

  /**
   * Add a given patient to their study. This service is a transaction.
   *
   * @param patient patient to add to study
   */
  @Override
  public void addPatientToStudy(Patient patient) {
    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    definition.setName("add new patient to a study");
    definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = platformTransactionManager.getTransaction(definition);

    try {
      int studyId = patient.getStudyId();
      int addressId = getAddressId(patient.getAddress());

      String sql = "INSERT INTO PATIENT (FIRST_NAME, LAST_NAME, DOB, SEX, HOMETOWN_CITY," +
              "NATIONALITY, RACE, ETHNICITY, STUDY_ID, PLACEBO, ADDRESS_ID, HEALTHY) VALUES" +
              "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

      jdbcTemplate.update(sql, patient.getFirstName(), patient.getLastName(), patient.getDob(),
              patient.getSex().toString(), patient.getHometown(), patient.getNationality(),
              patient.getRace(), patient.getEthnicity(), studyId, patient.isPlacebo(),
              addressId, patient.isHealthy());

      platformTransactionManager.commit(status);
    } catch (Exception e) {
      e.printStackTrace();
      platformTransactionManager.rollback(status);
      throw e;
    }
  }

  /**
   * Update the information for a given patient.
   *
   * @param patient patient object with modified values.
   */
  @Override
  public void updatePatientInfo(Patient patient) {
    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    definition.setName("update patient information");
    definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = platformTransactionManager.getTransaction(definition);

    try {
      int addressId = getAddressId(patient.getAddress());

      String sql = "UPDATE PATIENT SET FIRST_NAME = ?, LAST_NAME = ?, DOB = ?, SEX = ?, " +
              "HOMETOWN_CITY = ?, NATIONALITY = ?, RACE = ?, ETHNICITY = ?, ADDRESS_ID = ? " +
              "WHERE PATIENT_ID = ?";

      jdbcTemplate.update(sql, patient.getFirstName(), patient.getLastName(), patient.getDob(),
              patient.getSex().toString(), patient.getHometown(), patient.getNationality(),
              patient.getRace(), patient.getEthnicity(), addressId, patient.getPatientId());

      platformTransactionManager.commit(status);
    } catch (Exception e) {
      e.printStackTrace();
      platformTransactionManager.rollback(status);
      throw e;
    }
  }

  /**
   * Add a given clinician to a study.
   *
   * @param studyClinician object containing study and clinician information.
   */
  @Override
  public void addClinicianToStudy(StudyClinician studyClinician) {
    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    definition.setName("add patient and address");
    definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = platformTransactionManager.getTransaction(definition);

    try {
      Clinician clinician = studyClinician.getClinician();
      int clinicianId = getClinicianId(clinician);
      int studyId = studyClinician.getStudyId();

      String sql = "INSERT INTO STUDY_has_CLINICIAN values (?, ?)";

      jdbcTemplate.update(sql, studyId, clinicianId);

      platformTransactionManager.commit(status);
    } catch (Exception e) {
      e.printStackTrace();
      platformTransactionManager.rollback(status);
      throw e;
    }
  }

  /**
   * Add a principal investigator to the database.
   *
   * @param pi principal investigator object to add
   */
  @Override
  public void addPrincipalInvestigator(PrincipalInvestigator pi) {
    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    definition.setName("add principal investigator");
    definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = platformTransactionManager.getTransaction(definition);

    try {
      int addressId = getAddressId(pi.getAddress());
      int institutionId = getInstitutionId(pi.getInstitution());

      String sql = "INSERT INTO PRINCIPAL_INVESTIGATOR (FIRST_NAME, LAST_NAME, PHONE, EMAIL, " +
              "ADDRESS_ID, INSTITUTION_ID) VALUES (?, ?, ?, ?, ?, ?)";

      jdbcTemplate.update(sql, pi.getFirstName(), pi.getLastName(), pi.getPhone(),
              pi.getEmail(), addressId, institutionId);

      platformTransactionManager.commit(status);
    } catch (Exception e) {
      e.printStackTrace();
      platformTransactionManager.rollback(status);
      throw e;
    }
  }

  /**
   * Create a new study.
   *
   * @param study object containing study information
   */
  @Override
  public void createStudy(Study study) {
    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    definition.setName("create new study");
    definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = platformTransactionManager.getTransaction(definition);

    try {
      int studyId = insertStudy(study);
      List<StudyDrug> drugList = study.getDrugs();
      insertDrugsIntoStudy(studyId, drugList);

      platformTransactionManager.commit(status);
    } catch (Exception e) {
      e.printStackTrace();
      platformTransactionManager.rollback(status);
      throw e;
    }
  }

  private int insertStudy(Study study) {
    int conditionId = getConditionId(study.getMedicalCondition());
    int principalInvestigatorId = study.getPrincipalInvestigator().getPrincipalInvestigatorId();
    String insertIntoStudy = "INSERT INTO STUDY (TITLE, DESCRIPTION, START_DATE, END_DATE, " +
            "PRINCIPAL_INVESTIGATOR_ID, CONDITION_CONDITION_ID, COMPLETED, SUCCESSFUL) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    GeneratedKeyHolder holder = new GeneratedKeyHolder();
    PreparedStatementCreator psc = con -> {
      PreparedStatement ps = con.prepareStatement(insertIntoStudy, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, study.getTitle());
      ps.setString(2, study.getDescription());
      ps.setDate(3, study.getStartDate());
      ps.setDate(4, study.getEndDate());
      ps.setInt(5, principalInvestigatorId);
      ps.setInt(6, conditionId);
      ps.setBoolean(7, study.isCompleted());
      ps.setString(8, study.getSuccessful().toString());
      return ps;
    };

    jdbcTemplate.update(psc, holder);

    return holder.getKey().intValue();
  }

  private void insertDrugsIntoStudy(int studyId, List<StudyDrug> drugList) {
    String insertIntoStudyDrug = "INSERT INTO STUDY_DRUG_DETAILS VALUES (?, ?, ?, ?, ?, ?)";

    jdbcTemplate.batchUpdate(insertIntoStudyDrug, new BatchPreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps, int i) throws SQLException {
        StudyDrug studyDrug = drugList.get(i);
        int drugId = getDrugId(studyDrug.getDrug());
        ps.setInt(1, drugId);
        ps.setInt(2, studyId);
        ps.setInt(3, studyDrug.getDosageAmount());
        ps.setString(4, studyDrug.getDosageUnit());
        ps.setInt(5, studyDrug.getTreatmentIntervalTime());
        ps.setString(6, studyDrug.getTreatmentIntervalType().toString());
      }

      @Override
      public int getBatchSize() {
        return drugList.size();
      }
    });
  }

  /**
   * Close a study.
   *
   * @param studyId primary key of study to close.
   */
  @Override
  public void closeStudy(int studyId) {
    String sql = "UPDATE STUDY SET COMPLETED = TRUE WHERE STUDY_ID = ?";
    jdbcTemplate.update(sql, studyId);
  }

  /**
   * Retrieve the patient outcomes for a study by the treatment type.
   *
   * @param studyId study to retrieve outcomes for
   * @param placebo treatment type (either placebo or drug)
   * @return resultset containing patient outcomes matching the given parameters.
   */
  @Override
  public List<Patient> getOutcomesByTreatmentType(int studyId, boolean placebo) {
    String sql = "SELECT * FROM PATIENT JOIN ADDRESS ON PATIENT.ADDRESS_ID = ADDRESS.ADDRESS_ID " +
            "WHERE STUDY_ID = ? AND PLACEBO = ?";

    RowMapper<Patient> rm = new PatientRowMapper();
    RowMapperResultSetExtractor<Patient> rs =  new RowMapperResultSetExtractor<>(rm);

    return jdbcTemplate.query(sql, new Object[] {studyId, placebo}, rs);
  }

  /**
   * Get a list of patients matching the given parameters for the given study.
   *
   * @param studyId         primary key of study to query on
   * @param characteristics column names and values to query on
   * @return list of patients matching the given parameters.
   */
  @Override
  public List<Patient> getOutcomesByPatientCharacteristics(int studyId,
                                                           Map<String, Object> characteristics) {
    String sql = "SELECT * FROM PATIENT JOIN ADDRESS ON PATIENT.ADDRESS_ID = ADDRESS.ADDRESS_ID" +
            " WHERE STUDY_ID = ?";

    List<String> params = new ArrayList<>();
    for (String s : characteristics.keySet()) {
      if (characteristics.get(s) != null && characteristics.get(s) != "") {
        sql += " AND " + s + " = ?";
        params.add(s);
      }
    }

    String finalSql = sql;
    PreparedStatementCreator psc = con -> {
      PreparedStatement ps = con.prepareStatement(finalSql);
      ps.setInt(1, studyId);

      for (int i = 0; i < params.size(); i++) {
        ps.setObject(i + 2, characteristics.get(params.get(i)));
      }

      return ps;
    };

    RowMapper<Patient> rm = new PatientRowMapper();
    RowMapperResultSetExtractor<Patient> rs =  new RowMapperResultSetExtractor<>(rm);

    return jdbcTemplate.query(psc, rs);
  }

  /**
   * Retrieve a list of all studies in the database.
   *
   * @return list of studies.
   */
  @Override
  public Map<String, Object> getAllStudies() {
    CallableStatementCreator csc = con -> con.prepareCall("{ call get_all_studies() }");
    return jdbcTemplate.call(csc, new ArrayList<>());
  }

  /**
   * Helper method to update address table with a new address and return the generated
   * primary key, if needed.
   *
   * @param address address to add to table.
   * @return generated primary key for given address.
   */
  private int getAddressId(Address address) {
    if (address.getAddressId() != null) {
      return address.getAddressId();
    } else {
      String sql = "INSERT INTO ADDRESS (STREET, CITY, STATE, ZIP) VALUES (?, ?, ?, ?)";
      GeneratedKeyHolder holder = new GeneratedKeyHolder();
      PreparedStatementCreator psc = con -> {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, address.getStreet());
        ps.setString(2, address.getCity());
        ps.setString(3, address.getState());
        ps.setString(4, address.getZip());
        return ps;
      };

      jdbcTemplate.update(psc, holder);
      return holder.getKey().intValue();
    }
  }

  private int getInstitutionId(Institution institution) {
    if (institution.getInstitutionId() != null) {
      return institution.getInstitutionId();
    } else {
      int addressId = getAddressId(institution.getAddress());
      String sql = "INSERT INTO INSTITUTION (INSTITUTION_NAME, INSTITUTION_TYPE, ADDRESS_ID) " +
              "VALUES (?, ?, ?)";
      GeneratedKeyHolder holder = new GeneratedKeyHolder();
      PreparedStatementCreator psc = con -> {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, institution.getName());
        ps.setString(2, institution.getType().toString());
        ps.setInt(3, addressId);
        return ps;
      };

      jdbcTemplate.update(psc, holder);
      return holder.getKey().intValue();
    }
  }

  private int getClinicianId(Clinician clinician) {
    if (clinician.getClinicianId() != null) {
      return clinician.getClinicianId();
    } else {
      String sql = "INSERT INTO CLINICIAN (FIRST_NAME, LAST_NAME) VALUES (?, ?)";
      GeneratedKeyHolder holder = new GeneratedKeyHolder();
      PreparedStatementCreator psc = con -> {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, clinician.getFirstName());
        ps.setString(2, clinician.getLastName());
        return ps;
      };

      jdbcTemplate.update(psc, holder);
      return holder.getKey().intValue();
    }
  }

  private int getConditionId(MedicalCondition condition) {
    if (condition.getConditionId() != null) {
      return condition.getConditionId();
    } else {
      String sql = "INSERT INTO MEDICAL_CONDITION (NAME, DESCRIPTION) VALUES (?, ?)";
      GeneratedKeyHolder holder = new GeneratedKeyHolder();
      PreparedStatementCreator psc = con -> {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, condition.getName());
        ps.setString(2, condition.getDescription());
        return ps;
      };

      jdbcTemplate.update(psc, holder);
      return holder.getKey().intValue();
    }
  }

  private int getDrugId(Drug drug) {
    if (drug.getDrugId() != null) {
      return drug.getDrugId();
    } else {
      String sql = "INSERT INTO DRUG (MARKET_NAME, SCIENTIFIC_NAME, TOXICITY, PREVIOUS_SUCCESS) " +
              "VALUES (?, ?, ?, ?)";
      GeneratedKeyHolder holder = new GeneratedKeyHolder();
      PreparedStatementCreator psc = con -> {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, drug.getMarketName());
        ps.setString(2, drug.getScientificName());
        ps.setString(3, drug.getToxicity());
        ps.setInt(4, drug.getPreviousSuccess());
        return ps;
      };

      jdbcTemplate.update(psc, holder);
      return holder.getKey().intValue();
    }
  }
}
