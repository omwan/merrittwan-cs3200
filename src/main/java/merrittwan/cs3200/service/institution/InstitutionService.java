package merrittwan.cs3200.service.institution;

import java.util.List;

import merrittwan.cs3200.entity.Institution;

/**
 * Services to manage data in INSTITUTION table.
 * Created by olivi on 11/29/2017.
 */
public interface InstitutionService {

  /**
   * Get all institutions in database.
   * @return list of institutions.
   */
  List<Institution> getAllInstitutions();
}
