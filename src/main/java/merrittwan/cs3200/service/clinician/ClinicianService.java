package merrittwan.cs3200.service.clinician;

import java.util.List;

import merrittwan.cs3200.entity.Clinician;
import merrittwan.cs3200.entity.StudyClinician;

/**
 * Service to manage data in CLINICIAN table.
 * Created by olivi on 11/28/2017.
 */
public interface ClinicianService {

  /**
   * Get all clinicians in database.
   *
   * @return list of clinicians
   */
  List<Clinician> getAllClinicians();

  /**
   * Get all clinicians associated with a given study.
   *
   * @param studyId primary key of study to query on
   * @return list of clinicians associated with the given study id
   */
  List<StudyClinician> getCliniciansByStudy(int studyId);
}
