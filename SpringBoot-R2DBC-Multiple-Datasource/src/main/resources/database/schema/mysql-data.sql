INSERT INTO `roles`(`id`,`role_name`) VALUES (1, 'admin');
INSERT INTO `roles`(`id`,`role_name`) VALUES (2, 'user');

INSERT INTO `users` (`username`,`password`,`role_id`) VALUES ('tirmizee','tirmizee', 1);
INSERT INTO `users` (`username`,`password`,`role_id`) VALUES ('pratya','pratya', 2);

INSERT INTO `permissions`(`id`,`per_code`,`per_name`) VALUES (1, 'P001', 'Main page');
INSERT INTO `permissions`(`id`,`per_code`,`per_name`) VALUES (2, 'P002', 'Customer page');
INSERT INTO `permissions`(`id`,`per_code`,`per_name`) VALUES (3, 'P003', 'Product page');


INSERT INTO `role_permission`(`role_id`,`permission_id`) VALUES (1,1);
INSERT INTO `role_permission`(`role_id`,`permission_id`) VALUES (1,2);
INSERT INTO `role_permission`(`role_id`,`permission_id`) VALUES (1,3);
INSERT INTO `role_permission`(`role_id`,`permission_id`) VALUES (2,1);
INSERT INTO `role_permission`(`role_id`,`permission_id`) VALUES (2,2);