package dao;

import dataBase.DataBase;
import moduls.Department;
import moduls.Hospital;

import java.util.ArrayList;
import java.util.List;

import static dataBase.DataBase.hospitals;

public class DepartmentDaoImpl implements  DepartmentServiceDao, GenericServiceDao<Department>{
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(id);
        if (hospital == null) {
            return new ArrayList<>();
        }
        return hospital.getDepartments();
    }


    @Override
    public Department findDepartmentByName(String name) {
        return hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getDepartmentName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
    @Override
    public String add(Long hospitalId, Department department) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(hospitalId);
        if (hospital != null) {
            hospital.getDepartments().add(department);
            return "Successfully added";
        }
        return "Try again";
    }


    @Override
    public void removeById(Long id) {
        boolean delete= hospitals.stream()
                .anyMatch(hospital -> hospital.getDepartments().removeIf(department -> department.getId().equals(id)));
        System.out.println(delete?"Successfully deleted": "Try again");

    }

    @Override
    public String updateById(Long id, Department department) {
        return hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(existingDepartment -> existingDepartment.getId().equals(id))
                .findFirst()
                .map(existingDepartment -> {
                    existingDepartment.setDepartmentName(department.getDepartmentName());
                    return "Successfully updated";
                })
                .orElse("Try again.");
    }
}
