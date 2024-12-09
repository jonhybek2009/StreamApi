package dao;

import dataBase.DataBase;
import moduls.Hospital;
import moduls.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HospitalDaoImpl implements HospitalServiceDao{
    @Override
    public String addHospital(Hospital hospital) {
        DataBase.hospitals.add(hospital);
        return "Successfully added";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId()
                        .equals(id)).findFirst()
                .orElse(null);

    }

    @Override
    public List<Hospital> getAllHospital() {
        return DataBase.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .map(Hospital::getPatients)
                .findFirst()
                .orElseGet(ArrayList::new);
    }

    @Override
    public String deleteHospitalById(Long id) {
        boolean delete= DataBase.hospitals.removeIf(hospital -> hospital.getId().equals(id));
        return delete ? "Successfully deleted" : "Try again";

    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return DataBase.hospitals.stream()
                .filter(hospital -> hospital.getAddress().equalsIgnoreCase(address))
                .collect(Collectors.toMap(Hospital::getAddress,hospital -> hospital));
    }
}
