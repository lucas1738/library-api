CREATE TABLE public.tb_book (
                                nu_isbn VARCHAR(50) NOT NULL,
                                ds_title VARCHAR(200) NOT NULL,
                                ds_author VARCHAR(200) NOT NULL,
                                nu_pages NUMERIC NOT NULL,
                                dt_creation TIMESTAMP NOT NULL,
                                dt_update TIMESTAMP NOT NULL,
                                book_genre CHAR(1) NOT NULL,
                                CONSTRAINT nu_isbn PRIMARY KEY (nu_isbn)
);