-- run this to aim to this specific schema
USE `spacecraft_mission_monitoring_system`;

INSERT INTO employee (employeeID, name, username, password, workEmail, phoneNumber, location) VALUES
(1, 'Sonia Pandey','sonia@missioncontroller', '1234', 'spandey@nasa.org','123-456-7890', 77058),
(2, 'Julianna Arzola','julianna@flightdirector','3456', 'jarzola@nasa.org','123-456-7890', 32920),
(3, 'Joshua Boucher','joshuab@crewcommander', '1234', 'jboucher@nasa.org','123-456-7890', 91125),
(4, 'Nicholas Magtangob','nicolas@crewmember','3456', 'nmagtangob@nasa.org', '123-456-7890', 77058),
(5, 'Thong Nguyen', 'thongn@crewmember', '1234', 'tnguyen@nasa.org', '123-456-7890', 94043),
-- add some more examples:
(6, 'Emily Zhang', 'emily@missioncontroller', '1234', 'ezhang@nasa.org', '123-456-7890', 77058),
(7, 'Robert Davis','robert@missioncontroller','3456', 'rdavis@nasa.org', '123-456-7890', 30303),
(8, 'Laura Bennett','laura@flightdirector', '1234', 'lbennett@nasa.org', '123-456-7890', 32920),
(9, 'Ethan Moore','ethan@flightdirector', '3456', 'emoore@nasa.org', '123-456-7890', 77058),
(10, 'Amy Chen', 'amy@crewcommander', '3456', 'achen@nasa.org',       '123-456-7890', 30301),
(11, 'Hannah Lee', 'hannah@crew', '1234', 'hlee@nasa.org', '123-456-7890', 10001),
(12, 'David Kim', 'david@crew', '3456', 'dkim@nasa.org', '123-456-7890', 60616),
(13, 'Isabella Rivera', 'isabella@crew','1234', 'irivera@nasa.org','123-456-7890', 75201),
(14, 'Marcus Wright',   'marcus@crew', '3456', 'mwright@nasa.org', '123-456-7890', 77001);



-- you have to run this first before running INSERT for data mission (FK constraints) 
-- authorityLevel does not really mean anything for now. According to our system, every mission controller regardless of authoritylevel has the same access to the system.
--  We just leave it there so it is similar to real life scenario
INSERT INTO MissionController (employeeID, authorityLevel) VALUES
(1, 3),
(6, 1),
(7, 4);

INSERT INTO FlightDirector (employeeID, yearsOfExperience) VALUES
(2, 8),
(8, 6),
(9, 9);

-- spacecraft
INSERT INTO spacecraft (spacecraftID, missionID, spacecraftName, spacecraftType, manufacturer, capacity, maxFuelCapacity, thrustPower, weight, status) VALUES
(101, NULL, 'Odyssey-X', 'Exploration', 'SpaceX', 6, 12000, 8500, 18000, 'Active'),
(102, NULL, 'Apollo Nova', 'Research Shuttle', 'NASA', 4, 9000, 6000, 15000, 'Standby'),
(103, NULL, 'Titan II', 'Cargo', 'Boeing', 0, 20000, 5000, 22000, 'In Maintenance'),
(104, NULL, 'Vanguard Scout', 'Recon Drone', 'Lockheed Martin', 0, 3000, 1200, 3000, 'Decommissioned'),
(105, NULL, 'Eagle One', 'Manned Shuttle', 'Blue Origin', 3, 11000, 7000, 16000, 'Active'),
(106, NULL, 'Orion Capsule', 'Manned Capsule', 'NASA', 4, 10000, 7500, 15000, 'Active'),
(107, NULL, 'Dragon XL', 'Cargo', 'SpaceX', 0, 12000, 6000, 14000, 'Standby'),
(108, NULL, 'Starliner', 'Crewed Capsule', 'Boeing', 7, 9500, 7000, 15500, 'Testing'),
(109, NULL, 'Cygnus', 'Cargo Module', 'Northrop Grumman', 0, 8000, 5000, 13000, 'Decommissioned'),
(110, NULL, 'Lunar Lander One', 'Lander', 'Blue Origin', 2, 4000, 2500, 6000, 'Active');


-- for testing purpose: use TRUNCATE table will set the auto_increment back to 1 again, using DELETE only deletes data
-- disable FK check mode
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE mission;
-- enable FK check mode
SET FOREIGN_KEY_CHECKS = 1;
-- no need manually insert missionID
INSERT INTO mission (employeeID, spacecraftID, missionName, missionType, launchDate, missionStatus, missionObjective, initialFuelLevel, initialLocation, terminationDate) VALUES
(1, NULL, 'Voyager 1', 'Interstellar Probe', '1977-09-05', 'Inactive', 'Explore outer planets and interstellar space.', 10000, 'Cape Canaveral', '1989-11-30'),
(6, NULL, 'Voyager 2', 'Interstellar Probe', '1977-08-20', 'Inactive', 'Explore all outer planets including Uranus and Neptune.', 10500, 'Cape Canaveral', '1990-08-25'),
(6, NULL, 'Artemis I', 'Lunar Orbiter', '2022-11-16', 'Inactive', 'Test NASA’s new launch system and Orion spacecraft.', 7000, 'Kennedy Space Center', '2022-12-11'),
(7, NULL, 'New Horizons', 'Pluto Flyby', '2006-01-19', 'Inactive', 'Explore Pluto and the Kuiper Belt.', 9200, 'Cape Canaveral', '2015-07-14'),
(1, NULL, 'OSIRIS-REx', 'Asteroid Sample Return', '2016-09-08', 'Inactive', 'Collect samples from asteroid Bennu and return them to Earth.', 8700, 'Cape Canaveral', '2023-09-24');


-- for spacecraftcrew
INSERT INTO spacecraftcrew (crewID, missionID, crewHealthStatus) VALUES
(1, 1, 'Excellent'),
(2, 2, 'Good');

INSERT INTO CrewCommander (employeeID, crewID) VALUES
(3, 1),
(10, 2);

INSERT INTO CrewMember (employeeID, crewID) VALUES
(4, 1),
(5, 2),
(11, 1),
(12, 1),
(13, 2),
(14, 1);

-- issue
INSERT INTO issue (issueID, missionID, issueType, detectionTime, severityLevel, alertTriggered, resolutionStatus)
VALUES
(1, 1, 'Low Fuel', '1989-11-01 10:20 PST', 5, 1, 'Unresolved'),
(2, 2, 'Failed Maneuver', '1990-06-15 14:45 PST', 4, 1, 'Resolved'),
(3, 2, 'Data Transmission Error', '2013-03-22 12:00 PST', 3, 0, 'Resolved'),
(4, 4, 'Navigation Glitch', '2021-11-01 08:15 PST', 2, 0, 'Under Investigation'),
(5, 3, 'Power Fluctuation', '2013-07-08 09:40 PST', 3, 0, 'Resolved'),
(6, 2, 'Heat Shield Damage', '2022-12-01 16:10 PST', 4, 1, 'Unresolved'),
(7, 1, 'Radiation Spike', '2016-04-09 07:30 PST', 3, 1, 'Resolved'),
(8, 4, 'Sensor Calibration Failure', '2022-01-05 18:20 PST', 2, 0, 'Resolved'),
(9, 2, 'Thruster Misfire', '2023-05-14 11:50 PST', 4, 1, 'Resolved'),
(10, 3, 'Telemetry Loss', '2023-10-02 14:05 PST', 3, 1, 'Unresolved');



