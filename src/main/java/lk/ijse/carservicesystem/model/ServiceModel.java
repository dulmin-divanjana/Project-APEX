package lk.ijse.carservicesystem.model;

import lk.ijse.carservicesystem.db.DbConnection;
import lk.ijse.carservicesystem.dto.ServiceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceModel {


    public static boolean deleteService(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM service WHERE id = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    public  boolean saveService(ServiceDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();;

        String sql = "INSERT INTO service VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getId());
        pstm.setString(2,dto.getName());
        pstm.setString(3,dto.getPrice());
        pstm.setString(4,dto.getStatus());
        pstm.setString(5,dto.getDescription());


        boolean isSaved = pstm.executeUpdate()>0;
        return isSaved;
    }

    public List<ServiceDto> getAllService() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM service";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ServiceDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new ServiceDto(
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

    public boolean updateService(ServiceDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Service SET name = ?, price = ?, status = ? ,description =?  WHERE id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2,dto.getPrice());
        pstm.setString(3,dto.getStatus());
        pstm.setString(4,dto.getDescription());
        pstm.setString(5, dto.getId());

        return pstm.executeUpdate() >0;
    }
    public ServiceDto searchService(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM service WHERE id = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        ServiceDto dto = null;

        if (resultSet.next()){
            String i = resultSet.getString(1);
            String name = resultSet.getString(2);
            String price = resultSet.getString(3);
            String status = resultSet.getString(4);
            String description = resultSet.getString(5);

            dto = new ServiceDto(id,name,price,status,description);
        }
        return dto;
    }


}


