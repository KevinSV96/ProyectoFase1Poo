** CREAR LA BASE DE DATOS **
CREATE DATABASE biblioteca_poo;
GO

** CREAR LAS TABLAS **
USE biblioteca_poo;
GO

CREATE TABLE USUARIO (
    idUsuario INT PRIMARY KEY,
    nombreCompleto VARCHAR(255) NOT NULL,
    correo VARCHAR(255) UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    tipoUsuario VARCHAR(50) NOT NULL, 
    estadoCuenta VARCHAR(50) DEFAULT 'Activo'
);
GO

CREATE TABLE DOCUMENTO (
    isbn VARCHAR(20) PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    categoria VARCHAR(100)
);
GO

CREATE TABLE EJEMPLAR (
    idEjemplar INT PRIMARY KEY,
    isbn_documento VARCHAR(20) NOT NULL,
    ubicacion VARCHAR(100),
    disponible BIT NOT NULL DEFAULT 1, 

    FOREIGN KEY (isbn_documento) REFERENCES DOCUMENTO(isbn)
);
GO

CREATE TABLE PRESTAMO (
    idPrestamo INT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_ejemplar INT NOT NULL,
    fechaPrestamo DATE NOT NULL,
    fechaDevolucionEstimada DATE NOT NULL,
    fechaDevolucionReal DATE,
    
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(idUsuario),
    FOREIGN KEY (id_ejemplar) REFERENCES EJEMPLAR(idEjemplar)
);

GO
