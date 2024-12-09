package dao;

import moduls.Department;

import java.util.List;

public interface DepartmentServiceDao {
    List<Department> getAllDepartmentByHospital(Long id);
    Department findDepartmentByName(String name);

    String add(Long hospitalId, Department department);

    void removeById(Long id);

    String updateById(Long id, Department department);
}
