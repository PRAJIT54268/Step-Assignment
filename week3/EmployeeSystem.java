package week3;

public class EmployeeSystem {

    static class Employee {
        private String empId, empName;
        private double salary;

        Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }
    }

    static class ManagerEmployee extends Employee {
        private double teamBonus;

        ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    static class InternEmployee extends Employee {
        private double stipendCap;

        InternEmployee(String empId, String empName, double salary, double stipendCap) {
            super(empId, empName, salary);
            this.stipendCap = stipendCap;
        }

        double effectiveSalary() {
            return Math.min(getSalary(), stipendCap);
        }
    }

    public static void main(String[] args) {
        Employee plain = new Employee("E001", "Divya", 40000);
        ManagerEmployee manager = new ManagerEmployee("E002", "Karan", 70000, 8000);
        InternEmployee intern = new InternEmployee("E003", "Meera", 12000, 10000);

        Employee[] employees = { plain, manager, intern };
        String[] labels = { "Plain employee", "Manager", "Intern" };

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] instanceof ManagerEmployee m)
                System.out.println(labels[i] + " effective pay: Rs " + m.effectiveSalary());
            else if (employees[i] instanceof InternEmployee in)
                System.out.println(labels[i] + " effective pay: Rs " + in.effectiveSalary());
            else
                System.out.println(labels[i] + " pay: Rs " + employees[i].getSalary());
        }
    }
}