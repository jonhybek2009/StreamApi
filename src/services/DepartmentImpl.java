package services;

import dao.DepartmentDaoImpl;
import dao.DepartmentServiceDao;
import dao.GenericServiceDao;
import moduls.Department;

import java.util.List;

public class DepartmentImpl implements  DepartmentService, GenericServiceDao<Department> {
    private final DepartmentServiceDao departmentDao = new DepartmentDaoImpl();
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return departmentDao.getAllDepartmentByHospital(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return departmentDao.findDepartmentByName(name);
    }

    @Override
    public String add(Long hospitalId, Department department) {
        return departmentDao.add(hospitalId,department);
    }

    @Override
    public void removeById(Long id) {
        departmentDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Department department) {
        return departmentDao.updateById(id,department);
    }
}
