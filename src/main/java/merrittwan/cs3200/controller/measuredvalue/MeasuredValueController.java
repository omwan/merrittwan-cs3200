package merrittwan.cs3200.controller.measuredvalue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import merrittwan.cs3200.entity.ClinicianPatientMeasuredValue;
import merrittwan.cs3200.service.measuredvalue.MeasuredValueService;

/**
 * Controller for API endpoints for measured values.
 * Created by olivi on 11/29/2017.
 */
@Controller
@RequestMapping("/api/measuredvalue")
public class MeasuredValueController {

  @Autowired
  private MeasuredValueService measuredValueService;

  /**
   * Record a measured value for a patient by a clinician.
   *
   * @param recordedValue values to insert on table
   */
  @RequestMapping(value = "/record", method = RequestMethod.POST)
  @ResponseBody
  public void recordMeasuredValue(@RequestBody ClinicianPatientMeasuredValue recordedValue) {
    measuredValueService.recordMeasuredValue(recordedValue);
  }

  /**
   * Get all recorded values for a given patient.
   *
   * @param patientId primary key of patient to query on
   * @return list of recorded values for the given patient
   */
  @RequestMapping(value = "/patient", method = RequestMethod.GET)
  @ResponseBody
  public List<ClinicianPatientMeasuredValue> getRecordedValuesForPatient(
          @RequestParam(name = "patientId") int patientId) {
    return measuredValueService.getRecordedValuesForPatient(patientId);
  }
}
