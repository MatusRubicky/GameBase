use GameBase

CREATE TABLE Game
(ID INT NOT NULL IDENTITY(1,1),
 Title varchar(60),
 GameDesc varchar(1500),
 Studio varchar(150),
 ReleaseDate varchar(50)
 )

CREATE TABLE RelayTable
(GameID int,
 TagID int)

CREATE TABLE Tag
(ID INT NOT NULL IDENTITY(1,1),
 CategoryID int,
 TagName varchar(30)
 )

CREATE TABLE Category
(ID INT NOT NULL IDENTITY(1,1),
 CategoryName varchar(30)
 )
 
 CREATE TABLE Studio(
 ID INT NOT NULL IDENTITY(1, 1),
 name varchar(50) NOT NULL
 )
 
 CREATE TABLE StudiosGames(
 StudioID INT NOT NULL,
 GameID INT NOT NULL
 )
 

 /*Algoritmus podobnosti*/
 go
CREATE FUNCTION MostSimilarGames (@GID int)
RETURNS TABLE
AS
RETURN (
 select * from (select ID as gID,sum(pocetZhod) matchCount from (

 (select ID, 0 as pocetZhod from Game where ID<>@GID)

 union all

 (SELECT allTags.GameID,Count(*) as PocetZhod From RelayTable as allTags join (Select * From RelayTable Where GameID = @GID) as GIDTags On allTags.TagID = GIDTags.TagID
 Where allTags.GameID <> @GID Group By allTags.gameID)

 ) as t1 group by ID having sum(pocetZhod)>1) as one join (select * from Game) as two on one.gID=two.ID);