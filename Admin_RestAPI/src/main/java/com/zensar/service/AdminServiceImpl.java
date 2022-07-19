package com.zensar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Service;

import com.zensar.entity.AdminEntity;
import com.zensar.exception.InvalidSearchingDataException;
import com.zensar.repo.AdminRepo;
import com.zensar.dto.AdminDTO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public List<AdminDTO> adminList() {
		List<AdminEntity> adminEntities = adminRepo.findAll();
		List<AdminDTO> adminDTOs = new ArrayList<>();
		for (AdminEntity adminEntity : adminEntities) {
			adminDTOs.add(ConvertEntityToDTO(adminEntity));
		}
		return adminDTOs;
	}
	public AdminEntity ConvertDTOToEntity(AdminDTO adminDTO) {

		return modelMapper.map(adminDTO, AdminEntity.class);
	}

	public AdminDTO ConvertEntityToDTO(AdminEntity adminEntity) {
		return modelMapper.map(adminEntity, AdminDTO.class);
	}
	@Override
	public List<AdminDTO> searchByCriteria(String name, String email) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AdminEntity> criteriaQueryEntity = criteriaBuilder.createQuery(AdminEntity.class);
		Root<AdminEntity> rootDTO = criteriaQueryEntity.from(AdminEntity.class);

		Predicate namePredicate = criteriaBuilder.and();
		Predicate emailPredicate = criteriaBuilder.and();
		Predicate finalPredicate = criteriaBuilder.and();
		
		if (name != null && !"".equalsIgnoreCase(name)) {
			namePredicate = criteriaBuilder.like(rootDTO.get("name"), "%" + name + "%");
		}
		if (email != null && !"".equalsIgnoreCase(email)) {
			emailPredicate = criteriaBuilder.like(rootDTO.get("email"), "%" + email + "%");
		}
		
		finalPredicate = criteriaBuilder.and(namePredicate, emailPredicate);
		criteriaQueryEntity.where(finalPredicate);
		
		TypedQuery<AdminEntity> typedQuery = entityManager.createQuery(criteriaQueryEntity);
		List<AdminEntity> adminEntities = typedQuery.getResultList();
		List<AdminDTO> adminDTOs = new ArrayList<>();
		for (AdminEntity adminEntity : adminEntities) {
			adminDTOs.add(ConvertEntityToDTO(adminEntity));
		}

		if (adminDTOs.isEmpty()) {
			throw new InvalidSearchingDataException("Seaching Data is not Found");
		}

		return adminDTOs;
	}

	@Override
    public AdminDTO updateAdmin(int id, AdminDTO data) {
	
    	Optional<AdminEntity> opAdminEntity = adminRepo.findById(id);
    	
    	if(opAdminEntity.isPresent()) {
    		AdminEntity adminEntity = opAdminEntity.get();
    		adminEntity.setEmail(data.getEmail());
    		adminEntity.setName(data.getName());
    		adminEntity.setPassword(data.getPassword());
    		adminEntity.setPhNumber(data.getPhNumber());

    		adminEntity = adminRepo.save(adminEntity);
    	}
    	return data;
    }

    @Override
    public AdminDTO registerAdmin(AdminDTO admin) {
	
		AdminEntity adminEntity = ConvertDTOToEntity(admin);
		adminEntity = adminRepo.save(adminEntity);

		return ConvertEntityToDTO(adminEntity);
		
	}

}
