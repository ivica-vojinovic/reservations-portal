SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

BEGIN;
INSERT INTO `product` VALUES ('1', 'Test product', 'Test description');
INSERT INTO `product` VALUES ('2', 'Delete product', 'Product for delete');
INSERT INTO `product` VALUES ('3', 'Find all', 'Product for find all test');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
