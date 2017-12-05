package merrittwan.cs3200.service.address;

import java.util.List;

import merrittwan.cs3200.entity.Address;

/**
 * Service to manage data in ADDRESS table.
 * Created by olivi on 11/17/2017.
 */
public interface AddressService {

  /**
   * Get all addresses currently stored in database.
   *
   * @return list of address objects
   */
  List<Address> getAllAddresses();

  /**
   * Get the address object matching the given primary key.
   *
   * @param id primary key of address to find
   * @return address object with the given primary key
   */
  Address getAddress(int id);
}
