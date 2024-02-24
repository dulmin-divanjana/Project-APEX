package lk.ijse.carservicesystem.dto.tm;

public class ServiceTm {

    private String id;
    private String name;
    private String price;
    private String status;
    private String description;

    public ServiceTm() {
    }

    public ServiceTm(String id, String name, String price, String status, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ServiceTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +

                '}';
    }


}
