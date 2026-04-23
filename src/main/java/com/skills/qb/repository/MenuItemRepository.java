package com.skills.qb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skills.qb.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

}
