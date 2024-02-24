package lk.ijse.carservicesystem.dto;

public class AppointmentAndServiceDTO {

    private String amount;
    private String appointmentId;
    private String serviceId;

    public AppointmentAndServiceDTO() {
    }

    public AppointmentAndServiceDTO(String amount, String appointmentId, String serviceId) {
        this.amount = amount;
        this.appointmentId = appointmentId;
        this.serviceId = serviceId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "AppointmentAndService{" +
                "amount='" + amount + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                '}';
    }
}
