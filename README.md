# Proyecto_aquitectura_corte1 
## Cosas necesarias
1. Se debe tener computador "Muy importante"
2. Se debe tener java con el jdk 17
3. Se debe tener docker y docker compose

# Diagrama base de datos
![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/f0029b93-db4d-4136-a9d6-c400f03a29f5)

# Codigo base de datos
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

# Insercion datos base
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

# Datos prueba
* Contiene 5 usuarios, 2 gerentes y 3 desarrolladores
* Contiene 2 proyectos, 1 para cada usuario (proyecto A o proyecto B)
* Cada proyecto contiene 5 historias de usuario
* Cada historia de usuario contiene 2 tareas

# Pasos para ejecutar 
1. se debe correr la imagen de docker "docker-compose.yml"
* docker-compose pull
* docker-compose -f up -d
* si esto no sirve se debe agregar la ruta 
* docker-compose {proyecto/ruta} pull
* docker-compose -f {proyecto/ruta} up -d

2. se debe verificar la conexion en el navegador entrando al localhost con el pueto 8081
 * se debe indicar que es una base de datos postgres
 * usuario: walrus
 * contrasena: 12345
 * nombre bd: project_system
 * pulsar login

3. Una vez completado el paso anterior, debemos correr el archivo que se encuentra en la ruta 
* "Proyecto_arquitectura_corte1/src/main/java/com/proyectocorte1/Proyecto_arquitectura_corte1/ProyectoArquitecturaCorte1Application.java"

4. ya con el proyecto corriendo debemos ingresar a postman 
* ingresar la url del localhost "http://localhost:8080"
* empezar a calificar :(

# Metodos API proyecto CRUD
## Usuario
### GET
1. Ruta: "/usuario"
2. Retorna: Usuarios registrados
![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/a7cdd026-0363-4e82-99b0-0aecb31afbc9)


### POST
1. Ruta: "/usuario"
2. Estructura:
{
        "name": "usuarioPrueba",
        "mail": "prueba@example.com",
        "password": "contrasena1",
        "userType": 1
}
3. Retorna: "id_usuario" confirmando la creacion y si no, da error :(
![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/5bae58ea-fafa-4c8f-9f2c-567890644333)

### PUT
1. Ruta: "/usuario/{id}"
{
        "name": "usuarioPrueba",
        "mail": "prueba@example.com",
        "password": "contrasena1",
        "userType": 1
}
2. Retorna:
   * Cantidad de filas afectas
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/d3f4bc68-f7e4-40e1-a4ee-fcfdbeabf36e)

### DELETE
1. Ruta: "/usuario/{id}"
2. Retorna:
   * Cantidad de filas afectas
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/8573efe8-7d22-4ce1-8af6-0d2353ccae6b)



## PROYECTO

### GET
1. Ruta: "/usuario/{id}/proyecto"
2. Retorna:
   * Lista de proyectos acargo del usuario
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/b91daa83-d02b-4669-a1db-954b10be05c0)


### POST
1. Ruta: "/usuario/{id}/proyecto"
2. Estructura:
       {
        "name": "Proyecto_A",
        "description": "Descripción del Proyecto_A",
        "startDate": "2024-03-02",
        "manager": 1
    }
4. Retorna:
   * "No tienes permiso"
   * "Proyecto creado"
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/80e9fc83-6f91-4824-aff5-5e01c106a339)

   
### PUT
1. Ruta: "/usuario/{id_user}/proyecto/{id_project}
2. Estructura:
          {
        "name": "Proyecto_A",
        "description": "Descripción del Proyecto_A",
        "startDate": "2024-03-02",
        "manager": 1
    }
4. Retorna:
   * "No tienes permiso para actualizar proyectos"
   * "Proyecto actualiado"
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/af805fbc-4545-4fc3-bce9-7a1ac1e94043)

   
### DELETE
1. Ruta: "/usuario/{id_user}/proyecto/{id_project}
2. Retorna:
   * "No tienes permiso para eliminar proyectos"
   * "Proyecto eliminado"
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/9c87ea2d-1121-4c2c-a575-0171d34bd5ff)


## USUARIO_PROYECTO 

