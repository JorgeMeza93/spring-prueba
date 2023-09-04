package comdev4j.users.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class Controller {
	@GetMapping
	public String saludo() {
		return "!Hello World!";
	}
}
