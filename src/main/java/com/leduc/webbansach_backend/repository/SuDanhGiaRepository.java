package com.leduc.webbansach_backend.repository;

import com.leduc.webbansach_backend.entity.SuDanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuDanhGiaRepository extends JpaRepository<SuDanhGia, Long> {
}
