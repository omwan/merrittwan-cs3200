package merrittwan.cs3200.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

/**
 * Class to represent rows in CLINICIAN_PATIENT_MEASURED_VALUE table.
 * Created by olivi on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicianPatientMeasuredValue {

  private Patient patient;

  private MeasuredValue measuredValue;

  private Clinician clinician;

  private String value;

  private String valueUnit;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date date;

  public ClinicianPatientMeasuredValue() {

  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public MeasuredValue getMeasuredValue() {
    return measuredValue;
  }

  public void setMeasuredValue(MeasuredValue measuredValue) {
    this.measuredValue = measuredValue;
  }

  public Clinician getClinician() {
    return clinician;
  }

  public void setClinician(Clinician clinician) {
    this.clinician = clinician;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getValueUnit() {
    return valueUnit;
  }

  public void setValueUnit(String valueUnit) {
    this.valueUnit = valueUnit;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
