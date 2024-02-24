package lk.ijse.carservicesystem.dto.tm;

public class VehicleTm {

    private String id;
    private String model;
    private String color;
    private String mileage;
    private String customerId;

    public VehicleTm() {
    }

    public VehicleTm(String id, String model, String color, String mileage, String customerId) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "VehicleTm{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", mileage='" + mileage + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}

