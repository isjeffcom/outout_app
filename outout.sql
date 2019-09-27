-- phpMyAdmin SQL Dump
-- version 4.4.15.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2019-09-27 19:45:01
-- 服务器版本： 5.5.48-log
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `outout`
--

-- --------------------------------------------------------

--
-- 表的结构 `outout_event`
--

CREATE TABLE IF NOT EXISTS `outout_event` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `time` text NOT NULL,
  `date` text NOT NULL,
  `date_p` text NOT NULL,
  `des` text NOT NULL,
  `type` text NOT NULL,
  `attend` text,
  `img` text NOT NULL,
  `toVenue` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `outout_event`
--

INSERT INTO `outout_event` (`id`, `name`, `time`, `date`, `date_p`, `des`, `type`, `attend`, `img`, `toVenue`) VALUES
(1, 'Ohlala', '10PM - 4AM', 'May 5, 2018', '20-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '13,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 0),
(2, 'Saturdays', '12PM - 3AM', 'March 14, 2018', '19-04-2018', 'OAdsjvcn, fjsinvuaf nfoishvsdf nv aij', '13,16', '', 'https://www.isjeff.com/wp-content/uploads/2018/04/b.png', 1),
(3, 'Oh la la', '10PM - 4AM', 'March 14, 2018', '19-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '13,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 2),
(4, 'Saturdays', '11PM - 2AM', 'March 14, 2018', '19-04-2018', 'sdfjinjasdfn dsafsd', '13,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 3),
(5, 'Eskimo Project', '10PM - 4AM', 'March 14, 2018', '19-04-2018', 'pias i-4jf iosfasd', '13,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 0),
(9, 'Eskimo Project', '9PM - 1AM', 'March 14, 2018', '17-04-2018', 'jsaf sdfhuweb suhdf', '13,16', '', 'https://image.ibb.co/mkaFK7/cc.jpg', 2),
(10, 'Oh la la', '9PM - 1AM', 'March 14, 2018', '17-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '13,16', '', 'https://preview.ibb.co/iGm2XS/c8a7b6894f23b47e98ecdfb3b04c2c1fe.jpg', 0),
(11, 'Dirty Disco', '9PM - 1AM', 'March 14, 2018', '17-04-2018', 'sidojf sjafsd sdf usadf sdfasdfa', '13,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 1),
(12, 'Oh la la', '9PM - 1AM', 'March 14, 2018', '17-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '13,16', '', 'https://image.ibb.co/dRRKCS/6dea3074cb7b47b5928a55e2e8cd5c40.jpg', 3),
(13, 'Dirty Disco', '9PM - 1AM', 'March 14, 2018', '18-04-2018', 'osijadfisdjfiua sdfasd sd', '13,16', '', 'https://preview.ibb.co/iGm2XS/c8a7b6894f23b47e98ecdfb3b04c2c1fe.jpg', 1),
(14, 'Oh la la', '9PM - 1AM', 'March 14, 2018', '18-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '13,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 0),
(16, 'Eskimo Project', '9PM - 1AM', 'March 14, 2018', '18-04-2018', 'sdfaiosjf sdfas', '13,16', '', 'https://image.ibb.co/dRRKCS/6dea3074cb7b47b5928a55e2e8cd5c40.jpg', 0),
(17, 'Eskimo Project', '10PM - 4AM', 'March 14, 2018', '20-04-2018', 'Providing Portsmouth Students with the best club nights in town, in association with Portsmouth Students Union. Previous guests;', '13,16', '', 'https://preview.ibb.co/jXcV7S/TAG_Magazine_UK_Whats_On_Guide_Eskimo_Project.jpg', 1),
(18, 'Saturdays', '11PM - 2AM', 'March 14, 2018', '20-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '13,16', '', 'https://preview.ibb.co/nwTnnS/c29fdbd151e4549968e1b151022d66aee.jpg', 3),
(19, 'The Pound Party', '12PM - 3AM', 'April 20, 2018', '20-04-2018', 'Portsmouth''s most outrageously CHEAP night! A mash-up of r''n''b, grime, drum & bass, house and chart!', '13,16', '', 'https://preview.ibb.co/b2Re1n/va.jpg', 1),
(20, 'The Pound Party', '12PM - 3AM', 'April 22, 2018', '22-04-2018', 'Portsmouth''s most outrageously CHEAP night! A mash-up of r''n''b, grime, drum & bass, house and chart!', '9,16', '', 'https://preview.ibb.co/b2Re1n/va.jpg', 1),
(21, 'Saturdays', '11PM - 2AM', 'April 22, 2018', '22-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '13,16', '', 'https://preview.ibb.co/nwTnnS/c29fdbd151e4549968e1b151022d66aee.jpg', 3),
(23, 'Eskimo Project', '10PM - 4AM', 'April 22, 2018', '22-04-2018', 'Providing Portsmouth Students with the best club nights in town, in association with Portsmouth Students Union. Previous guests;', '5,16', '', 'https://preview.ibb.co/jXcV7S/TAG_Magazine_UK_Whats_On_Guide_Eskimo_Project.jpg', 1),
(24, 'Ohlala', '10PM - 4AM', 'April 22, 2018', '22-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '4,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 0),
(25, 'The Pound Party', '12PM - 3AM', 'April 23, 2018', '23-04-2018', 'Portsmouth''s most outrageously CHEAP night! A mash-up of r''n''b, grime, drum & bass, house and chart!', '9,16', '', 'https://preview.ibb.co/b2Re1n/va.jpg', 1),
(26, 'Saturdays', '11PM - 2AM', 'April 23, 2018', '23-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '5,16', '', 'https://preview.ibb.co/nwTnnS/c29fdbd151e4549968e1b151022d66aee.jpg', 3),
(27, 'Eskimo Project', '10PM - 4AM', 'April 23, 2018', '23-04-2018', 'Providing Portsmouth Students with the best club nights in town, in association with Portsmouth Students Union. Previous guests;', '13,16', '', 'https://preview.ibb.co/jXcV7S/TAG_Magazine_UK_Whats_On_Guide_Eskimo_Project.jpg', 1),
(29, 'The Pound Party', '12PM - 3AM', 'April 24, 2018', '24-04-2018', 'Portsmouth''s most outrageously CHEAP night! A mash-up of r''n''b, grime, drum & bass, house and chart!', '9,16', '', 'https://preview.ibb.co/b2Re1n/va.jpg', 1),
(30, 'Saturdays', '11PM - 2AM', 'April 24, 2018', '24-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '5,16', '', 'https://preview.ibb.co/nwTnnS/c29fdbd151e4549968e1b151022d66aee.jpg', 3),
(31, 'Eskimo Project', '10PM - 4AM', 'April 24, 2018', '24-04-2018', 'Providing Portsmouth Students with the best club nights in town, in association with Portsmouth Students Union. Previous guests;', '13,16', '', 'https://preview.ibb.co/jXcV7S/TAG_Magazine_UK_Whats_On_Guide_Eskimo_Project.jpg', 1),
(32, 'Ohlala', '10PM - 4AM', 'April 24, 2018', '24-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '13,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 0),
(33, 'Ohlala', '10PM - 4AM', 'April 25, 2018', '25-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '13,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 0),
(34, 'Eskimo Project', '10PM - 4AM', 'April 25, 2018', '25-04-2018', 'Providing Portsmouth Students with the best club nights in town, in association with Portsmouth Students Union. Previous guests;', '13,16', '', 'https://preview.ibb.co/jXcV7S/TAG_Magazine_UK_Whats_On_Guide_Eskimo_Project.jpg', 1),
(35, 'Saturdays', '11PM - 2AM', 'April 25, 2018', '25-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '5,16', '', 'https://preview.ibb.co/nwTnnS/c29fdbd151e4549968e1b151022d66aee.jpg', 3),
(36, 'The Pound Party', '12PM - 3AM', 'April 25, 2018', '25-04-2018', 'Portsmouth''s most outrageously CHEAP night! A mash-up of r''n''b, grime, drum & bass, house and chart!', '9,16', '', 'https://preview.ibb.co/b2Re1n/va.jpg', 1),
(37, 'EPIK!', '10PM - 4AM', 'April 26, 2018', '26-04-2018', 'Allow The Astoria to take you away from standard weekend clubbing and into our all new Carnival.', '13,16', '', 'https://preview.ibb.co/jvQBOc/c411b9bb1db8445759601aa8ae4d64aef.jpg', 0),
(38, 'Love2', '10PM - 4AM', 'April 26, 2018', '26-04-2018', 'Providing Portsmouth Students with the best club nights in town, in association with Portsmouth Students Union. Previous guests;', '13,16', '', 'https://preview.ibb.co/c5vZbx/c2d5c10ab288942a198b65b4ab58af8a3.jpg', 2),
(39, 'Love the 90s', '11PM - 2AM', 'April 26, 2018', '26-04-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '5,16', '', 'https://preview.ibb.co/cc9ubx/cb8aad488709949a0acafdcd3a5ede4c6.jpg', 3),
(40, 'The Pound Party', '12PM - 3AM', 'April 26, 2018', '26-04-2018', 'Portsmouth''s most outrageously CHEAP night! A mash-up of r''n''b, grime, drum & bass, house and chart!', '9,16', '', 'https://preview.ibb.co/b2Re1n/va.jpg', 1),
(41, 'Slug and Lettuce', '10AM - 10PM', 'April 27, 2018', '27-04-2018', 'Smart chain pub with a cocktail list, a classic British menu and uncluttered contemporary decor.', '11,17', '', 'https://preview.ibb.co/iAFP9H/C_h_Bct4_Ws_AYP3qv.jpg', 10),
(42, '3 4 All ', '8PM - 2AM', 'April 27, 2018', '27-04-2018', '3 4 ALL has landed!  A BRAND NEW night has arrived in Portsmouth! £3 drinks, entry and VIP booths!', '5,17', '', 'https://preview.ibb.co/kCcwpH/Tiger_Tiger_Portsmouth_Jewel_3.jpg', 3),
(43, 'One Eyed Dog', '3PM - 1:30AM', 'April 27, 2018', '27-04-2018', 'Great local Djs and very reasonably priced drinks with a good selection of spirits. Fun, cosy atmosphere during the day with free pool.', '11,17', '', 'https://preview.ibb.co/fgL7EH/18715661033_ef67ef607e_k_1.jpg', 13),
(44, 'Superhero Weekender pt.1', '9PM - 3AM', 'April 27, 2018', '27-04-2018', 'Popworld goes all Superhero on you. Join us for a massive weekend celebrating the hero in you! Whether you are Marvel or DC or anything in between. Free Superhero Masks throughout the weekend.', '9,16', '', 'https://image.ibb.co/gn7Rbx/superhero_csotumes2.jpg', 5),
(45, 'Popworld Saturdays', '8PM - 4AM', 'April 28, 2018', '28-04-2018', 'Buzzing bar and club with pop music from the 1990s to the present day, plus cocktails and theme nights.', '8,16', '', 'https://image.ibb.co/etDYmx/popworld.jpg', 5),
(46, 'The Liquorist', '9AM - 1:30AM', 'April 28, 2018', '28-04-2018', 'Our long awaited refurbishment has finally arrived, our location stays the same but, we’ve raised the bar, not only on our fantastic new food but, our outstanding cocktails also.', '11,17', '', 'https://image.ibb.co/eaZLGx/28872929_151422758859838_4243261946436696977_n.jpg', 7),
(47, 'Enclosure', '8PM - 2AM', 'April 28, 2018', '28-04-2018', 'Join us for our BRAND NEW Saturday nights... ENCLOSURE!Tiger offering 4 rooms with 4 different DJ''s to keep you going until 3am!Jewel Bar - The jewel in our Crown with elegant surroundings playing a mixture of current music and R''n''B. ', '5,16', '', 'https://cdn2.fatsoma.com/media/W1siZiIsInB1YmxpYy8yMDE4LzQvOS8xMC8zNC8zMS83NjMvRW5jbG9zdXJlLmpwZyJdLFsicCIsInRodW1iIiwiNjYweDI0NCMiXV0 ', 3),
(48, 'The Fat Fox', '12PM - 1:30AM', 'April 28, 2018', '28-04-2018', 'The Fat Fox and Atrium offer a little bit of everything good. Live sport, live DJs, hot food, cold beer, and cocktails.', '11,17', '', 'https://image.ibb.co/bXpRrx/150.jpg', 16),
(49, 'Brewhouse and Kitchen', '11AM - 10:30PM', 'April 29, 2018', '05-09-2018', 'Historic Tudor-style inn reborn as a stylish, colourful brewpub with an extensive dawn-to-dark menu.', '11,17', '', 'https://preview.ibb.co/dD1mPH/brewbourne0417.jpg', 8),
(50, 'Huis Belgian Bar', '10AM - 1AM', 'April 29, 2018', '05-09-2018', 'An independent bar in Southsea, Portsmouth, bringing Belgian beer and food culture together. Our beer range is extensive and ever changing with over 60 beers on offer. Our draughts include our own exclusive HUIS wheat, pilsner and blonde beers.', '11,17', '', 'https://preview.ibb.co/k3TGPH/Huis.jpg', 12),
(51, 'Waterhole Bar', '12PM - 12AM', 'April 29, 2018', '29-04-2018', 'The Waterhole has a good vibe, cheap food & a decent juke box - what more could a student want? It''s a cool place to be when watching matches.', '11,16', '', 'https://preview.ibb.co/eXHzuH/1471803_10153418408603957_6044562130864939729_n.jpg', 9),
(52, 'McDonalds', '6AM - 11PM', 'April 29, 2018', '29-04-2018', 'Classic, long-running fast-food chain known for its burgers, fries & shakes.', '16,17', '', 'https://image.ibb.co/jo85ZH/h_mcdonalds_Big_Mac_Extra_Value_Meals.png', 4),
(53, 'Drift Southsea', '4PM - 2:30AM', 'April 30, 2018', '30-04-2018', 'Drift Bar in Southsea, the home of award winning food and freshly homemade cocktails at Palmerston Road''s biggest and best beer garden.', '11,17', '', 'https://preview.ibb.co/gdS53c/671_v7jid3nryvhhboah.jpg', 11),
(54, 'Delight', '10PM - 2AM', 'April 30, 2018', '30-04-2018', 'The club night for Rock/Indie/Punk/Alternative music fans. Nonstop anthems, festival party jams & the best value booze in the city!', '5,16', '', 'https://preview.ibb.co/jTeJ6x/1_1512818436_8300.jpg', 0),
(55, 'Quids In!', '8PM - 2AM', 'April 30, 2018', '30-04-2018', 'Quids In Returns!! Portsmouth''s BIGGEST Monday night is back! £1 entry including 2-4-1 wristband', '5,16', '', 'https://cdn2.fatsoma.com/media/W1siZiIsInB1YmxpYy8yMDE4LzQvMjAvMTMvNDUvMzUvNTY0L3F1aWRzTkVXLmpwZyJdLFsicCIsInRodW1iIiwiNjYweDI0NCMiXV0', 3),
(56, 'HKC Vodka Bar', '7PM - 11PM', 'April 30, 2018', '05-09-2018', 'HKC Vodka Bar is the quintessential vodka bar. An intoxicating mix of delicious drinks and sultry surroundings make HKC a destination venue for the stylish.', '3,17', '', 'https://image.ibb.co/gwJypH/hong_kong_charlie_s_vodka.jpg', 14),
(57, 'Ohlala', '10PM - 4AM', 'May 01, 2018', '01-05-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '13,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 0),
(58, 'Eskimo Project', '10PM - 4AM', 'May 01, 2018', '01-05-2018', 'Providing Portsmouth Students with the best club nights in town, in association with Portsmouth Students Union. Previous guests;', '13,16', '', 'https://preview.ibb.co/jXcV7S/TAG_Magazine_UK_Whats_On_Guide_Eskimo_Project.jpg', 1),
(59, 'Dirty Disco', '9PM - 1AM', 'May 01, 2018', '01-05-2018', 'osijadfisdjfiua sdfasd sd', '13,16', '', 'https://preview.ibb.co/iGm2XS/c8a7b6894f23b47e98ecdfb3b04c2c1fe.jpg', 1),
(60, 'The Pound Party', '12PM - 3AM', 'May 01, 2018', '01-05-2018', 'Portsmouth''s most outrageously CHEAP night! A mash-up of r''n''b, grime, drum & bass, house and chart!', '13,16', '', 'https://preview.ibb.co/b2Re1n/va.jpg', 1),
(61, 'Digital media dinner', '6PM - 3AM', 'May 05, 2018', '05-05-2018', 'Digital media get messy part 2, come along and eat, drink and celebrate good times', '11,16', '', 'https://image.ibb.co/bYuDKH/h_mcdonalds_Big_Mac_Extra_Value_Meals.png', 4),
(62, 'Eskimo Project', '10PM - 4AM', 'April 27, 2018', '27-04-2018', 'Welcome to the Eskimo Project. Portsmouth''s Biggest Friday Night! All of Portsmouth''s Young Professionals and returning Students will be joining us this Friday to celebrate!', '13,16', '', 'https://image.ibb.co/jzbG9H/eskimo_project.png', 0),
(63, 'The Liquorist', '9AM - 1:30AM', 'April 27, 2018', '27-04-2018', 'Our long awaited refurbishment has finally arrived, our location stays the same but, we’ve raised the bar, not only on our fantastic new food but, our outstanding cocktails also.', '11,17', '', 'https://image.ibb.co/eaZLGx/28872929_151422758859838_4243261946436696977_n.jpg', 7),
(64, 'Superhero Weekender pt.2', '6PM - 3AM', 'April 28, 2018', '05-09-2018', 'Popworld goes all Superhero on you. Join us for a massive weekend celebrating the hero in you! Whether you are Marvel or DC or anything in between. Free Superhero Masks throughout the weekend.', '9,16', '', 'https://image.ibb.co/gn7Rbx/superhero_csotumes2.jpg', 5),
(67, 'Drift Southsea', '4PM - 2:30AM', 'Aug 31, 2018', '04-09-2018', 'Drift Bar in Southsea, the home of award winning food and freshly homemade cocktails at Palmerston Road''s biggest and best beer garden.', '11,17', '', 'https://preview.ibb.co/gdS53c/671_v7jid3nryvhhboah.jpg', 11),
(69, 'The Pound Party', '12PM - 3AM', 'Aug 31, 2018', '04-09-2018', 'Portsmouth''s most outrageously CHEAP night! A mash-up of r''n''b, grime, drum & bass, house and chart!', '13,16', '', 'https://preview.ibb.co/b2Re1n/va.jpg', 1),
(70, 'Ohlala', '10PM - 4AM', 'Aug 31, 2018', '04-09-2018', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.', '13,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 0),
(71, 'Enclosure', '8PM - 2AM', 'Sept 01, 2018', '01-09-2018', 'Join us for our BRAND NEW Saturday nights... ENCLOSURE!Tiger offering 4 rooms with 4 different DJ''s to keep you going until 3am!Jewel Bar - The jewel in our Crown with elegant surroundings playing a mixture of current music and R''n''B. ', '5,16', '', 'https://cdn2.fatsoma.com/media/W1siZiIsInB1YmxpYy8yMDE4LzQvOS8xMC8zNC8zMS83NjMvRW5jbG9zdXJlLmpwZyJdLFsicCIsInRodW1iIiwiNjYweDI0NCMiXV0 ', 3),
(72, 'Popworld Saturdays', '8PM - 4AM', 'Sept 01, 2018', '01-09-2018', 'Buzzing bar and club with pop music from the 1990s to the present day, plus cocktails and theme nights.', '8,16', '', 'https://image.ibb.co/etDYmx/popworld.jpg', 5),
(73, 'The Liquorist', '9AM - 1:30AM', 'Sept 01, 2018', '01-09-2018', 'Our long awaited refurbishment has finally arrived, our location stays the same but, we’ve raised the bar, not only on our fantastic new food but, our outstanding cocktails also.', '11,17', '', 'https://image.ibb.co/eaZLGx/28872929_151422758859838_4243261946436696977_n.jpg', 7),
(74, 'Delight', '10PM - 2AM', 'Sept 01, 2018', '01-09-2018', 'The club night for Rock/Indie/Punk/Alternative music fans. Nonstop anthems, festival party jams & the best value booze in the city!', '5,16', '', 'https://preview.ibb.co/jTeJ6x/1_1512818436_8300.jpg', 0),
(75, 'Brewhouse and Kitchen', '11AM - 10:30PM', 'Sept 02, 2018', '02-09-2018', 'Historic Tudor-style inn reborn as a stylish, colourful brewpub with an extensive dawn-to-dark menu.', '11,17', '', 'https://preview.ibb.co/dD1mPH/brewbourne0417.jpg', 8),
(76, 'Slug and Lettuce', '10AM - 10PM', 'Sept 02, 2018', '02-09-2018', 'Smart chain pub with a cocktail list, a classic British menu and uncluttered contemporary decor.', '11,17', '', 'https://preview.ibb.co/iAFP9H/C_h_Bct4_Ws_AYP3qv.jpg', 10),
(77, 'One Eyed Dog', '3PM - 1:30AM', 'Sept 02, 2018', '02-09-2018', 'Great local Djs and very reasonably priced drinks with a good selection of spirits. Fun, cosy atmosphere during the day with free pool.', '11,17', '', 'https://preview.ibb.co/fgL7EH/18715661033_ef67ef607e_k_1.jpg', 13),
(78, 'Drift Southsea', '4PM - 2:30AM', 'Sept 02, 2018', '02-09-2018', 'Drift Bar in Southsea, the home of award winning food and freshly homemade cocktails at Palmerston Road''s biggest and best beer garden.', '11,17', '', 'https://preview.ibb.co/gdS53c/671_v7jid3nryvhhboah.jpg', 11),
(79, 'Quids In!', '8PM - 2AM', 'Sept 03, 2018', '03-09-2018', 'Quids In Returns!! Portsmouth''s BIGGEST Monday night is back! £1 entry including 2-4-1 wristband', '5,16', '', 'https://cdn2.fatsoma.com/media/W1siZiIsInB1YmxpYy8yMDE4LzQvMjAvMTMvNDUvMzUvNTY0L3F1aWRzTkVXLmpwZyJdLFsicCIsInRodW1iIiwiNjYweDI0NCMiXV0', 3),
(80, 'Eskimo Project', '10PM - 4AM', 'Sept 03, 2018', '03-09-2018', 'Providing Portsmouth Students with the best club nights in town, in association with Portsmouth Students Union. Previous guests;', '13,16', '', 'https://preview.ibb.co/jXcV7S/TAG_Magazine_UK_Whats_On_Guide_Eskimo_Project.jpg', 1),
(81, 'The Liquorist', '9AM - 1:30AM', 'Aug 31, 2018', '04-09-2018', 'Our long awaited refurbishment has finally arrived, our location stays the same but, we’ve raised the bar, not only on our fantastic new food but, our outstanding cocktails also.', '11,17', '', 'https://image.ibb.co/eaZLGx/28872929_151422758859838_4243261946436696977_n.jpg', 7),
(82, 'HKC Vodka Bar', '7PM - 11PM', 'Sept 03, 2018', '03-09-2018', 'HKC Vodka Bar is the quintessential vodka bar. An intoxicating mix of delicious drinks and sultry surroundings make HKC a destination venue for the stylish.', '3,17', '', 'https://image.ibb.co/gwJypH/hong_kong_charlie_s_vodka.jpg', 14),
(83, 'EPIK!', '10PM - 4AM', 'Sept 03, 2018', '03-09-2018', 'Allow The Astoria to take you away from standard weekend clubbing and into our all new Carnival.', '13,16', '', 'https://preview.ibb.co/jvQBOc/c411b9bb1db8445759601aa8ae4d64aef.jpg', 0),
(84, 'Enclosure', '8PM - 2AM', 'May 02, 2018', '02-05-2018', 'Join us for our BRAND NEW Saturday nights... ENCLOSURE!Tiger offering 4 rooms with 4 different DJ''s to keep you going until 3am!Jewel Bar - The jewel in our Crown with elegant surroundings playing a mixture of current music and R''n''B. ', '5,16', '', 'https://cdn2.fatsoma.com/media/W1siZiIsInB1YmxpYy8yMDE4LzQvOS8xMC8zNC8zMS83NjMvRW5jbG9zdXJlLmpwZyJdLFsicCIsInRodW1iIiwiNjYweDI0NCMiXV0 ', 3),
(85, 'The Liquorist', '9AM - 1:30AM', 'May 02, 2018', '02-05-2018', 'Our long awaited refurbishment has finally arrived, our location stays the same but, we’ve raised the bar, not only on our fantastic new food but, our outstanding cocktails also.', '11,17', '', 'https://image.ibb.co/eaZLGx/28872929_151422758859838_4243261946436696977_n.jpg', 7),
(86, 'Brewhouse and Kitchen', '11AM - 10:30PM', 'May 02, 2018', '02-09-2018', 'Historic Tudor-style inn reborn as a stylish, colourful brewpub with an extensive dawn-to-dark menu.', '11,17', '', 'https://preview.ibb.co/dD1mPH/brewbourne0417.jpg', 8),
(87, 'HKC Vodka Bar', '7PM - 11PM', 'May 02, 2018', '02-05-2018', 'HKC Vodka Bar is the quintessential vodka bar. An intoxicating mix of delicious drinks and sultry surroundings make HKC a destination venue for the stylish.', '3,17', '', 'https://image.ibb.co/gwJypH/hong_kong_charlie_s_vodka.jpg', 14),
(88, 'Digital media dinner', '6PM - 3AM', 'May 02, 2018', '02-05-2018', 'Digital media get messy part 2, come along and eat, drink and celebrate good times', '11,16', '', 'https://image.ibb.co/bYuDKH/h_mcdonalds_Big_Mac_Extra_Value_Meals.png', 4),
(89, 'Dirty Disco', '9PM - 1AM', 'May 02, 2018', '02-05-2018', 'sidojf sjafsd sdf usadf sdfasdfa', '13,16', '', 'https://preview.ibb.co/dQakmn/552fa81f0d3c2202427f184cc8332747_rimg_w720_h273_gmir.jpg', 1);

-- --------------------------------------------------------

--
-- 表的结构 `outout_plan`
--

CREATE TABLE IF NOT EXISTS `outout_plan` (
  `id` int(11) NOT NULL,
  `uid` text NOT NULL,
  `eid_a` text NOT NULL,
  `date` text NOT NULL,
  `ts` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `outout_plan`
--

INSERT INTO `outout_plan` (`id`, `uid`, `eid_a`, `date`, `ts`) VALUES
(2, '1', '[9, 10, 11]', '17-04-2018', '1523975981'),
(3, '1', '[14, 16]', '18-04-2018', '1524006567'),
(4, '1', '[2, 3]', '19-04-2018', '1524146152'),
(5, '29', '[3, 2]', '19-04-2018', '1524169713'),
(6, '31', '[]', '20-04-2018', '1524233669'),
(7, '32', '[17, 19]', '20-04-2018', '1524234002'),
(8, '1', '[18, 17, 19, 1]', '20-04-2018', '1524234136'),
(9, '33', '[21, 23, 24]', '22-04-2018', '1524417012'),
(10, '1', '[23, 20, 24, 21]', '22-04-2018', '1524417106'),
(11, '1', '[26, 25]', '23-04-2018', '1524498759'),
(12, '34', '[29]', '25-03-2018', '1524603163'),
(13, '1', '[33, 34]', '25-04-2018', '1524624529'),
(14, '30', '[33, 36]', '25-04-2018', '1524647247'),
(16, '30', '[41, 42, 43]', '27-04-2018', '1524757195'),
(17, '1', '[37, 38, 39]', '26-04-2018', '1524768176'),
(18, '42', '[38, 37]', '26-04-2018', '1524770708'),
(19, '34', '[62, 63, 44, 43, 42, 41]', '27-04-2018', '1524825744'),
(20, '44', '[45, 47, 64, 46]', '28-04-2018', '1524829898'),
(21, '44', '[50]', '29-04-2018', '1524830117'),
(22, '45', '[49, 50, 51, 52]', '29-04-2018', '1524836991'),
(23, '44', '[41, 42, 62, 63]', '27-04-2018', '1524837150'),
(24, '44', '[53, 54, 56]', '30-04-2018', '1524911159'),
(25, '48', '[46, 48, 64]', '28-04-2018', '1524930365'),
(26, '44', '[70, 67, 69]', '31-08-2018', '1525160592'),
(27, '43', '[58, 57]', '01-05-2018', '1525181842'),
(28, '43', '[84, 87, 85]', '02-05-2018', '1525181914'),
(29, '1', '[57, 58]', '01-05-2018', '1525182713'),
(30, '1', '[87, 85, 84]', '02-05-2018', '1525182783'),
(31, '50', '[59, 58]', '01-05-2018', '1525186092'),
(32, '44', '[71, 74]', '01-09-2018', '1525249390'),
(33, '34', '[67, 69, 70]', '31-08-2018', '1525554995'),
(34, '51', '[67, 81]', '31-08-2018', '1526900751'),
(35, '34', '[74, 73, 72, 71]', '01-09-2018', '1528234928'),
(36, '34', '[75, 76, 77, 78]', '02-09-2018', '1528235483');

-- --------------------------------------------------------

--
-- 表的结构 `outout_plan_attends`
--

CREATE TABLE IF NOT EXISTS `outout_plan_attends` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `topid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `outout_uc_tags`
--

CREATE TABLE IF NOT EXISTS `outout_uc_tags` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `icon` text NOT NULL,
  `onpage` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `outout_uc_tags`
--

INSERT INTO `outout_uc_tags` (`id`, `name`, `icon`, `onpage`) VALUES
(2, 'Classical', 'https://image.ibb.co/dp6tgx/classical_icon_grey.png', 1),
(3, 'Decades', 'https://image.ibb.co/hLwtgx/decades_icon_grey.png', 1),
(4, 'Electronic', 'https://image.ibb.co/fBLYyc/electronic_icon_grey.png', 1),
(5, 'Hip hop', 'https://image.ibb.co/ivvNrx/hip_hop_icon_grey.png', 1),
(6, 'Jazz', 'https://image.ibb.co/dYMvBx/jazz_icon_grey.png', 1),
(7, 'Opera', 'https://image.ibb.co/hp88Wx/opera_icon_grey.png', 1),
(8, 'Pop', 'https://image.ibb.co/npHP4H/pop_icon_grey.png', 1),
(9, 'R&B', 'https://image.ibb.co/e09FBx/randb_icon_grey.png', 1),
(10, 'Rock', 'https://image.ibb.co/mMjRJc/rock_icon_grey.png', 1),
(11, 'Bar', 'https://image.ibb.co/cydOgx/bar_icon_grey.png', 2),
(12, 'Concert hall', 'https://image.ibb.co/kWHMZH/concert_hall_icon_grey.png', 2),
(13, 'Night club', 'https://image.ibb.co/e8C7PH/nightclub_icon_grey.png', 2),
(14, 'Theatre', 'https://image.ibb.co/mjTWjH/theatre_icon_grey.png', 2),
(15, 'Free', 'https://image.ibb.co/cRBqdc/free_icon_grey.png', 3),
(16, '£1-£5', 'https://image.ibb.co/jnk5Bx/one_to_five_icon_grey.png', 3),
(17, '£6-£10', 'https://image.ibb.co/nsOaBx/six_to_eleven_icon_grey.png', 3),
(18, '£11+', 'https://image.ibb.co/ffhP4H/eleven_icon_grey.png', 3);

-- --------------------------------------------------------

--
-- 表的结构 `outout_user`
--

CREATE TABLE IF NOT EXISTS `outout_user` (
  `id` int(11) NOT NULL,
  `username` text CHARACTER SET utf8 NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `psw` text CHARACTER SET utf8 NOT NULL,
  `tags_a` text,
  `ts` text CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `outout_user`
--

INSERT INTO `outout_user` (`id`, `username`, `email`, `psw`, `tags_a`, `ts`) VALUES
(1, 'Daniel Lowe', 'a@m.com', 'e10adc3949ba59abbe56e057f20f883e', '[4, 3, 6, 10, 7, 12, 13, 14, 15, 16]', '1523574172'),
(27, 'llp', 'llp@m.com', 'c8837b23ff8aaa8a2dde915473ce0991', '[10]', '1524053738'),
(28, 'cmpsdf', 'cmp@m.com', 'c8837b23ff8aaa8a2dde915473ce0991', '[10, 5]', '1524063390'),
(29, 'jeff', 'jeff@m.com', 'c8837b23ff8aaa8a2dde915473ce0991', '[4, 3, 7, 10, 13, 12, 15, 18, 17]', '1524166967'),
(30, 'Tineshia', 'tineshiajohn893@gmail.com', '805aeef288ff1c62d050196f5a06e41b', '[9, 7, 12, 14, 18]', '1524233462'),
(31, 'dan', 'dan@dan.com', '9180b4da3f0c7e80975fad685f7f134e', '[6, 7, 10, 9, 8, 5, 2, 3, 4, 14, 11, 12, 13, 18, 17, 15, 16]', '1524233593'),
(32, 'ray', 'ray@mail.com', '5d41402abc4b2a76b9719d911017c592', '[9, 8, 5, 16]', '1524233737'),
(33, 'Tineshia', 'tishjohn20@gmail.com', '805aeef288ff1c62d050196f5a06e41b', '[11, 9, 5, 8, 13, 15, 16]', '1524332340'),
(34, 'Tineshia :)', 'cupcakechroniclestj@gmail.com', '202cb962ac59075b964b07152d234b70', '[12, 9, 6, 5, 15, 18]', '1524498272'),
(35, 'tineshia', 'tineshia@cc.com', 'c20ad4d76fe97759aa27a0c99bff6710', '[8, 13, 17]', '1524565449'),
(36, 'myname', 'mn@m.com', 'c8837b23ff8aaa8a2dde915473ce0991', NULL, '1524667252'),
(37, 'jeffww', 'jeww@c.com', 'c8837b23ff8aaa8a2dde915473ce0991', '[7, 6, 9, 10, 13, 15]', '1524667300'),
(38, 'oewhf', 'ofwe@a.com', 'c8837b23ff8aaa8a2dde915473ce0991', '[7, 13, 15, 16]', '1524761918'),
(39, 'dan', 'dan@lowe.com', '4ba715503ca0b64f5d52d816dcae75e0', '[15, 4, 7, 10, 12]', '1524768847'),
(40, 'abc', 'abc@abc.com', 'e99a18c428cb38d5f260853678922e03', '[7, 12, 15]', '1524769504'),
(41, 'fgakbvd', 'gajd@hdbkxbe.civs', 'e10adc3949ba59abbe56e057f20f883e', '[10, 9, 7, 13, 12, 16, 15]', '1524770007'),
(42, 'fbsvf', 'fhg@sfd.con', '980ac217c6b51e7dc41040bec1edfec8', '[9, 7, 14, 15]', '1524770613'),
(43, 'Jeff Wu', 'isjeffcom@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '[3, 4, 7, 9, 10, 12, 13, 11, 15, 16]', '1524826769'),
(44, 'Mark', 'markwheeler26@yahoo.co.UK', '2ac9cb7dc02b3c0083eb70898e549b63', '[3, 5, 4, 11, 13, 15]', '1524829775'),
(45, 'Kris Searle', 'green-phoenix@hotmail.co.uk', 'fb77389901db560684cf86f5bfbb62f3', '[2, 3, 4, 5, 6, 7, 8, 10, 9, 11, 13, 12, 14, 15, 16, 17, 18]', '1524836828'),
(46, 'Valeska', 'valeska_g@hotmail.com', 'c4fd4c8cd32d463bc91891de6d325699', '[10, 6, 3, 8, 2, 11, 14, 12, 15, 16, 17]', '1524837399'),
(47, 'tia', 'tia_bea.co.uk@hotmail.com', '89358239647be5336ad984dfa8a81326', '[5, 12, 15]', '1524925942'),
(48, 'Tim Browne', 'timothybrowne@hotmail.com', '1692cf357d242eae5265512a5e88c9f3', '[5, 8, 9, 3, 11, 15, 16, 17]', '1524930269'),
(49, 'dubinbin', '734854291@qq.com', '6234b53ad37ba5cfc0fa9d04ffb25fd0', '[2, 11, 15]', '1525016271'),
(50, 'hentie', 'hentie.devries@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', '[10, 5, 8, 11, 14, 12, 17, 16]', '1525185947'),
(51, 'carl', 'carl@propartners.co.za', 'e10adc3949ba59abbe56e057f20f883e', '[6, 2, 9, 11, 12, 14, 15, 16, 17, 18]', '1526730328'),
(52, 'vis', 'vis@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', NULL, '1535458785'),
(53, 'vis', 'viss@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '[6, 4, 2, 12, 15]', '1535458801'),
(54, 'abc', 'flname1010@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '[9, 10, 8, 5, 7, 6, 2, 3, 14, 17]', '1535706753');

-- --------------------------------------------------------

--
-- 表的结构 `outout_venue`
--

CREATE TABLE IF NOT EXISTS `outout_venue` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `gl_la` text NOT NULL,
  `gl_lo` text NOT NULL,
  `address` text NOT NULL,
  `des` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `outout_venue`
--

INSERT INTO `outout_venue` (`id`, `name`, `gl_la`, `gl_lo`, `address`, `des`) VALUES
(0, 'The Astoria', '50.795903', '-1.093375', '37-39 Guildhall Walk, PO1 2RY', 'Nightclub with VIP balcony luring party crowds and students to house anthems and alternative sounds.'),
(1, 'PRYZM', '50.799147', '-1.091867', 'Connaught Drill Hall, Stanhope Rd, PO1 1DU', 'Chain nightclub with high-tech sound and lights and a regular lineup of themed dance music events.'),
(2, 'Lyberry', '50.796022', '-1.093179', '29-33 Guildhall Walk, PO1 2RY', 'Great cocktails on offer every night 2 for 1, the perfect place for classic bar games in a casual atmosphere. '),
(3, 'Tiger Tiger', '50.795198', '-1.107225', 'Gunwharf Quays, Gunwharf Rd, PO1 3TP', 'One stop nightspot with tiki bar, disco room, harbour-side terrace and modern, eclectic restaurant.'),
(4, 'McDonalds', '50.802788', '-1.087813', '219/223 Commercial Rd, Portsmouth PO1 4BJ', 'Classic, long-running fast-food chain known for its burgers, fries & shakes.'),
(5, 'Popworld', '50.797320', '-1.093542', '1 King Henry I St, Portsmouth PO1 2PT', 'Buzzing bar and club with pop music from the 1990s to the present day, plus cocktails and theme nights.\n\n'),
(7, 'The Liquorist', '50.795307', '-1.105288', 'R1 Boulevard Building, Portsmouth PO1 3TD', 'Our long awaited refurbishment has finally arrived, our location stays the same but, we’ve raised the bar, not only on our fantastic new food but, our outstanding cocktails also.'),
(8, 'Brewhouse and Kitchen', '50.796264', '-1.093198', '26 Guildhall Walk, Portsmouth PO1 2DD', 'Historic Tudor-style inn reborn as a stylish, colourful brewpub with an extensive dawn-to-dark menu.'),
(9, 'Waterhole Bar ', '50.794391', '-1.096880', 'Student Centre, Cambridge Road, Portsmouth PO1 2EF', 'The Waterhole has a good vibe, cheap food & a decent juke box - what more could a student want? It''s a cool place to be when watching matches'),
(10, 'Slug and Lettuce', '50.795325', '-1.107318', 'R07, 79 Gunwharf Rd, Portsmouth PO1 3TR', 'Smart chain pub with a cocktail list, a classic British menu and uncluttered contemporary decor.'),
(11, 'Drift Southsea', '50.784445', '-1.088896', '78 Palmerston Rd, Portsmouth, Southsea PO5 3PT', 'Drift Bar in Southsea, the home of award winning food and freshly homemade cocktails at Palmerston Road''s biggest and best beer garden.'),
(12, 'Huis Belgian Bar', '50.790081', '-1.087548', '62 Elm Grove, Portsmouth, Southsea PO5 1JG', 'An independent bar in Southsea, Portsmouth, bringing Belgian beer and food culture together. Our beer range is extensive and ever changing with over 60 beers on offer. Our draughts include our own exclusive HUIS wheat, pilsner and blonde beers.'),
(13, 'One Eyed Dog', '50.789338', '-1.082785', '177-185 Elm Grove, Portsmouth, Southsea PO5 1LU', 'Great local Djs and very reasonably priced drinks with a good selection of spirits. Fun, cosy atmosphere during the day with free pool.'),
(14, 'HKC Vodka Bar\n', '50.784328', '-1.088818', '75 Palmerston Rd, Portsmouth PO5 3PP', 'HKC Vodka Bar is the quintessential vodka bar. An intoxicating mix of delicious drinks and sultry surroundings make HKC a destination venue for the stylish.'),
(15, 'Kings Theatre\r\n', '50.787295', '-1.081368', '24 Albert Rd, Portsmouth, Southsea PO5 2QJ', 'Restored Edwardian theatre with a 1500-seat auditorium, hosting popular drama, music and comedy.'),
(16, 'The Fat Fox', '50.788393', '-1.082336', '11-13 Victoria Rd S, Portsmouth PO5 2SP', 'The Fat Fox and Atrium offer a little bit of everything good. Live sport, live DJs, hot food, cold beer, and cocktails.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `outout_event`
--
ALTER TABLE `outout_event`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `outout_plan`
--
ALTER TABLE `outout_plan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `outout_plan_attends`
--
ALTER TABLE `outout_plan_attends`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `outout_uc_tags`
--
ALTER TABLE `outout_uc_tags`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `outout_user`
--
ALTER TABLE `outout_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `outout_venue`
--
ALTER TABLE `outout_venue`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `outout_event`
--
ALTER TABLE `outout_event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=90;
--
-- AUTO_INCREMENT for table `outout_plan`
--
ALTER TABLE `outout_plan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `outout_plan_attends`
--
ALTER TABLE `outout_plan_attends`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `outout_uc_tags`
--
ALTER TABLE `outout_uc_tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `outout_user`
--
ALTER TABLE `outout_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=55;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
