## Tic Tac Toe
[![Build Status](https://travis-ci.org/cool-mist/TicTacToe.svg?branch=master)](https://travis-ci.org/cool-mist/TicTacToe)  [![codecov](https://codecov.io/gh/cool-mist/TicTacToe/branch/master/graph/badge.svg)](https://codecov.io/gh/cool-mist/TicTacToe)
- :x:'s and :o:'s 
- :robot: plays optimally 
- Java 

### Prerequisites
|Item|Requirement |
|--|--|
|Java|8+
|Maven|3+


### Build :building_construction:

Clone the project
```bash
> cd ttt-parent
> mvn clean install
```
### Run :runner:
```bash
> java -jar ttt/target/game.jar -h
usage: java -jar ttt/target/game.jar [options]
 -ai <arg>   AI (e)z or (h)ard
 -o          Play as O
 -x          Play as X
```

### Samples :man_playing_handball:

Conditions| [options]
---|---
Computer vs Computer (easy)|```-ai ez```
Player :x: vs Computer (easy)|```-x -ai ez```
Player :o: vs Computer (hard)|```-o -ai hard```
Player :x: vs Player :o:|```-xo```

