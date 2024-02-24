package lk.ijse.carservicesystem.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderTm {
    private String vehicaleID;
    private String Model;
    private String serviceId;
    private String type;
    private double totle;
}
