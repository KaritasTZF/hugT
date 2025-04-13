CREATE TABLE IF NOT EXISTS Users (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    phoneNumber TEXT NOT NULL,
);

CREATE TABLE IF NOT EXISTS Tours (
    id INTEGER PRIMARY KEY,
    tourName TEXT ,
    description TEXT ,
    price INT,
    date TEXT,
    city TEXT,
    schedule TEXT,
    availability int
);

CREATE TABLE IF NOT EXISTS Reviews (
    id INTEGER PRIMARY KEY,
    userId INTEGER REFERENCES Users(id) ON DELETE CASCADE,
    firstName TEXT REFERENCES Users(name) ON DELETE CASCADE,
    tourId INT REFERENCES Tours(id) ON DELETE CASCADE,
    rating INTEGER,
    comment TEXT
);

CREATE TABLE IF NOT EXISTS Bookings (
    userId INTEGER REFERENCES Users(id) ON DELETE CASCADE,
    tourId INTEGER REFERENCES Tours(id) ON DELETE CASCADE,
    firstName TEXT REFERENCES Users(name) ON DELETE CASCADE,
    email TEXT REFERENCES Users(email) ON DELETE CASCADE
);
