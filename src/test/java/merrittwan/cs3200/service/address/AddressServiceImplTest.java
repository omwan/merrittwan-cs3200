package merrittwan.cs3200.service.address;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import merrittwan.cs3200.entity.Address;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Class to test methods from service for addresses.
 * Created by olivi on 12/04/2017.
 */
public class AddressServiceImplTest {

  @Tested
  private AddressService addressService;

  @Injectable
  private JdbcTemplate jdbcTemplate;

  @Before
  public void setup() throws Exception {
    addressService = new AddressServiceImpl();
  }

  /**
   * Assert that service to retrieve all addresses returns the same values
   * retrieved by JDBC.
   */
  @Test
  public void testGetAllAddresses() throws Exception {
    List<Address> expected = new ArrayList<>();
    expected.add(createMockedAddress(1));

    new Expectations() {{
      jdbcTemplate.query(anyString, (RowMapperResultSetExtractor<?>) any);
      returns(expected);
    }};

    List<Address> actual = addressService.getAllAddresses();
    assertEquals(actual.size(), 1);
    assertEquals(actual.get(0), expected.get(0));
  }

  /**
   * Asserts that service to get a single address returns an address object
   * matching the given address Id.
   */
  @Test
  public void testGetAddress() throws Exception {
    final int addressId = 1;
    final Address expected = createMockedAddress(1);

    new Expectations() {{
      jdbcTemplate.queryForObject(anyString, new Object[]{addressId}, (RowMapper<?>) any);
      returns(expected);
    }};

    Address actual = addressService.getAddress(addressId);
    assertEquals(actual, expected);
    assertTrue(actual.getAddressId() == addressId);
  }

  private Address createMockedAddress(int addressId) {
    Address address = new Address();
    address.setAddressId(addressId);
    address.setStreet("street");
    address.setCity("city");
    address.setState("state");
    address.setZip("zip");
    return address;
  }
}