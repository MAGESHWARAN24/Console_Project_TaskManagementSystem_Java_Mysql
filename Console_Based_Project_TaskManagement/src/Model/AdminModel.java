package Model;

import java.sql.*;
import java.util.*;

public class AdminModel extends DB {

    public static ArrayList<String> workerList() {
        String query = "SELECT id,name FROM Users WHERE role = 'worker' AND isWork ='0'";
        ArrayList<String> Data = new ArrayList<>();
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            ResultSet r = pstm.executeQuery();
            while (r.next())
                Data.add(r.getString(1) + " " + r.getString(2));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Data;
    }

    public static ArrayList<String> DisplayProjects(int ID) {
        String query = "SELECT project_id,name,description FROM Projects WHERE u_id = ?";
        ArrayList<String> Data = new ArrayList<>();
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setInt(1, ID);
            ResultSet r = pstm.executeQuery();
            while (r.next())
                Data.add(r.getInt(1) + " " + r.getString(2) + " " + r.getString(4));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Data;
    }

    public static ArrayList<String> Tasks(int ID) {
        ArrayList<String> Data = new ArrayList<>();
        String query = "SELECT P.name, T.title, T.description, T.status, T.assigned_to FROM Projects P INNER JOIN Users U ON U.id = P.u_id "
                + "INNER JOIN Tasks T ON P.project_id = T.project_id WHERE U.id = ?";
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setInt(1, ID);
            ResultSet r = pstm.executeQuery();
            while (r.next())
                Data.add(r.getString(1) + " " + r.getString(2) + " " + r.getString(3) + " " + r.getString(4) + " "
                        + r.getInt(5));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Data;
    }

    public static ArrayList<String> Tasks(int ID, int P_ID) {
        ArrayList<String> Data = new ArrayList<>();
        String query = "SELECT P.name, T.title, T.description, T.status, T.assigned_to FROM Projects P INNER JOIN Users U ON U.id = P.u_id "
                + "INNER JOIN Tasks T ON P.project_id = T.project_id WHERE U.id = ? AND P.project_id = ?";
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setInt(1, ID);
            pstm.setInt(2, P_ID);
            ResultSet r = pstm.executeQuery();
            while (r.next())
                Data.add(r.getString(1) + " " + r.getString(2) + " " + r.getString(3) + " " + r.getString(4) + " "
                        + r.getInt(5));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Data;
    }

    public static boolean addProject(int ID, String p_Title, String p_Description) {
        String query = "INSERT INTO Projects(name,description,u_id) VALUES(?,?,?)";
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setString(1, p_Title);
            pstm.setString(2, p_Description);
            pstm.setInt(3, ID);
            int r = pstm.executeUpdate();
            return (r > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public static boolean addTask(int p_id, String p_Task_Title, String p_Task_Description, int p_Worker_id) {
        String query = "INSERT INTO Tasks(project_id,title,description,assigned_to) VALUES(?,?,?,?)";
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setInt(1, p_id);
            pstm.setString(2, p_Task_Title);
            pstm.setString(3, p_Task_Description);
            pstm.setInt(4, p_Worker_id);
            int r = pstm.executeUpdate();
            return (r > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public static boolean removeProject(int ID, int P_ID_To_Completed) {
        String query = "UPDATE Projects SET isComplete = 0 WHERE u_id = ? AND project_id = ?";
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setInt(1, ID);
            pstm.setInt(2, P_ID_To_Completed);
            int r = pstm.executeUpdate();
            return (r > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public static boolean changeWorker(int TaskID, int OldWorkerID, int NewWorkerID) {
        String query = "UPDATE Tasks SET assigned_to = ? WHERE assigned_to = ? AND task_id = ? AND status != 'done'";
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setInt(1, NewWorkerID);
            pstm.setInt(2, OldWorkerID);
            pstm.setInt(3, TaskID);
            int r = pstm.executeUpdate();
            return (r > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public static boolean newWorkerToProject(int iD, int project_id, int worker_id, String t_title,
            String t_description) {
        String query = "INSERT INTO Tasks(project_id,title,description,assigned_to) VALUES(?,?,?,?)";
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setInt(1, project_id);
            pstm.setString(2, t_title);
            pstm.setString(3, t_description);
            pstm.setInt(4, worker_id);
            int r = pstm.executeUpdate();
            return (r > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

    public static boolean Messages(int sender_id, int receicer_id, String message) {
        String query = "INSERT INTO Messages(sender_id,receiver_id,message) VALUES(?,?,?)";
        try {
            PreparedStatement pstm = getConnection().prepareStatement(query);
            pstm.setInt(1, sender_id);
            pstm.setInt(4, receicer_id);
            pstm.setString(3, message);
            int r = pstm.executeUpdate();
            return (r > 0);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
