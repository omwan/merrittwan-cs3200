package merrittwan.cs3200.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by olivi on 11/15/2017.
 */
public class AddressRowMapper implements RowMapper<Address> {

  public Address mapRow(ResultSet resultSet, int i) {
    Address address = new Address();
    try {
      address.setAddressId(resultSet.getInt("address_id"));
      address.setStreet(resultSet.getString("street"));
      address.setCity(resultSet.getString("city"));
      address.setState(resultSet.getString("state"));
      address.setZip(resultSet.getInt("zip"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return address;
  }
}
