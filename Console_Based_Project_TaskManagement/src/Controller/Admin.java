package Controller;

import Model.AdminModel;
import java.util.*;

public class Admin {
    public static ArrayList<String> viewAllProjects(int ID) {
        ArrayList<String> Data = AdminModel.DisplayProjects(ID);
        if (Data.isEmpty())
            Data.add("No Projects!!");
        return Data;
    }

    public static ArrayList<String> viewTask(int ID) {
        ArrayList<String> Data = AdminModel.Tasks(ID);
        if (Data.isEmpty())
            Data.add("No Task!!");
        return Data;
    }

    public static ArrayList<String> viewTask(int ID, int Project_id) {
        ArrayList<String> Data = AdminModel.Tasks(ID, Project_id);
        if (Data.isEmpty())
            Data.add("No Task!!");
        return Data;
    }

    public static boolean addNewProject(int ID, String p_Title, String p_Descripition) {
        return AdminModel.addProject(ID, p_Title, p_Descripition);
    }

    public static boolean addNewTask(int p_id, String p_Task_Title, String p_Task_Description, int p_Worker_id,
            int adminID) {
        boolean result = AdminModel.addTask(p_id, p_Task_Title, p_Task_Description, p_Worker_id);
        if (result)
            Admin.messageToWorker(adminID, p_Worker_id, "Check your Task List and try to complete as soon as possible");
        return result;
    }

    public static boolean removeProject(int ID, int P_ID_To_Completed) {
        return AdminModel.removeProject(ID, P_ID_To_Completed);
    }

    public static boolean swapTask(int ID, int OldWorkerID, int NewWorkerID) {
        return AdminModel.changeWorker(ID, OldWorkerID, NewWorkerID);
    }

    public static boolean addWorker(int ID, int project_id, int worker_id, String t_title, String t_description) {
        return AdminModel.newWorkerToProject(ID, project_id, worker_id, t_title, t_description);
    }

    public static boolean messageToWorker(int Sender_ID, int Receicer_ID, String Message) {
        return AdminModel.Messages(Sender_ID, Receicer_ID, Message);
    }

    public static ArrayList<String> workerList() {
        return AdminModel.workerList();
    }

    public static boolean isNewProjectVaild(int ID, String p_Title, String p_Description) {
        if (p_Title.isEmpty() || p_Description.isEmpty()) {
            System.out.println("-----Fill The Project Details-----");
            return false;
        }
        return Admin.addNewProject(ID, p_Title, p_Description);
    }

    public static boolean isNewTaskToWorkerVaild(int p_id, String p_Task_Title, String p_Task_Description,
            int p_Worker_id, int ID) {
        if (("" + p_id).isEmpty() || p_Task_Title.isEmpty() || p_Task_Description.isEmpty()
                || ("" + p_Worker_id).isEmpty()) {
            System.out.println("-----Some Fields are empty try again  ----");
            return false;
        }
        return Admin.addNewTask(p_id, p_Task_Title, p_Task_Description, p_Worker_id, ID);
    }

    public static boolean isProjectCompleted(int P_ID_To_Completed, int ID) {
        if (!("" + P_ID_To_Completed).isEmpty()) {
            System.out.println("-----Some Fields are empty try again  ----");
            return false;
        }
        return Admin.removeProject(ID, P_ID_To_Completed);
    }

    public static boolean isAssignTaskToAnotherWorker(int task_ID, int oldWorker_ID, int newWorker_ID, int ID) {
        if (("" + task_ID).isEmpty() || ("" + oldWorker_ID).isEmpty() || ("" + newWorker_ID).isEmpty()) {
            System.out.println("-----Some Fields are empty try again  ----");
            return false;
        }
        boolean result = swapTask(task_ID, oldWorker_ID, newWorker_ID);
        if (result) {
            Admin.messageToWorker(ID, newWorker_ID,
                    "You are " + newWorker_ID + " assigned to a new Task in " + task_ID + " check your task List");
            Admin.messageToWorker(ID, oldWorker_ID, "You are " + oldWorker_ID + " remove from this Task " + task_ID);
        }

        return result;
    }

    public static boolean isNewWorkToProject(int project_id, int worker_id, String t_title, String t_description,
            int ID) {
        if (("" + project_id).isEmpty() || ("" + worker_id).isEmpty() || t_title.isEmpty() || t_description.isEmpty()) {
            System.out.println("-----Some Fields are empty try again  ----");
            return false;
        }
        boolean result = Admin.addWorker(ID, project_id, worker_id, t_title, t_description);
        if (result) {
            Admin.messageToWorker(ID, worker_id,
                    "You have addition task to this project->" + project_id + " Check your task list!!");

        }
        return result;
    }

}
