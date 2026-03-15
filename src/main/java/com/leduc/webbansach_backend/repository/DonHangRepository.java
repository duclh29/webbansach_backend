package com.leduc.webbansach_backend.repository;

import com.leduc.webbansach_backend.entity.ChiTietDonHang;
import com.leduc.webbansach_backend.entity.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {
}
