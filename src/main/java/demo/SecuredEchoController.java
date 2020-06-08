package demo;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/secured/echoes")
public class SecuredEchoController {

	@GetMapping("/{message}")
	public String sayHi(@PathVariable String message) {
		return "User " + SecurityContextHolder.getContext().getAuthentication().getName() + " says "+ message;
	}
}
