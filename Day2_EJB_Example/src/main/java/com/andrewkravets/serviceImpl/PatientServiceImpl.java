package com.andrewkravets.serviceImpl;

import com.andrewkravets.model.Patient;
import com.andrewkravets.service.PatientService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Andrew Kravets.
 * Date: 1/1/14.
 * Url: andrewkravets.com
 */
@Stateless
public class PatientServiceImpl implements PatientService {

    public static final Class PATIENT_CLASS = Patient.class;
    @PersistenceContext(unitName = "patientsBase")
    private EntityManager em;

    public Patient findPatientById(Long id) {
        return (Patient) em.find(PATIENT_CLASS, id);
    }

    public List<Patient> getAllPatients() {
        return em.createQuery("select p from Patient p").getResultList();
    }

    public void removePatient(Patient patient) {
        patient = em.merge(patient);
        em.remove(patient);
    }

    public void savePatient(Patient patient) {
        em.persist(patient);
    }

    @Override
    public Patient merge(Patient patient) {
        return em.merge(patient);
    }

}
