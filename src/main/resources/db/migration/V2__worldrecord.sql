create table world_record (id bigint not null auto_increment,
                      description varchar(255) not null,
                      world_record_value double not null,
                      unit_of_measure varchar(255) not null,
                      date_of_record date not null,
                      recorder_id bigint not null,
                      primary key (id),
                      foreign key (recorder_id) references recorder(id));