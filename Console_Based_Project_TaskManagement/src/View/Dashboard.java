package View;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Model.DB;

public class Dashboard {
    private String role;
    private String userID;
    private String password;
    private int ID;

    Dashboard(String role, String userID, String password, int ID) {
        this.role = role;
        this.userID = userID;
        this.password = password;
        this.ID = ID;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRole() {
        return this.role;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getPassword() {
        return this.password;
    }

    public int getID() {
        return this.ID;
    }

    public class Main {
        static boolean isSignInValid(String userId, String password) {
            if (userId.isEmpty() || password.isEmpty()) {
                System.out.println("Some input fields are empty fill it!");
                return true;
            }
            String E_Regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\r\n";
            Pattern E_pattern = Pattern.compile(E_Regex);
            Matcher E_matcher = E_pattern.matcher(userId);
            if (E_matcher.matches()) {
                System.out.println("Enter the vaild email id");
                return true;
            }
            String P_Regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\r\n";
            Pattern P_pattern = Pattern.compile(P_Regex);
            Matcher P_matcher = P_pattern.matcher(password);
            if (password.length() < 8 || P_matcher.matches()) {
                System.out.println("Password must be 8 charcters or more & (Atleast one Uppercase,Lowercase,Digit)");
                return true;
            }
            return false;
        }

        static boolean isSignUpValid(String designation, String userId, String userName, String password) {
            if (designation.isEmpty() || userName.isEmpty() || userId.isEmpty() || password.isEmpty()) {
                System.out.println("Some input fields are empty fill it!");
                return true;
            }
            String P_Regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\r\n";
            Pattern P_pattern = Pattern.compile(P_Regex);
            Matcher P_matcher = P_pattern.matcher(password);
            String E_Regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\r\n";
            Pattern E_pattern = Pattern.compile(E_Regex);
            Matcher E_matcher = E_pattern.matcher(userId);
            if (password.length() < 8 || P_matcher.matches()) {
                System.out.println("Password must be 8 charcters and use special character");
                return true;
            }
            if (E_matcher.matches()) {
                System.out.println("Enter the vaild email id");
                return true;
            }
            return false;
        }

        static void signIn(Dashboard d) {
            Scanner scan = new Scanner(System.in);
            System.out.println("EmailID:");
            String userId = scan.nextLine();
            System.out.println("Password:");
            String password = scan.nextLine();
            if (isSignInValid(userId, password)) {
                signIn(d);
            }
            d.setUserID(userId);
            d.setPassword(password);
            ArrayList<String> r = DB.signIn(d);
            if (r.size() != 0) {
                String role = r.get(0);
                int ID = Integer.valueOf(r.get(1));
                System.out.println("<----------Wellcome " + role + " -------->");
                d.setRole(role);
                d.setID(ID);
                if (role.equalsIgnoreCase("admin")) {
                    AdminDashboard A = new AdminDashboard(d);
                    AdminDashboard.DisplayMenu();
                } else {
                    WorkerDashboard B = new WorkerDashboard(d);
                    WorkerDashboard.DisplayMenu();
                }
            } else {
                System.out.println("Enter the vaild Credentails");
                signIn(d);
            }
            scan.close();
        }

        static void signUp(Dashboard d) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the designation Admin = A / Worker = W:");
            String designation = scan.nextLine();
            System.out.println("Name:");
            String userName = scan.nextLine();
            System.out.println("EmailID:");
            String userId = scan.nextLine();
            System.out.println("Password:");
            String password = scan.nextLine();
            
            if (isSignUpValid(designation, userName, userId, password)) {
                signUp(d);
            }
            d.setRole((designation.equalsIgnoreCase("A")) ? "admin" : "woker");
            d.setUserID(userId);
            d.setPassword(password);
            DB.signUp(d,userName);
            signIn(d);
            scan.close();
        }
        static void Display() {
        	Scanner scan = new Scanner(System.in);
        	System.out.println("SignIn = In / SignUp = Up / Exit = E");
        	Dashboard d = new Dashboard("", "", "", 0);
            String op = "";
            do {
                op = scan.nextLine();
                if (op.equalsIgnoreCase("In"))
                    signIn(d);
                else if (op.equalsIgnoreCase("Up"))
                    signUp(d);
                else
                    System.out.println("----------try again----------");
            } while (!op.equalsIgnoreCase("E"));
            scan.close();
        }
        public static void main(String args[]) {    
            Display();
        }
    }
}
