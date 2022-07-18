package com.zensar.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.zensar.dto.Admin;
import com.zensar.service.AdminService;

class AdminControllerTest {

//    @Test
//    public void testAddUser() throws Exception {
//
//	User user = new User();
//	user.setUserName("raja");
//	user.setPassword("raja");
//
//	when(this.loginService.registerUser(user)).thenReturn(user);
//
//	MvcResult mvcResult = this.mockMvc
//		.perform(post("http://localhost:9009/olx/login/user").contentType("application/json")
//			.content(objectMapper.writeValueAsString(user)))
//		.andExpect(status().isOk()).andExpect(content().string(containsString("raja"))).andReturn();
//
//	String response = mvcResult.getResponse().getContentAsString();
//	assertEquals(response.contains("raja"), true);
//    }

    private AdminService adminService;

    @Test
    public void testRegisterAdmin() {
	Admin admin = new Admin();
	admin.setName("admin");
	admin.setPassword("admin");
	admin.setEmail("admin@gmail.com");
	admin.setPhoneNumber("1234512345");

	when(this.adminService.registerAdmin(admin).thenReturn(admin));

    }

}
