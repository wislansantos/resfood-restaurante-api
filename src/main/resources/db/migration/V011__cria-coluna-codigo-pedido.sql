alter table pedido add codigo varchar(36) after id;
update pedido set codigo = uuid();
alter table pedido modify codigo varchar(36) not null;
alter table pedido add constraint uk_pedido_codigo unique (codigo);
