USE alumnoapi;

select * from cursos;
select * from talleres;
select * from alumnos;
show tables;

select * from alumnos_cursos;
select * from alumno_taller;

insert into cursos (curso_nombre)
values ('Matematica'),('Fisica'),('Ciencia'),('Comunicacion'),('Fisica');

insert into talleres (taller_nombre)
values ('Cocina'),('Tejido'),('Robotica'),('Reparaciones'),('Ajedrez');

insert into alumnos (alumno_nombre)
values ('Alexis'),('Sara'),('Yeni'),('Emy'),('Scarlet');

insert into cursos (curso_nombre)
values ('Matematica'),('Fisica'),('Ciencia'),('Comunicacion'),('Fisica');



