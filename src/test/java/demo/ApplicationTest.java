package demo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
public class ApplicationTest {

    public static final String MESSAGE = "hello";
    public static final String PATH = "/secured/echoes/" + MESSAGE;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void requiresLogin() throws Exception {
        mockMvc.perform(get("/secured/echoes/" + MESSAGE))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void httpBasicWorks() throws Exception {
        this.mockMvc.perform(get("/secured/echoes/" + MESSAGE).with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(MESSAGE)));
    }

    @WithMockUser
    @Test
    public void authenticatedWorks() throws Exception {
        this.mockMvc.perform(get(PATH))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString(MESSAGE)));
    }
}
