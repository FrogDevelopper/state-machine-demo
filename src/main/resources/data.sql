INSERT INTO DEMO_PRODUCT(CODE, IS_NUMERIC) VALUES ('PRODUCT_1', false);
INSERT INTO DEMO_PRODUCT(CODE, IS_NUMERIC) VALUES ('PRODUCT_2', true);

INSERT INTO DEMO_ORDER (CODE, STATE, DESCRIPTION) VALUES ('ORDER_1', 'INITIAL', 'SIMPLE ORDER');
INSERT INTO PRODUCT_ORDER (ORDER_CODE, PRODUCT_CODE) VALUES ('ORDER_1', 'PRODUCT_1');
INSERT INTO PRODUCT_ORDER (ORDER_CODE, PRODUCT_CODE) VALUES ('ORDER_1', 'PRODUCT_2');

INSERT INTO DEMO_ORDER (CODE, STATE, DESCRIPTION) VALUES ('ORDER_2', 'INITIAL', 'ORDER TO CANCEL');
INSERT INTO PRODUCT_ORDER (ORDER_CODE, PRODUCT_CODE) VALUES ('ORDER_2', 'PRODUCT_1');

INSERT INTO DEMO_ORDER (CODE, STATE, DESCRIPTION) VALUES ('ORDER_3', 'INITIAL', 'NO PAYMENT ORDER');
INSERT INTO PRODUCT_ORDER (ORDER_CODE, PRODUCT_CODE) VALUES ('ORDER_3', 'PRODUCT_1');

INSERT INTO DEMO_ORDER (CODE, STATE, DESCRIPTION) VALUES ('ORDER_4', 'INITIAL', 'NUMERIC ORDER');
INSERT INTO PRODUCT_ORDER (ORDER_CODE, PRODUCT_CODE) VALUES ('ORDER_4', 'PRODUCT_2');
