package com.josh.intranet.repository;

import com.josh.intranet.model.Address;
import com.josh.intranet.model.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {
}
