-- 1. Tabla Pelicula
CREATE TABLE Pelicula (
    id_pelicula      NUMBER        PRIMARY KEY,
    titulo           VARCHAR2(100) NOT NULL UNIQUE,
    genero           VARCHAR2(50)  NOT NULL,
    duracion         NUMBER        NOT NULL CHECK (duracion > 0),
    clasificacion    VARCHAR2(10)  NOT NULL
);

-- 2. Tabla Sala
CREATE TABLE Sala (
    id_sala      NUMBER        PRIMARY KEY,
    numero       NUMBER        NOT NULL UNIQUE,
    capacidad    NUMBER        NOT NULL CHECK(capacidad > 0),
    tipo         VARCHAR2(10)  NOT NULL
);

-- 3. Tabla Cliente
CREATE TABLE Cliente (
    id_cliente   NUMBER         PRIMARY KEY,
    nombre       VARCHAR2(80)   NOT NULL,
    email        VARCHAR2(100)  NOT NULL UNIQUE,
    telefono     VARCHAR2(20)
);

-- 4. Tabla Funcion
CREATE TABLE Funcion (
    id_funcion    NUMBER          PRIMARY KEY,
    id_pelicula   NUMBER          NOT NULL,
    id_sala       NUMBER          NOT NULL,
    fecha         DATE            NOT NULL,
    hora          VARCHAR2(10)    NOT NULL,
    precio        NUMBER(10,2)    NOT NULL CHECK(precio > 0),

    CONSTRAINT fk_funcion_pelicula FOREIGN KEY (id_pelicula)
        REFERENCES Pelicula(id_pelicula),

    CONSTRAINT fk_funcion_sala FOREIGN KEY (id_sala)
        REFERENCES Sala(id_sala)
);

-- 5. Tabla Boleto
CREATE TABLE Boleto (
    id_boleto       NUMBER         PRIMARY KEY,
    id_funcion      NUMBER         NOT NULL,
    id_cliente      NUMBER         NOT NULL,
    numero_asiento  NUMBER         NOT NULL CHECK(numero_asiento > 0),
    precio          NUMBER(10,2)   NOT NULL CHECK(precio >= 0),

    CONSTRAINT uq_asiento UNIQUE (id_funcion, numero_asiento),

    CONSTRAINT fk_boleto_funcion FOREIGN KEY (id_funcion)
        REFERENCES Funcion(id_funcion),

    CONSTRAINT fk_boleto_cliente FOREIGN KEY (id_cliente)
        REFERENCES Cliente(id_cliente)
);