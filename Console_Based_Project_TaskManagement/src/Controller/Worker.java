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
    
    public static boolean isTaskUpdateVaild(int worker_id, int task_id, String status) {
    	if((""+ worker_id).isEmpty()||(""+task_id).isEmpty() || status.isEmpty()) {
    		System.out.println("Some Fields are Empty try again!!");
    		return false;
    	}
    	if(!(""+worker_id).matches("[0-9]+") || !(""+task_id).matches("[0-9]+")) {
    		System.out.println("Worker_id & Task_id must be a number ");
    		return false;
    	}
    	return updatePendingTask(worker_id, task_id, status);
    }
    public static boolean updatePendingTask(int ID, int Task_id, String status) {
        return WorkerModel.updateStatus(ID, Task_id, status);
    }

    public static boolean sendMessageToAdmin(int ID, int admin_id, String message) {
        return WorkerModel.Message(ID, admin_id, message);
    }

	public static boolean isMessage(int sender_id, int receiver_id, String message) {
		if((""+sender_id).isEmpty() || (""+receiver_id).isEmpty() || message.isEmpty()) {
			System.out.println("Some fields are empty try  again!");
			return false;
		}			
		if(!(""+sender_id).matches("[0-9]+") || !(""+receiver_id).matches("[0-9]+")) {
			System.out.println("Receiver Id must be a number");
			return false;
		}
			
		return sendMessageToAdmin(sender_id,receiver_id,message);
	}
	public static ArrayList<String> showMessages(int ID) {
		ArrayList<String> Messages = WorkerModel.showMessages(ID);
		if(Messages.isEmpty())
			Messages.add("No Messages at now");
		return Messages;
	}
}
