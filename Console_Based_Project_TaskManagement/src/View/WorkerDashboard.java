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
        System.out.println("5. See All Messages");
        System.out.println("0. Exit");
        System.out.println("-.-.".repeat(10)+"-");
        System.out.println("Enter The Choice:");
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
                	System.out.println("-.-.".repeat(10)+"-");
                    ArrayList<String> TaskList = Worker.viewAllProjects(ID);
                    System.out.println("Worked Projects");
                    Display(TaskList);
                    System.out.println("-.-.".repeat(10)+"-");
                    break;
                case 2:
                	System.out.println("-.-.".repeat(10)+"-");
                    ArrayList<String> PendingTask = Worker.viewPendingTask(ID);
                    System.out.println("Pending Works");
                    System.out.println("task_id"+" ".repeat(5)+"project_id"+" ".repeat(5)+"title"+" ".repeat(5)+"description");
                    Display(PendingTask);
                    System.out.println("-.-.".repeat(10)+"-");
                    break;
                case 3:
                	System.out.println("-.-.".repeat(10)+"-");
                    PendingTask = Worker.viewPendingTask(ID);
                    System.out.println("task_id"+" ".repeat(5)+"project_id"+" ".repeat(5)+"title"+" ".repeat(5)+"description");
                    Display(PendingTask);
                    System.out.println("-.-.".repeat(10)+"-");
                    System.out.println("Enter The Task ID");
                    int Task_id = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter The Status");
                    System.out.println("1 => In_progress");
                    System.out.println("2 => Done");
                    int op = Integer.valueOf(scan.nextLine());
                    String status = (op == 1) ? "in_progress" : "done";
                    if (Worker.isTaskUpdateVaild(ID, Task_id, status)) {
                        System.out.println("Status Updated Successfully");
                    }
                    break;
                case 4:
                    System.out.println("Enter Admin ID To send a Message:");
                    int Admin_id = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter The Message");
                    String Message = scan.nextLine();
                    
                    if (Worker.isMessage(ID, Admin_id, Message)) {
                        System.out.println("Message sended successfully!");
                    }
                    break;
                case 5:
                	System.out.println("-.-.".repeat(10)+"-");
                	System.out.println("Messages!!");
                	System.out.println();
                	System.out.println("SenderName  Messages                    ReceicveAt");
                	ArrayList<String> Messages = Worker.showMessages(ID);
                	Display(Messages);
                	System.out.println();
                	break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
            DisplayMenu();
        } while (choice != 0);
        scan.close();
    }
}
