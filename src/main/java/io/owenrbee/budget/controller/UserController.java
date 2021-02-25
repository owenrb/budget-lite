package io.owenrbee.budget.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@GetMapping
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
		
		System.out.println("Authenticate");
		
		String name = principal.getAttribute("name");
		if(name == null) {
			name = principal.getAttribute("login");
		}
		
		
        return Collections.singletonMap("name", name);
    }

}
