package lk.ijse.carservicesystem.model;

import lk.ijse.carservicesystem.db.DbConnection;
import lk.ijse.carservicesystem.dto.EmployeeDto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {


        public static boolean deleteEmployee(String id) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "DELETE FROM employee WHERE id = ? ";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,id);
            return pstm.executeUpdate()>0;
        }

        public  boolean saveEmployee(EmployeeDto dto) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();;

            String sql = "INSERT INTO employee VALUES(?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1,dto.getId());
            pstm.setString(2,dto.getName());
            pstm.setString(3,dto.getEmail());
            pstm.setString(4,dto.getContact());
            pstm.setString(5,dto.getAddress());


            boolean isSaved = pstm.executeUpdate()>0;
            return isSaved;
        }

        public List<EmployeeDto> getAllEmployee() throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM employee";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            ArrayList<EmployeeDto> dtoList = new ArrayList<>();

            while (resultSet.next()){
                dtoList.add(
                        new EmployeeDto(
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

        public boolean updateEmployee(EmployeeDto dto) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE Employee SET name = ?, contact = ?, email = ? ,address =?  WHERE id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getName());
            pstm.setString(2,dto.getContact());
            pstm.setString(3,dto.getEmail());
            pstm.setString(4,dto.getAddress());
            pstm.setString(5, dto.getId());

            return pstm.executeUpdate() >0;
        }
        public EmployeeDto searchEmployee(String id) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM employee WHERE id = ? ";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();

            EmployeeDto dto = null;

            if (resultSet.next()){
                String emp_id = resultSet.getString(1);
                String emp_name = resultSet.getString(2);
                String contact = resultSet.getString(3);
                String email = resultSet.getString(4);
                String address = resultSet.getString(5);

                dto = new EmployeeDto(emp_id,emp_name,contact,email,address);
            }
            return dto;
        }


    }

