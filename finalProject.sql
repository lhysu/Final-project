# finalproject
CREATE SCHEMA `finalproject` ;
USE finalproject;
# 회원
CREATE TABLE `finalproject`.`member` (
  `member_id` VARCHAR(255) NOT NULL,
  `member_num` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `pw` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `signup_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `points` INT NULL,
  `adCheck` TINYINT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE INDEX `member_num_UNIQUE` (`member_num` ASC) VISIBLE);

# 회원 더미값 20개
INSERT INTO `finalproject`.`member` (member_id, member_num, name, pw, email, phone_number, address, points, adCheck) VALUES 
('user01', 1, 'User01', 'password001', 'user01@example.com', '010-6289-7172', 'Seoul', 82, 0),
('user02', 2, 'User02', 'password002', 'user02@example.com', '010-7597-8338', 'Seoul', 180, 0),
('user03', 3, 'User03', 'password003', 'user03@example.com', '010-1091-7260', 'Seoul', 175, 1),
('user04', 4, 'User04', 'password004', 'user04@example.com', '010-4115-5039', 'Seoul', 142, 1),
('user05', 5, 'User05', 'password005', 'user05@example.com', '010-6004-6296', 'Seoul', 87, 1),
('user06', 6, 'User06', 'password006', 'user06@example.com', '010-4696-4745', 'Seoul', 86, 1),
('user07', 7, 'User07', 'password007', 'user07@example.com', '010-8924-9159', 'Seoul', 155, 1),
('user08', 8, 'User08', 'password008', 'user08@example.com', '010-4997-7140', 'Seoul', 95, 0),
('user09', 9, 'User09', 'password009', 'user09@example.com', '010-4257-2742', 'Seoul', 102, 0),
('user10', 10, 'User10', 'password010', 'user10@example.com', '010-6864-1868', 'Seoul', 109, 1),
('user11', 11, 'User11', 'password011', 'user11@example.com', '010-6140-1084', 'Seoul', 178, 0),
('user12', 12, 'User12', 'password012', 'user12@example.com', '010-5979-8100', 'Seoul', 58, 1),
('user13', 13, 'User13', 'password013', 'user13@example.com', '010-9589-1137', 'Seoul', 98, 1),
('user14', 14, 'User14', 'password014', 'user14@example.com', '010-1910-8879', 'Seoul', 102, 0),
('user15', 15, 'User15', 'password015', 'user15@example.com', '010-1356-4416', 'Seoul', 119, 1),
('user16', 16, 'User16', 'password016', 'user16@example.com', '010-2065-6833', 'Seoul', 121, 0),
('user17', 17, 'User17', 'password017', 'user17@example.com', '010-2409-2204', 'Seoul', 128, 0),
('user18', 18, 'User18', 'password018', 'user18@example.com', '010-3575-8441', 'Seoul', 75, 0),
('user19', 19, 'User19', 'password019', 'user19@example.com', '010-5111-4827', 'Seoul', 116, 1),
('user20', 20, 'User20', 'password020', 'user20@example.com', '010-7462-7230', 'Seoul', 121, 0);

# 고객센터
CREATE TABLE `finalproject`.`help` (
  `help_num` INT NOT NULL AUTO_INCREMENT,
  `member_id` VARCHAR(255) NOT NULL,
  `inquiry_type` VARCHAR(45) NOT NULL,
  `inquiry_text` VARCHAR(2000) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` VARCHAR(45) NULL,
  `response` VARCHAR(2000) NULL,
  `responded_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `help_views` INT NULL,
  `file` VARCHAR(255) NULL,
  PRIMARY KEY (`help_num`),
  UNIQUE INDEX `member_id_UNIQUE` (`member_id` ASC) VISIBLE);
  
# 고객센터 더미값 20개
INSERT INTO `finalproject`.`help` (member_id, inquiry_type, inquiry_text, status, help_views, file) VALUES 
('user01', 'Return', 'I have an issue with product 1.', 'Pending', 13, 'file1.jpg'),
('user02', 'Account', 'I have an issue with product 2.', 'Resolved', 17, 'file2.jpg'),
('user03', 'Return', 'I have an issue with product 3.', 'Resolved', 8, 'file3.jpg'),
('user04', 'Return', 'I have an issue with product 4.', 'Resolved', 11, 'file4.jpg'),
('user05', 'Coupon', 'I have an issue with product 5.', 'Resolved', 12, 'file5.jpg'),
('user06', 'Product', 'I have an issue with product 6.', 'Resolved', 9, 'file6.jpg'),
('user07', 'Product', 'I have an issue with product 7.', 'Resolved', 6, 'file7.jpg'),
('user08', 'Delivery', 'I have an issue with product 8.', 'Pending', 14, 'file8.jpg'),
('user09', 'Product', 'I have an issue with product 9.', 'Pending', 18, 'file9.jpg'),
('user10', 'Delivery', 'I have an issue with product 10.', 'Resolved', 6, 'file10.jpg'),
('user11', 'Delivery', 'I have an issue with product 11.', 'Resolved', 14, 'file11.jpg'),
('user12', 'Delivery', 'I have an issue with product 12.', 'Pending', 17, 'file12.jpg'),
('user13', 'Delivery', 'I have an issue with product 13.', 'Pending', 20, 'file13.jpg'),
('user14', 'Return', 'I have an issue with product 14.', 'Resolved', 7, 'file14.jpg'),
('user15', 'Account', 'I have an issue with product 15.', 'Pending', 19, 'file15.jpg'),
('user16', 'Return', 'I have an issue with product 16.', 'Resolved', 15, 'file16.jpg'),
('user17', 'Product', 'I have an issue with product 17.', 'Resolved', 18, 'file17.jpg'),
('user18', 'Account', 'I have an issue with product 18.', 'Pending', 5, 'file18.jpg'),
('user19', 'Return', 'I have an issue with product 19.', 'Pending', 11, 'file19.jpg'),
('user20', 'Return', 'I have an issue with product 20.', 'Pending', 19, 'file20.jpg');
  
# 이벤트
CREATE TABLE `finalproject`.`event` (
  `event_num` INT NOT NULL AUTO_INCREMENT,
  `event_title` VARCHAR(255) NOT NULL,
  `event_content` VARCHAR(2000) NOT NULL,
  `event_wdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `event_views` INT NULL,
  `event_img` VARCHAR(255) NULL,
  `file` VARCHAR(255) NULL,
  PRIMARY KEY (`event_num`));
  
