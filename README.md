# CPSC 210 Personal Project: Climb Tracker for the Climbing Gym

## Project Proposal

The goal of this application is to provide an interface for a user to track climbs
in the rock climbing gym and add/remove climbs as they are set or removed. The grading scale used in 
 this program is made
for *The Hive Climbing*.
 Eventually, this functionality will be extended into an interactive
map of the rock climbing gym that can be 
be used to display all boulders in the gym. Additionally, it will be able to allow the user
to sort by difficulty and location of the boulders. Users of this application
could be other climbers.

This project is of interest to me because I am an avid rock climber
and I spend most of my free time there. However, when I went to
the gym for the first time, and whenever I go to a different gym, I find the
amount of climbs and lack of organization annoying. There is never any documentation on the climbs that
 is all in once place.
 I think an application that could sort climbs 
and provide a map of where they are in the gym would be useful to newcomers.

##User Stories

- As a user, I want to be able to sort climbs in terms of their difficulty.

- As a user, I want to be able to add new climbs to the gym.

- As a user, I want to be able to see only climbs that are a specific difficulty

- As a user, I want to be able to see all the climbs in the gym, grouped together by wall.

- As a user, I want to be able to remove climbs from the gym.

- As a user, I want to be able to save the list of climbs to a file.

- As a user, I want to be able to load the list of climbs when the program starts.

## Instructions for Grader

- You can generate the first required event by clicking on any wall button, then choosing a Color (string)
- and a grade and the wall, then press add.
- You can generate the second required event by looking on the same panel to see that this new climb has been added
- You can locate my visual component on the home panel, which displays a image on a jlabel.
- You can save the state of my application by exiting out of the program.
- You can reload the state of my application by launching the program.

##Task 4

- There is exesscive couplling in my GUI class from in the initializing of walls. I recognized this while initially working
on the program but I was just trying to finish the GUI as fast as possible.
- There is a lack of cohesion in the methods. Even I cannot figure out how the program works sometimes. I want to try to 
put similar pieces of code together into methods.


