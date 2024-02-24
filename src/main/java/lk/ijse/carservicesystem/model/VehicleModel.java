package lk.ijse.carservicesystem.model;

import lk.ijse.carservicesystem.db.DbConnection;
import lk.ijse.carservicesystem.dto.VehicleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleModel {
    public static boolean deleteModel(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Vehicle WHERE id = ?");
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public boolean saveVehicle(VehicleDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO vehicle VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getModel());
        pstm.setString(3, dto.getColor());
        pstm.setString(4,dto.getMileage());
        pstm.setString(5,dto.getCustomerId());

        return pstm.executeUpdate()>0;
    }

    public boolean updateVehicle(VehicleDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Vehicle SET model= ? ,colour = ? , mileage = ? WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getModel());
        pstm.setString(2, dto.getColor());
        pstm.setString(3, dto.getMileage());
        pstm.setString(4, dto.getId());

        return pstm.executeUpdate()>0;
    }

    public VehicleDto searchVehicle(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM vehicle WHERE id = ?");
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();
        VehicleDto dto = null;
        if (resultSet.next()) {
            dto = new VehicleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return dto;
    }


    public static List<VehicleDto> getAllVehicle() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM vehicle";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<VehicleDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new VehicleDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)

                    )
            );
        }
        return dtoList;
    }


}

