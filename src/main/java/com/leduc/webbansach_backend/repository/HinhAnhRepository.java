package com.leduc.webbansach_backend.repository;

import com.leduc.webbansach_backend.entity.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Integer> {
}
