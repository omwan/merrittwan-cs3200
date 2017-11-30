package merrittwan.cs3200.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class to represent rows in DRUG table.
 * Created by olivi on 11/20/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Drug {

  private Integer drugId;

  private String marketName;

  private String scientificName;

  private String toxicity;

  private int previousSuccess;

  public Drug() {

  }

  public Integer getDrugId() {
    return drugId;
  }

  public void setDrugId(Integer drugId) {
    this.drugId = drugId;
  }

  public String getMarketName() {
    return marketName;
  }

  public void setMarketName(String marketName) {
    this.marketName = marketName;
  }

  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }

  public String getToxicity() {
    return toxicity;
  }

  public void setToxicity(String toxicity) {
    this.toxicity = toxicity;
  }

  public int getPreviousSuccess() {
    return previousSuccess;
  }

  public void setPreviousSuccess(int previousSuccess) {
    this.previousSuccess = previousSuccess;
  }
}
