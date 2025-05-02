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


-- for testing purpose, do NOT run this
INSERT INTO Mission (missionID, employeeID, missionName, missionType, launchDate, missionStatus, missionObjective, initialFuelLevel, initialLocation, terminationDate) VALUES
(1, 1, 'Voyager 1', 'Interstellar Probe', '1977-09-05', 'Completed', 'Explore outer planets and interstellar space.', 10000, 'Cape Canaveral', '1989-11-30'),
(2, 1, 'Voyager 2', 'Interstellar Probe', '1977-08-20', 'Completed', 'Explore all outer planets including Uranus and Neptune.', 10500, 'Cape Canaveral', '1990-08-25'),
(3, 2, 'Curiosity', 'Mars Rover', '2011-11-26', 'Active', 'Analyze Martian climate and geology.', 8000, 'Cape Canaveral', NULL),
(4, 2, 'Perseverance', 'Mars Rover', '2020-07-30', 'Active', 'Search for signs of ancient life on Mars.', 8500, 'Cape Canaveral', NULL),
(5, 3, 'Juno', 'Jupiter Orbiter', '2011-08-05', 'Active', 'Study Jupiter’s atmosphere, magnetic field, and gravity.', 9000, 'Cape Canaveral', NULL),
(6, 3, 'Artemis I', 'Lunar Orbiter', '2022-11-16', 'Completed', 'Test NASA’s new launch system and Orion spacecraft.', 7000, 'Kennedy Space Center', '2022-12-11'),
(7, 4, 'New Horizons', 'Pluto Flyby', '2006-01-19', 'Completed', 'Explore Pluto and the Kuiper Belt.', 9200, 'Cape Canaveral', '2015-07-14'),
(8, 4, 'Parker Solar Probe', 'Solar Mission', '2018-08-12', 'Active', 'Study the outer corona of the sun.', 9700, 'Cape Canaveral', NULL),
(9, 5, 'James Webb', 'Space Telescope', '2021-12-25', 'Active', 'Observe distant galaxies and exoplanets.', 11000, 'Guiana Space Centre', NULL),
(10, 5, 'OSIRIS-REx', 'Asteroid Sample Return', '2016-09-08', 'Completed', 'Collect samples from asteroid Bennu and return them to Earth.', 8700, 'Cape Canaveral', '2023-09-24');
 

