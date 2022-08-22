package com.example.demo.controller;

import com.example.demo.model.PatientRecord;
import com.example.demo.repository.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PatientRecordController {
    @Autowired
    PatientRecordRepository patientRecordRepository;
    @GetMapping("/listofpatients")
    public List<PatientRecord> getAllPatient(){return patientRecordRepository.findAll();}

    @GetMapping("/patient/{id}")
    public PatientRecord getPatient(@PathVariable Long id){return patientRecordRepository.findById(id).get();}

    @PostMapping("/patient")
    public String addPatient(@RequestBody PatientRecord patientRecord){
        boolean patientexistance = patientRecordRepository.existsById(patientRecord.getId());
        if(!patientexistance)
        {
            patientRecordRepository.save(patientRecord);
            return " patient added Successfully";
        }
        else {
            return "patient already exist ";
        }
    }

    @DeleteMapping("/patient/{id}")
    public String deletePatient(@PathVariable Long id){
        boolean studentexistance = patientRecordRepository.existsById(id);
        if(studentexistance) {
            patientRecordRepository.deleteById(id);
            return "patient deleted successfully";
        }
        else {
            return "patient does not exist";
        }
    }
    @PutMapping("/patient/{id}")
    public String updatePatient(@RequestBody PatientRecord patientRecord, @PathVariable Long id){
        PatientRecord Patientobj = patientRecordRepository.findById(id).get();
        Patientobj.setAge(patientRecord.getAge());
        Patientobj.setName(patientRecord.getName());
        Patientobj.setAddress(patientRecord.getAddress());
        patientRecordRepository.save(Patientobj);
        return "Record is Updated ";
    }
}