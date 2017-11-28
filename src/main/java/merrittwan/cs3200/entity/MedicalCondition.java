package merrittwan.cs3200.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class to represent rows in MEDICAL_CONDITION table.
 * Created by olivi on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicalCondition {

  private int conditionId;

  private String name;

  private String description;

  public MedicalCondition() {

  }

  public int getConditionId() {
    return conditionId;
  }

  public void setConditionId(int conditionId) {
    this.conditionId = conditionId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
