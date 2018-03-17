/*
	Inicialização do banco de dados h2 na memória
*/

/*
--------------
	ROLES
--------------
*/
insert into ROLE (ID, NAME) values
	(1, 'ROLE_BASIC'),
	(2, 'ROLE_ADMIN');

/*
---------------
	USUÁRIOS
---------------
*/
insert into USER (ID, PASSWORD, PASSWORD_CONFIRM, USERNAME) values
	-- Administrador
	(1, '$2a$10$52njWIJ0NoRRpVTb47eu9.gccmA9NEWWg2pOdSqetqlY1mi9YFz0e', '123456789', 'admins'),
	-- Visitante
	(2, '$2a$10$DCQIvtWs/7n/ESYCqAhjUeKmca0BUyWNm3o.cUXom1yzLMB9wFQGO', '123456789', 'visita');

/*
--------------------
	USER - ROLE
--------------------
*/
insert into USER_ROLE (USER_ID, ROLE_ID) values
	(1, 1), -- 'admin'  tem 'ROLE_ADMIN'
	(1, 2), -- 'admin'  tem 'ROLE_BASIC'
	(2, 1); -- 'visita' tem 'ROLE_BASIC'

/*
-------------------
	AUTORES
-------------------
*/
insert into AUTOR (ID, NOME) values
	(1, 'Machado de Assis'),
	(2, 'Autor de Teste');

/*
-------------------
	LIVROS
-------------------
*/
insert into LIVRO (ID, FOTO, NOME, QUANTIDADE_PAGINAS, AUTOR_ID) values
	(1, null, 'Dom Casmurro',	300, 1),
	(2, null, 'Livro de Teste', 550, 2);

/*
-------------------
	EMPRÉSTIMOS
-------------------
*/
insert into EMPRESTIMO (ID, DATA_DEVOLUCAO, DATA_EMPRESTIMO, LIVRO_ID) values
	(1, '03/17/2018', '06/17/2018', 1),
	(2, '04/17/2018', '08/17/2018', 2);
