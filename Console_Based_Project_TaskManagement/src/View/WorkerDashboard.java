package View;

import java.util.*;
import Controller.Worker;

// Handle the other Exceptions
public class WorkerDashboard extends Dashboard {
    static int ID;

    public WorkerDashboard(Dashboard d) {
        super(d.getRole(), d.getUserID(), d.getPassword(), d.getID());
        ID = d.getID();
    }

    public static void DisplayMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. View All Projects");
        System.out.println("2. View Pending Task");
        System.out.println("3. Update Pending Task");
        System.out.println("4. Send Message to Admin");
        System.out.println("0. Exit");
        int choice = Integer.valueOf(scan.nextLine());
        run(choice);
        scan.close();
    }

    static void Display(ArrayList<String> Data) {
        for (String i : Data)
            System.out.println(i);
    }

    public static void run(int choice) {
        Scanner scan = new Scanner(System.in);
        do {
            switch (choice) {
                case 1:
                    ArrayList<String> TaskList = Worker.viewAllProjects(ID);
                    System.out.println("Worked Projects");
                    Display(TaskList);
                    break;
                case 2:
                    ArrayList<String> PendingTask = Worker.viewPendingTask(ID);
                    System.out.println("Pending Works");
                    Display(PendingTask);
                    break;
                case 3:
                    PendingTask = Worker.viewPendingTask(ID);
                    Display(PendingTask);
                    System.out.println("Enter The Task ID");
                    int Task_id = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter The Status");
                    System.out.println("1 => In_progress");
                    System.out.println("2 => Done");
                    int op = Integer.valueOf(scan.nextLine());
                    String status = (op == 1) ? "in_progress" : "done";
                    if (Worker.updatePendingTask(ID, Task_id, status)) {
                        System.out.println("Status Updated Successfully");
                    }
                    break;
                case 4:
                    System.out.println("Enter Admin ID To send a Message:");
                    int Admin_id = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter The Message");
                    String Message = scan.nextLine();
                    if (Worker.sendMessageToAdmin(ID, Admin_id, Message)) {
                        System.out.println("Message sended successfully!");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
            DisplayMenu();
        } while (choice != 0);
        scan.close();
    }
}
