package apple;

import javax.net.ssl.SSLServerSocket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * @author Neeraj
 *         This class will -
 *         1. Create server socket
 *         2. Read data from csv
 *         3. Load data from csv to memory, join by id
 *         4. Accept input id from client socket
 *         5. Return o/p
 *         6. Accept Special Command to stop server socket
 */

public class AppleDataEngineeringAssignment {


    public static void main(String[] args) throws Exception {
        // Start server socket
        int portNumber = 4444; // @ TODO Read from property file
        String employeeNamesFile = "/home/neeraj/Desktop/employee_names.csv"; // @ TODO Read from property file
        String employeePayFile = "/home/neeraj/Desktop/employee_pay.csv"; // @ TODO Read from property file
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";

        try (ServerSocket listener = new ServerSocket(portNumber)) {
            while (true) {
                try (Socket socket = listener.accept()) {
                    PrintWriter out =
                            new PrintWriter(socket.getOutputStream(), true);
                    out.println(new Date().toString());

                    Map<String, Employee> employees = new HashMap<>();
                    br = new BufferedReader(new FileReader(employeeNamesFile));
                    while ((line = br.readLine()) != null) {
                        // use comma as separator
                        String[] employeeNameFields = line.split(csvSplitBy);
                        Employee employee = new Employee();
                        String empId = employeeNameFields[0];
                        employee.setEmpId(empId);
                        employee.setFirstName(employeeNameFields[1]);
                        employee.setLastName(employeeNameFields[2]);

                        employees.put(empId, employee);
                    }

                    br = new BufferedReader(new FileReader(employeePayFile));
                    while ((line = br.readLine()) != null) {
                        // use comma as separator
                        String[] employeePayFields = line.split(csvSplitBy);
                        Employee employee = employees.get(employeePayFields[0]);
                        // Check if employee existed in employee names file, assuming it was master data file for getting employees
                        if (employee == null)
                            throw new AppleDataException(String.format("Employee does not exist with id {}", employeePayFields[0]));

                        employee.setSalary(Integer.parseInt(employeePayFields[1]));
                        employee.setBonus(Integer.parseInt(employeePayFields[2]));
                    }
                }
            }
        } finally {
            li
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
