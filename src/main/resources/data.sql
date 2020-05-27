# Empty all tables
SET FOREIGN_KEY_CHECKS=0;
TRUNCATE royalstacks.account;
TRUNCATE royalstacks.business_account;
TRUNCATE royalstacks.customer;
TRUNCATE royalstacks.employee;
TRUNCATE royalstacks.private_account;
TRUNCATE royalstacks.user;
SET FOREIGN_KEY_CHECKS=1;

# User
INSERT INTO `royalstacks`.`user` (`userid`, `first_name`, `last_name`, `password`, `username`) VALUES ('100', 'William', 'Blake', '$2a$12$3UKu5q/9fYPhgsE1qFzKc.rSASmG0xroNaVh7XcYNZSb7b0vY6riy', 'Customer100');
INSERT INTO `royalstacks`.`user` (`userid`, `first_name`, `last_name`, `password`, `username`) VALUES ('101', 'Jane', 'Austen', '$2a$12$3UKu5q/9fYPhgsE1qFzKc.rSASmG0xroNaVh7XcYNZSb7b0vY6riy', 'Customer101');
INSERT INTO `royalstacks`.`user` (`userid`, `first_name`, `last_name`, `password`, `username`) VALUES ('102', 'Charlotte', 'Brontë', '$2a$12$3UKu5q/9fYPhgsE1qFzKc.rSASmG0xroNaVh7XcYNZSb7b0vY6riy', 'Customer102');
INSERT INTO `royalstacks`.`user` (`userid`, `first_name`, `last_name`, `password`, `username`) VALUES ('103', 'Ben', 'Jonson', '$2a$12$3UKu5q/9fYPhgsE1qFzKc.rSASmG0xroNaVh7XcYNZSb7b0vY6riy', 'Customer103');
INSERT INTO `royalstacks`.`user` (`userid`, `first_name`, `last_name`, `password`, `username`) VALUES ('104', 'David', 'Mitchell', '$2a$12$3UKu5q/9fYPhgsE1qFzKc.rSASmG0xroNaVh7XcYNZSb7b0vY6riy', 'Customer104');
INSERT INTO `royalstacks`.`user` (`userid`, `first_name`, `last_name`, `password`, `username`) VALUES ('105', 'Jessica', 'Mitford', '$2a$12$3UKu5q/9fYPhgsE1qFzKc.rSASmG0xroNaVh7XcYNZSb7b0vY6riy', 'Customer105');
INSERT INTO `royalstacks`.`user` (`userid`, `first_name`, `last_name`, `password`, `username`) VALUES ('106', 'William', 'Shakespeare', '$2a$12$3UKu5q/9fYPhgsE1qFzKc.rSASmG0xroNaVh7XcYNZSb7b0vY6riy', 'Customer106');
INSERT INTO `royalstacks`.`user` (`userid`, `first_name`, `last_name`, `password`, `username`) VALUES ('200', 'Stephen', 'Fry', '$2a$12$3UKu5q/9fYPhgsE1qFzKc.rSASmG0xroNaVh7XcYNZSb7b0vY6riy', 'Employee200');
INSERT INTO `royalstacks`.`user` (`userid`, `first_name`, `last_name`, `password`, `username`) VALUES ('201', 'Virginia', 'Woolf', '$2a$12$3UKu5q/9fYPhgsE1qFzKc.rSASmG0xroNaVh7XcYNZSb7b0vY6riy', 'Employee201');
INSERT INTO `royalstacks`.`user` (`userid`, `first_name`, `last_name`, `password`, `username`) VALUES ('202', 'Graham', 'Greene', '$2a$12$3UKu5q/9fYPhgsE1qFzKc.rSASmG0xroNaVh7XcYNZSb7b0vY6riy', 'Employee202');

