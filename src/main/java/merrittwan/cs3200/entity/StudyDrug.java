package merrittwan.cs3200.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class to represent rows in STUDY_DRUG_DETAILS table.
 * Created by olivi on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyDrug {

  private int studyId;

  private Drug drug;

  private int dosageAmount;

  private String dosageUnit;

  private int treatmentIntervalTime;

  private IntervalType treatmentIntervalType;

  public Drug getDrug() {
    return drug;
  }

  public int getStudyId() {
    return studyId;
  }

  public void setStudyId(int studyId) {
    this.studyId = studyId;
  }

  public enum IntervalType {
    MONTH, WEEK, DAY, HOUR, MINUTE
  }

  public StudyDrug() {

  }

  public void setDrug(Drug drug) {
    this.drug = drug;
  }

  public int getDosageAmount() {
    return dosageAmount;
  }

  public void setDosageAmount(int dosageAmount) {
    this.dosageAmount = dosageAmount;
  }

  public String getDosageUnit() {
    return dosageUnit;
  }

  public void setDosageUnit(String dosageUnit) {
    this.dosageUnit = dosageUnit;
  }

  public int getTreatmentIntervalTime() {
    return treatmentIntervalTime;
  }

  public void setTreatmentIntervalTime(int treatmentIntervalTime) {
    this.treatmentIntervalTime = treatmentIntervalTime;
  }

  public IntervalType getTreatmentIntervalType() {
    return treatmentIntervalType;
  }

  public void setTreatmentIntervalType(IntervalType treatmentIntervalType) {
    this.treatmentIntervalType = treatmentIntervalType;
  }
}
