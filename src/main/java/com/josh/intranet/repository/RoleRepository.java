package com.josh.intranet.repository;

import com.josh.intranet.model.Project;
import com.josh.intranet.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
