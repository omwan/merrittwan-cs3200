package merrittwan.cs3200.rowmap;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import merrittwan.cs3200.entity.Address;
import merrittwan.cs3200.entity.Institution;

/**
 * Created by olivi on 11/28/2017.
 */
public class InstitutionRowMapper implements RowMapper<Institution> {

  @Override
  public Institution mapRow(ResultSet rs, int rowNum) {
    Institution institution = new Institution();

    try {
      institution.setInstitutionId(rs.getInt("institution_id"));
      institution.setName(rs.getString("institution_name"));
      institution.setType(Enum.valueOf(Institution.InstitutionType.class,
              rs.getString("institution_type").toUpperCase()));

      RowMapper<Address> addressRowMapper = new AddressRowMapper();
      Address address = addressRowMapper.mapRow(rs, rowNum);
      institution.setAddress(address);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return institution;
  }
}
