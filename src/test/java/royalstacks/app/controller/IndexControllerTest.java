package royalstacks.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class IndexControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private IndexController indexController;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(new IndexController()).build();
    }

    @Test
    public void uriTemplate() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("homepage"));


    }
}
