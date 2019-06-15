package com.rainbow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.config.domain.SystemConfig;
import com.rainbow.config.service.SystemConfigService;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.service.SupervisionSastindService;
import com.rainbow.system.domain.User;
import com.rainbow.system.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RainbowApplicationTests {


	private MockMvc mockMvc;

	private MockHttpSession session;

	@Autowired
	private WebApplicationContext wac;


	@Before
	public void setupMockMvc() {

//		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//		params.add("username", "mrbird");
//		params.add("password", "123456");
//		params.add("code", "test");
//		params.add("rememberMe", "true");
//		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
///*		session = new MockHttpSession();
//		User user = new User();
//		user.setUsername("MrBird");
//		user.setPassword("42ee25d1e43e9f57119a00d0a39e5250");
//		session.setAttribute("user", user);*/
//
//		SupervisionSastind sastind = new SupervisionSastind();
//		sastind.setId("111");
//		String requestJson =JSONObject.toJSONString(sastind);

		try {
//			mockMvc.perform(MockMvcRequestBuilders.get("/sastind/delete").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(MockMvcResultMatchers.status().isOk());
			//mockMvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void Test1() {
//		try {
//			mockMvc.perform(MockMvcRequestBuilders.post("/user/delete").param("ids","10"))
//			.andDo(MockMvcResultHandlers.print());
//		}catch (Exception e){
//			e.printStackTrace();
//		}
	}




	@Autowired
	private UserService userService;


	@Test
	public void  test() throws Exception {
//		User user = new User();
//		user.setUserId(10L);
//		user.setUsername("haol");
//		user.setPassword("123456");
//		user.setEmail("haol@");
//		user.setStatus("1");
//		userService.save(user);
	}

	@Test
	public void testDeleUser() throws Exception{
//		userService.deleteByKey(10);
//		System.out.println("test");
	}

	@Autowired
	private SystemConfigService systemConfigService;

	@Test
	public void testGetAllSystemConfig() {
		/*Map<String, List<SystemConfig>> map;
		map = systemConfigService.getAllSystemConfigList();

		int a=5;*/
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("tableName","config_political");
//		map.put("id", "640c55a746b847d89cc36886b8af4702");
//		map.put("value","modify2");
//		//map.put("order","5");
//		systemConfigService.modifyConfig(map);

//		int a=5;
	}
}
