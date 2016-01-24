package com.lucien.spirit.module.security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucien.spirit.module.security.model.Role;
import com.lucien.spirit.module.security.model.enums.Permission;
import com.lucien.spirit.module.security.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<String> queryAllPermissions() {
        List<String> permissions = new ArrayList<String>();
        for (Permission permission : Permission.values())
            permissions.add(permission.getAbbreviation());
        Collections.sort(permissions);
        return permissions;
    }

    @Transactional
    public void update(Role role) {
        Role roleTemp = this.roleRepository.getOne(role.getId());
        roleTemp.setDescription(role.getDescription());
        roleTemp.setName(role.getName());
        roleTemp.setResource(role.getResource());
        this.roleRepository.saveAndFlush(roleTemp);
    }

	public void save(Role role) {
		roleRepository.save(role);
	}

	public Role findOne(long id) {
		return roleRepository.findOne(id);
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}
}
