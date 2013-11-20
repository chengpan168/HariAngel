CREATE sequence seq_position 
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;
create table t_position (
	position_id numeric(10 , 0 ) ,
	position_name varchar(20) not null ,
	tip  numeric(8 , 0 ) default 0 ,
	description varchar(100) ,
	valid numeric(2 , 0) default 1 ,
	create_time timestamp not null ,
	
	constraint position_name_unique unique (position_name) ,
	constraint position_id_pk primary key (position_id) 
) ;

CREATE sequence seq_staff
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;
create table t_staff(
	staff_id numeric(10 , 0) ,
	staff_name varchar(20) not null ,
	staff_code numeric(10 , 0) not null unique ,
	id_card_num varchar(20) not null ,
	phone_num varchar(20) not null ,
	account_num varchar(20) ,
	sex numeric(1,0)  default 1 ,
	status numeric(2,0) default 1 ,
	address varchar(100) ,
	birth_time timestamp ,
	entry_time timestamp not null ,
	create_time timestamp not null ,
	remarks varchar(100) ,
	constraint staff_id_pk primary key (staff_id) 
) ;

CREATE sequence seq_staff_position 
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;

create table t_staff_position(
	staff_position_id numeric(10 , 0) primary key ,
	staff_id numeric(10 , 0) ,
	position_id numeric(10 , 0) ,
	create_time timestamp , 
	constraint staff_position_staff_id_fk foreign key (staff_id) 
		references t_staff(staff_id) on delete no action  ,
	constraint staff_position_position_id_fk foreign key (position_id) 
		references t_position (position_id) on delete restrict
) ;


CREATE sequence seq_member_card 
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;

create table t_member_card (
	member_card_id numeric(10 , 0) primary key  ,
	name varchar(50) not null unique ,
	discount numeric(3 , 0) default 100 ,
	fee numeric(5 , 0) ,
	recharge_min numeric(5 , 0) ,
	duration numeric(5 , 0) default -1, --无限制
	valid numeric(2 , 0) default 1 , --有效
	create_time timestamp not null 
	
);
	  
CREATE sequence seq_member
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;

create table t_member (
	member_id numeric(10 , 0 ) primary key ,
	member_num varchar(10) not null unique ,
	member_card_id numeric(10 , 0 ) ,
	name varchar(20) ,
	address varchar(100) ,
	sex numeric(1,0) default 1 ,
	id_card_num varchar(20) ,
	phone_num varchar(20) ,
	email varchar(100) ,
	score numeric(10 , 0 ) default 0 ,
	balance numeric(8 , 2 ) default 0 ,
	staff_code numeric(10 , 0 ) ,
	valid numeric(2 , 0 ) default 1 ,
	birthday_time timestamp ,
	create_time timestamp ,
	constraint member_staff_code_fk foreign key (staff_code) references t_staff(staff_code) on delete restrict 
) ;	  


CREATE sequence seq_role
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;
create table t_role (
	role_id numeric(10 , 0 ) primary key ,
	role_name varchar(20) not null unique ,
	create_time timestamp not null
) ;


CREATE sequence seq_user
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;

--用户表
create table t_user (
	user_id numeric(10 , 0 ) primary key ,
	user_name varchar(20) not null unique ,
	password varchar(20) ,
	staff_id numeric(10 , 0 ) ,
	role_id numeric(10 ,0) ,
	last_login_time timestamp ,
	create_time timestamp , 
	constraint user_staff_id_fk foreign key (staff_id) references t_staff (staff_id) on delete restrict ,
	constraint user_role_id_fk foreign key (role_id) references t_role (role_id) on delete restrict
) ;

CREATE sequence seq_recharge_record 
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;
create table t_recharge_record (
	recharage_record_id numeric(10 , 0) primary key , 
	member_id numeric(10 , 0) ,
	recharge_amount numeric(8 , 0) ,
	amount numeric(8,2) ,
	user_id numeric(10 , 0) ,
	recharge_time timestamp ,
	constraint recharge_record_member_id_fk foreign key  (member_id) 
		references t_member (member_id) on delete no action ,
	constraint recharge_record_user_id_fk foreign key (user_id) references t_user (user_id) on delete restrict
) ;
	

