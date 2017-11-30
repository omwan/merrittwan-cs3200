package merrittwan.cs3200.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;
import java.util.List;

/**
 * Class to represent rows in STUDY table.
 * Created by olivi on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Study {

  private int studyId;

  private String title;

  private String description;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date endDate;

  private PrincipalInvestigator principalInvestigator;

  private MedicalCondition medicalCondition;

  private boolean completed;

  private SuccessStatus successful;

  private List<StudyDrug> drugs;

  public enum SuccessStatus {
    SUCCESSFUL, FAILED, IN_PROGRESS
  }

  public Study() {

  }

  public int getStudyId() {
    return studyId;
  }

  public void setStudyId(int studyId) {
    this.studyId = studyId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public PrincipalInvestigator getPrincipalInvestigator() {
    return principalInvestigator;
  }

  public void setPrincipalInvestigator(PrincipalInvestigator principalInvestigator) {
    this.principalInvestigator = principalInvestigator;
  }

  public MedicalCondition getMedicalCondition() {
    return medicalCondition;
  }

  public void setMedicalCondition(MedicalCondition medicalCondition) {
    this.medicalCondition = medicalCondition;
  }

  public Boolean isCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  public SuccessStatus getSuccessful() {
    return successful;
  }

  public void setSuccessful(SuccessStatus successful) {
    this.successful = successful;
  }

  public List<StudyDrug> getDrugs() {
    return drugs;
  }

  public void setDrugs(List<StudyDrug> drugs) {
    this.drugs = drugs;
  }
}
