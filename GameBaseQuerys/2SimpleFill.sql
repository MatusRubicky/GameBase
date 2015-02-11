use GameBase

/*select * from Game
select * from RelayTable
select * from Tag
select * from Category*/


insert Category values('Gameplay');
insert Category values('Multiplayer');
insert Category values('Network');
insert Category values('Platform');
insert Category values('Setting');



insert Tag values(1,'1st person');
insert Tag values(1,'3rd person');
insert Tag values(1,'Strategy');
insert Tag values(1,'Racing');
insert Tag values(1,'Moba');
insert Tag values(1,'Turn based');

insert Tag values(2,'SinglePlayer');
insert Tag values(2,'Co-op');
insert Tag values(2,'PVP');
insert Tag values(2,'PVE');
insert Tag values(2,'MMO');

insert Tag values(3,'LAN');
insert Tag values(3,'TCP');
insert Tag values(3,'Always Online');

insert Tag values(4,'PC');
insert Tag values(4,'XBOX One');
insert Tag values(4,'PS4');
insert Tag values(4,'XBOX 360');
insert Tag values(4,'PS3');

insert Tag values(5,'Past');
insert Tag values(5,'Present');
insert Tag values(5,'Future');
insert Tag values(5,'Alternate Reality');
insert Tag values(5,'Steampunk');
insert Tag values(5,'World War');
insert Tag values(5,'SciFi');



select * from Game
select * from RelayTable
select * from Category

select Tag.ID,Category.CategoryName,TagName from Tag join Category on Tag.CategoryID=Category.ID