DROP DATABASE IF EXISTS MOVIE_THEATRE;
CREATE DATABASE MOVIE_THEATRE;
USE MOVIE_THEATRE;

DROP TABLE IF EXISTS THEATRE;
CREATE TABLE THEATRE (
	TheatreID				INT AUTO_INCREMENT,
	Address					varchar(100) not null,
	TheatreName				varchar(100) not null,
    primary key(TheatreID)
);

DROP TABLE IF EXISTS MOVIE;
CREATE TABLE MOVIE (
	MovieID					INT AUTO_INCREMENT,
	Title					varchar(100)	not null,
	Genre					varchar(100)	not null,
    Theatre					int not null,
    foreign key (Theatre) references THEATRE(TheatreID),
    primary key(MovieID)
);

DROP TABLE IF EXISTS LOCATION;
CREATE TABLE LOCATION (
	LocationID				INT AUTO_INCREMENT,
	Address					varchar(100)	not null,
	LocationName			varchar(100)	not null,
    primary key(LocationID)
);



DROP TABLE IF EXISTS SHOWTIME;
CREATE TABLE SHOWTIME (
	ShowtimeID				INT AUTO_INCREMENT,
	Movie					int	not null,
	Time					TIME NOT NULL,
    Theatre					int not null,

    primary key(ShowtimeID),
    foreign key (Movie) references MOVIE(MovieID),
    foreign key (Theatre) references THEATRE(TheatreID)
);

DROP TABLE IF EXISTS REG_USER;
CREATE TABLE REG_USER (
	ID					INT AUTO_INCREMENT,
	Fname				varchar(50)	not null,
	Lname				varchar(50)	not null,
    Email				varchar(50)	not null,
    Address				varchar(50)	not null,
    MemberShipJoinDate	DATE NOT NULL,
    MemberShipExpiry	DATE NOT NULL,
    PaymentMethod		varchar(50),
	primary key (ID)
);

DROP TABLE IF EXISTS SEAT;
CREATE TABLE SEAT (
	SeatID				INT AUTO_INCREMENT,
    Showtime			int not null,
    Theatre				int not null,
    Movie				int not null,
	SeatRow				char(1)	not null,
	SeatColumn			int	not null,
    Available			int not null,
    RegUser				int,

	primary key (seatID),
    foreign key (RegUser) references REG_USER(ID),
    foreign key (Theatre) references THEATRE(TheatreID),
    foreign key (Movie) references MOVIE(MovieID),
    foreign key (Showtime) references SHOWTIME(ShowtimeID)
);

INSERT INTO THEATRE (Address, TheatreName)
VALUES
('Location A','AcmePlex: Location A'),
('Location B','AcmePlex: Location A'),
('Westhills','AcmePlex: Westills'),
('Candy Cane Lane, North Pole','AcmePlex: Santa Edition');

INSERT INTO MOVIE (Title, Genre, Theatre)
VALUES
('The Garfeild Movie','Fat Cat, Animated, Lasagna', 2),
('The Wild Robot','Adventure, Family, Animated', 2),
('The Barbie Movie','Societal Issues', 1),
('Barbie as the Three Musketeers','Action, Friendship, Animated', 1),
('Barbie as the Island Princess','Tropical, Adventure, Animated', 1),
('Barbie in the Princess Charm School','Magical, Redemption Arc, Animated', 1),
('The Garfeild Movie','Fat Cat, Animated, Lasagna', 1),
('The Spongebob Movie','Comedy, Adventure, Animated', 3),
('Clueless','Rom-Com', 3),
('Diary of a Wimpy Kid: Rodrick Rules','Comedy, Slice of Life?', 3),
('Kimi No Nawa','Anime, Romance', 3),
('The Garfeild Movie','Fat Cat, Animated, Lasagna', 3),
('Home Alone','Christmas, Comedy', 4),
('Home Alone 2','Christmas, Comedy', 4),
('Home Alone 3','Christmas, Comedy', 4),
('The Garfeild Movie','Fat Cat, Animated, Lasagna', 4),
('The Grinch','Christmas, Kindness, Animated', 4);