# 이벤트 더미값 20개
INSERT INTO `finalproject`.`event` (event_title, event_content, event_views, event_img, file) VALUES 
('Event 1', 'Content for event 1.', 212, 'event1.jpg', 'file1.jpg'),
('Event 2', 'Content for event 2.', 131, 'event2.jpg', 'file2.jpg'),
('Event 3', 'Content for event 3.', 113, 'event3.jpg', 'file3.jpg'),
('Event 4', 'Content for event 4.', 250, 'event4.jpg', 'file4.jpg'),
('Event 5', 'Content for event 5.', 238, 'event5.jpg', 'file5.jpg'),
('Event 6', 'Content for event 6.', 100, 'event6.jpg', 'file6.jpg'),
('Event 7', 'Content for event 7.', 80, 'event7.jpg', 'file7.jpg'),
('Event 8', 'Content for event 8.', 58, 'event8.jpg', 'file8.jpg'),
('Event 9', 'Content for event 9.', 189, 'event9.jpg', 'file9.jpg'),
('Event 10', 'Content for event 10.', 250, 'event10.jpg', 'file10.jpg'),
('Event 11', 'Content for event 11.', 156, 'event11.jpg', 'file11.jpg'),
('Event 12', 'Content for event 12.', 223, 'event12.jpg', 'file12.jpg'),
('Event 13', 'Content for event 13.', 179, 'event13.jpg', 'file13.jpg'),
('Event 14', 'Content for event 14.', 245, 'event14.jpg', 'file14.jpg'),
('Event 15', 'Content for event 15.', 60, 'event15.jpg', 'file15.jpg'),
('Event 16', 'Content for event 16.', 68, 'event16.jpg', 'file16.jpg'),
('Event 17', 'Content for event 17.', 209, 'event17.jpg', 'file17.jpg'),
('Event 18', 'Content for event 18.', 218, 'event18.jpg', 'file18.jpg'),
('Event 19', 'Content for event 19.', 139, 'event19.jpg', 'file19.jpg'),
('Event 20', 'Content for event 20.', 221, 'event20.jpg', 'file20.jpg');

# 찜
CREATE TABLE `finalproject`.`like` (
  `like_num` INT NOT NULL AUTO_INCREMENT,
  `product_num` INT NOT NULL,
  `member_id` VARCHAR(255) NOT NULL,
  `createdDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`like_num`));
  
# 찜 더미값 20개
INSERT INTO `finalproject`.`like` (product_num, member_id) VALUES 
(9, 'user01'),
(11, 'user02'),
(16, 'user03'),
(1, 'user04'),
(2, 'user05'),
(15, 'user06'),
(2, 'user07'),
(18, 'user08'),
(12, 'user09'),
(5, 'user10'),
(17, 'user11'),
(18, 'user12'),
(17, 'user13'),
(16, 'user14'),
(7, 'user15'),
(6, 'user16'),
(13, 'user17'),
(6, 'user18'),
(10, 'user19'),
(4, 'user20');
  
# 공지사항
CREATE TABLE `finalproject`.`notice` (
  `notice_num` INT NOT NULL AUTO_INCREMENT,
  `notice_title` VARCHAR(255) NOT NULL,
  `notice_content` VARCHAR(2000) NOT NULL,
  `notice_wdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `notice_views` INT NULL,
  `notice_img` VARCHAR(255) NULL,
  `file` VARCHAR(255) NULL,
  PRIMARY KEY (`notice_num`));

# 공지사항 더미값 20개
INSERT INTO `finalproject`.`notice` (notice_title, notice_content, notice_views, notice_img, file) VALUES 
('Notice Title 1', 'Notice Content 1.', 33, 'notice1.jpg', 'file1.jpg'),
('Notice Title 2', 'Notice Content 2.', 63, 'notice2.jpg', 'file2.jpg'),
('Notice Title 3', 'Notice Content 3.', 49, 'notice3.jpg', 'file3.jpg'),
('Notice Title 4', 'Notice Content 4.', 37, 'notice4.jpg', 'file4.jpg'),
('Notice Title 5', 'Notice Content 5.', 43, 'notice5.jpg', 'file5.jpg'),
('Notice Title 6', 'Notice Content 6.', 27, 'notice6.jpg', 'file6.jpg'),
('Notice Title 7', 'Notice Content 7.', 72, 'notice7.jpg', 'file7.jpg'),
('Notice Title 8', 'Notice Content 8.', 45, 'notice8.jpg', 'file8.jpg'),
('Notice Title 9', 'Notice Content 9.', 90, 'notice9.jpg', 'file9.jpg'),
('Notice Title 10', 'Notice Content 10.', 79, 'notice10.jpg', 'file10.jpg'),
('Notice Title 11', 'Notice Content 11.', 29, 'notice11.jpg', 'file11.jpg'),
('Notice Title 12', 'Notice Content 12.', 70, 'notice12.jpg', 'file12.jpg'),
('Notice Title 13', 'Notice Content 13.', 31, 'notice13.jpg', 'file13.jpg'),
('Notice Title 14', 'Notice Content 14.', 49, 'notice14.jpg', 'file14.jpg'),
('Notice Title 15', 'Notice Content 15.', 58, 'notice15.jpg', 'file15.jpg'),
('Notice Title 16', 'Notice Content 16.', 19, 'notice16.jpg', 'file16.jpg'),
('Notice Title 17', 'Notice Content 17.', 71, 'notice17.jpg', 'file17.jpg'),
('Notice Title 18', 'Notice Content 18.', 20, 'notice18.jpg', 'file18.jpg'),
('Notice Title 19', 'Notice Content 19.', 60, 'notice19.jpg', 'file19.jpg'),
('Notice Title 20', 'Notice Content 20.', 29, 'notice20.jpg', 'file20.jpg');
  
# 재활용품 분리 방법
CREATE TABLE `finalproject`.`recycletip` (
  `recycleTip_num` INT NOT NULL AUTO_INCREMENT,
  `recycleTip_title` VARCHAR(255) NOT NULL,
  `recycleTip_content` VARCHAR(2000) NOT NULL,
  `recycleTip_views` INT NULL,
  `recycleTip_likes` INT NULL,
  `recycleTip_img` VARCHAR(255) NULL,
  `recycleTip_wdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file` VARCHAR(255) NULL,
  PRIMARY KEY (`recycleTip_num`));
  
# 재활용품 분리 방법 더미값 20개
INSERT INTO `finalproject`.`recycletip` (recycleTip_title, recycleTip_content, recycleTip_views, recycleTip_likes, recycleTip_img, file) VALUES 
('Recycle Tip 1', 'Tip content for 1.', 136, 54, 'tip1.jpg', 'file1.jpg'),
('Recycle Tip 2', 'Tip content for 2.', 79, 34, 'tip2.jpg', 'file2.jpg'),
('Recycle Tip 3', 'Tip content for 3.', 254, 69, 'tip3.jpg', 'file3.jpg'),
('Recycle Tip 4', 'Tip content for 4.', 312, 98, 'tip4.jpg', 'file4.jpg'),
('Recycle Tip 5', 'Tip content for 5.', 115, 50, 'tip5.jpg', 'file5.jpg'),
('Recycle Tip 6', 'Tip content for 6.', 180, 20, 'tip6.jpg', 'file6.jpg'),
('Recycle Tip 7', 'Tip content for 7.', 325, 78, 'tip7.jpg', 'file7.jpg'),
('Recycle Tip 8', 'Tip content for 8.', 109, 79, 'tip8.jpg', 'file8.jpg'),
('Recycle Tip 9', 'Tip content for 9.', 121, 88, 'tip9.jpg', 'file9.jpg'),
('Recycle Tip 10', 'Tip content for 10.', 132, 40, 'tip10.jpg', 'file10.jpg'),
('Recycle Tip 11', 'Tip content for 11.', 86, 95, 'tip11.jpg', 'file11.jpg'),
('Recycle Tip 12', 'Tip content for 12.', 263, 75, 'tip12.jpg', 'file12.jpg'),
('Recycle Tip 13', 'Tip content for 13.', 134, 48, 'tip13.jpg', 'file13.jpg'),
('Recycle Tip 14', 'Tip content for 14.', 304, 20, 'tip14.jpg', 'file14.jpg'),
('Recycle Tip 15', 'Tip content for 15.', 305, 74, 'tip15.jpg', 'file15.jpg'),
('Recycle Tip 16', 'Tip content for 16.', 357, 12, 'tip16.jpg', 'file16.jpg'),
('Recycle Tip 17', 'Tip content for 17.', 420, 96, 'tip17.jpg', 'file17.jpg'),
('Recycle Tip 18', 'Tip content for 18.', 95, 27, 'tip18.jpg', 'file18.jpg'),
('Recycle Tip 19', 'Tip content for 19.', 314, 87, 'tip19.jpg', 'file19.jpg'),
('Recycle Tip 20', 'Tip content for 20.', 350, 52, 'tip20.jpg', 'file20.jpg');

