SELECT a3.name AS Unisson, c1.name AS Perso_1, a1.name AS Tech_1, c2.name AS Perso_2, a2.name AS Tech_2 
FROM Compound c 
JOIN Compound_Component_Arte cca1 ON c.id = cca1.compound_id 
JOIN Compound_Component_Arte cca2 ON c.id = cca2.compound_id 
JOIN Arte a1 ON cca1.arte_id = a1.id 
JOIN Arte a2 ON cca2.arte_id = a2.id 
JOIN Arte a3 ON C.compound_arte_id = a3.id 
JOIN Character_Arte ca1 ON ca1.arte_id = a1.id 
JOIN Character_Arte ca2 ON ca2.arte_id = a2.id 
JOIN Character c1 ON ca1.character_id = c1.id 
JOIN Character c2 ON ca2.character_id = c2.id 
WHERE cca1.id < cca2.id 
AND cca1.compound_id = cca2.compound_id 
AND c1.id != c2.id 
AND ((c1.name LIKE 'lloyd%' AND c2.name LIKE 'colette%') OR (c2.name LIKE 'lloyd%' AND c1.name LIKE 'colette%'));