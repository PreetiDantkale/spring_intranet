package com.josh.intranet.repository;

import com.josh.intranet.model.ContactDetails;
import com.josh.intranet.model.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Long> {
}
