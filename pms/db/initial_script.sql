## Create Database

create DATABASE IF NOT EXISTS pms;

## Create Reservation table
CREATE TABLE `reservation` (
  `rez_guid` binary(16) NOT NULL,
  `profile_id` varchar(20) NOT NULL,
  `rez_no` varchar(20) NOT NULL,
  `rez_status` enum('ARRIVING','IN-HOUSE','CHECKED-OUT') NOT NULL DEFAULT 'ARRIVING',
  `room_no` varchar(10) DEFAULT NULL,
  `checked_in_at` datetime DEFAULT NULL,
  `checked_out_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`rez_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 

## Create Smart Door Lock Hist table
CREATE TABLE `smart_door_lock_hist` (
  `hist_guid` binary(16) NOT NULL,
  `door_lock_no` varchar(10) NOT NULL,
  `rez_no` varchar(20) NOT NULL,
  `room_no` varchar(10) NOT NULL,
  `req_id` varchar(100) NOT NULL,
  `req_time` datetime NOT NULL,
  PRIMARY KEY (`hist_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 