CREATE sequence seq_service
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;
create table t_service (
	service_id numeric(10 , 0) primary key ,
	service_name varchar(20) not null unique ,
	price numeric(8, 0) default 0 ,
	vip_discount numeric(1 , 0) default 1,
	valid numeric(2 , 0) default 1,
	score numeric(5 , 0) default 0 ,
	remarks varchar(100) ,
	create_time timestamp not null
) ;


CREATE sequence seq_service_position
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;
create table t_service_position (
	service_position_id numeric(10 , 0) primary key ,
	service_id numeric(10 , 0) ,
	position_id numeric(10 , 0) , 
	create_time timestamp not null , 
	constraint service_position_service_id_fk foreign key (service_id ) 
		references t_service (service_id ) on delete restrict ,
	constraint service_position_position_id_fk foreign key (position_id) 
		references t_position(position_id) on delete restrict
) ;

CREATE sequence seq_work_record
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;
	  
create table t_work_record (
	work_record_id numeric(10 , 0) primary key ,
	serial_num varchar(20) not null unique ,
	staff_id numeric(10 , 0) ,
	service_id numeric(10 ,0) ,
	member_id numeric(10 , 0) default 0 ,
	ismember numeric(1 , 0) default 1 ,
	sum_payable numeric(8 , 2) ,
	payment_amount numeric(8 , 2) ,
	remarks varchar(100) ,
	create_time timestamp not null , 
	constraint work_record_member_id_fk foreign key (member_id ) 
		references t_member(member_id) on delete restrict ,
	constraint work_record_staff_id_fk foreign key (staff_id ) 
		references t_staff(staff_id) on delete restrict ,
	constraint work_record_service_id_fk foreign key (service_id ) 
		references t_service(service_id) on delete restrict 
) ;

/*
CREATE sequence seq_work_record_staff
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;
create table t_work_record_staff(
	work_record_staff_id int ,
	staff_id int ,
	work_record_id int ,
	create_time timestamp ,
	constraint work_record_staff_staff_id_fk foreign key (staff_id) 
		references t_staff(staff_id) on delete no action ,
	constraint work_record_staff_work_record_id_fk foreign key (work_record_id) 
		references t_work_record(work_record_id) on delete no action 
		
) ;


CREATE sequence seq_work_record_service
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;
	  
create table t_work_record_service(
	work_record_staff_id int ,
	service_id int ,
	work_record_id int ,
	create_time timestamp ,
	constraint work_record_service_service_id_fk foreign key (service_id) 
		references t_service(service_id) on delete no action ,
	constraint work_record_service_work_record_id_fk foreign key (work_record_id) 
		references t_work_record(work_record_id) on delete no action 
		
) ;
*/
CREATE sequence seq_acl
	 AS int 
  	START WITH 1
	  INCREMENT BY 1 
	  MAXVALUE 999999999
	  MINVALUE 1 
	  NO CYCLE ;
	  
create table t_acl (
	acl_id numeric(10 , 0) primary key ,
	role_id numeric(10 ,0) ,
	module_id numeric(10 ,0) ,
	permission numeric( 1, 0 ) default 1 ,
	remarks varchar(100) ,
	create_time timestamp not null , 
	constraint acl_role_id_fk foreign key (role_id ) 
		references t_role(role_id) on delete restrict 
) ;

drop table t_staff_position ;
drop table t_staff ;
drop table t_position ;
drop table t_member_card ;
drop table t_member ;

DROP SEQUENCE SEQ_POSITION  RESTRICT;
DROP SEQUENCE SEQ_STAFF  RESTRICT;
DROP SEQUENCE SEQ_STAFF_POSITION  RESTRICT;
DROP SEQUENCE SEQ_RECHARGE_RECORD  RESTRICT;
DROP SEQUENCE SEQ_MEMBER_CARD  RESTRICT;
DROP SEQUENCE SEQ_MEMBER  RESTRICT;
DROP SEQUENCE SEQ_WORK_RECORD  RESTRICT;
DROP SEQUENCE SEQ_RECHARGE_RECORD  RESTRICT;
DROP SEQUENCE SEQ_SERVICE  RESTRICT;
DROP SEQUENCE SEQ_SERVICE_POSITION  RESTRICT;
DROP SEQUENCE SEQ_WORK_RECORD  RESTRICT;
DROP SEQUENCE SEQ_USER  RESTRICT;
