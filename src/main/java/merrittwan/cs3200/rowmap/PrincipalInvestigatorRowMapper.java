//package merrittwan.cs3200.rowmap;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import merrittwan.cs3200.entity.Address;
//import merrittwan.cs3200.entity.Institution;
//import merrittwan.cs3200.entity.PrincipalInvestigator;
//
///**
// * Created by olivi on 11/28/2017.
// */
//public class PrincipalInvestigatorRowMapper implements RowMapper<PrincipalInvestigator> {
//
//  @Override
//  public PrincipalInvestigator mapRow(ResultSet rs, int rowNum) {
//    PrincipalInvestigator principalInvestigator = new PrincipalInvestigator();
//
//    try {
//      principalInvestigator.setPrincipalInvestigatorId(rs.getInt("principal_investigator_id"));
//      principalInvestigator.setFirstName(rs.getString("first_name"));
//      principalInvestigator.setLastName(rs.getString("last_name"));
//      principalInvestigator.setPhone(rs.getString("phone"));
//      principalInvestigator.setEmail(rs.getString("email"));
//
//      Address address = new Address();
//      address.setAddressId("");
//
//      RowMapper<Institution> institutionRowMapper = new InstitutionRowMapper();
//      Institution institution = institutionRowMapper.mapRow(rs, rowNum);
//      principalInvestigator.setInstitution(institution);
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//
//    return principalInvestigator;
//  }
//}
