package app.PatientHealthApp.jsonObject.request.surgery;

/**
 * Class used for receiving {@link @RequestBody} http requests.
 * For requests that mark an {@link AppoinmentRequest} as 
 * answered.
 */
public class AppointmentUpdateRequest {

    Integer pid;//patientId

    Integer rid;//requestId

    Boolean answered;//

    public AppointmentUpdateRequest() {
        
    }

    public AppointmentUpdateRequest(Integer pid, Integer rid, Boolean answered) {
        this.pid = pid;
        this.rid = rid;
        this.answered = answered;
    }


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }


    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    
}