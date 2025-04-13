# Cluster 4 verkefni í Þróun hugbúnaðar HBV401G

This project was made for the HBV401G class of Háskóli Íslands, spring 2025. 
This is the project of group 4T but includes projects of teams 4F, 4D, and 4H.

The 4T team members are Karitas, Loftur, Einar and Brynjar

## To run this project: 
- Download this project from Github
- Install dependancies with maven

HBV-4T/src/main/java/project/ui/MainApp.java is the main to run for the T team's project. 
For the F/H/D team's ui, their respective folders have their main.
 - Some filepaths might be hardcoded. Please do not move the team's project folders outside of hugT or rename them.
 - The T team's project is in java version 21, but this may not be the same for all teams.

## Locations
There is little overlap in locations between teams. 
The only locations that are shared between all 3 of F/H/D are 
Akureyri, Reykjavík and Vestmannaeyjar. 

|                   | 4F                | 4H             | 4D             |
|-------------------|-------------------|----------------|----------------|
| Akureyri          | Akureyri          | Akureyri       | Akureyri       |
| Blönduós          | Blönduós          |                |                |
| Borganes          |                   | Borganes       |                |
| Borgarnes         |                   |                | Borgarnes      |
| Egilsstaðir       | Egilsstaðir       | Egilsstaðir    |                |
| Flúðir            |                   | Flúðir         |                |
| Grindavík         |                   | Grindavík      | Grindavik      |
| Hella             |                   |                | Hella          |
| Höfn              |                   |                | Hofn           |
| Höfn í Hornafirði | Höfn í Hornafirði |                |                |
| Hvolsvöllur       |                   | Hvolsvöllur    |                |
| Ísafjörður        | Ísafjörður        | Ísafjörður     |                |
| Jökulsárlón       |                   |                | Jokulsarlon    |
| Keflavík          | Keflavík          | Keflavík       |                |
| Mývatnssveit      |                   | Mývatnssveit   |                |
| Ólafsvík          | Ólafsvík          |                |                |
| Reykjavík         | Reykjavík         | Reykjavík      | Reykjavik      |
| Sauðárkrókur      |                   | Sauðárkrókur   |                |
| Selfoss           | Selfoss           | Selfoss        |                |
| Siglufjörður      |                   | Siglufjörður   |                |
| Snæfellsnes       |                   | Snæfellsnes    |                |
| Þingvellir        |                   |                | Thingvellir    |
| Vatnajökull       | Vatnajökull       |                |                |
| Vestmannaeyjar    | Vestmannaeyjar    | Vestmannaeyjar | Vestmannaeyjar |
| Vík               |                   | Vík            | Vik            |
| Vopnafjörður      | Vopnafjörður      |                |                |

- It seems that the H team misspelled Borgarnes in their database and so there are two entries in the T team's list.
- Both "Höfn" and "Höfn í Hornafirði" appear as seperate locations - this was not noticed until the time of writing.

In any case, it is possible within the T team's project to book things in different locations.

## Timeframes
There is, it seems, also quite little overlap in valid timeframes. 
This is primarily because of the day tour and hotel databases having very few dates for their entries.
The day tours are quite few (only 15) and are very spread out, ranging from 2025-05-20 to 2025-12-15, though most are during the summertime.
The hotels only have 3 valid check-in dates, those being 2025-05-10, 2025-05-17, and 2025-05-24. 
So you see that in order to find a trip with both a hotel and day tour, the search parameters must be quite wide.

## Booking
As the F team had not implemented booking, the T team ui only books the selected hotels and day tours.

## Login
Both the Sign In and Sign Up options actually accept any username and password and create a new user with that info which only lasts during runtime of the project.
However, if you book a trip, the booking is saved in the H booking database under that username. 
This is not visible within the T team's ui, but the H team ui has a view of all bookings regardless of user.