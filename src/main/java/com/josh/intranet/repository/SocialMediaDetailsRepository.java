package com.josh.intranet.repository;

import com.josh.intranet.model.PersonalDetails;
import com.josh.intranet.model.SocialMediaDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaDetailsRepository extends JpaRepository<SocialMediaDetails, Long> {
}
