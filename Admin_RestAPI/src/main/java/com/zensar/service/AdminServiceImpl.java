package com.zensar.service;

import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Service;

import com.zensar.dto.AdminDTO;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public List<AdminDTO> adminList() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<AdminDTO> searchByCriteria(String name, String username) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Admin updateAdmin(Admin admin) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Admin registerAdmin(Admin admin) {
	// TODO Auto-generated method stub
	return null;
    }

}
