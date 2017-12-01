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

  private int patientId;

  private MeasuredValue measuredValue;

  private int clinicianId;

  private int valueMeasure;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date date;

  public ClinicianPatientMeasuredValue() {

  }

  public int getPatientId() {
    return patientId;
  }

  public void setPatientId(int patientId) {
    this.patientId = patientId;
  }

  public MeasuredValue getMeasuredValue() {
    return measuredValue;
  }

  public void setMeasuredValue(MeasuredValue measuredValue) {
    this.measuredValue = measuredValue;
  }

  public int getClinicianId() {
    return clinicianId;
  }

  public void setClinicianId(int clinicianId) {
    this.clinicianId = clinicianId;
  }

  public int getValueMeasure() {
    return valueMeasure;
  }

  public void setValueMeasure(int valueMeasure) {
    this.valueMeasure = valueMeasure;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
