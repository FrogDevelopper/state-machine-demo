CREATE TABLE DEMO_ORDER
(
  CODE            VARCHAR(20),
  STATE           VARCHAR(20),
  DESCRIPTION     VARCHAR(256)
);

CREATE TABLE DEMO_PRODUCT
(
  CODE            VARCHAR(20),
  IS_NUMERIC      BOOLEAN
);

CREATE TABLE PRODUCT_ORDER
(
  ORDER_CODE      VARCHAR(20),
  PRODUCT_CODE    VARCHAR(20)
)