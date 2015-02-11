/*Najvyssi prave pridane primarny kluc*/
select SCOPE_IDENTITY()

/*Najvyssie ID v hrach*/
select top 1 ID from Game order by ID desc

/*Algoritmus podobnosti*/
DECLARE @IDhry2 int;
SET @IDhry2 = 1;

SELECT allET.gameID,Count(*) as PocetZhod From rlGamePlay as allET join (Select * From  rlGamePlay  Where gameID = @IDhry2 ) as myET On allET.tgID = myET.tgID
 Where allET.gameID <> @IDhry2 Group By  allET.gameID Having Count(*) > 0 Order By Count(*) DESC, allET.gameID

 /*Vsetky pouzivatelom vytvorene funkcie*/
SELECT name AS function_name
,SCHEMA_NAME(schema_id) AS schema_name
,type_desc
FROM sys.objects
WHERE type_desc LIKE '%FUNCTION%';

/*Odstranenie funkcie*/
drop function MostSimilarGames