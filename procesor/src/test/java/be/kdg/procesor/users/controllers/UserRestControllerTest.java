package be.kdg.procesor.users.controllers;

import be.kdg.procesor.users.dto.AdminDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRestController userRestController;

    @Test
    public void loadUser() throws Exception {

        mockMvc.perform(get("/api/users/1")
                .with(user("sam").password("pwd"))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());
        //.andExpect(jsonPath("city", is(arrival.getCity())));
    }

    @Test
    public void createUser() {
    }

    @Test
    public void updateUser() throws Exception {
        AdminDTO adminDTO = new AdminDTO("sammy", "abc");
        String requestJson = objectMapper.writeValueAsString(adminDTO);

        mockMvc.perform(post("/api/users")
                .with(user("sam").password("pwd"))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andDo(print());
        //.andExpect(content().string(containsString("sammy")));

    }

    @Test
    public void deleteUser() {
    }
}