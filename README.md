# ChromeBrowser-offline-game

This is a simple version of the game inspired from the T-rex game that appears on your Google Chrome Browser (in case of no internet).

## Tools/Libraries used :-

* Java
* Swing

## Code Structure

* Entire code is divided into six classes
* GameWindow class is the main class that is the Frame of the game. It calls the  MainScreen class.
* MainScreen handles all the action that is going to take place on the screen. It instantiates all the other classes.
* All the remaining classes are for handling a specific purpose.
* Agent class is for handling the T-Rex in the game.
* Obstacle class is for the obstacle (i.e Cactus that appear). To handle many such obstacles we have the enemy class.
* Similarly we have the ground and the cloud class for the purpose that is clear by the name.

## Improvements that can be done :-

* Currently we have a bounding box that is enveloping the T-rex image. The dimensions of these bounding boxes are used to detect a collision with an obstacle. Now the shape of the box is a square. As the dimensions of the box are not the same as the image(they are slightly bigger than the image ) for technical reasons , Collision occurs even when the actual image of the T-Rex has not hit the obstacle. This is not the code issue . Therefore any improvements to the technique for detecting collision are welcomed.


