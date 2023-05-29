PRAGMA foreign_keys = ON;

CREATE TABLE "accounts" (
	"id" 	INTEGER PRIMARY KEY,
	"email"	TEXT NOT NULL UNIQUE COLLATE NOCASE,
	"phone_number"	TEXT UNIQUE,
	"username"	TEXT NOT NULL,
	"password"	TEXT NOT NULL,
	"created_at" INTEGER NOT NULL
);

CREATE TABLE "restaurants" (
	"id"	INTEGER PRIMARY KEY,
	"restaurant_name"	TEXT NOT NULL,
	"rating" 	DECIMAL(2,1) NOT NULL,
	"food_type"	TEXT NOT NULL,
	"delivery_time"	INTEGER NOT NULL,
	"discount_percentage" INTEGER
);

CREATE TABLE "accounts_restaurants_favourites"(
	"account_id"		INTEGER NOT NULL,
	"restaurant_id"		INTEGER NOT NULL,
	"is_favourite"		INTEGER NOT NULL,
	FOREIGN KEY("account_id") REFERENCES "accounts"("id")
		ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("restaurant_id") REFERENCES "restaurants"("id")
		ON UPDATE CASCADE ON DELETE CASCADE,
	UNIQUE("account_id", "restaurant_id")
);

INSERT INTO "accounts" ("email", "username", "password",
	"created_at")
VALUES
	("admin@google.com", "admin", "123", 0),
	("tester@google.com", "tester", "321", 0);

INSERT INTO "restaurants" ("restaurant_name", "rating", "food_type",
	"delivery_time", "discount_percentage")
VALUES
	("Conrad food", 4.6, "Pizza", 15, 50),
	("SK Restro", 4.4, "Chinese", 30, 40),
	("Black Fish",4.3, "Seafood", 20, 40),
	("Goichi Oniko", 4.0, "Fastfood", 20, 50),
	("The burger paradise", 4.9,"Fastfood", 15, 20);