# Customer
INSERT INTO `royalstacks`.`customer` (`address`, `city`, `email`, `is_business_account_holder`, `phone_number`, `postal_code`, `bsn`, `userid`) VALUES ('Straat 1', 'Grotestad', 'user@example.com', b'0', '0612345678', '1000XX', '123456789', '100');
INSERT INTO `royalstacks`.`customer` (`address`, `city`, `email`, `is_business_account_holder`, `phone_number`, `postal_code`, `bsn`, `userid`) VALUES ('Straat 1', 'Grotestad', 'user@example.com', b'1', '0612345678', '1000XX', '123456789', '101');
INSERT INTO `royalstacks`.`customer` (`address`, `city`, `email`, `is_business_account_holder`, `phone_number`, `postal_code`, `bsn`, `userid`) VALUES ('Straat 1', 'Grotestad', 'user@example.com', b'1', '0612345678', '1000XX', '123456789', '102');
INSERT INTO `royalstacks`.`customer` (`address`, `city`, `email`, `is_business_account_holder`, `phone_number`, `postal_code`, `bsn`, `userid`) VALUES ('Straat 1', 'Grotestad', 'user@example.com', b'0', '0612345678', '1000XX', '123456789', '103');
INSERT INTO `royalstacks`.`customer` (`address`, `city`, `email`, `is_business_account_holder`, `phone_number`, `postal_code`, `bsn`, `userid`) VALUES ('Straat 1', 'Grotestad', 'user@example.com', b'0', '0612345678', '1000XX', '123456789', '104');
INSERT INTO `royalstacks`.`customer` (`address`, `city`, `email`, `is_business_account_holder`, `phone_number`, `postal_code`, `bsn`, `userid`) VALUES ('Straat 1', 'Grotestad', 'user@example.com', b'1', '0612345678', '1000XX', '123456789', '105');
INSERT INTO `royalstacks`.`customer` (`address`, `city`, `email`, `is_business_account_holder`, `phone_number`, `postal_code`, `bsn`, `userid`) VALUES ('Straat 1', 'Grotestad', 'user@example.com', b'1', '0612345678', '1000XX', '123456789', '106');


# Employee
INSERT INTO `royalstacks`.`employee` (`position`, `userid`) VALUES ('Head Private', '200');
INSERT INTO `royalstacks`.`employee` (`position`, `userid`) VALUES ('Head Business', '201');
INSERT INTO `royalstacks`.`employee` (`position`, `userid`) VALUES ('Head Business', '202');

# Account
INSERT INTO `royalstacks`.`account` (`account_id`, `account_number`, `balance`) VALUES ('300', 'NL16ROYA9082037890', '127.56');
INSERT INTO `royalstacks`.`account` (`account_id`, `account_number`, `balance`) VALUES ('301', 'NL44ROYA6435690758', '15912.13');
INSERT INTO `royalstacks`.`account` (`account_id`, `account_number`, `balance`) VALUES ('302', 'NL85ROYA8877477636', '4625.88');
INSERT INTO `royalstacks`.`account` (`account_id`, `account_number`, `balance`) VALUES ('303', 'NL07ROYA5373380466', '654312.47');
INSERT INTO `royalstacks`.`account` (`account_id`, `account_number`, `balance`) VALUES ('304', 'NL29ROYA5082680188', '2.50');
INSERT INTO `royalstacks`.`account` (`account_id`, `account_number`, `balance`) VALUES ('305', 'NL21ROYA8261521222', '4766.23');

# Business account
INSERT INTO `royalstacks`.`business_account` (`company_name`, `kvk_number`, `sector`, `vat_number`, `account_id`) VALUES ('Winkel BV', '12345678', 'Detailhandel', '123456789', '301');
INSERT INTO `royalstacks`.`business_account` (`company_name`, `kvk_number`, `sector`, `vat_number`, `account_id`) VALUES ('Nogeenwinkel BV', '23456789', 'Detailhandel', '234567890', '303');
INSERT INTO `royalstacks`.`business_account` (`company_name`, `kvk_number`, `sector`, `vat_number`, `account_id`) VALUES ('Boerderij BV', '34567890', 'Agrarisch', '345678901', '305');

# Customer account
#INSERT INTO `royalstacks`.`customer_account` (`customer_userid`, `account_account_id`) VALUES ('', '');

# Account_account_holders
#INSERT INTO `royalstacks`.`account_account_holders` (`account_account_id`, `account_holders_userid`) VALUES ('', '');