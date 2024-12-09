package services;

import dao.PatientDaoImpl;
import dao.PatientServiceDao;
import moduls.Patient;

import java.util.List;
import java.util.Map;

public class PatientImpl implements PatientService, GenericService<Patient> {
    private final PatientServiceDao patientServiceDao = new PatientDaoImpl();

    @Override
    public String add(Long hospitalId, Patient patient) {
        return patientServiceDao.add(hospitalId,patient);
    }

    @Override
    public void removeById(Long id) {
        patientServiceDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Patient patient) {
        return patientServiceDao.updateById(id,patient);
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        return patientServiceDao.addPatientsToHospital(id,patients);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientServiceDao.getPatientById(id);
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        return patientServiceDao.getPatientByAge();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return patientServiceDao.sortPatientsByAge(ascOrDesc);
    }
}
