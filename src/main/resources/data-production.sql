
/*
-------------------
	CRIA ROLES
-------------------

Se houver usuários sem roles quando inicia o banco, cria pra eles.
*/

-- Cliente Básico
MERGE INTO ROLE 
  USING (VALUES 'ROLE_BASIC') L (NAME)
  ON (ROLE.NAME=L.NAME)
  WHEN NOT MATCHED THEN INSERT (NAME) VALUES (L.NAME);

-- Administrativo
MERGE INTO ROLE 
  USING (VALUES 'ROLE_ADMIN') L (NAME)
  ON (ROLE.NAME=L.NAME)
  WHEN NOT MATCHED THEN INSERT (NAME) VALUES (L.NAME);
