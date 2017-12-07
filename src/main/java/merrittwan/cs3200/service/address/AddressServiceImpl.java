package merrittwan.cs3200.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

import merrittwan.cs3200.entity.Address;
import merrittwan.cs3200.rowmap.AddressRowMapper;

/**
 * Service to manage data in ADDRESS table.
 * Created by olivi on 11/15/2017.
 */
@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * Get all addresses currently stored in database.
   *
   * @return list of address objects
   */
  @Override
  public List<Address> getAllAddresses() {
    RowMapper<Address> rm = new AddressRowMapper();
    RowMapperResultSetExtractor<Address> rs = new RowMapperResultSetExtractor<>(rm);
    String sql = "select * from ADDRESS";
    return jdbcTemplate.query(sql, rs);
  }

  /**
   * Get the address object matching the given primary key.
   *
   * @param id primary key of address to find
   * @return address object with the given primary key
   */
  @Override
  public Address getAddress(int id) {
    RowMapper<Address> rm = new AddressRowMapper();
    String sql = "select * from ADDRESS where address_id = ?";
    return jdbcTemplate.queryForObject(sql, new Object[]{id}, rm);
  }
}
