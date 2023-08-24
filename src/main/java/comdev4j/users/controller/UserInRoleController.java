package comdev4j.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import comdev4j.users.services.RoleService;

@RestController
@RequestMapping("/roles")
public class UserInRoleController {
	
	@Autowired
	private RoleService service;
}
