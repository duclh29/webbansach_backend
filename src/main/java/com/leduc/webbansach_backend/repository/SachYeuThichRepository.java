package com.leduc.webbansach_backend.repository;

import com.leduc.webbansach_backend.entity.SachYeuThich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SachYeuThichRepository extends JpaRepository<SachYeuThich, Integer> {
}
