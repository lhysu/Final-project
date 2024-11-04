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
  `postcode` VARCHAR(255) NULL,
  `address` VARCHAR(255) NULL,
  `address_detail` VARCHAR(255) NULL,
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
('user20', 20, 'User20', 'password020', 'user20@example.com', '010-7462-7230', 'Seoul', 121, 0)
;

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
  `help_img` VARCHAR(255) NULL,
  `file` VARCHAR(255) NULL,
  PRIMARY KEY (`help_num`));
  
# 고객센터 더미값 20개
INSERT INTO `finalproject`.`help` (member_id, inquiry_type, inquiry_text, status, help_views, help_img, file) VALUES 
('user01', 'Return', 'I have an issue with product 1.', 'Pending', 13, 'help1.jpg', 'file1.jpg'),
('user02', 'Account', 'I have an issue with product 2.', 'Resolved', 17, 'help1.jpg', 'file2.jpg'),
('user03', 'Return', 'I have an issue with product 3.', 'Resolved', 8, 'help1.jpg', 'file3.jpg'),
('user04', 'Return', 'I have an issue with product 4.', 'Resolved', 11, 'help1.jpg', 'file4.jpg'),
('user05', 'Coupon', 'I have an issue with product 5.', 'Resolved', 12, 'help1.jpg', 'file5.jpg'),
('user06', 'Product', 'I have an issue with product 6.', 'Resolved', 9, 'help1.jpg', 'file6.jpg'),
('user07', 'Product', 'I have an issue with product 7.', 'Resolved', 6, 'help1.jpg', 'file7.jpg'),
('user08', 'Delivery', 'I have an issue with product 8.', 'Pending', 14, 'help1.jpg', 'file8.jpg'),
('user09', 'Product', 'I have an issue with product 9.', 'Pending', 18, 'help1.jpg', 'file9.jpg'),
('user10', 'Delivery', 'I have an issue with product 10.', 'Resolved', 6, 'help1.jpg', 'file10.jpg'),
('user11', 'Delivery', 'I have an issue with product 11.', 'Resolved', 14, 'help1.jpg', 'file11.jpg'),
('user12', 'Delivery', 'I have an issue with product 12.', 'Pending', 17, 'help1.jpg', 'file12.jpg'),
('user13', 'Delivery', 'I have an issue with product 13.', 'Pending', 20, 'help1.jpg', 'file13.jpg'),
('user14', 'Return', 'I have an issue with product 14.', 'Resolved', 7, 'help1.jpg', 'file14.jpg'),
('user15', 'Account', 'I have an issue with product 15.', 'Pending', 19, 'help1.jpg', 'file15.jpg'),
('user16', 'Return', 'I have an issue with product 16.', 'Resolved', 15, 'help1.jpg', 'file16.jpg'),
('user17', 'Product', 'I have an issue with product 17.', 'Resolved', 18, 'help1.jpg', 'file17.jpg'),
('user18', 'Account', 'I have an issue with product 18.', 'Pending', 5, 'help1.jpg', 'file18.jpg'),
('user19', 'Return', 'I have an issue with product 19.', 'Pending', 11, 'help1.jpg', 'file19.jpg'),
('user20', 'Return', 'I have an issue with product 20.', 'Pending', 19, 'help1.jpg', 'file20.jpg');
  
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
CREATE TABLE `finalproject`.`likeList` (
  `likeList_num` INT NOT NULL AUTO_INCREMENT,
  `product_num` INT NOT NULL,
  `member_id` VARCHAR(255) NOT NULL,
  `price` INT NOT NULL,
  `product_img` VARCHAR(255) NOT NULL,
  `product_name` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`likeList_num`));
  
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
  `count` INT NOT NULL DEFAULT 1,
  `price` INT NOT NULL,
  `point` INT NOT NULL,
  `company` VARCHAR(255) NOT NULL,
  `product_img` VARCHAR(255) NOT NULL,
  `category` VARCHAR(255) NULL,
  `rating` DOUBLE NULL,
  PRIMARY KEY (`product_num`));

