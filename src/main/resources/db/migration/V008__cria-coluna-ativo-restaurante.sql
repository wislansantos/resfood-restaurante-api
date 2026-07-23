alter table restaurante
add column ativo tinyint(1);

update restaurante
set ativo = true;

alter table restaurante
modify column ativo tinyint(1) not null default true;
