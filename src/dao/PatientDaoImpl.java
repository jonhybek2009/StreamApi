package dao;

import dataBase.DataBase;
import moduls.Hospital;
import moduls.Patient;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatientDaoImpl implements  PatientServiceDao, GenericServiceDao<Patient> {
    @Override
    public String add(Long hospitalId, Patient patient) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(hospitalId);
        if (hospital != null) {
            boolean exists = hospital.getPatients().stream()
                    .anyMatch(p -> p.getId().equals(patient.getId()));
            if (exists) {
                return "Patient with this ID already exists";
            }
            hospital.getPatients().add(patient);
            return "Successfully added";
        }
        return "Hospital not found. Try again";
    }

    @Override
    public void removeById(Long id) {
        DataBase.hospitals.stream()
                .filter(hospital -> hospital.getPatients().removeIf(patient -> patient.getId().equals(id)))
                .findFirst()
                .ifPresentOrElse(
                        hospital -> System.out.println("Successfully deleted"),
                        () -> System.out.println("Try again")
                );
    }

    @Override
    public String updateById(Long id, Patient patient) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(existingPatient -> {
                    existingPatient.setFirstName(patient.getFirstName());
                    existingPatient.setLastName(patient.getLastName());
                    existingPatient.setAge(patient.getAge());
                    existingPatient.setGender(patient.getGender());
                    return "Successfully updated";
                })
                .orElse("Try again");
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(hospital -> {
                    hospital.getPatients().addAll(patients);
                    return "Successfully added";
                })
                .orElse("Try again");
    }

    @Override
    public Patient getPatientById(Long id) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .filter(patient -> patient.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .collect(Collectors.toMap(
                        Patient::getAge,
                        patient -> patient,
                        (existing, o) -> existing // Обрабатываем дубли по возрасту
                ));
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .sorted("asc".equalsIgnoreCase(ascOrDesc)
                        ? Comparator.comparingInt(Patient::getAge)
                        : Comparator.comparingInt(Patient::getAge).reversed())
                .toList();
    }
}
