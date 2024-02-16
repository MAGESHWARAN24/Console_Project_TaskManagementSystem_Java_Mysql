package Model;

import java.sql.*;
import java.util.ArrayList;

public class WorkerModel {
    static Connection getConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/TaskManagementSystem";
        String jdbcUsername = "root";
        String jdbcPassword = "Mypassword1234";
        try {
            return DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> DisplayProjects(int ID) {
        String query = "SELECT P.name, P.description FROM Users u INNER JOIN Tasks T ON U.id = T.assigned_to "
                + "INNER JOIN Projects P ON P.project_id = T.project_id WHERE U.id = ?";
        ArrayList<String> Data = new ArrayList<>();
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setInt(1, ID);
            ResultSet r = pstm.executeQuery();
            while (r.next())
                Data.add(r.getString(1) + " " + r.getString(2));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Data;
    }

    public static ArrayList<String> pendingTask(int ID) {
        String query = "SELECT task_id,project_id,title,description FROM Tasks WHERE assigned_to = ? AND status != 'done'";
        ArrayList<String> Data = new ArrayList<>();
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setInt(1, ID);
            ResultSet r = pstm.executeQuery();
            while (r.next())
                Data.add(r.getString(1) + " " + r.getString(2) + " " + r.getString(3) + " " + r.getString(4));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Data;
    }

    public static boolean updateStatus(int ID, int task_id, String status) {
        String query = "UPDATE Tasks SET status = ? WHERE assigned_to = ? AND task_id = ?";
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setString(1, status);
            pstm.setInt(2, ID);
            pstm.setInt(3, task_id);
            int r = pstm.executeUpdate();
            return (r > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public static boolean Message(int ID, int admin_id, String message) {
        String query = "INSERT INTO Messages(sender_id,receiver_id,message) VALUES(?,?,?)";
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setInt(1, ID);
            pstm.setInt(2, admin_id);
            pstm.setString(3, message);
            int r = pstm.executeUpdate();
            return (r > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }
}