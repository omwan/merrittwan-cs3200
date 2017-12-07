package merrittwan.cs3200.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import merrittwan.cs3200.entity.Address;
import merrittwan.cs3200.service.address.AddressService;

/**
 * Controller for API endpoints for addresses.
 * Created by olivi on 11/15/2017.
 */
@Controller
@CrossOrigin
@RequestMapping("/api/address")
public class AddressController {

  @Autowired
  private AddressService addressService;

  /**
   * Get all addresses currently stored in database.
   *
   * @return list of address objects
   */
  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public List<Address> getAllAddresses() {
    return addressService.getAllAddresses();
  }

  /**
   * Get the address object matching the given primary key.
   *
   * @param id primary key of address to find
   * @return address object with the given primary key
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public Address getAddress(@RequestParam(name = "id") int id) {
    return addressService.getAddress(id);
  }

}
