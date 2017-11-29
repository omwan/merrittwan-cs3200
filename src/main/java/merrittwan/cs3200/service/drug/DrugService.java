package merrittwan.cs3200.service.drug;

import java.util.List;

import merrittwan.cs3200.entity.Drug;
import merrittwan.cs3200.entity.StudyDrug;

/**
 * Created by olivi on 11/28/2017.
 */
public interface DrugService {

  void addNewDrug(Drug drug);

  void deleteDrug(int drugId);

  List<Drug> getAllDrugs();

  List<StudyDrug> getDrugsByStudy(int studyId);
}
