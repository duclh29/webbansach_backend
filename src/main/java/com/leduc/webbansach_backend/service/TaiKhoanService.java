package com.leduc.webbansach_backend.service;

import com.leduc.webbansach_backend.entity.NguoiDung;
import com.leduc.webbansach_backend.entity.ThongBao;
import com.leduc.webbansach_backend.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaiKhoanService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private EmailService emailService;


    public ResponseEntity<?> dangKyNguoiDung(NguoiDung nguoiDung) {
        // kiem tra da ton tai chua
        if(nguoiDungRepository.existsByTenDangNhap(nguoiDung.getTenDangNhap())) {
            return ResponseEntity.badRequest().body(new ThongBao("Ten dang nhap da ton tai! Vui long nhap lai thong tin"));
        }
        // kiem tra email da ton tai chua
        if(nguoiDungRepository.existsByEmail(nguoiDung.getEmail())) {
            return ResponseEntity.badRequest().body(new ThongBao("Email da ton tai! Vui long nhap lai thong tin email"));

        }

        //Ma hoa mat khau
        String endcrypt = passwordEncoder.encode(nguoiDung.getMatKhau());
        nguoiDung.setMatKhau(endcrypt);

        //gan va gui thong kick hoat
        nguoiDung.setMaKichHoat(taoMaKichHoat());
        nguoiDung.setDaKichHoat(false);

        // Luu nguoi dung vao db
        NguoiDung nguoiDung_daDangKy = nguoiDungRepository.save(nguoiDung);

        //gui emai cho nguoi dung de kich hoat
        guiEmaiKichHoat(nguoiDung.getEmail(), nguoiDung.getMaKichHoat());

        return ResponseEntity.ok("Đăng ký thành công");

    }

    private String taoMaKichHoat(){
        // tao 1 ma ngau nhien
        return UUID.randomUUID().toString();
    }

    private void guiEmaiKichHoat(String email, String maKichHoat){
        String subject = "Kích hoạt tài khoản của bạn tại Webbansach";
        String text = "Vui lòng sủ dụng mã sau để kích hoạt cho tài khoản <"+email+"> <"+maKichHoat+">";
        text+="<br/> Click vào đường link để kích hoạt tài khoản: ";
        String url = "http://localhost:3000/kich-hoat/"+email+"/"+maKichHoat;
        text+="<br/> <a href="+url+">"+url+"</a>";

        emailService.sendMessage(email, subject, "lehuynhduc150303@gmail.com", text);
    }


    public ResponseEntity<?> kichHoatTaiKhoan(String email, String maKichHoat) {
        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(email);

        if(nguoiDung == null) {
            return ResponseEntity.badRequest().body(new ThongBao("Người dùng không tồn tại!"));
        }

        if(nguoiDung.isDaKichHoat()) {
            return ResponseEntity.badRequest().body(new ThongBao("Tài khoản đã được kích hoạt"));
        }

        if(maKichHoat.equals(nguoiDung.getMaKichHoat())) {
            nguoiDung.setDaKichHoat(true);
            nguoiDungRepository.save(nguoiDung);
            return ResponseEntity.ok("Kích hoạt tài khoản thành công!");
        }else {
            return ResponseEntity.badRequest().body(new ThongBao("Mã kích hoạt không chính xác!"));
        }

    }




}
