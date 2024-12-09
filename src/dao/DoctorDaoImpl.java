package dao;

import dataBase.DataBase;
import moduls.Department;
import moduls.Doctor;
import moduls.Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DoctorDaoImpl implements DoctorServiceDao, GenericServiceDao<Doctor> {
    @Override
    public String add(Long hospitalId, Doctor doctor) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(hospitalId);
        if (hospital != null) {
            hospital.getDoctors().add(doctor);
            return "Successfully added";
        }
        return "Try again";
    }


    @Override
    public void removeById(Long id) {
        boolean isRemoved = DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDoctors().stream())
                .anyMatch(doctor -> doctor.getId().equals(id) &&
                        DataBase.hospitals.stream()
                                .anyMatch(hospital -> hospital.getDoctors().remove(doctor)));

        System.out.println(isRemoved ? "Successfully deleted" : "Doctor not found. Try again.");
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDoctors().stream())
                .filter(existingDoctor -> existingDoctor.getId().equals(id))
                .findFirst()
                .map(existingDoctor -> {
                    existingDoctor.setFirstName(doctor.getFirstName());
                    existingDoctor.setLastName(doctor.getLastName());
                    existingDoctor.setGender(doctor.getGender());
                    existingDoctor.setExperienceYear(doctor.getExperienceYear());
                    return "Successfully updated";
                })
                .orElse("Try again");
    }

    @Override
    public Doctor findDoctorById(Long id) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDoctors().stream())
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getId().equals(departmentId))
                .findFirst()
                .map(department -> {
                    List<Doctor> doctors = doctorsId.stream()
                            .map(this::findDoctorById)
                            .filter(Objects::nonNull)
                            .toList();
                    department.getDoctors().addAll(doctors);
                    return "Successfully assigned";
                })
                .orElse("Try again");
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(id);
        return hospital != null ? hospital.getDoctors() : new ArrayList<>();
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(id)) {
                    return department.getDoctors();
                }
            }
        }
        return new ArrayList<>();
    }
}
