package demo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Test
	public void requiresLogin() throws Exception {
		this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void httpBasicWorks() throws Exception {
		this.mockMvc.perform(get("/").with(httpBasic("user","password")))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello Boot!"));
	}

	@WithMockUser
	@Test
	public void authenticatedWorks() throws Exception {
		this.mockMvc.perform(get("/"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(content().string("Hello Boot!"));
	}
}
