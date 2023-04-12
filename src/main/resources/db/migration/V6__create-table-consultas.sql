create table consultas(

    id bigint not null auto_increment,
    medico_id bigint not null,
    paciente_id bigint not null,
    data datetime not null,

    primary key(id),
    foreign key(medico_id) references medicos(id),
    foreign key(paciente_id) references pacientes(id)
);