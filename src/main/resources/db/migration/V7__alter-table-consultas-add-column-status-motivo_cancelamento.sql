alter table consultas
add status varchar(25),
add motivo_cancelamento varchar(50);

update consultas set status = 'AGENDADO';

alter table consultas modify column status varchar(25) not null;