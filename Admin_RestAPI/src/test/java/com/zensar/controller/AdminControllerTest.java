package com.zensar.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zensar.dto.AdminDTO;
import com.zensar.service.AdminService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class AdminControllerTest {

	@MockBean
	    AdminService adminService;


	    @Autowired
	    MockMvc mockMvc;

	    @Autowired
	    AdminController adminController;

	    @Autowired
	    ObjectMapper objectMapper;
	    
	    @Test
	    public void testRegisterAdmin() throws Exception {

	        AdminDTO admin = new AdminDTO();
	        admin.setName("prashant");

	        when(this.adminService.registerAdmin(admin)).thenReturn(admin);
	        MvcResult mvcResult = this.mockMvc.perform(post("http://localhost:7779/admin/user")
	                .contentType("application/json").content(objectMapper.writeValueAsString(admin)))
	                .andExpect(status().isCreated()).andReturn();

	        String response = mvcResult.getResponse().getContentAsString();
	        assertEquals(response.contains("name"), true);

	    }


}
