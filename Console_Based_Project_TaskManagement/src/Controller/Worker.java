package Controller;

import java.util.ArrayList;
import Model.WorkerModel;

public class Worker {

    public static ArrayList<String> viewAllProjects(int ID) {
        ArrayList<String> Data = WorkerModel.DisplayProjects(ID);
        if (Data.isEmpty())
            Data.add("No Projects!!");
        return Data;
    }

    public static ArrayList<String> viewPendingTask(int ID) {
        ArrayList<String> Data = WorkerModel.pendingTask(ID);
        if (Data.isEmpty())
            Data.add("No Projects!!");
        return Data;
    }

    // Message
    public static boolean updatePendingTask(int ID, int Task_id, String status) {
        return WorkerModel.updateStatus(ID, Task_id, status);
    }

    public static boolean sendMessageToAdmin(int ID, int admin_id, String message) {
        return WorkerModel.Message(ID, admin_id, message);
    }
}
