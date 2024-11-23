/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Huyen
 */
public class NhanVienRepository {
    public ArrayList<NhanVien> getAll() {
        // B1: Viet cau SQL 
        String sql = """
                     SELECT MaNV, MatKhau, HoTen, VaiTro
                     FROM   NhanVien
                     """;
        // B2: Mo cong ket noi => Xay ra ngoai le => nem vao try catch
        // try..with..resource
        // try(nhung doi tuong nao can dong ket noi ){
        //            
        // }catch(ten loi){
        //     // ngoai le
        // }
        // B3: Tao ArrayList 
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            // table => kieu du lieu ResultSet
            ResultSet rs = ps.executeQuery();
            // Khi kieu du lieu tra ve la 1 bang => executeQuery
            while (rs.next()) { // Kiem tra xem con co dong de doc tiep hay k 
                // B4: Tao doi tuong nhan vien 
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setHoTen(rs.getString(2));
                nv.setMatKhau(rs.getString(3));
                nv.setVaiTro(rs.getString(4));
                // B5: Add vao list 
                lstNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace(); // nem loi khi xay ra 
        }
        return lstNhanVien;
    }
     public NhanVien getOne(int ma) {
        String sql = """
                     SELECT MaNV, MatKhau, HoTen, VaiTro
                     FROM   NhanVien
                     WHERE MaNV = ? 
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1, ma);
            // table => kieu du lieu ResultSet
            ResultSet rs = ps.executeQuery();
            // Khi kieu du lieu tra ve la 1 bang => executeQuery
            while (rs.next()) { // Kiem tra xem con co dong de doc tiep hay k 
                // B4: Tao doi tuong nhan vien 
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt(1));
                nv.setHoTen(rs.getString(2));
                nv.setMatKhau(rs.getString(3));
                nv.setVaiTro(rs.getString(4));
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace(); // nem loi khi xay ra 
        }
        return null;
    }
     // Add => CREATE => Insert into 
    public boolean add(NhanVien nv) {
        // B1: Viet cau SQL 
        String sql = """
                    INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) 
                    VALUES(?,?,?,?);
                     """;
        int check = 0; // false 
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getMaNV());
            ps.setObject(2, nv.getMatKhau());
            ps.setObject(3, nv.getHoTen());
            ps.setObject(4, nv.getVaiTro());
            check = ps.executeUpdate(); // Tra ra so dong duoc add thanh cong 
        } catch (Exception e) {
            e.printStackTrace(); // nem loi khi xay ra 
        }
        return check > 0;
    }

       public static void main(String[] args) {
//        System.out.println(new GiangVienRepository().getOne("TienNh21"));
        System.out.println(new NhanVienRepository().getAll());
        
        //System.out.println(new NhanVienRepository().add(new NhanVien(6, "matkhau6", "Dinh Tien Quang", "Nhan vien")));
        
        //System.out.println(new NhanVienRepository().getAll());
        System.out.println(new NhanVienRepository().getOne(6));
    }

}