### GET
1. Ruta: "/usuario/{id}/proyecto/{id_project}/usuarios"
2. Retorna:
   * lista de usuarios en un proyecto
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/a6cda144-c43a-4362-b9f9-2cb976702890)

   
### POST
1. Ruta: "/usuario/{id}/proyecto/{id_project}/usuarios"
2. Estructura:
          {
        "startDate": "2024-03-02",
        "idUser": 3,
        "idProject": 1
    }
4. Retorna:
   * "No tienes permiso para agregar usuarios"
   * "Usuario agregado al proyecto "
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/d13380b7-2ba0-4c88-a9b8-e642a5acfe78)

   
### PUT
1. Ruta: "/usuario/{id}/proyecto/{id_project}/usuarios/{id_user}"
2. Estructura:
       {
        "startDate": "2024-03-02",
        "idUser": 3,
        "idProject": 1
    }
4. Retorna:
   * "No tienes permisos para actualizar usuarios"
   * "Usuario actualizado"
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/abb4ff37-9880-4741-b731-0e031a860c49)

   
### DELETE
1. Ruta: "/usuario/{id}/proyecto/{id_project}/usuarios/{id_user}"
2. Retorna:
   * "No tienes permiso para eliminar usuarios"
   * "Usuario eliminado"
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/7e8a54f9-a255-4a39-82dc-5f0419dafc23)

 

## HISTORIAS_USUARIO

### GET
1. Ruta: "/usuario/{id_user}/proyecto/{id_project}/historias"
2. Retorna:
   * lista de historias de usuario del proyecto
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/759d0ab4-11dc-44dd-bdf7-223d882a2f2c)

   
### POST
1. Ruta: "/usuario/{id_user}/proyecto/{id_project}/historias"
2. Estructura:
       {
        "details": "historia prueba",
        "criteria": "Criterios prueba",
        "idUserStory": 3,
        "idStatus": 1,
        "idProject": 1
    }
4. Retorna:
   * "No tienes permiso para crear historias de usuario"
   * "Historia de usuario creada"
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/802284fc-5519-4f7b-87cc-754269f9c630)

   
### PUT
1. Ruta: "/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}"
2. Estructura:
       {
        "details": "historia prueba",
        "criteria": "Criterios prueba",
        "idUserStory": 3,
        "idStatus": 1,
        "idProject": 1
    }
4. Retorna:
   * "No tienes permiso para modificar las historias de usuario"
   * "Se ha modificado la historia de usuario"
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/c04c0cf1-b32f-4159-a952-32d68296ab27)

   
### DELETE
1. Ruta: "/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}"
2. Retorna:
   * "No tienes permiso para eliminar las historias de usuario"
   * "La hisotira de usuario no se ha finalizado"
   * "Se ha elimiando la historia de usuario"
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/b4bfe40c-9821-4aaa-a972-dff2b470045e)

## TAREAS

### GET
1. Ruta: "/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}/tareas"
2. Retorna:
   * lista de tareas asociadas a la historia de usuario
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/62d97a85-e71c-4190-b6a1-28e69465ccc7)

   
### POST
1. Ruta: "/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}/tareas"
2. Estructura:
       {
        "description": "tarea prueba",
        "status": 1,
        "idUserTask": 3,
        "idUserStory": 1
    }
4. Retorna:
   * "No tienes permisos para crear taraes"
   * "Tarea creada"
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/cb31d309-06a9-4572-8272-86ee08f88ed6)

   
### PUT
1. Ruta: "/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}/tareas/{id_task}"
2. Estructura:
       {
        "description": "tarea prueba",
        "status": 1,
        "idUserTask": 3,
        "idUserStory": 1
    }
4. Retorna:
   * "No tienes permisos para actualizar taraes"
   * "Se ha modificado la tarea"
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/fc7a841e-6c5a-4fca-af3b-2e67aee37eb4)

   
### DELETE
1. Ruta: "/usuario/{id_user}/proyecto/{id_project}/historias/{id_userStory}/tareas/{id_task}"
2. Retorna:
   * "No tienes permisos para crear taraes"
   * "La tarea no se ha finalizado"
   * "Se ha elimiando la tarea de usuario"
   * Error
   * ![image](https://github.com/JuandAvendanog/Proyecto_arq_corte1/assets/141972525/3bb3b190-eb93-4f5d-a97c-719ec9cbe396)





