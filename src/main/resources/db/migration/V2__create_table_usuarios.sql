CREATE TABLE usuarios (
    id BIGINT not null AUTO_INCREMENT,
    nombre VARCHAR(100) not null,
    contrasenia VARCHAR(300) not null,

    primary key(id)
);