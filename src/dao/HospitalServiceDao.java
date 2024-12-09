package dao;

import moduls.Hospital;
import moduls.Patient;

import java.util.List;
import java.util.Map;

public interface HospitalServiceDao {
    String addHospital(Hospital hospital);
    Hospital findHospitalById(Long id);
    List<Hospital> getAllHospital();
    List<Patient> getAllPatientFromHospital(Long id);
    String deleteHospitalById(Long id);
    Map<String, Hospital> getAllHospitalByAddress(String address);
}
