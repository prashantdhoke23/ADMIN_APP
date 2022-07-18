package com.zensar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.AdminDTO;
import com.zensar.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping(value = "/user", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Admin registerAdmin(@RequestBody Admin admin) {
	return adminService.registerAdmin(admin);

    }

    @PutMapping(value = "/user", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Admin updateAdmin(@RequestBody Admin admin) {
	return adminService.updateAdmin(admin);

    }

    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<AdminDTO> adminList() {
	return adminService.adminList();
    }

    @GetMapping(value = "/search", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<AdminDTO> searchByCriteria(@RequestParam(name = "name", required = false) String name,
	    @RequestParam(name = "name", required = false) String username) {
	return adminService.searchByCriteria(name, username);
    }

}
