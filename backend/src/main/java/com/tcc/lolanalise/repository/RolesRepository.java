package com.tcc.lolanalise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.lolanalise.domain.Role;
import com.tcc.lolanalise.domain.RoleName;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long>{
	public List<Role> findByName(RoleName name);
}
