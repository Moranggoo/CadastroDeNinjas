-- V2: Migrations para adicionar a coluna de rank na tb_cadastro

ALTER TABLE tb_cadastro
ADD COLUMN rank VARCHAR(255);