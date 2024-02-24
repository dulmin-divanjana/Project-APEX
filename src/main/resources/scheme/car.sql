drop database if exists car;
create database car;
use car;

create table user(
                     id VARCHAR(6)PRIMARY KEY,
                     User_name VARCHAR(30),
                     password VARCHAR(30)
);

create table customer(
                         id VARCHAR(6) PRIMARY KEY,
                         name VARCHAR(30),
                         email VARCHAR(40),
                         contact VARCHAR(10),
                         address VARCHAR(30)
                     );

create table vehicle(
                        id VARCHAR(6) PRIMARY KEY,
                        model VARCHAR(30),
                        colour VARCHAR(20),
                        mileage VARCHAR(20),
                        customerId varchar(6),
                        CONSTRAINT FOREIGN KEY(customerId) REFERENCES customer(id) on Delete Cascade 	on Update Cascade
);

create table appointment(
                            id VARCHAR(6) PRIMARY KEY,
                            date DATE,
                            status VARCHAR(20),
                            Description text(50)
);

create table item(
                     id VARCHAR(6) PRIMARY KEY,
                     name VARCHAR(30),
                     price DECIMAL(10,2),
                     category VARCHAR(30),
                     status VARCHAR(20),
                     description text(50)
);

create table appointmentAndItem(
                                   appointmentId varchar(6),
                                   itemId varchar(6),
                                   qty varchar(10),
                                   CONSTRAINT FOREIGN KEY(appointmentId) REFERENCES appointment(id) on Delete 	Cascade on Update Cascade,
                                   CONSTRAINT FOREIGN KEY(itemId) REFERENCES item(id) on Delete Cascade on 	Update Cascade
);


create table supplier(
                         id VARCHAR(6) PRIMARY KEY,
                         name VARCHAR(30),
                         email VARCHAR(40),
                         contact VARCHAR(10),
                         location VARCHAR(30)
);

create table itemAndSupplier(
                                itemId varchar(6),
                                supplierId varchar(6),
                                CONSTRAINT FOREIGN KEY(itemId) REFERENCES  item(id) on Delete Cascade on 	Update Cascade,
                                CONSTRAINT FOREIGN KEY(supplierId) REFERENCES supplier(id) on Delete Cascade 	on Update Cascade
);


create table service(
                        id VARCHAR(6) PRIMARY KEY,
                        name VARCHAR(30),
                        price DECIMAL(10,2),
                        status VARCHAR(20),
                        description text(50)
);

create table appointmentAndService(
                                      amount VARCHAR(20),
                                      appointmentId varchar(6),
                                      serviceId varchar(6),
                                      CONSTRAINT FOREIGN KEY(appointmentId) REFERENCES appointment(id) on Delete 	Cascade on Update Cascade,
                                      CONSTRAINT FOREIGN KEY(serviceId) REFERENCES service(id) on Delete Cascade on 	Update Cascade
);


create table employee(
                         id VARCHAR(6) PRIMARY KEY,
                         name VARCHAR(30),
                         address VARCHAR(30),
                         contact VARCHAR(10),
                         email VARCHAR(40)
);

create table attendance(
                           id VARCHAR(6) PRIMARY KEY,
                           date DATE,
                           Working_hours VARCHAR(6),
                           Attendance_history VARCHAR(30),
                           employeeId varchar(6),
                           CONSTRAINT FOREIGN KEY(employeeId) REFERENCES employee(id) on Delete 	Cascade on Update Cascade
);


create table expenses(
                         date DATE,
                         amount VARCHAR(20),
                         description VARCHAR(50),
                         userId varchar(6),
                         CONSTRAINT FOREIGN KEY(userId) REFERENCES user(id) on Delete Cascade on 	Update Cascade
);
