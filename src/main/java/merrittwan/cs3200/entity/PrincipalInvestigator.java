package merrittwan.cs3200.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class to represent data in the PRINCIPAL_INVESTIGATOR table.
 * Created by olivi on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrincipalInvestigator {

  private int principalInvestigatorId;

  private String firstName;

  private String lastName;

  private String phone;

  private String email;

  private Address address;

  private Institution institution;

  public PrincipalInvestigator() {

  }

  public int getPrincipalInvestigatorId() {
    return principalInvestigatorId;
  }

  public void setPrincipalInvestigatorId(int principalInvestigatorId) {
    this.principalInvestigatorId = principalInvestigatorId;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Institution getInstitution() {
    return institution;
  }

  public void setInstitution(Institution institution) {
    this.institution = institution;
  }
}
