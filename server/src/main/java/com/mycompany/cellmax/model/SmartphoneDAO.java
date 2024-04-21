package com.mycompany.cellmax.model;

import com.mycompany.cellmax.controller.SQLConnection;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SmartphoneDAO {

    private final SQLConnection sqlConnection;

    public SmartphoneDAO() {
        this.sqlConnection = new SQLConnection();
        this.sqlConnection.connectDatabase();
    }

    public List<Smartphone> getAllSmartphones() {
        List<Smartphone> smartphones = new ArrayList<>();
        try {
            Connection conn = this.sqlConnection.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM smartphones");
            while (rs.next()) {
                Smartphone smartphone = new Smartphone();
                smartphone.setId(rs.getLong("id"));
                smartphone.setName(rs.getString("name"));
                smartphone.setBrand(rs.getString("brand"));
                smartphone.setYear(rs.getInt("year"));
                smartphone.setSystemName(rs.getString("systemName"));
                smartphone.setScreenSize(rs.getDouble("screenSize"));
                smartphone.setCpu(rs.getString("cpu"));
                smartphone.setCameraNumber(rs.getInt("cameraNumber"));
                smartphone.setCameraMP(rs.getInt("cameraMP"));
                smartphone.setImageLink(rs.getString("imageLink"));
                smartphones.add(smartphone);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return smartphones;
    }

    public Smartphone addSmartphone(Smartphone smartphone) {
        try {
            Connection conn = this.sqlConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO smartphones (name, brand, year, systemName, screenSize, cpu, cameraNumber, cameraMP, imageLink) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, smartphone.getName());
            statement.setString(2, smartphone.getBrand());
            statement.setInt(3, smartphone.getYear());
            statement.setString(4, smartphone.getSystemName());
            statement.setDouble(5, smartphone.getScreenSize());
            statement.setString(6, smartphone.getCpu());
            statement.setInt(7, smartphone.getCameraNumber());
            statement.setInt(8, smartphone.getCameraMP());
            statement.setString(9, smartphone.getImageLink());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Novo smartphone adicionado com sucesso!");
                return smartphone;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Smartphone editSmartphone(Smartphone smartphone) {
        try (Connection conn = this.sqlConnection.getConnection(); PreparedStatement statement = conn.prepareStatement("UPDATE smartphones SET name = ?, brand = ?, year = ?, systemName = ?, screenSize = ?, cpu = ?, cameraNumber = ?, cameraMP = ?, imageLink = ? WHERE id = ?")) {

            statement.setString(1, smartphone.getName());
            statement.setString(2, smartphone.getBrand());
            statement.setInt(3, smartphone.getYear());
            statement.setString(4, smartphone.getSystemName());
            statement.setDouble(5, smartphone.getScreenSize());
            statement.setString(6, smartphone.getCpu());
            statement.setInt(7, smartphone.getCameraNumber());
            statement.setInt(8, smartphone.getCameraMP());
            statement.setString(9, smartphone.getImageLink());
            statement.setLong(10, smartphone.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Smartphone atualizado com sucesso!");
                return smartphone;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
