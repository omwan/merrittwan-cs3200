package merrittwan.cs3200.service.condition;

import java.util.List;

import merrittwan.cs3200.entity.MedicalCondition;

/**
 * Service to manage data in MEDICAL_CONDITION table.
 * Created by olivi on 11/28/2017.
 */
public interface MedicalConditionService {

  /**
   * Get all medical conditions in database.
   *
   * @return list of medical conditions
   */
  List<MedicalCondition> getAllMedicalConditions();
}
