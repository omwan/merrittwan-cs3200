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
 * Created by olivi on 11/29/2017.
 */
@Controller
@RequestMapping("/api/measuredvalue")
public class MeasuredValueController {

  @Autowired
  private MeasuredValueService measuredValueService;

  @RequestMapping(value = "/record", method = RequestMethod.POST)
  @ResponseBody
  public void recordMeasuredValue(@RequestBody ClinicianPatientMeasuredValue recordedValue) {
    measuredValueService.recordMeasuredValue(recordedValue);
  }

  @RequestMapping(value = "/patient", method = RequestMethod.GET)
  @ResponseBody
  public List<ClinicianPatientMeasuredValue> getRecordedValuesForPatient(
          @RequestParam(name = "patientId") int patientId) {
    return measuredValueService.getRecordedValuesForPatient(patientId);
  }
}