# 재활용품 분리방법 댓글
CREATE TABLE `finalproject`.`recycletipcomment` (
  `tipComment_num` INT NOT NULL AUTO_INCREMENT,
  `recycleTip_num` INT NOT NULL,
  `member_id` VARCHAR(255) NOT NULL,
  `tipComment_content` VARCHAR(2000) NOT NULL,
  `tipComment_wdate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tipComment_num`));
  
# 재활용품 분리방법 댓글 더미값 20개
INSERT INTO `finalproject`.`recycletipcomment` (recycleTip_num, member_id, tipComment_content) VALUES 
(6, 'user01', 'Comment on tip 1.'),
(17, 'user02', 'Comment on tip 2.'),
(20, 'user03', 'Comment on tip 3.'),
(6, 'user04', 'Comment on tip 4.'),
(20, 'user05', 'Comment on tip 5.'),
(6, 'user06', 'Comment on tip 6.'),
(10, 'user07', 'Comment on tip 7.'),
(4, 'user08', 'Comment on tip 8.'),
(14, 'user09', 'Comment on tip 9.'),
(3, 'user10', 'Comment on tip 10.'),
(9, 'user11', 'Comment on tip 11.'),
(20, 'user12', 'Comment on tip 12.'),
(10, 'user13', 'Comment on tip 13.'),
(17, 'user14', 'Comment on tip 14.'),
(15, 'user15', 'Comment on tip 15.'),
(4, 'user16', 'Comment on tip 16.'),
(18, 'user17', 'Comment on tip 17.'),
(5, 'user18', 'Comment on tip 18.'),
(20, 'user19', 'Comment on tip 19.'),
(13, 'user20', 'Comment on tip 20.');

# 재활용 라이프
CREATE TABLE `finalproject`.`recyclelife` (
  `recycleLife_num` INT NOT NULL AUTO_INCREMENT,
  `member_id` VARCHAR(255) NOT NULL,
  `recycleLife_title` VARCHAR(255) NOT NULL,
  `recycleLife_content` VARCHAR(2000) NOT NULL,
  `recycleLife_views` INT NULL,
  `recycleLife_likes` INT NULL,
  `recycleLife_img` VARCHAR(255) NULL,
  `recycleLife_wdate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `file` VARCHAR(255) NULL,
  PRIMARY KEY (`recycleLife_num`));

# 재활용 라이프 더미값 20개
INSERT INTO `finalproject`.`recyclelife` (member_id, recycleLife_title, recycleLife_content, recycleLife_views, recycleLife_likes, recycleLife_img, file) VALUES 
('user01', 'Recycling Life 1', 'Recycling life content for 1.', 115, 21, 'life1.jpg', 'file1.jpg'),
('user02', 'Recycling Life 2', 'Recycling life content for 2.', 122, 56, 'life2.jpg', 'file2.jpg'),
('user03', 'Recycling Life 3', 'Recycling life content for 3.', 81, 40, 'life3.jpg', 'file3.jpg'),
('user04', 'Recycling Life 4', 'Recycling life content for 4.', 122, 40, 'life4.jpg', 'file4.jpg'),
('user05', 'Recycling Life 5', 'Recycling life content for 5.', 53, 55, 'life5.jpg', 'file5.jpg'),
('user06', 'Recycling Life 6', 'Recycling life content for 6.', 60, 39, 'life6.jpg', 'file6.jpg'),
('user07', 'Recycling Life 7', 'Recycling life content for 7.', 92, 20, 'life7.jpg', 'file7.jpg'),
('user08', 'Recycling Life 8', 'Recycling life content for 8.', 71, 47, 'life8.jpg', 'file8.jpg'),
('user09', 'Recycling Life 9', 'Recycling life content for 9.', 114, 60, 'life9.jpg', 'file9.jpg'),
('user10', 'Recycling Life 10', 'Recycling life content for 10.', 59, 44, 'life10.jpg', 'file10.jpg'),
('user11', 'Recycling Life 11', 'Recycling life content for 11.', 127, 32, 'life11.jpg', 'file11.jpg'),
('user12', 'Recycling Life 12', 'Recycling life content for 12.', 81, 39, 'life12.jpg', 'file12.jpg'),
('user13', 'Recycling Life 13', 'Recycling life content for 13.', 97, 38, 'life13.jpg', 'file13.jpg'),
('user14', 'Recycling Life 14', 'Recycling life content for 14.', 114, 50, 'life14.jpg', 'file14.jpg'),
('user15', 'Recycling Life 15', 'Recycling life content for 15.', 110, 41, 'life15.jpg', 'file15.jpg'),
('user16', 'Recycling Life 16', 'Recycling life content for 16.', 69, 39, 'life16.jpg', 'file16.jpg'),
('user17', 'Recycling Life 17', 'Recycling life content for 17.', 59, 36, 'life17.jpg', 'file17.jpg'),
('user18', 'Recycling Life 18', 'Recycling life content for 18.', 57, 32, 'life18.jpg', 'file18.jpg'),
('user19', 'Recycling Life 19', 'Recycling life content for 19.', 136, 22, 'life19.jpg', 'file19.jpg'),
('user20', 'Recycling Life 20', 'Recycling life content for 20.', 124, 52, 'life20.jpg', 'file20.jpg');


# 상품
CREATE TABLE `finalproject`.`product` (
  `product_num` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(255) NOT NULL,
  `price` INT NOT NULL,
  `point` INT NOT NULL,
  `company` VARCHAR(255) NOT NULL,
  `product_img` VARCHAR(255) NOT NULL,
  `category` VARCHAR(255) NULL,
  `rating` INT NULL,
  `file` VARCHAR(255) NULL,
  PRIMARY KEY (`product_num`));
  
# 상품 더미값 20개
INSERT INTO `finalproject`.`product` (product_name, price, point, company, product_img, category, rating, file) VALUES 
('Product 1', 7951, 370, 'Company1', 'product1.jpg', 'Bathroom', 4, 'file1.jpg'),
('Product 2', 30918, 170, 'Company2', 'product2.jpg', 'Living', 3, 'file2.jpg'),
('Product 3', 29930, 382, 'Company3', 'product3.jpg', 'Kitchen', 5, 'file3.jpg'),
('Product 4', 19530, 66, 'Company4', 'product4.jpg', 'Living', 5, 'file4.jpg'),
('Product 5', 12877, 417, 'Company5', 'product5.jpg', 'Kitchen', 4, 'file5.jpg'),
('Product 6', 26066, 98, 'Company6', 'product6.jpg', 'Gift', 4, 'file6.jpg'),
('Product 7', 49129, 317, 'Company7', 'product7.jpg', 'Stationery', 4, 'file7.jpg'),
('Product 8', 38649, 289, 'Company8', 'product8.jpg', 'Kitchen', 4, 'file8.jpg'),
('Product 9', 46600, 436, 'Company9', 'product9.jpg', 'Stationery', 4, 'file9.jpg'),
('Product 10', 12937, 105, 'Company10', 'product10.jpg', 'Kitchen', 3, 'file10.jpg'),
('Product 11', 33039, 474, 'Company11', 'product11.jpg', 'Stationery', 4, 'file11.jpg'),
('Product 12', 35260, 82, 'Company12', 'product12.jpg', 'Gift', 5, 'file12.jpg'),
('Product 13', 31018, 78, 'Company13', 'product13.jpg', 'Kitchen', 4, 'file13.jpg'),
('Product 14', 1018, 392, 'Company14', 'product14.jpg', 'Bathroom', 3, 'file14.jpg'),
('Product 15', 5608, 141, 'Company15', 'product15.jpg', 'Stationery', 4, 'file15.jpg'),
('Product 16', 33479, 216, 'Company16', 'product16.jpg', 'Stationery', 4, 'file16.jpg'),
('Product 17', 34953, 388, 'Company17', 'product17.jpg', 'Stationery', 5, 'file17.jpg'),
('Product 18', 24041, 329, 'Company18', 'product18.jpg', 'Gift', 4, 'file18.jpg'),
('Product 19', 26763, 474, 'Company19', 'product19.jpg', 'Kitchen', 3, 'file19.jpg'),
('Product 20', 34711, 269, 'Company20', 'product20.jpg', 'Stationery', 5, 'file20.jpg');

# 장바구니
CREATE TABLE `finalproject`.`cart` (
  `cart_num` INT NOT NULL,
  `product_num` INT NOT NULL,
  `member_id` VARCHAR(255) NOT NULL,
  `count` INT NOT NULL,
  `price` INT NOT NULL,
  `product_img` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`cart_num`));
  
# 장바구니 더미값 20개
INSERT INTO `finalproject`.`cart` (cart_num, product_num, member_id, count, price, product_img) VALUES 
(1, 7, 'user01', 3, 25448, 'product1.jpg'),
(2, 11, 'user02', 2, 21163, 'product2.jpg'),
(3, 7, 'user03', 1, 33893, 'product3.jpg'),
(4, 4, 'user04', 5, 26723, 'product4.jpg'),
(5, 20, 'user05', 1, 41399, 'product5.jpg'),
(6, 12, 'user06', 5, 5384, 'product6.jpg'),
(7, 14, 'user07', 4, 38249, 'product7.jpg'),
(8, 16, 'user08', 4, 22802, 'product8.jpg'),
(9, 13, 'user09', 4, 5812, 'product9.jpg'),
(10, 17, 'user10', 1, 19468, 'product10.jpg'),
(11, 1, 'user11', 5, 20518, 'product11.jpg'),
(12, 4, 'user12', 1, 36434, 'product12.jpg'),
(13, 5, 'user13', 5, 46165, 'product13.jpg'),
(14, 4, 'user14', 1, 15172, 'product14.jpg'),
(15, 14, 'user15', 1, 24661, 'product15.jpg'),
(16, 15, 'user16', 5, 25433, 'product16.jpg'),
(17, 15, 'user17', 2, 9261, 'product17.jpg'),
(18, 8, 'user18', 5, 47287, 'product18.jpg'),
(19, 20, 'user19', 2, 2434, 'product19.jpg'),
(20, 15, 'user20', 2, 2885, 'product20.jpg');

# 기부 가능 물품
CREATE TABLE `finalproject`.`donateitem` (
  `donateItem_num` INT NOT NULL AUTO_INCREMENT,
  `member_id` VARCHAR(45) NULL,
  `donateItem_title` VARCHAR(45) NOT NULL,
  `donateItem_content` VARCHAR(1000) NOT NULL,
  `donateItem_img` VARCHAR(255) ,
  `donateItem_wdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `donateItem_address` VARCHAR(255) NOT NULL,
  `donateItem_item` VARCHAR(45) NOT NULL,
  `donate_state` VARCHAR(45),
  PRIMARY KEY (`donateItem_num`));
  
