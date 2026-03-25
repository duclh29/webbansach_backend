package com.leduc.webbansach_backend.service;

import com.leduc.webbansach_backend.entity.NguoiDung;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    public NguoiDung findByUsername(String tenDangNhap);



}
