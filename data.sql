-- run this to aim to this specific schema
USE `spacecraft_mission_monitoring_system`;

INSERT INTO Employee (employeeID, name, role, workEmail, phoneNumber, location) VALUES
(1, 'Sonia Pandey', 'Mission Controller', 'spandey@nasa.org', '555-1234', '77058'),
(2, 'Julianna Arzola', 'Flight Director', 'jarzola@nasa.org', '555-2345', '32920'),
(3, 'Joshua Boucher', 'Spacecraft Crew', 'jboucher@nasa.org', '555-3456', '91125'),
(4, 'Nicholas Magtangob', 'Flight Director', 'nmagtangob@nasa.org', '555-4567', '77058'),
(5, 'Thong Nguyen', 'Spacecraft Crew', 'tnguyen@nasa.org', '555-5678', '94043');

-- you have to run this first before running INSERT for data mission (FK constraints) 
-- authorityLevel does not really mean that one employee role have more power than the others, more like each level will have different responsibilities
-- level 1: Mission Controller -> Enter mission info, schedule a future or immediate manuever, terminate mission
-- level 2: Flight Director -> schedule a future or immediate manuever, 
INSERT INTO MissionController (employeeID, authorityLevel) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 3),
(5, 2);

-- for testing purpose: use TRUNCATE table will set the auto_increment back to 1 again, using DELETE only deletes data
-- disable FK check mode
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE mission;
-- enable FK check mode
SET FOREIGN_KEY_CHECKS = 1;

-- no need to manually insert missionID
INSERT INTO Mission ( employeeID, missionName, missionType, launchDate, missionStatus, missionObjective, initialFuelLevel, initialLocation, terminationDate) VALUES
( 1, 'Voyager 1', 'Interstellar Probe', '1977-09-05', 'Completed', 'Explore outer planets and interstellar space.', 10000, 'Cape Canaveral', '1989-11-30'),
( 1, 'Voyager 2', 'Interstellar Probe', '1977-08-20', 'Completed', 'Explore all outer planets including Uranus and Neptune.', 10500, 'Cape Canaveral', '1990-08-25'),
( 2, 'Curiosity', 'Mars Rover', '2011-11-26', 'Active', 'Analyze Martian climate and geology.', 8000, 'Cape Canaveral', NULL),
( 2, 'Perseverance', 'Mars Rover', '2020-07-30', 'Active', 'Search for signs of ancient life on Mars.', 8500, 'Cape Canaveral', NULL),
( 3, 'Juno', 'Jupiter Orbiter', '2011-08-05', 'Active', 'Study Jupiter’s atmosphere, magnetic field, and gravity.', 9000, 'Cape Canaveral', NULL),
( 3, 'Artemis I', 'Lunar Orbiter', '2022-11-16', 'Completed', 'Test NASA’s new launch system and Orion spacecraft.', 7000, 'Kennedy Space Center', '2022-12-11'),
( 4, 'New Horizons', 'Pluto Flyby', '2006-01-19', 'Completed', 'Explore Pluto and the Kuiper Belt.', 9200, 'Cape Canaveral', '2015-07-14'),
( 4, 'Parker Solar Probe', 'Solar Mission', '2018-08-12', 'Active', 'Study the outer corona of the sun.', 9700, 'Cape Canaveral', NULL),
( 5, 'James Webb', 'Space Telescope', '2021-12-25', 'Active', 'Observe distant galaxies and exoplanets.', 11000, 'Guiana Space Centre', NULL),
( 5, 'OSIRIS-REx', 'Asteroid Sample Return', '2016-09-08', 'Completed', 'Collect samples from asteroid Bennu and return them to Earth.', 8700, 'Cape Canaveral', '2023-09-24');



-- for flight director
INSERT INTO flightdirector (employeeID, yearsOfExperience) VALUES
(1, 5),
(2,7),
(4,8);

-- for spacecraftcrew
INSERT INTO spacecraftcrew (crewID, missionID, crewSize, commander, crewHealthStatus) VALUES
(1, 1, 5, 'Lena Park', 'Excellent'),
(2, 2, 3, 'Dmitri Ivanov', 'Good'),
(3, 3, 4, 'Mei Tanaka', 'Fair'),
(4, 4, 2, 'Alejandro Torres', 'Excellent');


-- issue
INSERT INTO issue (issueID, missionID, issueType, detectionTime, severityLevel, alertTriggered, resolutionStatus)
VALUES
(1, 1, 'Low Fuel', '1989-11-01 10:20 PST', 5, 1, 'Unresolved'),
(2, 2, 'Failed Maneuver', '1990-06-15 14:45 PST', 4, 1, 'Resolved'),
(3, 3, 'Data Transmission Error', '2013-03-22 12:00 PST', 3, 0, 'Resolved'),
(4, 4, 'Navigation Glitch', '2021-11-01 08:15 PST', 2, 0, 'Under Investigation'),
(5, 5, 'Power Fluctuation', '2013-07-08 09:40 PST', 3, 0, 'Resolved'),
(6, 6, 'Heat Shield Damage', '2022-12-01 16:10 PST', 4, 1, 'Unresolved'),
(7, 7, 'Radiation Spike', '2016-04-09 07:30 PST', 3, 1, 'Resolved'),
(8, 8, 'Sensor Calibration Failure', '2022-01-05 18:20 PST', 2, 0, 'Resolved'),
(9, 9, 'Thruster Misfire', '2023-05-14 11:50 PST', 4, 1, 'Resolved'),
(10, 10, 'Telemetry Loss', '2023-10-02 14:05 PST', 3, 1, 'Unresolved');



