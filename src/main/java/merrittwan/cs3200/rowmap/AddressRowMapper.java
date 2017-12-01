package merrittwan.cs3200.rowmap;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import merrittwan.cs3200.entity.Address;

/**
 * RowMapper for ADDRESS table.
 * Created by olivi on 11/15/2017.
 */
public class AddressRowMapper implements RowMapper<Address> {

  /**
   * Map a ResultSet to an Address POJO.
   *
   * @param rs query results
   * @param rowNum       row number
   * @return Address object with fields filled in from query results.
   */
  public Address mapRow(ResultSet rs, int rowNum) {
    Address address = new Address();
    try {
      address.setAddressId(rs.getInt("address_id"));
      address.setStreet(rs.getString("street"));
      address.setCity(rs.getString("city"));
      address.setState(rs.getString("state"));
      address.setZip(rs.getString("zip"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return address;
  }
}
