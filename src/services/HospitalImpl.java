package services;

import dao.HospitalDaoImpl;
import dao.HospitalServiceDao;
import moduls.Hospital;
import moduls.Patient;

import java.util.List;
import java.util.Map;

public class HospitalImpl implements HospitalService{
    private final HospitalServiceDao hospitalServiceDao = new HospitalDaoImpl();
    @Override
    public String addHospital(Hospital hospital) {
        return hospitalServiceDao.addHospital(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalServiceDao.findHospitalById(id);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalServiceDao.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return hospitalServiceDao.getAllPatientFromHospital(id);
    }

    @Override
    public String deleteHospitalById(Long id) {
        return hospitalServiceDao.deleteHospitalById(id);
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return hospitalServiceDao.getAllHospitalByAddress(address);
    }
}
