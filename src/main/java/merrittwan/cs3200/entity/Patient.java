package merrittwan.cs3200.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Class to represent rows in PATIENT table.
 * Created by olivi on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {

  private int patientId;

  private String firstName;

  private String lastName;

  private Date dob;

  private Sex sex;

  private String hometown;

  private String nationality;

  private String race;

  private String ethnicity;

  private int studyId;

  private boolean placebo;

  private Address address;

  private boolean healthy;

  public enum Sex {
    MALE, FEMALE
  }

  public Patient() {

  }

  public int getPatientId() {
    return patientId;
  }

  public void setPatientId(int patientId) {
    this.patientId = patientId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

  public String getHometown() {
    return hometown;
  }

  public void setHometown(String hometown) {
    this.hometown = hometown;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public String getRace() {
    return race;
  }

  public void setRace(String race) {
    this.race = race;
  }

  public String getEthnicity() {
    return ethnicity;
  }

  public void setEthnicity(String ethnicity) {
    this.ethnicity = ethnicity;
  }

  public int getStudyId() {
    return studyId;
  }

  public void setStudyId(int studyId) {
    this.studyId = studyId;
  }

  public boolean isPlacebo() {
    return placebo;
  }

  public void setPlacebo(boolean placebo) {
    this.placebo = placebo;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public boolean isHealthy() {
    return healthy;
  }

  public void setHealthy(boolean healthy) {
    this.healthy = healthy;
  }
}
