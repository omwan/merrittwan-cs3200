package merrittwan.cs3200.service.drug;

import java.util.List;

import merrittwan.cs3200.entity.Drug;
import merrittwan.cs3200.entity.StudyDrug;

/**
 * Service to manage data in DRUG table.
 * Created by olivi on 11/28/2017.
 */
public interface DrugService {

  /**
   * Add a new drug to the database.
   *
   * @param drug values to add to drug table
   */
  void addNewDrug(Drug drug);

  /**
   * Delete a given drug from the database.
   *
   * @param drugId primary key of drug to be deleted
   */
  void deleteDrug(int drugId);

  /**
   * Get all drugs in the database.
   *
   * @return list of drugs
   */
  List<Drug> getAllDrugs();

  /**
   * Get all drugs associated with a given study.
   *
   * @param studyId primary key of study to query on
   * @return list of drugs associated with the given study id
   */
  List<StudyDrug> getDrugsByStudy(int studyId);
}
