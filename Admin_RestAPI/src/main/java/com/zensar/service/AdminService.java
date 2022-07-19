package com.zensar.service;

import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;

import com.zensar.dto.AdminDTO;

public interface AdminService {

    List<AdminDTO> adminList();

    List<AdminDTO> searchByCriteria(String name, String email);

    Admin updateAdmin(Admin admin);

    Admin registerAdmin(Admin admin);

}
