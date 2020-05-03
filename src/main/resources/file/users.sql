
-- User --
DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users` (
  `id` VARCHAR(255) NOT NULL,

  `username` VARCHAR(255) NULL,
  `password` VARCHAR(255) NULL,

  `name` VARCHAR(1000) NULL,
  `dateOfBirth` VARCHAR(255) NULL,
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

INSERT INTO 
Users(id,username,password,name,dateOfBirth,gender,isActivated,userType,lastLogin,imageAvatarUrl,request,baseAddress,billingAddress,deliveryAddress,createdAt,createdBy,updatedAt,updatedBy) 
VALUES ('12345', 'testuser1', 'testpassword1', 'MinhNguyen', '01/01/1991', 'Male', 'TRUE', 'BUYER', '24-04-2020 00:35:17', 'https://www.google.com/MinhNguyenAvatar.png', 'request-json-format', '', '', '', '24-04-2020 00:35:17', 'Anonymous', '24-04-2020 00:35:17', 'Anonymous');

INSERT INTO 
Users(id,username,password,name,dateOfBirth,gender,isActivated,userType,lastLogin,imageAvatarUrl,request,baseAddress,billingAddress,deliveryAddress,createdAt,createdBy,updatedAt,updatedBy) 
VALUES ('56789', 'selleruser', 'sellerpassword', 'MinhNguyen', '01/01/1991', 'Male', 'TRUE', 'SELLER', '24-04-2020 00:35:17', 'https://www.google.com/MinhNguyenAvatar.png', 'request-json-format', '', '', '', '24-04-2020 00:35:17', 'Anonymous', '24-04-2020 00:35:17', 'Anonymous');

SELECT * FROM `Users`;
