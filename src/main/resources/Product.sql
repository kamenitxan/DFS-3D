CREATE TABLE Product
(
    name        varchar(255),
    price       integer,
    id          integer NOT NULL REFERENCES JakonObject (id) ON DELETE CASCADE,
    description varchar(255),
    withModel   boolean,
    objectOrder double precision DEFAULT 10 NOT NULL,
    primary key (id)
)

