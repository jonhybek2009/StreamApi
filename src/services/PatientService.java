package services;

import moduls.Patient;

import java.util.List;
import java.util.Map;

public interface PatientService {
    String add(Long hospitalId, Patient patient);

    String updateById(Long id, Patient patient);

    String addPatientsToHospital(Long id, List<Patient> patients);

    Patient getPatientById(Long id);

    Map<Integer, Patient> getPatientByAge();

    List<Patient> sortPatientsByAge(String ascOrDesc);
}