# 기부 가능 물품 더미값 20개
INSERT INTO `finalproject`.`donateitem` (member_id, donateItem_title, donateItem_content, donateItem_img, donateItem_wdate, donateItem_address, donateItem_item) VALUES 
('user01', 'Donate Title 1', 'Content for donate item 1', 'donate1.jpg', '2024-10-16', 'Address 1', 'Donate Item 1'),
('user02', 'Donate Title 2', 'Content for donate item 2', 'donate2.jpg', '2024-10-21', 'Address 2', 'Donate Item 2'),
('user03', 'Donate Title 3', 'Content for donate item 3', 'donate3.jpg', '2024-10-21', 'Address 3', 'Donate Item 3'),
('user04', 'Donate Title 4', 'Content for donate item 4', 'donate4.jpg', '2024-10-13', 'Address 4', 'Donate Item 4'),
('user05', 'Donate Title 5', 'Content for donate item 5', 'donate5.jpg', '2024-10-6', 'Address 5', 'Donate Item 5'),
('user06', 'Donate Title 6', 'Content for donate item 6', 'donate6.jpg', '2024-10-23', 'Address 6', 'Donate Item 6'),
('user07', 'Donate Title 7', 'Content for donate item 7', 'donate7.jpg', '2024-10-26', 'Address 7', 'Donate Item 7'),
('user08', 'Donate Title 8', 'Content for donate item 8', 'donate8.jpg', '2024-10-20', 'Address 8', 'Donate Item 8'),
('user09', 'Donate Title 9', 'Content for donate item 9', 'donate9.jpg', '2024-10-17', 'Address 9', 'Donate Item 9'),
('user10', 'Donate Title 10', 'Content for donate item 10', 'donate10.jpg', '2024-10-1', 'Address 10', 'Donate Item 10'),
('user11', 'Donate Title 11', 'Content for donate item 11', 'donate11.jpg', '2024-10-17', 'Address 11', 'Donate Item 11'),
('user12', 'Donate Title 12', 'Content for donate item 12', 'donate12.jpg', '2024-10-27', 'Address 12', 'Donate Item 12'),
('user13', 'Donate Title 13', 'Content for donate item 13', 'donate13.jpg', '2024-10-7', 'Address 13', 'Donate Item 13'),
('user14', 'Donate Title 14', 'Content for donate item 14', 'donate14.jpg', '2024-10-2', 'Address 14', 'Donate Item 14'),
('user15', 'Donate Title 15', 'Content for donate item 15', 'donate15.jpg', '2024-10-10', 'Address 15', 'Donate Item 15'),
('user16', 'Donate Title 16', 'Content for donate item 16', 'donate16.jpg', '2024-10-15', 'Address 16', 'Donate Item 16'),
('user17', 'Donate Title 17', 'Content for donate item 17', 'donate17.jpg', '2024-10-3', 'Address 17', 'Donate Item 17'),
('user18', 'Donate Title 18', 'Content for donate item 18', 'donate18.jpg', '2024-10-19', 'Address 18', 'Donate Item 18'),
('user19', 'Donate Title 19', 'Content for donate item 19', 'donate19.jpg', '2024-10-3', 'Address 19', 'Donate Item 19'),
('user20', 'Donate Title 20', 'Content for donate item 20', 'donate20.jpg', '2024-10-9', 'Address 20', 'Donate Item 20');

  # 주문
  CREATE TABLE `finalproject`.`order` (
  `order_num` INT NOT NULL,
  `product_num` INT NOT NULL,
  `member_id` VARCHAR(255) NOT NULL,
  `coupon_code` VARCHAR(100) NULL,
  `count` INT NOT NULL,
  `postcode` VARCHAR(255) NULL,
  `address` VARCHAR(255) NOT NULL,
  `address_detail` VARCHAR(255) NULL,
  `tel` VARCHAR(45) NOT NULL,
  `reusing` TINYINT DEFAULT 0,
  `discount` INT NULL,
  `delivery_fee` INT NOT NULL,
  `delivery_memo` VARCHAR(255) NULL,
  `payCheck` TINYINT DEFAULT 0,
  `total_price` INT NOT NULL,
  `order_state` VARCHAR(45),
  PRIMARY KEY (`order_num`),
  UNIQUE INDEX `coupon_code_UNIQUE` (`coupon_code` ASC) VISIBLE,
  UNIQUE INDEX `member_id_UNIQUE` (`member_id` ASC) VISIBLE);
  
  # 주문 더미값 20개
