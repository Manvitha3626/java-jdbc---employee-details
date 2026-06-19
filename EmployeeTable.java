package lib;
import java.sql.*;
import java.util.Scanner;
public class EmployeeTable {
    static Connection con;
    static Statement stmt;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college",
                    "root",
                    "Manvitha123");
            stmt = con.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS emp");
            stmt.executeUpdate(
                "CREATE TABLE emp(" +
                "employee_id INT PRIMARY KEY AUTO_INCREMENT," +
                "employee_name VARCHAR(50)," +
                "job_role VARCHAR(50)," +
                "salary DOUBLE)"
            );
            System.out.println("Table Created!");
            while (true) {
                System.out.println("\n1. Add Employee");
                System.out.println("2. Display Employees");
                System.out.println("3. Update Salary");
                System.out.println("4. Delete Employee");
                System.out.println("5. Display Column Details");
                System.out.println("6. Exit");
                System.out.print("Enter Choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        displayEmployees();
                        break;
                    case 3:
                        updateSalary();
                        break;
                    case 4:
                        deleteEmployee();
                        break;
                    case 5:
                        displayColumns();
                        break;
                    case 6:
                        exitProgram();
                        break;
                    default:
                        System.out.println("Invalid Choice!");
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // add Employee
    static void addEmployee() {
        try {
            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter Role: ");
            String role = sc.next();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            String sql = "INSERT INTO emp(employee_name,job_role,salary) VALUES('" +
                         name + "','" + role + "'," + salary + ")";

            stmt.executeUpdate(sql);

            System.out.println("Employee Added!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Display 
    static void displayEmployees() {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
            while (rs.next()) {
                System.out.println(
                    rs.getInt(1) + " " +
                    rs.getString(2) + " " +
                    rs.getString(3) + " " +
                    rs.getDouble(4)
                );
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //Update 
    static void updateSalary() {
        try {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            System.out.print("Enter New Salary: ");
            double salary = sc.nextDouble();
            stmt.executeUpdate(
                "UPDATE emp SET salary=" + salary +
                " WHERE employee_id=" + id
            );
            System.out.println("Salary Updated!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //  Delete 
    static void deleteEmployee() {
        try {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();

            stmt.executeUpdate(
                "DELETE FROM emp WHERE employee_id=" + id
            );

            System.out.println("Employee Deleted!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //  Display Column Details
    static void displayColumns() {
        try {
            System.out.print("Enter Column Name: ");
            String col = sc.next();
            ResultSet rs = stmt.executeQuery("SELECT " + col + " FROM emp");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Invalid Column Name!");
        }
    }

    // end
    static void exitProgram() {
        try {
            stmt.close();
            con.close();
            sc.close();

            System.out.println(" End");
           
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}