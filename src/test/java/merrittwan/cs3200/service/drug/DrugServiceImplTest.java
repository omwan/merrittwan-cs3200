package merrittwan.cs3200.service.drug;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import merrittwan.cs3200.entity.Drug;
import merrittwan.cs3200.entity.StudyDrug;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

import static org.junit.Assert.assertEquals;

/**
 * Class to test methods from service for drugs.
 * Created by olivi on 11/28/2017.
 */
public class DrugServiceImplTest {

  @Tested
  private DrugService drugService;

  @Injectable
  private JdbcTemplate jdbcTemplate;

  @Before
  public void setup() throws Exception {
    drugService = new DrugServiceImpl();
  }

  /**
   * Assert that service to add a drug calls the appropriate jdbcTemplate method with
   * the values from the given drug.
   */
  @Test
  public void testAddDrug() throws Exception {
    final Drug drug = createMockedDrug();
    new Expectations() {{
      jdbcTemplate.update(anyString, drug.getMarketName(), drug.getScientificName(),
              drug.getToxicity(), drug.getPreviousSuccess());
    }};

    drugService.addNewDrug(drug);
  }

  /**
   * Assert that service to delete a drug calls the appropriate jdbcTemplate method with
   * the given drug primary key.
   */
  @Test
  public void testDeleteDrug() throws Exception {
    final int drugId = 1;

    new Expectations() {{
      jdbcTemplate.update(anyString, drugId);
    }};

    drugService.deleteDrug(drugId);
  }

  /**
   * Assert that the list of drugs returned by the service matches the values returned
   * from the query.
   */
  @Test
  public void testGetAllDrugs() throws Exception {
    final List<Drug> expected = new ArrayList<>();
    final Drug drug = createMockedDrug();
    expected.add(drug);

    new Expectations() {{
      jdbcTemplate.query(anyString, (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<Drug> actual = drugService.getAllDrugs();
    assertEquals(1, actual.size());
    assertEquals(actual.get(0), expected.get(0));
  }

  /**
   * Assert that the list of StudyDrugs returned by the service matches the values returned
   * from the query.
   */
  @Test
  public void testGetDrugsByStudy() throws Exception {
    final List<StudyDrug> expected = new ArrayList<>();
    final Drug drug = createMockedDrug();
    final StudyDrug studyDrug = createMockedStudyDrug(drug);
    expected.add(studyDrug);

    final int studyId = 1;

    new Expectations() {{
      jdbcTemplate.query(anyString, new Object[]{studyId}, (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<StudyDrug> actual = drugService.getDrugsByStudy(studyId);
    assertEquals(1, actual.size());
    assertEquals(actual.get(0), expected.get(0));
    assertEquals(actual.get(0).getDrug(), expected.get(0).getDrug());
  }

  /**
   * Create a mocked instance of a drug.
   *
   * @return mocked Drug instance.
   */
  private Drug createMockedDrug() {
    Drug drug = new Drug();
    drug.setDrugId(1);
    drug.setMarketName("market name");
    drug.setScientificName("scientific name");
    drug.setToxicity("toxicity");
    drug.setPreviousSuccess(1);
    return drug;
  }

  /**
   * Create a mocked instance of a StudyDrug object with the given drug.
   *
   * @param drug drug to create mocked studyDrug for.
   * @return mocked StudyDrug instance.
   */
  private StudyDrug createMockedStudyDrug(Drug drug) {
    StudyDrug studyDrug = new StudyDrug();
    studyDrug.setStudyId(1);
    studyDrug.setDrug(drug);
    studyDrug.setDosageAmount(1);
    studyDrug.setDosageUnit("unit");
    studyDrug.setTreatmentIntervalTime(1);
    studyDrug.setTreatmentIntervalType(StudyDrug.IntervalType.DAY);
    return studyDrug;
  }

}