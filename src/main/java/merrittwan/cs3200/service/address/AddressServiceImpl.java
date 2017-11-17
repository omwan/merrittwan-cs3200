package merrittwan.cs3200.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

import merrittwan.cs3200.repository.Address;
import merrittwan.cs3200.repository.AddressRowMapper;

/**
 * Created by olivi on 11/15/2017.
 */
@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Address> getAllAddresses() {
    RowMapper<Address> rm = new AddressRowMapper();
    RowMapperResultSetExtractor<Address> rs = new RowMapperResultSetExtractor<>(rm);
    String sql = "select * from ADDRESS";
    return jdbcTemplate.query(sql, rs);
  }

  @Override
  public Address getAddress(int id) {
    RowMapper<Address> rm = new AddressRowMapper();
    String sql = "select * from ADDRESS where address_id = ?";
    return jdbcTemplate.queryForObject(sql, new Object[] {id}, rm);
  }
}
