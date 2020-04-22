
-- User --
DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users` (
  `id` VARCHAR(255) NOT NULL,

  `username` VARCHAR(255) NULL,
  `password` VARCHAR(255) NULL,

  `name` VARCHAR(1000) NULL,
  `dob` VARCHAR(255) NULL,
  `gender` VARCHAR(255) NULL,
  `isActivated` VARCHAR(255) NULL,
  `userType` VARCHAR(255) NULL,
  `lastLogin` VARCHAR(255) NULL,
  `imageAvatarUrl` VARCHAR(255) NULL,
  `request` VARCHAR(1000) NULL,

  `baseAddress` VARCHAR(1000) NULL,
  `billingAddress` VARCHAR(1000) NULL,
  `deliveryAddress` VARCHAR(1000) NULL,

  `createdAt` VARCHAR(255) NULL,
  `createdBy` VARCHAR(255) NULL,
  `updatedAt` VARCHAR(255) NULL,
  `updatedBy` VARCHAR(255) NULL,

  PRIMARY KEY (`id`));

SELECT * FROM `Users`;
