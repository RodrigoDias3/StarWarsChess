# **StarWarsChess**
Description
StarWars DEISI Chess is a unique adaptation of traditional chess, incorporating elements from the Star Wars universe for an immersive experience.

![deisi-chess](https://github.com/user-attachments/assets/b65ba4c5-6713-4511-9ce3-9e3a5935ca82)

## **Code Structure**
### The game is built around several key classes:
- **GameManager**: Controls the game flow, interacts with the board, manages moves, saving, and victory conditions.
- **Board**: Stores essential information like board size, piece positions, and statistics.
- **Team**: Represents each team, keeping track of name, pieces, captures, and moves.
- **Pieces**: Each piece has an ID, type, and movement rules. There are eight special types, including the Guardian, which protects allies (Yoda for White, Palpatine for Black).
### Additional Features
- **JogadaValida**: Suggests valid moves and assigns points to each one.
- **Statistics.kt**: Provides game stats like top captures, scores, and most moved pieces, using functional programming.

This project makes chess more dynamic and strategic by blending Star Wars with innovative mechanics! 🚀♟️

### _**Tabela De Peças**_

| PEICES | WHITE | BLACK | DESCRIPTION | VALUE |
|---------------|--------------------------------------------------------------------|------------------------------------------------------------------|------------------------------------------------------------------|------------------------------------------------------------------|
| King | Luke Skywalker<br/> ![img.png](src/images/crazy_emoji_white.png) | Darth Vader <br/>![img.png](src/images/crazy_emoji_black.png) | Can move 1 square in all directions | 1000 points
| Queen | Princesa Leia<br/> ![img.png](src/images/rainha_white.png) | Padmé Amidala<br/>![img.png](src/images/rainha_black.png) | Can move 5 squares in all directions | 8 points
| Village Bishop | Han Solo<br/> ![img.png](src/images/padre_vila_white.png) | General Grievous<br/>![img.png](src/images/padre_vila_black.png) | Can move 3 squares diagonally | 3 points
| Magical Knight | Obi-Wan Kenobi<br/> ![img.png](src/images/ponei_magico_white.png) | Conde Dookan<br/>![img.png](src/images/ponei_magico_black.png) | Can move in an L-shape with 2 squares to the front and 2 to the side | 5 points
| Horizontal Rook | R2-D2 <br/>![img.png](src/images/torre_h_white.png) | Mandalorian<br/>![img.png](src/images/torre_h_black.png) | Can move infinitely many squares horizontally | 3 points
| Vertical Rook | Chewbacca <br/>![img.png](src/images/torre_v_white.png) | Darth Maul <br/>![img.png](src/images/torre_v_black.png) | Can move infinitely many squares vertically | 3 points
| Homer Simpson | Jar Jar Binks <br/>![img.png](src/images/homer_white.png) | Jabba the Hutt <br/>![img.png](src/images/homer_black.png) | Can move 1 squares diagonally, but every 3 shifts the piece is asleep and cannot be used | 2 points
| Joker | C-3PO<br/>![img.png](src/images/joker_white.png) | Strong Trooper <br/>![img.png](src/images/joker_black.png) | Each round take on one of the types of pieces | 4 points
| Guardion | Yoda <br/>![img.png](src/images/guardiao_white.png) | Sheev Palpatine <br/>![img.png](src/images/guardiao_black.png) | Can move 2 squares horizontally or vertically, protects the pieces in the squares around | 2 points

## Demonstration
- [Youtube Video](https://www.youtube.com/watch?v=5nXnG-uezm8)

## This project was developed by:
- Rodrigo Dias - a22205897
- Rui Ferreira - a22207191
