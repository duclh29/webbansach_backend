package com.leduc.webbansach_backend.repository;

import com.leduc.webbansach_backend.entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheLoaiRepository extends JpaRepository<TheLoai, Integer> {
}
