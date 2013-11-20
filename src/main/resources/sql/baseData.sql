--职位表数据
insert into t_position 
	(position_id , position_name ,tip , description , create_time) 
values ( next value for seq_position  , 'shouyinyuan' ,  0 
		, 'shouyinyuan' ,  TIMESTAMP('2012-12-25 12:00:00') ) ;

insert into t_position 
	(next value for seq_position  , position_name ,tip , description , create_time) 
values(2 , 'lifashi' ,  0 , 'lifashi' ,  TIMESTAMP('2012-12-25 12:00:00') );

insert into t_position 
	(position_id , position_name ,tip , description , create_time) 
values (next value for seq_position  , 'dianzhang' ,  0 
		, 'dianzhang' ,  TIMESTAMP('2012-12-25 12:00:00') );