package app.PatientHealthApp.restControllers.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.PatientHealthApp.domain.objects.surgery.AppointmentRequest;
import app.PatientHealthApp.domain.users.Patient;
import app.PatientHealthApp.jsonObject.request.surgery.AppointmentUpdateRequest;
import app.PatientHealthApp.jsonObject.response.Response;
import app.PatientHealthApp.jsonObject.response.surgery.AppointmentRequestResponseList;
import app.PatientHealthApp.services.UserServiceDetailsImpl;

/**
 * 
 */

 @RestController
 @RequestMapping("ajax/admin-appointment")
public class ManageAppointmentRest {
    
    @Autowired
    UserServiceDetailsImpl userServices;

    /** Appointment Requests */
    // Create Appointment Request

    // Read Appointment Requests
    @GetMapping(value="/read-requests/{patientId}")
    public Object readAppointmentRequest(@PathVariable int patientId) {
        Patient p = userServices.getPatientRepo().findById(patientId);
        List<AppointmentRequest> appointments = p.getAppointmentRequests();
        AppointmentRequestResponseList res = new AppointmentRequestResponseList();
       if (appointments.size() != 0) {
           res.setResponse(true);//successful
           res.setRequests(appointments);
       }

        return res;
    }

    // Update Appointment Requests - mark as answered
    @PostMapping(value="/update-requests")
    public Object updateAppointmentRequest(@RequestBody AppointmentUpdateRequest request) {
        Response res = new Response();
        if (request == null) {
            res.setResponse(false);
            res.setMessage("HTTP request body is empty");
            return res;
        }
        int pid = request.getPid();//patient id
        int rid = request.getRid();//(appointment) request id
        boolean answered = request.getAnswered();

        Patient p = userServices.getPatientRepo().findById(pid);
        AppointmentRequest req = p.getAppointmentRequests().get(rid);
        
       
        res.setResponse(true);
        res.setMessage("Appointment updated successfully.");
        return res;
    }

    // Delete Appointment Requests


    /** Appointment Requests */

    //Response to appointment - and set appointment request



/** Appointments  */

    //Create Appointment


    //Read Apppointment

    //Update Appointment

    //Delete Appointment

}
