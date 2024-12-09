import dataBase.Counter;
import dataBase.DataBase;
import enums.Gender;
import moduls.Department;
import moduls.Doctor;
import moduls.Hospital;
import moduls.Patient;
import services.DepartmentImpl;
import services.DoctorImpl;
import services.HospitalImpl;
import services.PatientImpl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        PatientImpl patientService = new PatientImpl();
        DoctorImpl doctorService = new DoctorImpl();
        HospitalImpl hospitalService = new HospitalImpl();
        DepartmentImpl departmentService = new DepartmentImpl();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        while (true) {
            try {

                System.out.println("1.Hospital service");
                System.out.println("2.Doctor service");
                System.out.println("3.Patient service");
                System.out.println("4.Department  service");
                System.out.print("Choose an option: ");
                int choice = scanner1.nextInt();
                switch (choice) {
                    case 1: {
                        try {
                            boolean check = true;
                            while (check) {
                                System.out.println("1. Create hospital");
                                System.out.println("2. Find hospital by id");
                                System.out.println("3. Get all hospital");
                                System.out.println("4. Get all patient from hospital id");
                                System.out.println("5. Delete hospital by id");
                                System.out.println("6. Get hospital by address");
                                System.out.println("7. Return to menu");

                                int choice1 = scanner1.nextInt();
//                                scanner.nextLine();
                                switch (choice1) {
                                    case 1: {
                                        try {

//                                            scanner.nextLine();
                                            System.out.print("Enter hospital name: ");
                                            String hospitalName = scanner.nextLine();
                                            System.out.print("Enter hospital address: ");
                                            String address = scanner.nextLine();
                                            Hospital hospital = new Hospital(Counter.counterHospitalId(), hospitalName, address, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                                            System.out.println(hospitalService.addHospital(hospital));
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                    case 2: {
                                        System.out.println("Enter hospital id: ");
                                        Long id = scanner1.nextLong();
//                                        scanner.nextLine();
                                        System.out.println(hospitalService.findHospitalById(id));
                                        break;
                                    }

                                    case 3: {
                                        System.out.println(hospitalService.getAllHospital());
                                    }
                                    break;
                                    case 4: {
                                        System.out.println("Enter hospital id: ");
                                        Long id = scanner1.nextLong();

//                                        scanner.nextLine();
                                        System.out.println(hospitalService.getAllPatientFromHospital(id));
                                    }
                                    break;
                                    case 5: {
                                        System.out.println("Enter hospital id: ");
                                        Long id = scanner1.nextLong();
//                                        scanner.nextLine();
                                        System.out.println(hospitalService.deleteHospitalById(id));
                                        break;
                                    }
                                    case 6: {
                                        System.out.println("Enter hospital address: ");
                                        String address = scanner.nextLine();
                                        System.out.println(hospitalService.getAllHospitalByAddress(address));
                                        break;
                                    }
                                    case 7: {
                                        check = false;
                                        break;
                                    }

                                    default: {
                                        System.out.println("Some problem try again");
                                    }
                                }
                                if (!check) {
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Try again");
                        }
                    }
//                    scanner.nextLine();
                    break;
                    case 2: {
                        try {
                            boolean check = true;
                            while (check) {
                                System.out.println("1. Create doctor ");
                                System.out.println("2. Find doctor by id");
                                System.out.println("3. Get all doctors by hospital id");
                                System.out.println("4. Delete doctor by id");
                                System.out.println("5. Update doctors");
                                System.out.println("6. Get all doctors by department Id");
                                System.out.println("7. Return to menu");

                                int choice1 = scannerInt.nextInt();
//                                scanner.nextLine();
                                switch (choice1) {
                                    case 1: {
                                        try {
                                            Long doctorId = Counter.counterDoctorId();
                                            scanner.nextLine();
                                            System.out.print("Enter doctor's first name: ");
                                            String doctorFirstName = scanner.nextLine();
                                            System.out.print("Enter doctor's last name: ");
                                            String doctorLastName = scanner.nextLine();
                                            System.out.print("Enter doctor's gender (MALE/FEMALE): ");
                                            String genderInput = scanner.nextLine().toUpperCase();
                                            Gender doctorGender = Gender.valueOf(genderInput);
                                            System.out.print("Enter doctor's experience years: ");
                                            int experienceYears = scanner.nextInt();
                                            Doctor doctor = new Doctor(doctorId, doctorFirstName, doctorLastName, doctorGender, experienceYears);
                                            System.out.print("Enter hospital ID to add this doctor: ");
                                            Long hospitalIdForDoctor = scanner.nextLong();
                                            System.out.println(doctorService.add(hospitalIdForDoctor, doctor));

                                        } catch (InputMismatchException e) {
                                            System.out.println("Ошибка: введено не число. Попробуйте снова.");
                                            scanner.nextLine();
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    }

                                    case 2: {
                                        System.out.println("Enter doctor id: ");
                                        Long id = scanner1.nextLong();
                                        System.out.println(doctorService.findDoctorById(id));
                                        break;
                                    }

                                    case 3: {

                                        System.out.println("Enter hospital id");
                                        Long id = scanner1.nextLong();
//                                        scanner1.nextLine();
                                        System.out.println(doctorService.getAllDoctorsByHospitalId(id));

                                        break;
                                    }
                                    case 4: {
                                        System.out.println("Enter doctor id: ");
                                        Long id = scanner1.nextLong();
//                                        scanner1.nextLine();
                                        doctorService.removeById(id);
                                    }
                                    break;
                                    case 5: {
                                        try {
                                            System.out.println("Write id doctor to update: ");
                                            Long id = scanner1.nextLong();
//                                            scanner.nextLine();

                                            System.out.println("Write name for update:");
                                            String name = scanner.nextLine();
                                            for (int i = 0; i < name.length(); i++) {
                                                if (!Character.isLetter(name.charAt(i))) {
                                                    throw new IllegalArgumentException("Write only letters");
                                                }
                                            }

                                            Doctor doctor = new Doctor();
                                            doctor.setFirstName(name);
                                            String doctor1 = doctorService.updateById(id, doctor);
                                            System.out.println("Successfully updated" + doctor1);
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        } catch (Exception e) {
                                            System.out.println("Error " + e.getMessage());
                                        }
                                        break;
                                    }
                                    case 6: {
                                        try {
                                            System.out.print("Enter department ID: ");
                                            Long departmentIdForDoctors = scanner.nextLong();
                                            System.out.println(doctorService.getAllDoctorsByDepartmentId(departmentIdForDoctors));
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;

                                    }
                                    case 7: {
                                        check = false;
                                        break;
                                    }
                                    default: {
                                        System.out.println("Some problem try again");
                                    }
                                }
                                if (!check) {
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Try again");
                        }
                    }

                    scanner.nextLine();
                    break;
                    case 3: {
                        try {
                            boolean check = true;
                            while (check) {
                                System.out.println("1. Create patient ");
                                System.out.println("2. Get patient by id ");
                                System.out.println("3. Get patient by age");
                                System.out.println("4. Sort patient by age");
                                System.out.println("5. Delete patient by id");
                                System.out.println("6. Return to menu");

                                int choice1 = scanner1.nextInt();
//                                scanner.nextLine();
                                switch (choice1) {
                                    case 1: {
                                        try {
                                            Long patientId = Counter.counterPatientId();
                                            scanner.nextLine();
                                            System.out.print("Enter patient's first name: ");
                                            String patientFirstName = scanner.nextLine();
                                            System.out.print("Enter patient's last name: ");
                                            String patientLastName = scanner.nextLine();
                                            System.out.print("Enter patient's age: ");
                                            int patientAge = scanner.nextInt();
                                            System.out.print("Enter patient's gender (MALE/FEMALE): ");
                                            scanner.nextLine();
                                            String patientGenderInput = scanner.nextLine().toUpperCase();
                                            Gender patientGender = Gender.valueOf(patientGenderInput);
                                            Patient patient = new Patient(patientId, patientFirstName, patientLastName, patientAge, patientGender);
                                            System.out.print("Enter hospital ID to add this patient: ");
                                            Long hospitalIdForPatient = scanner.nextLong();
                                            System.out.println(patientService.add(hospitalIdForPatient, patient));
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                    case 2: {
                                        System.out.println("Enter patient id: ");
                                        Long id = scanner1.nextLong();
//                                        scanner1.nextLine();
                                        System.out.println(patientService.getPatientById(id));
                                        break;
                                    }
                                    case 3: {
                                        System.out.println(patientService.getPatientByAge());
                                        break;
                                    }

                                    case 4: {
                                        System.out.println("asc or desc: ");
                                        String asc = scanner.nextLine();
                                        System.out.println(patientService.sortPatientsByAge(asc));
                                        break;
                                    }

                                    case 5: {
                                        System.out.println("Enter patient id: ");
                                        Long id = scanner1.nextLong();
//                                        scanner1.nextLine();
                                        patientService.removeById(id);
                                        break;
                                    }
                                    case 6: {
                                        check = false;
                                        break;
                                    }
                                    default: {
                                        System.out.println("Some problem try again");
                                    }
                                }
                                if (!check) {
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Try again");
                        }
                    }

//                    scanner.nextLine();
                    break;
                    case 4: {
                        try {
                            boolean check = true;
                            while (check) {
                                System.out.println("1. Create department");
                                System.out.println("2. Get all department by hospital id");
                                System.out.println("3. Get department by department name");
                                System.out.println("4.  Delete department by id");
                                System.out.println("5. Update department name");
                                System.out.println("6. Assign doctor to department");
                                System.out.println("7. Return menu");

                                int choice1 = scanner.nextInt();

                                switch (choice1) {
                                    case 1: {
                                        try {
                                            System.out.println("Enter hospital id: ");
                                            Long id1 = scanner.nextLong();
                                            System.out.println("Department name: ");
                                            String name = scannerStr.nextLine();
                                            for (int i = 0; i < name.length(); i++) {
                                                if (!Character.isLetter(name.charAt(i))) {
                                                    throw new IllegalArgumentException("Only letters");
                                                }
                                            }
                                            Long id = Counter.counterDepartmentId();


                                            System.out.println(departmentService.add(id1, new Department(id,name)));
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                    case 2: {
                                        System.out.println(" hospital id ? ");
                                        Long id = scanner1.nextLong();
                                        System.out.println(departmentService.getAllDepartmentByHospital(id));
//                                        for (Hospital hospital1 : DataBase.hospitals) {
//                                            for (Department department1 : hospital1.getDepartments()) {
//                                                if (department1 == null) {
//                                                    System.out.println("Is empty");
//                                                }
//                                            }
//                                        }
                                    }
                                    break;
                                    case 3: {
                                        try {
                                            System.out.println("Enter department name");
                                            String name = scannerStr.nextLine();
                                            for (int i = 0; i < name.length(); i++) {
                                                if (!Character.isLetter(name.charAt(i))) {
                                                    throw new IllegalArgumentException("Only letters");
                                                }
                                            }
                                            System.out.println(departmentService.findDepartmentByName(name));
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                    case 4: {
                                        System.out.println("Enter department ID to delete: ");
                                        Long id = scanner1.nextLong();
                                        departmentService.removeById(id);
                                        break;
                                    }

                                    case 5: {
                                        try {
                                            System.out.println("Write id department to update: ");
                                             Long id = scanner1.nextLong();

                                            System.out.println("Write name for update:");
                                            String name = scannerStr.nextLine();

                                            for (int i = 0; i < name.length(); i++) {
                                                if (!Character.isLetter(name.charAt(i))) {
                                                    throw new IllegalArgumentException("Write only letters");
                                                }
                                            }

                                            String result = departmentService.updateById(id, new Department(null, name, null));
                                            System.out.println(result);

                                            if (result.equals("Successfully updated")) {
                                                for (Hospital hospital : DataBase.hospitals) {
                                                    for (Department department : hospital.getDepartments()) {
                                                        if (department.getId().equals(id)) {
                                                            System.out.println("Updated department: " + department);
                                                        }
                                                    }
                                                }
                                            }
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        } catch (Exception e) {
                                            System.out.println("Error: " + e.getMessage());
                                        }
                                    }
                                    break;
                                    case 6: {
                                        System.out.print("Enter department ID: ");
                                        Long departmentIdForAssignment = scanner.nextLong();
                                        System.out.print("Enter doctor IDs (comma-separated): ");
//                                        scanner.nextLine();
                                        String[] doctorIdsInput = scannerStr.nextLine().split(",");
                                        List<Long> doctorIds = new ArrayList<>();
                                        for (String id : doctorIdsInput) {
                                            doctorIds.add(Long.parseLong(id.trim()));
                                        }
                                        System.out.println(doctorService.assignDoctorToDepartment(departmentIdForAssignment, doctorIds));
                                        break;
                                    }

                                    case 7: {
                                        check = false;

//                                        scanner1.nextLine();
                                        break;

                                    }
                                    default: {
                                        System.out.println("Some problem try again");
                                    }
                                }
                                if (!check) {
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Try again");
//                            scanner.nextLine();
//                            scanner1.nextLine();
                        }
                    }

//                    scanner.nextLine();
                    break;

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}