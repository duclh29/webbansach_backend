package com.leduc.webbansach_backend.repository;

import com.leduc.webbansach_backend.entity.Quyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyenRepository extends JpaRepository<Quyen, Integer> {
}
