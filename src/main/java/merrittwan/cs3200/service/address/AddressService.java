package merrittwan.cs3200.service.address;

import java.util.List;

import merrittwan.cs3200.repository.Address;

/**
 * Created by olivi on 11/17/2017.
 */
public interface AddressService {

  List<Address> getAllAddresses();

  Address getAddress(int id);
}
