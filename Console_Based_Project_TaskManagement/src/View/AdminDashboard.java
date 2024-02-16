package View;

import Controller.Admin;

import java.util.*;

public class AdminDashboard extends Dashboard {
    static int ID;

    public AdminDashboard(Dashboard d) {
        super(d.getRole(), d.getUserID(), d.getPassword(), d.getID());
        ID = d.getID();
    }

    static Scanner getInput() {
        return new Scanner(System.in);
    }

    public static void DisplayMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. View All Projects");
        System.out.println("2. View Task");
        System.out.println("3. Add new Project");
        System.out.println("4. Add new Task To Worker");
        System.out.println("5. Update The Project To Completed");
        System.out.println("6. Change The Task To Another Worker");
        System.out.println("7. Add New The Worker To Current Project");
        System.out.println("8. Message to Worker");
        System.out.println("0. Logout");
        System.out.println("-.-.".repeat(10));
        System.out.print("Enter your choice: ");
        int choice = scan.nextInt();
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
                    System.out.println("Projects:");
                    System.out.println("Project_Id Projects_Name  Description  Worker_ID");
                    ArrayList<String> Projects = Admin.viewAllProjects(ID);
                    Display(Projects);
                    break;
                case 2:
                    System.out.println("All Tasks:");
                    ArrayList<String> Tasks = Admin.viewTask(ID);
                    Display(Tasks);
                    break;
                case 3:
                    System.out.println("Enter the Project Title:");
                    String p_Title = getInput().nextLine();
                    System.out.println("Enter the Project Description:");
                    String p_Description = getInput().nextLine();
                    if (Admin.isNewProjectVaild(ID, p_Title, p_Description)) {
                        System.out.println("New Project add successfully");
                    }
                    break;
                case 4:
                    System.out.println("Projects:");
                    System.out.println("Project_Id Projects_Name  Description  Worker_ID");
                    ArrayList<String> AllProjects = Admin.viewAllProjects(ID);
                    Display(AllProjects);
                    System.out.println("Enter the project id: ");
                    int P_id = Integer.valueOf(getInput().nextLine());
                    System.out.println("Enter Task Title:");
                    String P_Task_Title = getInput().nextLine();
                    System.out.println("Enter Task Description:");
                    String P_Task_Description = getInput().nextLine();
                    System.out.println("Enter the Worker ID to assign the task:");
                    int P_Worker_id = Integer.valueOf(getInput().nextLine());
                    if (Admin.isNewTaskToWorkerVaild(P_id, P_Task_Title, P_Task_Description, P_Worker_id, ID))
                        System.out.println("Task added Successfully");
                    break;
                case 5:
                    ArrayList<String> _Projects = Admin.viewAllProjects(ID);
                    Display(_Projects);
                    System.out.println("Enter the Project_id to remove");
                    int P_ID_To_Completed = Integer.valueOf(scan.nextLine());
                    if (Admin.isProjectCompleted(P_ID_To_Completed, ID)) {
                        System.out.println("Project was completed successfully");
                    }
                    break;
                case 6:
                    System.out.println("Enter the Project ID");
                    int P_ID = Integer.valueOf(scan.nextLine());
                    ArrayList<String> TaskList = Admin.viewTask(ID, P_ID);
                    Display(TaskList);
                    System.out.println("Enter the Task ID");
                    int Task_ID = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter the Old Worker ID:");
                    int OldWorkerID = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter the New Worker ID:");
                    int NewWorkerID = Integer.valueOf(scan.nextLine());
                    ArrayList<String> WorkerList = Admin.workerList();
                    Display(WorkerList);
                    if (Admin.isAssignTaskToAnotherWorker(Task_ID, OldWorkerID, NewWorkerID, ID)) {
                        System.out.println(NewWorkerID + " task assigned to " + Task_ID);
                    }
                    break;
                case 7:
                    Projects = Admin.viewAllProjects(ID);
                    Display(Projects);// change display query to isComplete != done
                    System.out.println("Enter the Project ID to add new Worker");
                    int Project_id = Integer.valueOf(scan.nextLine());
                    WorkerList = Admin.workerList();
                    Display(WorkerList);
                    System.out.println("Enter The New Worker ID");
                    int worker_id = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter The Task Title For New User");
                    String t_title = scan.nextLine();
                    System.out.println("Enter The Task Description");
                    String t_description = scan.nextLine();
                    if (Admin.isNewWorkToProject(Project_id, worker_id, t_title, t_description, ID)) {
                        System.out.println(t_title + " is assigned to new worker (ID)" + worker_id);
                    }
                    break;
                case 8:
                    Tasks = Admin.viewTask(ID);
                    Display(Tasks);
                    System.out.println("Enter the Worker ID ----> Message!!!");
                    int w_id = Integer.valueOf(scan.nextLine());
                    System.out.println("Enter the Message:");
                    String message = scan.nextLine();
                    if (Admin.messageToWorker(ID, w_id, message)) {
                        System.out.println("Message Sended Successfully");
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
