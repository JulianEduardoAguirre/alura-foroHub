CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(60),
    mensaje TEXT,
    fecha_creacion DATETIME,
    status BOOLEAN,
    autor VARCHAR(90),
    curso VARCHAR(60)
);