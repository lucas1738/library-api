CREATE TABLE public.tb_seller (
                                seller_uuid VARCHAR(50) NOT NULL,
                                seller_key VARCHAR(200) NOT NULL,
                                seller_description VARCHAR(200) NOT NULL,
                                document_number VARCHAR(200) NOT NULL,
                                dt_creation TIMESTAMP NOT NULL,
                                CONSTRAINT seller_uuid PRIMARY KEY (seller_uuid)
);