INSERT INTO `finalproject`.`order` (order_num, product_num, member_id, coupon_code, count, address, tel, reusing, discount, delivery_fee, delivery_memo, payCheck, total_price) VALUES 
(1, 17, 'user01', 'COUPON1', 5, 'Address 1', '010-5690-9095', 0, 100, 4747, 'Memo 1', 0, 10917),
(2, 12, 'user02', 'COUPON2', 3, 'Address 2', '010-2224-5058', 1, 15, 3607, 'Memo 2', 1, 78160),
(3, 6, 'user03', 'COUPON3', 2, 'Address 3', '010-6430-9927', 0, 6, 1155, 'Memo 3', 0, 57017),
(4, 5, 'user04', 'COUPON4', 4, 'Address 4', '010-3727-8448', 0, 5, 1845, 'Memo 4', 1, 9331),
(5, 8, 'user05', 'COUPON5', 1, 'Address 5', '010-2147-7721', 0, 72, 2266, 'Memo 5', 1, 87698),
(6, 14, 'user06', 'COUPON6', 4, 'Address 6', '010-3203-3178', 0, 28, 1680, 'Memo 6', 0, 36908),
(7, 8, 'user07', 'COUPON7', 4, 'Address 7', '010-9458-8074', 1, 84, 4084, 'Memo 7', 1, 99732),
(8, 19, 'user08', 'COUPON8', 2, 'Address 8', '010-7283-6066', 0, 25, 1561, 'Memo 8', 1, 81293),
(9, 18, 'user09', 'COUPON9', 2, 'Address 9', '010-2471-2273', 1, 5, 1674, 'Memo 9', 1, 79770),
(10, 3, 'user10', 'COUPON10', 1, 'Address 10', '010-2249-4970', 0, 36, 1196, 'Memo 10', 1, 81067),
(11, 14, 'user11', 'COUPON11', 4, 'Address 11', '010-6653-1377', 0, 76, 2189, 'Memo 11', 0, 73455),
(12, 7, 'user12', 'COUPON12', 3, 'Address 12', '010-9553-2626', 1, 34, 4456, 'Memo 12', 1, 31104),
(13, 3, 'user13', 'COUPON13', 2, 'Address 13', '010-2146-6252', 0, 44, 1823, 'Memo 13', 1, 41559),
(14, 1, 'user14', 'COUPON14', 3, 'Address 14', '010-4032-4479', 1, 4, 4176, 'Memo 14', 1, 95191),
(15, 8, 'user15', 'COUPON15', 4, 'Address 15', '010-7383-6549', 0, 99, 3805, 'Memo 15', 0, 53540),
(16, 7, 'user16', 'COUPON16', 5, 'Address 16', '010-9873-3843', 0, 57, 1274, 'Memo 16', 0, 81644),
(17, 18, 'user17', 'COUPON17', 2, 'Address 17', '010-9840-3766', 1, 53, 2473, 'Memo 17', 1, 78576),
(18, 9, 'user18', 'COUPON18', 5, 'Address 18', '010-3210-1243', 1, 89, 2714, 'Memo 18', 0, 67279),
(19, 9, 'user19', 'COUPON19', 2, 'Address 19', '010-8305-2282', 0, 28, 3504, 'Memo 19', 1, 47645),
(20, 7, 'user20', 'COUPON20', 2, 'Address 20', '010-4209-8782', 1, 49, 2284, 'Memo 20', 0, 40746);
  
  # 쿠폰
  CREATE TABLE `finalproject`.`coupon` (
  `coupon_code` VARCHAR(100) NOT NULL,
  `member_id` VARCHAR(255) NULL,
  `coupon_name` VARCHAR(45) NOT NULL,
  `use_sdate` DATETIME NOT NULL,
  `use_edate` DATETIME NOT NULL,
  `discount_rate` INT NULL,
  `used` TINYINT DEFAULT 0,
  PRIMARY KEY (`coupon_code`),
  UNIQUE INDEX `coupon_code_UNIQUE` (`coupon_code` ASC) VISIBLE);

ALTER TABLE `finalproject`.`coupon` 
CHANGE COLUMN `use_sdate` `use_sdate` TIMESTAMP NOT NULL ,
CHANGE COLUMN `use_edate` `use_edate` TIMESTAMP NOT NULL ;

# 쿠폰 더미값 20개
INSERT INTO `finalproject`.`coupon` (coupon_code, member_id, coupon_name, use_sdate, use_edate, discount_rate) VALUES 
('COUPON1', 'user01', 'Coupon 1', '2024-10-17', '2024-12-14', 24),
('COUPON2', 'user02', 'Coupon 2', '2024-10-1', '2024-12-18', 20),
('COUPON3', 'user03', 'Coupon 3', '2024-10-2', '2024-12-16', 20),
('COUPON4', 'user04', 'Coupon 4', '2024-10-28', '2024-12-10', 10),
('COUPON5', 'user05', 'Coupon 5', '2024-10-26', '2024-12-22', 21),
('COUPON6', 'user06', 'Coupon 6', '2024-10-14', '2024-12-20', 19),
('COUPON7', 'user07', 'Coupon 7', '2024-10-28', '2024-12-18', 9),
('COUPON8', 'user08', 'Coupon 8', '2024-10-14', '2024-12-30', 8),
('COUPON9', 'user09', 'Coupon 9', '2024-10-25', '2024-12-13', 29),
('COUPON10', 'user10', 'Coupon 10', '2024-10-28', '2024-12-12', 25),
('COUPON11', 'user11', 'Coupon 11', '2024-10-8', '2024-12-11', 23),
('COUPON12', 'user12', 'Coupon 12', '2024-10-6', '2024-12-6', 6),
('COUPON13', 'user13', 'Coupon 13', '2024-10-8', '2024-12-17', 9),
('COUPON14', 'user14', 'Coupon 14', '2024-10-7', '2024-12-12', 5),
('COUPON15', 'user15', 'Coupon 15', '2024-10-6', '2024-12-31', 7),
('COUPON16', 'user16', 'Coupon 16', '2024-10-12', '2024-12-14', 24),
('COUPON17', 'user17', 'Coupon 17', '2024-10-29', '2024-12-26', 6),
('COUPON18', 'user18', 'Coupon 18', '2024-10-5', '2024-12-14', 24),
('COUPON19', 'user19', 'Coupon 19', '2024-10-8', '2024-12-6', 8),
('COUPON20', 'user20', 'Coupon 20', '2024-10-25', '2024-12-26', 14);

