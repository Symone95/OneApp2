create database oneApp2;
use oneApp2;
create table eroi 
					(
						id int primary key auto_increment,
						nome varchar(500),
						foto varchar(200),
						forza int,
						stamina int,
						efficacia int
					);

create table mostri 
					(
						id int primary key auto_increment, 
                        nome varchar(500),
                        minaccia varchar(100),
                        foto varchar(200),
                        forza int,
                        stamina int,
                        efficacia int
					);

create table scontri
					(
						id int primary key auto_increment,
                        id_eroe int,
                        id_mostro int,
                        foreign key(id_eroe) references eroi(id)
                        ON DELETE RESTRICT
						ON UPDATE CASCADE,
                        foreign key(id_mostro) references mostri(id)
                        ON DELETE RESTRICT
						ON UPDATE CASCADE
					);

create table armi
				(
					id int primary key auto_increment,
                    nome_arma varchar(500),
                    att int
				);
                
create table armature
					(
						id int primary key auto_increment,
                        nome_armatura varchar(500),
                        dif int
					);
                    
create table equip_eroi
					(
						id_equip int primary key auto_increment,
                        id_eroe int,
                        id_arma int,
                        id_armatura int,
						foreign key(id_eroe) references eroi(id)
                        ON DELETE RESTRICT
						ON UPDATE CASCADE,
                        foreign key(id_arma) references armi(id)
                        ON DELETE RESTRICT
						ON UPDATE CASCADE,
                        foreign key(id_armatura) references armature(id)
                        ON DELETE RESTRICT
						ON UPDATE CASCADE
					);

create table equip_mostri
					(
						id_equip int primary key auto_increment,
                        id_mostro int,
                        id_arma int,
                        id_armatura int,
						foreign key(id_mostro) references mostri(id)
                        ON DELETE RESTRICT
						ON UPDATE CASCADE,
                        foreign key(id_arma) references armi(id)
                        ON DELETE RESTRICT
						ON UPDATE CASCADE,
                        foreign key(id_armatura) references armature(id)
                        ON DELETE RESTRICT
						ON UPDATE CASCADE
					);
					
create table utenti 
					(
						id int primary key auto_increment,
						nome varchar(500),
						cognome varchar(500),
						email varchar(500),
						psw varchar(15),
						lvl int(1) NULL DEFAULT 0
					);
					
insert into utenti (nome, cognome, email, psw) values
('Dario','Naghipour','tipiacerebbe@stam.it','ma col c...'),
('Federeico','Festa','paolocasertano@nonprogramma.it','paolocasertanononsaprogrammare'),
('Simone','Scalamandré','Davidenonhai@stapotenza.it','dicalcolo');
                    
CREATE VIEW lista_eroi AS
			SELECT 
					eroi.*,
                    armi.nome_arma,
                    armi.att,
                    armature.nome_armatura,
                    armature.dif
			FROM 
					eroi,
                    equip_eroi,
                    armi,
                    armature
			WHERE
					eroi.id = equip_eroi.id_eroe
					AND
                    equip_eroi.id_arma = armi.id
                    AND
                    equip_eroi.id_armatura = armature.id;	
                    
CREATE VIEW lista_mostri AS
			SELECT 
					mostri.*,
                    armi.nome_arma,
                    armi.att,
                    armature.nome_armatura,
                    armature.dif
			FROM 
					mostri,
                    equip_mostri,
                    armi,
                    armature
			WHERE
					mostri.id = equip_mostri.id_mostro
					AND
                    equip_mostri.id_arma = armi.id
                    AND
                    equip_mostri.id_armatura = armature.id;	
                    
insert into eroi(nome, foto, forza, stamina, efficacia) values
('Saitama','indirizzo del gif di fede',99,99,10),
('Spatent Rider','indirizzo del gif',0,1,1);

insert into mostri(nome, foto, minaccia, forza, stamina, efficacia) values
('Vaccine Man','indirizzo del gif di fede','demone',70,60,5),
('Plutoink','indirizzo del gif','tigre',60,40,4);

insert into armi (nome_arma, att) values
('Guanti rossi di Saitama',0),
('Bicicletta',0),
('mani nude', 10),
('tridente', 20);

insert into armature (nome_armatura, dif) values
('tuta e mantello',0),
('armatura da ciclista',1),
('senza armatura',20),
('tuta ultra gigante',10);

insert into scontri (id_eroe, id_mostro) values
(1,1),
(1,2);

insert into equip_eroi (id_eroe, id_arma, id_armatura) values
(1,1,1),
(2,2,2);

insert into equip_mostri (id_mostro, id_arma, id_armatura) values
(1,3,3),
(2,4,4);
