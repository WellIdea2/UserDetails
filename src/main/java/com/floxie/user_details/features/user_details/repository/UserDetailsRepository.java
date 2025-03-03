package com.floxie.user_details.features.user_details.repository;

import com.floxie.user_details.features.user_details.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, UUID> {

  void deleteByUserId(UUID userId);

  boolean existsByIdAndUserId(UUID id, UUID userId);

  Optional<UserDetails> findByUserId(UUID userId);
}