# 쿠폰 생성 더미값 5개
INSERT INTO `finalproject`.`coupon` (coupon_code, coupon_name, use_sdate, use_edate, discount_rate) VALUES 
('COUPON21', 'Coupon 21', '2024-10-17', '2024-12-14', 24),
('COUPON22', 'Coupon 22', '2024-10-1', '2024-12-18', 20),
('COUPON23', 'Coupon 23', '2024-10-2', '2024-12-16', 20),
('COUPON24', 'Coupon 24', '2024-10-28', '2024-12-10', 10),
('COUPON25', 'Coupon 25', '2024-10-26', '2024-12-22', 21);

# 리뷰
CREATE TABLE `review` (
  `review_num` int NOT NULL,
  `member_id` varchar(255) NOT NULL,
  `product_num` int NOT NULL,
  `content` varchar(1000) NOT NULL,
  `rating` int NOT NULL,
  `review_img` varchar(255) DEFAULT NULL,
  `createdDate` timestamp NOT NULL,
  `modifiedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`review_num`)
);

 # 리뷰 더미값 20개
 INSERT INTO `finalproject`.`review` (review_num, member_id, product_num, content, rating, review_img, createdDate, modifiedDate) VALUES 
(1, 'user01', 12, 'Review content for product 1', 2, 'review1.jpg', '2024-10-10', '2024-10-21'),
(2, 'user02', 4, 'Review content for product 2', 4, 'review2.jpg', '2024-10-2', '2024-10-10'),
(3, 'user03', 1, 'Review content for product 3', 3, 'review3.jpg', '2024-10-6', '2024-10-25'),
(4, 'user04', 8, 'Review content for product 4', 4, 'review4.jpg', '2024-10-19', '2024-10-10'),
(5, 'user05', 5, 'Review content for product 5', 2, 'review5.jpg', '2024-10-14', '2024-10-15'),
(6, 'user06', 14, 'Review content for product 6', 3, 'review6.jpg', '2024-10-21', '2024-10-30'),
(7, 'user07', 19, 'Review content for product 7', 1, 'review7.jpg', '2024-10-2', '2024-10-12'),
(8, 'user08', 1, 'Review content for product 8', 5, 'review8.jpg', '2024-10-15', '2024-10-28'),
(9, 'user09', 12, 'Review content for product 9', 5, 'review9.jpg', '2024-10-16', '2024-10-19'),
(10, 'user10', 18, 'Review content for product 10', 2, 'review10.jpg', '2024-10-16', '2024-10-6'),
(11, 'user11', 6, 'Review content for product 11', 2, 'review11.jpg', '2024-10-1', '2024-10-27'),
(12, 'user12', 13, 'Review content for product 12', 1, 'review12.jpg', '2024-10-2', '2024-10-6'),
(13, 'user13', 3, 'Review content for product 13', 4, 'review13.jpg', '2024-10-22', '2024-10-10'),
(14, 'user14', 20, 'Review content for product 14', 3, 'review14.jpg', '2024-10-16', '2024-10-10'),
(15, 'user15', 6, 'Review content for product 15', 1, 'review15.jpg', '2024-10-9', '2024-10-28'),
(16, 'user16', 11, 'Review content for product 16', 3, 'review16.jpg', '2024-10-8', '2024-10-23'),
(17, 'user17', 16, 'Review content for product 17', 3, 'review17.jpg', '2024-10-4', '2024-10-25'),
(18, 'user18', 18, 'Review content for product 18', 1, 'review18.jpg', '2024-10-19', '2024-10-27'),
(19, 'user19', 12, 'Review content for product 19', 5, 'review19.jpg', '2024-10-22', '2024-10-4'),
(20, 'user20', 3, 'Review content for product 20', 3, 'review20.jpg', '2024-10-24', '2024-10-25'); 

  # 재활용 라이프 댓글
  CREATE TABLE `finalproject`.`recyclelifecomment` (
  `lifeComment_num` INT NOT NULL,
  `recycleLife_num` INT NOT NULL,
  `member_id` VARCHAR(255) NOT NULL,
  `lifeComment_content` VARCHAR(1000) NOT NULL,
  `lifeComment_wdate` TIMESTAMP NOT NULL,
  PRIMARY KEY (`lifeComment_num`));
  
  # 재활용 라이프 댓글 더미값 20개
  INSERT INTO `finalproject`.`recyclelifecomment` (lifeComment_num, recycleLife_num, member_id, lifeComment_content, lifeComment_wdate) VALUES 
(1, 6, 'user01', 'Comment on life 1', '2024-10-6'),
(2, 7, 'user02', 'Comment on life 2', '2024-10-19'),
(3, 20, 'user03', 'Comment on life 3', '2024-10-23'),
(4, 15, 'user04', 'Comment on life 4', '2024-10-28'),
(5, 20, 'user05', 'Comment on life 5', '2024-10-24'),
(6, 1, 'user06', 'Comment on life 6', '2024-10-24'),
(7, 4, 'user07', 'Comment on life 7', '2024-10-6'),
(8, 12, 'user08', 'Comment on life 8', '2024-10-26'),
(9, 2, 'user09', 'Comment on life 9', '2024-10-7'),
(10, 1, 'user10', 'Comment on life 10', '2024-10-4'),
(11, 18, 'user11', 'Comment on life 11', '2024-10-2'),
(12, 4, 'user12', 'Comment on life 12', '2024-10-26'),
(13, 19, 'user13', 'Comment on life 13', '2024-10-25'),
(14, 17, 'user14', 'Comment on life 14', '2024-10-23'),
(15, 1, 'user15', 'Comment on life 15', '2024-10-8'),
(16, 13, 'user16', 'Comment on life 16', '2024-10-26'),
(17, 1, 'user17', 'Comment on life 17', '2024-10-26'),
(18, 17, 'user18', 'Comment on life 18', '2024-10-20'),
(19, 17, 'user19', 'Comment on life 19', '2024-10-15'),
(20, 1, 'user20', 'Comment on life 20', '2024-10-9');
  # 배송 조회
  CREATE TABLE `finalproject`.`delivery` (
  `delivery_num` INT NOT NULL,
  `pay_num` INT NOT NULL,
  `product_num` INT NOT NULL,
  `member_id` VARCHAR(255) NOT NULL,
  `order_num` INT NOT NULL,
  `tracking_num` VARCHAR(255) NULL,
  `courier` VARCHAR(45) NOT NULL,
  `delivery_status` VARCHAR(45) NULL,
  `shipped_date` TIMESTAMP NOT NULL,
  `delivery_date` TIMESTAMP NULL,
  PRIMARY KEY (`delivery_num`, `order_num`),
  UNIQUE INDEX `member_id_UNIQUE` (`member_id` ASC) VISIBLE);
  
 # 배송 조회 더미값 20개
 INSERT INTO `finalproject`.`delivery` (delivery_num, pay_num, product_num, member_id, order_num, tracking_num, courier, delivery_status, shipped_date, delivery_date) VALUES 
(1, 8, 8, 'user01', 7, 'TRACK1', 'Courier 1', 'Shipped', '2024-10-18', '2024-10-26'),
(2, 8, 19, 'user02', 9, 'TRACK2', 'Courier 2', 'Delivered', '2024-10-21', '2024-10-24'),
(3, 4, 20, 'user03', 9, 'TRACK3', 'Courier 3', 'Delivered', '2024-10-25', '2024-10-23'),
(4, 18, 13, 'user04', 9, 'TRACK4', 'Courier 4', 'Delivered', '2024-10-28', '2024-10-13'),
(5, 19, 2, 'user05', 8, 'TRACK5', 'Courier 5', 'Delivered', '2024-10-5', '2024-10-22'),
(6, 17, 9, 'user06', 7, 'TRACK6', 'Courier 6', 'Pending', '2024-10-6', '2024-10-21'),
(7, 6, 17, 'user07', 7, 'TRACK7', 'Courier 7', 'Pending', '2024-10-14', '2024-10-1'),
(8, 18, 13, 'user08', 5, 'TRACK8', 'Courier 8', 'Shipped', '2024-10-29', '2024-10-17'),
(9, 1, 15, 'user09', 15, 'TRACK9', 'Courier 9', 'Pending', '2024-10-19', '2024-10-9'),
(10, 12, 19, 'user10', 17, 'TRACK10', 'Courier 10', 'Shipped', '2024-10-9', '2024-10-15'),
(11, 18, 19, 'user11', 7, 'TRACK11', 'Courier 11', 'Pending', '2024-10-5', '2024-10-13'),
(12, 16, 17, 'user12', 16, 'TRACK12', 'Courier 12', 'Delivered', '2024-10-21', '2024-10-30'),
(13, 19, 8, 'user13', 9, 'TRACK13', 'Courier 13', 'Shipped', '2024-10-13', '2024-10-16'),
(14, 7, 15, 'user14', 6, 'TRACK14', 'Courier 14', 'Shipped', '2024-10-18', '2024-10-23'),
(15, 7, 13, 'user15', 20, 'TRACK15', 'Courier 15', 'Shipped', '2024-10-19', '2024-10-10'),
(16, 17, 10, 'user16', 19, 'TRACK16', 'Courier 16', 'Pending', '2024-10-7', '2024-10-28'),
(17, 10, 15, 'user17', 12, 'TRACK17', 'Courier 17', 'Pending', '2024-10-14', '2024-10-27'),
(18, 19, 7, 'user18', 16, 'TRACK18', 'Courier 18', 'Pending', '2024-10-26', '2024-10-2'),
(19, 4, 12, 'user19', 11, 'TRACK19', 'Courier 19', 'Delivered', '2024-10-22', '2024-10-4'),
(20, 9, 13, 'user20', 11, 'TRACK20', 'Courier 20', 'Pending', '2024-10-4', '2024-10-13');
  
  # 결제
  CREATE TABLE `finalproject`.`payment` (
  `pay_num` int NOT NULL,
  `product_num` int NOT NULL,
  `member_id` varchar(255) NOT NULL,
  `coupon_code` varchar(255) DEFAULT NULL,
  `order_num` int NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `count` int NOT NULL,
  `price` int NOT NULL,
  `member_name` varchar(45) NOT NULL,
  `address` varchar(255) NOT NULL,
  `tel` VARCHAR(45) NOT NULL,
  `reusing` tinyint DEFAULT NULL,
  `discount` int DEFAULT NULL,
  `delivery_fee` int NOT NULL,
  `delivery_memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pay_num`),
  UNIQUE KEY `member_id_UNIQUE` (`member_id`)
);

# 결제 더미값 20개
INSERT INTO `finalproject`.`payment` (pay_num, product_num, member_id, coupon_code, order_num, product_name, count, price, member_name, address, tel, reusing, discount, delivery_fee, delivery_memo) VALUES 
(1, 7, 'user01', 'COUPON1', 1, 'Product 1', 5, 29372, 'User01', 'Address 1', '010-1046-6723', 0, 4, 3051, 'Memo 1'),
(2, 14, 'user02', 'COUPON2', 7, 'Product 2', 5, 37280, 'User02', 'Address 2', '010-5134-6294', 1, 13, 1219, 'Memo 2'),
(3, 9, 'user03', 'COUPON3', 6, 'Product 3', 4, 38596, 'User03', 'Address 3', '010-3501-6504', 0, 4, 3317, 'Memo 3'),
(4, 14, 'user04', 'COUPON4', 9, 'Product 4', 4, 28945, 'User04', 'Address 4', '010-8033-7927', 0, 61, 2482, 'Memo 4'),
(5, 18, 'user05', 'COUPON5', 18, 'Product 5', 2, 33024, 'User05', 'Address 5', '010-7070-2366', 1, 72, 3810, 'Memo 5'),
(6, 9, 'user06', 'COUPON6', 15, 'Product 6', 1, 43243, 'User06', 'Address 6', '010-9871-1491', 1, 90, 3472, 'Memo 6'),
(7, 5, 'user07', 'COUPON7', 12, 'Product 7', 1, 21079, 'User07', 'Address 7', '010-5282-5503', 1, 50, 4478, 'Memo 7'),
(8, 17, 'user08', 'COUPON8', 15, 'Product 8', 2, 33273, 'User08', 'Address 8', '010-8560-7251', 0, 96, 2008, 'Memo 8'),
(9, 7, 'user09', 'COUPON9', 13, 'Product 9', 3, 13177, 'User09', 'Address 9', '010-9935-7081', 0, 45, 1365, 'Memo 9'),
(10, 10, 'user10', 'COUPON10', 9, 'Product 10', 3, 29499, 'User10', 'Address 10', '010-8984-9876', 1, 50, 2676, 'Memo 10'),
(11, 3, 'user11', 'COUPON11', 16, 'Product 11', 2, 45772, 'User11', 'Address 11', '010-8646-6500', 0, 42, 4898, 'Memo 11'),
(12, 1, 'user12', 'COUPON12', 3, 'Product 12', 5, 20216, 'User12', 'Address 12', '010-6000-5018', 0, 24, 1858, 'Memo 12'),
(13, 14, 'user13', 'COUPON13', 7, 'Product 13', 3, 14446, 'User13', 'Address 13', '010-2727-5032', 1, 5, 4628, 'Memo 13'),
(14, 17, 'user14', 'COUPON14', 7, 'Product 14', 5, 32308, 'User14', 'Address 14', '010-6149-8055', 1, 59, 3262, 'Memo 14'),
(15, 6, 'user15', 'COUPON15', 5, 'Product 15', 3, 49223, 'User15', 'Address 15', '010-6076-3068', 0, 57, 4013, 'Memo 15'),
(16, 17, 'user16', 'COUPON16', 8, 'Product 16', 1, 30187, 'User16', 'Address 16', '010-6194-9466', 0, 85, 4830, 'Memo 16'),
(17, 19, 'user17', 'COUPON17', 18, 'Product 17', 5, 11290, 'User17', 'Address 17', '010-2353-4441', 1, 89, 4656, 'Memo 17'),
(18, 9, 'user18', 'COUPON18', 1, 'Product 18', 1, 10223, 'User18', 'Address 18', '010-1913-4161', 1, 71, 1296, 'Memo 18'),
(19, 5, 'user19', 'COUPON19', 16, 'Product 19', 5, 43399, 'User19', 'Address 19', '010-8616-7238', 0, 32, 3992, 'Memo 19'),
(20, 11, 'user20', 'COUPON20', 2, 'Product 20', 3, 17449, 'User20', 'Address 20', '010-5965-2106', 1, 83, 4375, 'Memo 20');

##########-------------###########
  
# 고객센터에서 회원 참조
ALTER TABLE `finalproject`.`help` 
ADD CONSTRAINT `FK_help_member`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
#리뷰에서 회원, 상품 참조
ALTER TABLE `finalproject`.`review` 
ADD INDEX `FK_REVIEW_PRODUCT_idx` (`product_num` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`review` 
ADD CONSTRAINT `FK_REVIEW_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_REVIEW_PRODUCT`
  FOREIGN KEY (`product_num`)
  REFERENCES `finalproject`.`product` (`product_num`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
#장바구니에서 회원, 상품 참조
ALTER TABLE `finalproject`.`cart` 
ADD INDEX `FK_CART_MEMBER_idx` (`member_id` ASC) VISIBLE,
ADD INDEX `FK_CART_PRODUCT_idx` (`product_num` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`cart` 
ADD CONSTRAINT `FK_CART_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_CART_PRODUCT`
  FOREIGN KEY (`product_num`)
  REFERENCES `finalproject`.`product` (`product_num`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

#찜에서 회원, 상품 참조
ALTER TABLE `finalproject`.`like`
ADD CONSTRAINT `FK_LIKE_MEMBER`
FOREIGN KEY (`member_id`) 
REFERENCES `finalproject`.`member` (`member_id`)
ON DELETE CASCADE 
ON UPDATE NO ACTION;

ALTER TABLE `finalproject`.`like`
ADD CONSTRAINT `FK_LIKE_PRODUCT`
FOREIGN KEY (`product_num`) 
REFERENCES `finalproject`.`product` (`product_num`)
ON DELETE CASCADE 
ON UPDATE CASCADE;

# 배송조회에서 결제,상품,회원,주문 참조
ALTER TABLE `finalproject`.`delivery` 
ADD INDEX `FK_DELIVERY_PAYMENT_idx` (`pay_num` ASC) VISIBLE,
ADD INDEX `FK_DELIVERY_PRODUCT_idx` (`product_num` ASC) VISIBLE,
ADD INDEX `FK_DELIVERY_ORDER_idx` (`order_num` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`delivery` 
ADD CONSTRAINT `FK_DELIVERY_PAYMENT`
  FOREIGN KEY (`pay_num`)
  REFERENCES `finalproject`.`payment` (`pay_num`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_DELIVERY_PRODUCT`
  FOREIGN KEY (`product_num`)
  REFERENCES `finalproject`.`product` (`product_num`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_DELIVERY_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_DELIVERY_ORDER`
  FOREIGN KEY (`order_num`)
  REFERENCES `finalproject`.`order` (`order_num`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
# 결제에서 상품,회원,쿠폰,주문 참조
ALTER TABLE `finalproject`.`payment` 
ADD INDEX `FK_PAYMENT_PRODUCT_idx` (`product_num` ASC) VISIBLE,
ADD INDEX `FK_PAYMENT_COUPON_idx` (`coupon_code` ASC) VISIBLE,
ADD INDEX `FK_PAYMENT_ORDER_idx` (`order_num` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`payment` 
ADD CONSTRAINT `FK_PAYMENT_PRODUCT`
  FOREIGN KEY (`product_num`)
  REFERENCES `finalproject`.`product` (`product_num`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_PAYMENT_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_PAYMENT_COUPON`
  FOREIGN KEY (`coupon_code`)
  REFERENCES `finalproject`.`coupon` (`coupon_code`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_PAYMENT_ORDER`
  FOREIGN KEY (`order_num`)
  REFERENCES `finalproject`.`order` (`order_num`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
# 주문에서 상품,회원,쿠폰 참조
ALTER TABLE `finalproject`.`order` 
ADD INDEX `FK_ORDER_PRODUCT_idx` (`product_num` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`order` 
ADD CONSTRAINT `FK_ORDER_PRODUCT`
  FOREIGN KEY (`product_num`)
  REFERENCES `finalproject`.`product` (`product_num`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_ORDER_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_ORDER_COUPON`
  FOREIGN KEY (`coupon_code`)
  REFERENCES `finalproject`.`coupon` (`coupon_code`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
# 쿠폰에서 회원 참조
ALTER TABLE `finalproject`.`coupon` 
ADD INDEX `FK_COUPON_MEMBER_idx` (`member_id` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`coupon` 
ADD CONSTRAINT `FK_COUPON_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
# '재활용품 분리방법 댓글'에서 재활용품 분리방법, 회원 참조
ALTER TABLE `finalproject`.`recycletipcomment` 
ADD INDEX `FK_TIPCOMMENT_TIP_idx` (`recycleTip_num` ASC) VISIBLE,
ADD INDEX `FK_TIPCOMMENT_MEMBER_idx` (`member_id` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`recycletipcomment` 
ADD CONSTRAINT `FK_TIPCOMMENT_TIP`
  FOREIGN KEY (`recycleTip_num`)
  REFERENCES `finalproject`.`recycletip` (`recycleTip_num`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_TIPCOMMENT_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
# '기부 가능 물품'에서 회원 참조
ALTER TABLE `finalproject`.`donateitem` 
ADD CONSTRAINT `FK_DONATE_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
# '재활용 라이프'에서 회원 참조
ALTER TABLE `finalproject`.`recyclelife` 
ADD INDEX `FK_RECYCLELIFE_MEMBER_idx` (`member_id` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`recyclelife` 
ADD CONSTRAINT `FK_RECYCLELIFE_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
# '재활용 라이프 댓글'에서 재활용 라이프,회원 참조
ALTER TABLE `finalproject`.`recyclelifecomment` 
ADD INDEX `FK_LIFECOMMENT_LIFE_idx` (`recycleLife_num` ASC) VISIBLE,
ADD INDEX `FK_LIFECOMMENT_MEMBER_idx` (`member_id` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`recyclelifecomment` 
ADD CONSTRAINT `FK_LIFECOMMENT_LIFE`
  FOREIGN KEY (`recycleLife_num`)
  REFERENCES `finalproject`.`recyclelife` (`recycleLife_num`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_LIFECOMMENT_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
