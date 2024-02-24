package lk.ijse.carservicesystem.dto;

public class AttendanceDto {
    private String id;
    private String date;
    private String working_hours;
    private String attendance_history;
    private String employeeId;

    public AttendanceDto() {
    }

    public AttendanceDto(String id, String date, String working_hours, String attendance_history, String employeeId) {
        this.id = id;
        this.date = date;
        this.working_hours = working_hours;
        this.attendance_history = attendance_history;
        this.employeeId = employeeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }

    public String getAttendance_history() {
        return attendance_history;
    }

    public void setAttendance_history(String attendance_history) {
        this.attendance_history = attendance_history;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "AttendanceDto{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", working_hours='" + working_hours + '\'' +
                ", attendance_history='" + attendance_history + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}
