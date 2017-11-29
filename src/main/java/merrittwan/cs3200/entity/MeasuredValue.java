package merrittwan.cs3200.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class to represent rows in MEASURED_VALUE table.
 * Created by olivi on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeasuredValue {

  private Integer measuredValueId;

  private String valueName;

  private String valueDescription;

  private String valueUnit;

  private int maxHealthyAmount;

  private int minHealthyAmount;

  public MeasuredValue() {

  }

  public Integer getMeasuredValueId() {
    return measuredValueId;
  }

  public void setMeasuredValueId(Integer measuredValueId) {
    this.measuredValueId = measuredValueId;
  }

  public String getValueName() {
    return valueName;
  }

  public void setValueName(String valueName) {
    this.valueName = valueName;
  }

  public String getValueDescription() {
    return valueDescription;
  }

  public void setValueDescription(String valueDescription) {
    this.valueDescription = valueDescription;
  }

  public String getValueUnit() {
    return valueUnit;
  }

  public void setValueUnit(String valueUnit) {
    this.valueUnit = valueUnit;
  }

  public int getMaxHealthyAmount() {
    return maxHealthyAmount;
  }

  public void setMaxHealthyAmount(int maxHealthyAmount) {
    this.maxHealthyAmount = maxHealthyAmount;
  }

  public int getMinHealthyAmount() {
    return minHealthyAmount;
  }

  public void setMinHealthyAmount(int minHealthyAmount) {
    this.minHealthyAmount = minHealthyAmount;
  }
}