# 장바구니
CREATE TABLE `finalproject`.`cart` (
  `cart_num` INT NOT NULL AUTO_INCREMENT,
  `product_num` INT NOT NULL,
  `member_id` VARCHAR(255) NOT NULL,
  `count` INT NOT NULL,
  `price` INT NOT NULL,
  `product_img` VARCHAR(255) NOT NULL,
  `product_name` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`cart_num`));

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
  `merchant_uid` VARCHAR(255) NOT NULL,
  `product_num` INT NULL,
  `member_id` VARCHAR(255)  NULL,
  `coupon_code` VARCHAR(100) NULL,
  `points` INT NULL,
  `postcode` VARCHAR(255) NULL,
  `address` VARCHAR(255) NOT NULL,
  `address_detail` VARCHAR(255) NULL,
  `tel` VARCHAR(45) NOT NULL,
  `reusing` TINYINT DEFAULT 0,
  `discount` INT NULL,
  `delivery_fee` INT NOT NULL,
  `delivery_memo` VARCHAR(255) NULL,
  `total_price` INT NOT NULL,
  `final_price` INT  NULL,
  `order_state` VARCHAR(45),
  `order_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`merchant_uid`));
  
  # 주문 더미값 20개
INSERT INTO `finalproject`.`order` (merchant_uid,product_num ,member_id, coupon_code, postcode, address, address_detail, tel, reusing, discount, delivery_fee, delivery_memo,total_price,order_state) VALUES 
('O111122226', 1,'user01', 'COUPON1', '07512', '경기도 성남시 판교로1','1011호' ,'010-5690-9095', 0, 10, 2500, 'Memo 1', 90000,'배송 준비중'),
('O456645612', 2,'user02', '12345789', '38545', '경기도 성남시 판교로2','1012호' ,'010-2224-5058', 1, 10, 0, 'Memo 2', 80000,'배송 준비중'),
('O884551222', 3,'user03', 'COUPON3', '78970', '경기도 성남시 판교로3','1013호','010-6430-9927', 0, 10, 2500, 'Memo 3', 50000,'배송 준비중'),
('O888884422', 4,'user04', 'COUPON4', '12016', '경기도 성남시 판교로4','1014호','010-3727-8448', 0, 20, 3000, 'Memo 4', 9000,'배송 준비중'),
('O451432221', 5,'user05', 'COUPON5', '78980', '경기도 성남시 판교로5','1015호','010-2147-7721', 0, 30, 0, 'Memo 5', 80000,'배송 준비중');

# 주문 상세 테이블
CREATE TABLE `finalproject`.`order_item` (
  `order_item_id` INT NOT NULL AUTO_INCREMENT,
  `merchant_uid` VARCHAR(255)  NULL,
  `product_num` INT  NULL,
  `quantity` INT  NULL,
  `price` INT  NULL,
  PRIMARY KEY (`order_item_id`));
  
 # 주문 상세 테이블 5개
INSERT INTO `finalproject`.`order_item` (merchant_uid,product_num ,quantity, price) VALUES 
('O111122226', 1,11, 7951),
('O456645612', 2,22, 20000),
('O884551222', 3,33, 30000),
('O888884422', 4,44, 40000),
('O451432221', 5,55, 50000);
  
  # 쿠폰
  CREATE TABLE `finalproject`.`coupon` (
  `coupon_code` VARCHAR(100)  NOT NULL,
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
('12345789', 'user02', '5000원 이상 할인 쿠폰', '2024-10-1', '2024-12-18', 20),
('98877512', 'user02', '5000원 이상 할인 쿠폰', '2024-10-1', '2024-12-10', 10),
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
  `review_num` int NOT NULL AUTO_INCREMENT,
  `member_id` varchar(255) NOT NULL,
  `product_num` int NOT NULL,
  `content` varchar(1000) NOT NULL,
  `rating` DOUBLE NOT NULL,
  `review_img` varchar(255) DEFAULT NULL,
  `createdDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `product_name` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`review_num`)
);

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
  `product_name` VARCHAR(255) NOT NULL,
  `member_id` VARCHAR(255) NOT NULL,
  `merchant_uid` VARCHAR(255) NOT NULL,
  `tracking_num` VARCHAR(255) NULL,
  `courier` VARCHAR(45) NOT NULL,
  `delivery_status` VARCHAR(45) NULL,
  `shipped_date` TIMESTAMP NOT NULL,
  `delivery_date` TIMESTAMP NULL,
  PRIMARY KEY (`delivery_num`));
  
 # 배송 조회 더미값 20개
 INSERT INTO `finalproject`.`delivery` (delivery_num, pay_num, product_name, member_id, merchant_uid, tracking_num, courier, delivery_status, shipped_date, delivery_date) VALUES 
