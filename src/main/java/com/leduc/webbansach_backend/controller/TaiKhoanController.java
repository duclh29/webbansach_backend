package com.leduc.webbansach_backend.controller;

import com.leduc.webbansach_backend.entity.NguoiDung;
import com.leduc.webbansach_backend.security.JwtResponse;
import com.leduc.webbansach_backend.security.LoginRequest;
import com.leduc.webbansach_backend.service.JwtService;
import com.leduc.webbansach_backend.service.TaiKhoanService;
import com.leduc.webbansach_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tai-khoan")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/dang-ky")
    public ResponseEntity<?> dangKyNguoiDung(@Validated @RequestBody NguoiDung  nguoiDung) {
        ResponseEntity<?> responseEntity = taiKhoanService.dangKyNguoiDung(nguoiDung);
        return responseEntity;
    }

    @GetMapping("/kich-hoat")
    public ResponseEntity<?> kichHoatTaiKhoan(@RequestParam String email, @RequestParam String maKichHoat) {
        ResponseEntity<?> responseEntity = taiKhoanService.kichHoatTaiKhoan(email, maKichHoat);
        return responseEntity;
    }

    @PostMapping("/dang-nhap")
    public ResponseEntity<?> dangNhap(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                final String jwt = jwtService.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu không chính xác");
        }
        return ResponseEntity.badRequest().body("Xác thực không thành công");


    }

}
