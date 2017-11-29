package merrittwan.cs3200.service.clinician;

import java.util.List;

import merrittwan.cs3200.entity.Clinician;
import merrittwan.cs3200.entity.StudyClinician;

/**
 * Created by olivi on 11/28/2017.
 */
public interface ClinicianService {

  List<Clinician> getAllClinicians();

  List<StudyClinician> getCliniciansByStudy(int studyId);
}
