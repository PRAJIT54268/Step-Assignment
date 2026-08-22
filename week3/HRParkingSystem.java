package week3;

public class HRParkingSystem {

    static class Employee {
        private String empId, empName;
        private double salary;

        Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        double getSalary() { return salary; }

        double effectiveSalary() { return salary; }
    }

    static class ManagerEmployee extends Employee {
        private double teamBonus;

        ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() { return getSalary() + teamBonus; }
    }

    static class InternEmployee extends Employee {
        private double stipendCap;

        InternEmployee(String empId, String empName, double salary, double stipendCap) {
            super(empId, empName, salary);
            this.stipendCap = stipendCap;
        }

        double effectiveSalary() { return Math.min(getSalary(), stipendCap); }
    }

    static class ParkingSlot {
        String slotNo;
        int capacity, occupiedCount;

        ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        boolean allot() {
            if (occupiedCount < capacity) { occupiedCount++; return true; }
            return false;
        }
    }

    static class CompanyEmployeeRecord {
        String name, empId;
        Employee employee;
        ParkingSlot slot;
        static int totalRecords = 0;

        CompanyEmployeeRecord(String name, String empId, Employee employee) {
            this.name = name;
            this.empId = empId;
            this.employee = employee;
            totalRecords++;
        }

        String fullProfile() {
            String slotInfo = (slot != null) ? slot.slotNo : "no parking assigned";
            return name + " | Pay: Rs " + employee.effectiveSalary() + " | Slot: " + slotInfo;
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot s : slots) {
            if (s.occupiedCount < s.capacity) return s;
        }
        return null;
    }

    static void safeAllot(ParkingSlot[] slots, CompanyEmployeeRecord record) {
        ParkingSlot slot = findAvailableSlot(slots);
        if (slot == null) System.out.println("No slots available for " + record.name);
        else { slot.allot(); record.slot = slot; }
    }

    public static void main(String[] args) {
        CompanyEmployeeRecord divya = new CompanyEmployeeRecord("Divya", "E001", new ManagerEmployee("E001", "Divya", 70000, 8000));
        CompanyEmployeeRecord karan = new CompanyEmployeeRecord("Karan", "E002", new Employee("E002", "Karan", 40000));
        CompanyEmployeeRecord meera = new CompanyEmployeeRecord("Meera", "E003", new InternEmployee("E003", "Meera", 12000, 10000));

        ParkingSlot[] slots = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 4)
        };

        safeAllot(slots, divya);
        safeAllot(slots, karan);

        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}