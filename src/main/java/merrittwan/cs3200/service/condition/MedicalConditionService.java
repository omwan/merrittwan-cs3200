package merrittwan.cs3200.service.condition;

import java.util.List;

import merrittwan.cs3200.entity.MedicalCondition;

/**
 * Created by olivi on 11/28/2017.
 */
public interface MedicalConditionService {

  List<MedicalCondition> getAllMedicalConditions();
}
