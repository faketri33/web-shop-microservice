import org.faketri.infastructure.user.controller.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.logging.Logger;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = controller.class)
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureRestDocs()
public class UserControllerTest {

    private static final Logger LOGGER = Logger.getLogger(UserControllerTest.class.getName());
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEndpoint() throws Exception {
        mockMvc.perform(get("/api/user/"))
                .andExpect(status().isOk())
                .andDo(document("user-endpoint"));
    }
}
