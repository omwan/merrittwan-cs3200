package merrittwan.cs3200;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by olivi on 11/17/2017.
 */
@RequestMapping("/hello")
@Controller
public class HelloController {

  @RequestMapping(value = "/world", method = RequestMethod.GET)
  @ResponseBody
  public String helloWorld() {
    return "hello world";
  }

}
