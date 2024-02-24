package lk.ijse.carservicesystem.model;

import lk.ijse.carservicesystem.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteOrderModel {

    public static boolean deleteAll(String customerId, String vehicaleId) throws SQLException {

        Connection connection = null;

        try {
            connection= DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean vehicaleDelete = VehicleModel.deleteModel(vehicaleId);
            System.out.println("1 "+vehicaleDelete);
            if(vehicaleDelete){
                boolean customerDelete = CustomerModel.deleteCustomer(customerId);
                System.out.println("2 "+customerDelete);
                if (customerDelete) {
                    connection.commit();
                }else {
                    connection.rollback();
                }
            }else {
                connection.rollback();
            }


        } catch (SQLException e) {
            if(connection!= null) connection.rollback();
            e.printStackTrace();
        }finally {
            if(connection!= null) connection.setAutoCommit(true);
        }
        return true;
    }
}
