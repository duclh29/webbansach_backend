package com.leduc.webbansach_backend.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Sach")
public class Sach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_sach")
    private int maSach;

    @Column(name = "ten_sach", length = 256)
    private String tenSach;

    @Column(name = "ten_tac_gia", length = 256)
    private String tenTacGia;

    @Column(name = "isbn", length = 256)
    private String ISBN;

    @Column(name = "ma_ta", length = 256, columnDefinition = "text")
    private String mota;

    @Column(name = "gia_niem_yet", length = 256)
    private double giaNiemYet;

    @Column(name = "gia_ban", length = 256)
    private double giaBan;

    @Column(name = "so_luong", length = 256)
    private int soLuong;

    @Column(name = "trung_binh_xep_hang", length = 256)
    private double trungBinhXepHang;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH
    })
    @JoinTable(
            name = "sach_theloai",
            joinColumns = @JoinColumn(name = "ma_sach"),
            inverseJoinColumns = @JoinColumn(name = "ma_the_loai"))
    List<TheLoai> danhSachTheLoai;

    @OneToMany(mappedBy = "sach", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE
    })
    List<HinhAnh> danhSachHinhAnh;


    @OneToMany(mappedBy = "sach", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE
    })
    List<SuDanhGia> danhSachSuDanhGia;


    @OneToMany(mappedBy = "sach", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH
    })
    List<ChiTietDonHang> danhSachChiTietDonHang;


    @OneToMany(mappedBy = "sach", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE
    })
    List<SachYeuThich> danhSachYeuThich;
}
