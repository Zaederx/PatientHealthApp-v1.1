package app.PatientHealthApp.restControllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.PatientHealthApp.jsonObject.response.Response;
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
        Response res = new Response();

        return res;
    }

    // Update Appointment Requests

    // Delete Appointment Requests


    /** Appointment Requests */

    //Response to appointment - and set appointment request



/** Appointments  */

    //Create Appointment


    //Read Apppointment

    //Update Appointment

    //Delete Appointment

}
