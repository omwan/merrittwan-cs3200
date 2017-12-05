package merrittwan.cs3200.service.principal;

import java.util.Map;

/**
 * Service to manage data in PRINCIPAL_INVESTIGATOR table.
 * Created by olivi on 11/28/2017.
 */
public interface PrincipalInvestigatorService {

  /**
   * Get all principal investigators in the database.
   * @return list of principal investigators.
   */
  Map<String, Object> getAllPrincipalInvestigators();
}
