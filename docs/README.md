### ER-model – Touristguide

ER diagram

https://lucid.app/lucidchart/283ff079-1301-4caa-9c8b-2c2d4c173dca/edit?invitationId=inv_ad224aa9-c1b0-4cfa-aae0-a828aba43a1a&page=0_0#

## Beskrivelse
Databasen gemmer turistattraktioner. Hver attraktion ligger i én by og kan have flere tags (fx GRATIS, MUSEUM, BØRNEVENLIG). Databasen erstatter den hardcodede liste i `TouristRepository`.

## Tabeller

### ATTRACTION
| Kolonne | Datatype | Nøgle | Beskrivelse |
|---|---|---|---|
| attraction_id | INT | PK | Unikt id for attraktionen |
| attraction_name | VARCHAR(100) | | Navnet på attraktionen |
| description | VARCHAR(1000) | | Kort beskrivelse |
| city_id | INT | FK | Den by attraktionen ligger i |

### CITY
| Kolonne | Datatype | Nøgle | Beskrivelse |
|---|---|---|---|
| city_id | INT | PK | Unikt id for byen |
| city_name | VARCHAR(50) | | Navnet på byen |

### TAG
| Kolonne | Datatype | Nøgle | Beskrivelse |
|---|---|---|---|
| tag_id | INT | PK | Unikt id for tagget |
| tag_name | VARCHAR(50) | | Navnet på tagget |

### ATTRACTION_TAG
| Kolonne | Datatype | Nøgle | Beskrivelse |
|---|---|---|---|
| attraction_id | INT | PK, FK | Peger på ATTRACTION |
| tag_id | INT | PK, FK | Peger på TAG |

## Relationer
* Attraction har en mange-til-mange-relation til Tag. Det betyder, at én attraktion kan have 0 til mange tags, og ét tag kan høre til 0 til mange attraktioner. Fx har Tivoli både tagget børnevenlig og natur, og natur bruges også af Rosenborg og Nyhavn. Da en mange-til-mange-relation ikke kan laves direkte i databasen, bruger vi koblingstabellen ATTRACTION_TAG. Linjerne til ATTRACTION_TAG er fuldt optrukne, fordi relationen er identificerende: `attraction_id` og `tag_id` er både fremmednøgler og tilsammen primærnøgle (PK, FK).

* City har en en-til-mange-relation til Attraction. Én by kan have 0 til mange attraktioner, men hver attraktion ligger i præcis én by. Derfor har Attraction fremmednøglen `city_id`. Linjen er stiplet, fordi relationen er ikke-identificerende: `city_id` er kun en fremmednøgle og ikke en del af Attractions primærnøgle.

## Designvalg
* **Datatyper:** INT til id'er. VARCHAR med en passende længde til tekst – beskrivelser kan være lange, derfor 1000.
* **NOT NULL:** Navne og `city_id` skal altid have en værdi, da en attraktion ikke giver mening uden navn eller by.
* **Primærnøgler:** `attraction_id`, `city_id` og `tag_id` autogenereres (AUTO_INCREMENT). I ATTRACTION_TAG er primærnøglen kombinationen af de to fremmednøgler, så den autogenereres ikke.
* **Fremmednøgler:** `city_id` i ATTRACTION og begge kolonner i ATTRACTION_TAG sikrer, at man kun kan pege på byer, attraktioner og tags der findes.
* **Unikke indeks:** `city_name` og `tag_name` er unikke, så samme by eller tag ikke oprettes to gange. Kombinationen i ATTRACTION_TAG er automatisk unik, fordi den er primærnøgle.
