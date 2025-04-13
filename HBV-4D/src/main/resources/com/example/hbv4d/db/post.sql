INSERT INTO Users (name, email, phoneNumber) VALUES ('Alla', 'alla@hi.is', '1234567')
INSERT INTO Users (name, email, phoneNumber) VALUES ('Maria', 'maria@hi.is', '7654321')

INSERT INTO Tours (id, tourName, description, price, date, city, schedule, availability)
VALUES
(1, 'Scenic Mountain Hike', 'A beautiful hike through the mountains.', 9000, '2025-05-20', 'Reykjavik', '10:00 AM', 20),
(2, 'Northern Lights Tour', 'Experience the magic of the Aurora Borealis.', 18000, '2025-12-15', 'Akureyri', '9:00 PM', 10),
(3, 'Golden Circle Tour', 'See geysers, waterfalls, and historic sites.', 35000, '2025-07-10', 'Thingvellir', '8:00 AM', 30),
(4, 'Whale Watching Adventure', 'Embark on a boat tour to see whales, dolphins, and seabirds.', 15000, '2025-05-20', 'Reykjavik', '1:00 PM', 25),
(5, 'Ice Cave Exploration', 'Explore the stunning ice caves and learn about glacial formations.', 25000, '2025-06-15', 'Hofn', '9:00 AM', 15),
(6, 'Volcanic Crater Walk', 'Hike around a volcanic crater and learn about Icelands geothermal activity.', 12000, '2025-07-05', 'Reykjavik', '11:00 AM', 20),
(7, 'Puffin Watching Boat Tour', 'Take a boat ride to see Icelands famous puffins up close.', 10000, '2025-05-20', 'Vestmannaeyjar', '3:00 PM', 30),
(8, 'Blue Lagoon Spa Retreat', 'Relax in the geothermal waters of the Blue Lagoon.', 28000, '2025-08-10', 'Grindavik', '5:00 PM', 20),
(9, 'Reykjavik Food & Culture Walk', 'A guided walking tour featuring Icelandic cuisine and cultural landmarks.', 14000, '2025-06-20', 'Reykjavik', '2:00 PM', 18),
(10, 'Lava Tunnel Exploration', 'Walk through an ancient lava tube cave and witness unique rock formations.', 20000, '2025-07-18', 'Hella', '10:00 AM', 12),
(11, 'Glacier Lagoon Kayaking', 'Kayak through the breathtaking Jokulsarlon Glacier Lagoon.', 30000, '2025-06-30', 'Jokulsarlon', '8:00 AM', 12),
(12, 'Horseback Riding on Black Sand Beaches', 'Ride Icelandic horses along the scenic black sand beaches.', 18000, '2025-05-20', 'Vik', '4:00 PM', 16),
(13, 'Snaefellsnes Peninsula Exploration', 'Discover volcanoes, glaciers, and coastal beauty in Snaefellsnes.', 32000, '2025-07-25', 'Borgarnes', '7:30 AM', 22),
(14, 'Inside the Volcano Tour', 'Descend into the magma chamber of a dormant volcano.', 42000, '2025-06-05', 'Reykjavik', '9:00 AM', 10),
(15, 'Midnight Sun Photography Tour', 'Capture the golden glow of the midnight sun in Iceland.', 20000, '2025-06-21', 'Akureyri', '11:00 PM', 8);

INSERT INTO Reviews (id, userId, firstName, tourId, rating, comment)
VALUES
(1, 101, 'Embla', 1, 5, 'Amazing experience, loved every moment!'),
(2, 102, 'Guðný', 1, 4, 'Great views, but the hike was a bit tough.'),
(3, 103, 'Guðrún', 2, 5, 'Northern Lights were breathtaking!'),
(4, 104, 'Valbjörn', 3, 3, 'The tour was okay, but a bit overpriced.'),
(5, 105, 'Ben', 2, 5, 'Absolutely mesmerizing! The Northern Lights were spectacular.'),
(6, 106, 'Elizabeth', 3, 4, 'Loved the Golden Circle Tour, but wish we had more time at some stops.'),
(7, 107, 'Halldór', 4, 5, 'The whale watching tour was unforgettable! Saw so many whales up close.'),
(8, 108, 'Stefany', 4, 4, 'Great tour, but it was a bit windy on the boat. Bring warm clothes!'),
(9, 109, 'Aaron', 5, 5, 'The ice caves were stunning! A must-do experience in Iceland.'),
(10, 110, 'Thomas', 6, 4, 'The volcanic crater walk was interesting, but I expected more challenging trails.'),
(11, 111, 'John', 7, 5, 'Puffins everywhere! Such a cute and fun experience.'),
(12, 112, 'Emma', 8, 5, 'Blue Lagoon was so relaxing! Perfect way to unwind.'),
(13, 113, 'Charlotte', 9, 5, 'The food tour was delicious! Got to try so many Icelandic specialties.'),
(14, 114, 'Rose', 10, 5, 'Exploring the lava tunnel was fascinating. Our guide was super informative.'),
(15, 115, 'William', 11, 5, 'Kayaking in the glacier lagoon was a once-in-a-lifetime experience!'),
(16, 116, 'Amelia', 12, 4, 'Horseback riding on the black sand beach was stunning, but it was very windy.'),
(17, 117, 'Eiríkur', 13, 5, 'Snaefellsnes Peninsula is an absolute gem! So much beauty in one day.'),
(18, 118, 'Charles', 14, 5, 'Going inside the volcano was mind-blowing. Never thought I’d see something like this.'),
(19, 119, 'Noah', 15, 5, 'Midnight sun tour was magical! Perfect for photographers.'),
(20, 120, 'Lucas', 15, 4, 'A great experience, but make sure to bring extra layers for the cold night.');


