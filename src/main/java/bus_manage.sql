-- tạo bảng người lái
      create table driver(
      	id number primary key,
      	fullName nvarchar2(100) not null,
      	address nvarchar2(50) not null,
        phone nvarchar2(20) not null,
        levele nvarchar2(20) not null
      );
-- tạo bảng tuyến 
      create table route (
      	id number primary key,
      	distance number not null, -- khoảng cach
      	numberOfStops  number not null -- điểm dừng
      );
-- tạo bảng phân công 
      create table assignment(
            id number primary key,
            
            driver_id number not null constraint driver_id references driver(id),
            driver_fullName nvarchar2(50) not null,
            driver_address nvarchar2(50) not null,
            driver_phone nvarchar2(50) not null,
            driver_levele nvarchar2(50) not null,
            
            route_id number not null constraint route_id references route(id),
            route_distance number not null,
            route_numberOfStops number not null,
            numberOfTurns number not null 
);
      
    DROP table route;
    DROP table driver;
    DROP table assignment;
      --


----------------------------------------------------------------------------

insert into route values (102, 21, 11);
insert into driver values (10000, n'phong', n'hanoi', n'096218371', 'A');
insert into assignment values ( 10000,10000, n'phong', n'hanoi', n'0962183721', 'A', 100, 20, 10, 2);

select * from route;
select * from driver;
select * from assignment;