(1, 1, 8, 'user01', 'O111122226', 'TRACK1', 'Courier 1', 'Shipped', '2024-10-18', '2024-10-26'),
(2, 2, 19, 'user02', 'O456645612', 'TRACK2', 'Courier 2', 'Delivered', '2024-10-21', '2024-10-24'),
(3, 3, 20, 'user03', 'O884551222', 'TRACK3', 'Courier 3', 'Delivered', '2024-10-25', '2024-10-23'),
(4, 4, 13, 'user04', 'O888884422', 'TRACK4', 'Courier 4', 'Delivered', '2024-10-28', '2024-10-13'),
(5, 5, 2, 'user05', 'O451432221', 'TRACK5', 'Courier 5', 'Delivered', '2024-10-5', '2024-10-22');

  
  # 결제
  CREATE TABLE `finalproject`.`payment` (
  `pay_num` INT NOT NULL AUTO_INCREMENT,
  `merchant_uid` VARCHAR(255) NULL,
  `imp_uid` VARCHAR(255) NULL,
  `member_id` VARCHAR(255) NULL,
  `paid_amount` INT NULL,
  `pay_method` VARCHAR(45) NULL,
  `pay_status` VARCHAR(45) NULL,
  `pay_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tracking_number` VARCHAR(255) NULL,
  PRIMARY KEY (`pay_num`)
);

# 결제 더미값 20개
INSERT INTO `finalproject`.`payment` 
(merchant_uid, imp_uid, member_id, paid_amount, pay_method, pay_status, tracking_number) 
VALUES 
('O111122226', 'imp_11111', 'user01', 10000, 'card', '결제 완료', '11111'),
('O456645612', 'imp_22222', 'user02', 20000, 'card', '결제 완료', '22222'),
('O884551222', 'imp_33333', 'user03', 30000, 'card', '결제 완료', '33333'),
('O888884422', 'imp_44444', 'user04', 40000, 'card', '결제 완료', '44444'),
('O451432221', 'imp_55555', 'user05', 50000, 'card', '결제 완료', '55555');

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
ALTER TABLE `finalproject`.`likeList`
ADD CONSTRAINT `FK_LIKELIST_MEMBER`
FOREIGN KEY (`member_id`) 
REFERENCES `finalproject`.`member` (`member_id`)
ON DELETE CASCADE 
ON UPDATE NO ACTION;

ALTER TABLE `finalproject`.`likeList`
ADD CONSTRAINT `FK_LIKELIST_PRODUCT`
FOREIGN KEY (`product_num`) 
REFERENCES `finalproject`.`product` (`product_num`)
ON DELETE CASCADE 
ON UPDATE CASCADE;

# 배송조회에서 결제,상품,회원,주문 참조
ALTER TABLE `finalproject`.`delivery` 
ADD INDEX `FK_DELIVERY_PAYMENT_idx` (`pay_num` ASC) VISIBLE,
ADD INDEX `FK_DELIVERY_ORDER_idx` (`merchant_uid` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`delivery` 
ADD CONSTRAINT `FK_DELIVERY_PAYMENT`
  FOREIGN KEY (`pay_num`)
  REFERENCES `finalproject`.`payment` (`pay_num`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,

ADD CONSTRAINT `FK_DELIVERY_ORDER`
  FOREIGN KEY (`merchant_uid`)
  REFERENCES `finalproject`.`order` (`merchant_uid`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
# 결제에서 상품,회원,쿠폰,주문 참조
ALTER TABLE `finalproject`.`payment` 
ADD INDEX `FK_PAYMENT_ORDER_idx` (`merchant_uid` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`payment` 
ADD CONSTRAINT `FK_PAYMENT_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
# 주문에서 상품,회원,쿠폰 참조
ALTER TABLE `finalproject`.`order` 
ADD CONSTRAINT `FK_ORDER_MEMBER`
  FOREIGN KEY (`member_id`)
  REFERENCES `finalproject`.`member` (`member_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_ORDER_COUPON`
  FOREIGN KEY (`coupon_code`)
  REFERENCES `finalproject`.`coupon` (`coupon_code`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_ORDER_PRODUCT`
  FOREIGN KEY (`product_num`)
  REFERENCES `finalproject`.`product` (`product_num`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

#주문 상세에서 주문 참조
ALTER TABLE `finalproject`.`order_item` 
ADD INDEX `FK_ITEM_ORDER_idx` (`merchant_uid` ASC) VISIBLE,
ADD INDEX `FK_ITEM_PRODUCT_idx` (`product_num` ASC) VISIBLE;
;
ALTER TABLE `finalproject`.`order_item` 
ADD CONSTRAINT `FK_ITEM_ORDER`
  FOREIGN KEY (`merchant_uid`)
  REFERENCES `finalproject`.`order` (`merchant_uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_ITEM_PRODUCT`
  FOREIGN KEY (`product_num`)
  REFERENCES `finalproject`.`product` (`product_num`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
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
