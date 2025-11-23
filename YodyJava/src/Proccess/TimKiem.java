/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proccess;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ngocanh
 */
public class TimKiem {
    public List<PhieuXuat> searchByMaPX(String keyword) throws SQLException {
    List<PhieuXuat> list = new ArrayList<>();
    String sql = "SELECT * FROM PHIEUXUAT WHERE maphieuxuat LIKE ?";

    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, "%" + keyword + "%");

    ResultSet rs = ps.executeQuery();
    while(rs.next()){
        PhieuXuat px = new PhieuXuat();
        px.setMaPhieuXuat(rs.getInt("maphieuxuat"));
        px.setNgayXuat(rs.getTimestamp("ngayxuat"));
        px.setMaKH(rs.getInt("makh"));
        px.setMaNV(rs.getInt("manv"));
        px.setTongTien(rs.getFloat("tongtien"));
        px.setGhiChu(rs.getString("ghichu"));
        list.add(px);
    }
    return list;
}
}
