package lk.ijse.carservicesystem.dto.tm;

public class SupplierTm {

    private String id;
    private String name;
    private String email;
    private String contact;
    private String location;

    public SupplierTm() {
    }

    public SupplierTm(String id, String name, String email, String contact, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.location = location;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SupplierTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

