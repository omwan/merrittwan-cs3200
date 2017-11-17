package merrittwan.cs3200.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import merrittwan.cs3200.repository.Address;
import merrittwan.cs3200.service.address.AddressService;

/**
 * Created by olivi on 11/15/2017.
 */
@Controller
@RequestMapping("/address")
public class AddressController {

  @Autowired
  private AddressService addressService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public List<Address> getAllAddresses() {
    return addressService.getAllAddresses();
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  @ResponseBody
  public Address getAddress(@RequestParam(name = "id") int id) {
    return addressService.getAddress(id);
  }

}
