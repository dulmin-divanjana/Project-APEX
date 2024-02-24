package lk.ijse.carservicesystem.dto.tm;

    public class CustomerTm {
        private String id;
        private String name;
        private String address;

        private String email;
        private String contact;

        public CustomerTm() {
        }

        public CustomerTm(String id, String name, String address, String contact,String email ) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.contact = contact;
            this.email = email;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }


        @Override
        public String toString() {
            return "CustomerTm{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", contact='" + contact + '\'' +
                    ", email='"   + email + '\'' +
                    '}';
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
    }
