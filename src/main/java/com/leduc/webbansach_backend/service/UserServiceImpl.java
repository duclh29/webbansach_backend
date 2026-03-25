package com.leduc.webbansach_backend.service;

import com.leduc.webbansach_backend.entity.NguoiDung;
import com.leduc.webbansach_backend.entity.Quyen;
import com.leduc.webbansach_backend.repository.NguoiDungRepository;
import com.leduc.webbansach_backend.repository.QuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private QuyenRepository quyenRepository;

    @Autowired
    public UserServiceImpl(NguoiDungRepository nguoiDungRepository, QuyenRepository quyenRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
        this.quyenRepository = quyenRepository;
    }

    @Override
    public NguoiDung findByUsername(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        NguoiDung nguoiDung= nguoiDungRepository.findByTenDangNhap(username);

        if(nguoiDung==null){
            throw new UsernameNotFoundException("Tai khoan khong ton tai?");
        }
        User user = new User(nguoiDung.getTenDangNhap(), nguoiDung.getMatKhau(), rolesToAuthorities(nguoiDung.getDanhSachQuyen()));
        return user;


    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Quyen> quyens) {

        return quyens.stream().map(quyen -> new SimpleGrantedAuthority( quyen.getTenQuyen())).collect(Collectors.toList());


    }

}
