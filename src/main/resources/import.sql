insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Indiana');
insert into kitchen (id, name) values (3, 'Brasileira');
insert into kitchen (id, name) values (4, 'Italiana');

insert into restaurant (id, name, delivery_fee, kitchen_id) values (1, 'Thai Gourmet', 10, 1);
insert into restaurant (id, name, delivery_fee, kitchen_id) values (2, 'Tuk Tuk Indian Food', 15, 2);
insert into restaurant (id, name, delivery_fee, kitchen_id) values (3, 'La Nonna', 15, 4);
insert into restaurant (id, name, delivery_fee, kitchen_id) values (4, 'Paulista', 23, 3);

insert into city (id, name, state_id) values (1, 'Campinas', 1);
insert into city (id, name, state_id) values (2, 'Curitiba', 2);
insert into city (id, name, state_id) values (4, 'Salvador', 4);
insert into city (id, name, state_id) values (3, 'Fortaleza', 3);

insert into method_payment (id, description) values (1, 'Cartão Crédito');
insert into method_payment (id, description) values (2, 'Cartão Débito');
insert into method_payment (id, description) values (3, 'Pix');
insert into method_payment (id, description) values (4, 'Dinheiro');

insert into permission (id,name, description) values (1, 'Consultar Cozinhas', 'Permitir consultar cozinhas');
insert into permission (id,name, description) values (2, 'Editar Cozinhas', 'Permitir editar cozinhas');
insert into permission (id,name, description) values (3, 'Remover Cozinhas', 'Permitir remover cozinhas');
insert into permission (id,name, description) values (4, 'Alterar Cozinhas', 'Permitir alterar cozinhas');

insert into state (id, name) values (1, 'São Paulo');
insert into state (id, name) values (2, 'Paraná');
insert into state (id, name) values (3, 'Céara');
insert into state (id, name) values (4, 'Bahia');
