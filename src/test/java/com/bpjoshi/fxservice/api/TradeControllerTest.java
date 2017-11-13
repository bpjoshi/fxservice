package com.bpjoshi.fxservice.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bpjoshi.fxservice.FxserviceApplication;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FxserviceApplication.class)
@ActiveProfiles("test")
public class TradeControllerTest {
	

	@Autowired
    WebApplicationContext context;
	@Autowired
	TradeController tradeController;
	private MockMvc mvc;
	
	@Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

	 @Test
    public void shouldHaveEmptyDB() throws Exception {
        mvc.perform(get("/trades/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
	 
}
