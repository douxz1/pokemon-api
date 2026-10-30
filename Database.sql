CREATE TABLE ddd_pokemon (
    codigo NUMBER GENERATED ALWAYS AS IDENTITY,
    nome VARCHAR2(100) NOT NULL,
    tipo VARCHAR2(50) NOT NULL,
    nivel NUMBER(3) NOT NULL,
    data_captura DATE NOT NULL,
    PRIMARY KEY (codigo)
);