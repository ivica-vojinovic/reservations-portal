SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

BEGIN;

INSERT INTO `product` (product_id, product_name, product_description) VALUES ('1', 'Test product', 'Test description');
INSERT INTO `product` (product_id, product_name, product_description) VALUES ('2', 'Delete product', 'Product for delete');
INSERT INTO `product` (product_id, product_name, product_description) VALUES ('3', 'Find all', 'Product for find all test');

INSERT INTO `user_profile` (user_profile_id, email, first_name, last_name, password, phone_number) VALUES ('1', 'admin@emisia.net', 'Admin Name', 'Admin Last Name', '$2a$06$kEcAZMqeT4gAglOBV99ucuIk.XkNcw81V6Wft6F8R81j9SkT6GnFK', '+38164543765756');

COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
