CREATE DATABASE project_system;

\connect  project_system;

CREATE TABLE Tipo_usuario (
                              ID SERIAL PRIMARY KEY,
                              Tipo VARCHAR(15)
);

CREATE TABLE Usuario (
                         ID SERIAL PRIMARY KEY,
                         Nombre VARCHAR(255) UNIQUE,
                         Correo VARCHAR(255) UNIQUE,
                         Contrasena VARCHAR(255),
                         Tipo_usuario_ID INTEGER,
                         CONSTRAINT FK_Tipo_usuario_ID FOREIGN KEY (Tipo_usuario_ID) REFERENCES Tipo_usuario(ID)
);

CREATE TABLE Proyecto (
                          ID SERIAL PRIMARY KEY,
                          Nombre VARCHAR(30),
                          Descripcion VARCHAR(2000),
                          FechaInicio DATE,
                          Gerente_ID INTEGER,
                          CONSTRAINT FK_Gerente_ID FOREIGN KEY (Gerente_ID) REFERENCES Usuario(ID)
);

CREATE TABLE Usuario_proyecto (
                                  ID SERIAL PRIMARY KEY,
                                  FechaInicio DATE,
                                  Usuario_ID INTEGER,
                                  Proyecto_ID INTEGER,
                                  CONSTRAINT FK_Usuario_proyecto_Usuario_ID FOREIGN KEY (Usuario_ID) REFERENCES Usuario(ID),
                                  CONSTRAINT FK_Usuario_proyecto_Proyecto_ID FOREIGN KEY (Proyecto_ID) REFERENCES Proyecto(ID)
);

CREATE TABLE Estado (
                        ID SERIAL PRIMARY KEY,
                        Estado VARCHAR(25)
);

CREATE TABLE Historia_usuario (
                                  ID SERIAL PRIMARY KEY,
                                  Detalles VARCHAR(2000),
                                  Criterios_aceptacion VARCHAR(2000),
                                  Usuario_historia_ID INTEGER,
                                  Estado_ID INTEGER,
                                  Proyecto_ID INTEGER,
                                  CONSTRAINT FK_Historia_usuario_Usuario_ID FOREIGN KEY (Usuario_historia_ID) REFERENCES Usuario_proyecto(ID),
                                  CONSTRAINT FK_Historia_usuario_Estado_ID FOREIGN KEY (Estado_ID) REFERENCES Estado(ID),
                                  CONSTRAINT FK_Historia_usuario_Proyecto_ID FOREIGN KEY (Proyecto_ID) REFERENCES Proyecto(ID)
);

CREATE TABLE Tarea (
                       ID SERIAL PRIMARY KEY,
                       Descripcion VARCHAR(2000),
                       Usuario_tarea_ID INTEGER,
                       Estado_tarea_ID INTEGER,
                       Historia_usuario_ID INTEGER,
                       CONSTRAINT FK_Tarea_Usuario_tarea_ID FOREIGN KEY (Usuario_tarea_ID) REFERENCES Usuario_proyecto(ID),
                       CONSTRAINT FK_Tarea_Estado_tarea_ID FOREIGN KEY (Estado_tarea_ID) REFERENCES Estado(ID),
                       CONSTRAINT FK_Tarea_Historia_usuario_ID FOREIGN KEY (Historia_usuario_ID) REFERENCES Historia_usuario(ID)
);

CREATE TABLE Cambio_estado (
                               ID SERIAL PRIMARY KEY,
                               Fecha_cambio DATE,
                               Estado_cambio_ID INTEGER,
                               Usuario_ID INTEGER,
                               Status_type VARCHAR(40),
                               CONSTRAINT FK_Cambio_estado_Estado_cambio_ID FOREIGN KEY (Estado_cambio_ID) REFERENCES Estado(ID)
);

INSERT INTO Tipo_usuario (Tipo) VALUES
                                    ('Gerente'),
                                    ('Desarrollador');

INSERT INTO Estado (Estado) VALUES
                                ('Nueva'),
                                ('En desarrollo'),
                                ('Finalizada');

INSERT INTO Usuario (Nombre, Correo, Contrasena, Tipo_usuario_ID)
VALUES
    ('Gerente1', 'gerente1@example.com', 'contrasena1', 1),
    ('Gerente2', 'gerente2@example.com', 'contrasena2', 1),
    ('Desarrollador1', 'desarrollador1@example.com', 'contrasena3', 2),
    ('Desarrollador2', 'desarrollador2@example.com', 'contrasena4', 2),
    ('Desarrollador3', 'desarrollador3@example.com', 'contrasena5', 2),
    ('Desarrollador4', 'desarrollador4@example.com', 'contrasena6', 2),
    ('Desarrollador5', 'desarrollador5@example.com', 'contrasena7', 2);

-- Crear 2 proyectos diferentes
INSERT INTO Proyecto (Nombre, Descripcion, FechaInicio, Gerente_ID)
VALUES
    ('Proyecto_A', 'Descripción del Proyecto_A', CURRENT_DATE, 1),
    ('Proyecto_B', 'Descripción del Proyecto_B', CURRENT_DATE, 2);

-- Asociar 4 desarrolladores a cada proyecto
INSERT INTO Usuario_proyecto (FechaInicio, Usuario_ID, Proyecto_ID)
VALUES
    (CURRENT_DATE, 3, 1),
    (CURRENT_DATE, 4, 1),
    (CURRENT_DATE, 5, 1),
    (CURRENT_DATE, 6, 1),
    (CURRENT_DATE, 7, 2),
    (CURRENT_DATE, 3, 2),
    (CURRENT_DATE, 4, 2),
    (CURRENT_DATE, 5, 2);

-- Crear 5 historias de usuario para cada proyecto
INSERT INTO Historia_usuario (Detalles, Criterios_aceptacion, Usuario_historia_ID, Estado_ID, Proyecto_ID)
VALUES
    ('Implementar funcionalidad de administración', 'Criterios HU1', 3, 1, 1),
    ('Diseñar interfaz de usuario avanzada', 'Criterios HU2', 4, 1, 1),
    ('Optimizar rendimiento del sistema', 'Criterios HU3', 5, 1, 1),
    ('Agregar funcionalidad de informes', 'Criterios HU4', 6, 1, 1),
    ('Corregir errores críticos', 'Criterios HU5', 7, 1, 1),
    ('Implementar sistema de notificaciones', 'Criterios HU1', 3, 1, 2),
    ('Actualizar diseño de la página principal', 'Criterios HU2', 4, 1, 2),
    ('Agregar soporte para múltiples idiomas', 'Criterios HU3', 5, 1, 2),
    ('Optimizar base de datos', 'Criterios HU4', 6, 1, 2),
    ('Crear panel de administración avanzado', 'Criterios HU5', 7, 1, 2);

-- Crear 3 tareas para cada historia
INSERT INTO Tarea (Descripcion, Usuario_tarea_ID, Estado_tarea_ID, Historia_usuario_ID)
VALUES
    ('Codificar funcionalidad de administración', 3, 1, 1),
    ('Diseñar interfaz avanzada', 4, 1, 1),
    ('Realizar pruebas de rendimiento', 5, 1, 1),
    ('Implementar generación de informes', 6, 1, 2),
    ('Corregir estilos de la página principal', 7, 1, 2),
    ('Configurar notificaciones avanzadas', 3, 1, 3),
    ('Actualizar estilos CSS', 4, 1, 3),
    ('Optimizar soporte para idiomas', 5, 1, 4),
    ('Optimizar consultas SQL', 6, 1, 4),
    ('Implementar funcionalidad avanzada de administración', 7, 1, 5);