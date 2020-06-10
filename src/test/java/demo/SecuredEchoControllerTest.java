package demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = SecuredEchoController.class)
@ExtendWith(SpringExtension.class)
public class SecuredEchoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void userDeniedForNoUser() throws Exception {
		mockMvc.perform(get("/secured/echoes/hello"))
			.andExpect(status().isUnauthorized());
	}

	@WithMockUser
	@Test
	public void userAllowedUser() throws Exception {
		mockMvc.perform(get("/secured/echoes/hello"))
			.andExpect(status().isOk());
	}

}
