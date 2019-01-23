create table if not exists table_a(
  id int(10) not null,
  name varchar(16) not null,
  value double(20,3) not null
);

insert into table_a values
 (1,"test-1",1),
 (2,"test-2",2),
 (3,"test-3",3),
 (4,"test-4",4),
 (5,"test-5",5),
 (6,"test-6",6),
 (7,"test-7",7),
 (8,"test-8",8)
 ;

select count(*) from table_a;
