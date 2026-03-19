package com.leduc.webbansach_backend.repository;

import com.leduc.webbansach_backend.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "nguoi-dung")
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
}
