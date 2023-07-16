CREATE TABLE if not exists members (
                         memid integer NOT NULL,
                         surname varchar(200) NOT NULL,
                         firstname varchar(200) NOT NULL,
                         address varchar(300) NOT NULL,
                         zipcode integer NOT NULL,
                         telephone varchar(20) NOT NULL,
                         recommendedby integer,
                         joindate timestamp NOT NULL
);