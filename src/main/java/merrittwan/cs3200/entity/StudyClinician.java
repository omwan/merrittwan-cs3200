package merrittwan.cs3200.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class to represent rows in STUDY_HAS_CLINICIAN table.
 * Created by olivi on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyClinician {

  private int studyId;

  private Clinician clinician;

  public StudyClinician() {

  }

  public int getStudyId() {
    return studyId;
  }

  public void setStudyId(int studyId) {
    this.studyId = studyId;
  }

  public Clinician getClinician() {
    return clinician;
  }

  public void setClinician(Clinician clinician) {
    this.clinician = clinician;
  }
}
