package merrittwan.cs3200.service.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.Arrays;
import java.util.Map;

import merrittwan.cs3200.entity.Address;
import merrittwan.cs3200.entity.Patient;

/**
 * Created by olivi on 11/28/2017.
 */
@Service
public class StudyServiceImpl implements StudyService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private PlatformTransactionManager platformTransactionManager;

  @Override
  public void addPatientToStudy(Patient patient) {
    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    definition.setName("add patient and address");
    definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = platformTransactionManager.getTransaction(definition);

    try {
      int studyId = patient.getStudy().getStudyId();
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

  @Override
  public Map<String, Object> getOutcomesByTreatmentType(int studyId, boolean placebo) {
    CallableStatementCreator csc = con -> {
      CallableStatement cs = con.prepareCall("{ call search_outcomes_by_treatment_type(?, ?) }");
      cs.setInt(1, studyId);
      cs.setBoolean(2, placebo);
      return cs;
    };

    return jdbcTemplate.call(csc, Arrays.asList(new SqlParameter(Types.INTEGER),
            new SqlParameter(Types.TINYINT)));
  }

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
}
