package com.groupeisi.adminservice.repository;

import com.groupeisi.adminservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
