alter table restaurante add aberto tinyint(1);

update restaurante set aberto = false;

alter table restaurante
modify aberto tinyint(1) not null;
