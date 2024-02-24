package lk.ijse.carservicesystem.dto;

public class AppointmentAndItemDto {
    private String appointmentId;
    private String itemId;

    public AppointmentAndItemDto() {
    }

    public AppointmentAndItemDto(String appointmentId, String itemId) {
        this.appointmentId = appointmentId;
        this.itemId = itemId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "AppointmentAndItemDto{" +
                "appointmentId='" + appointmentId + '\'' +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}
