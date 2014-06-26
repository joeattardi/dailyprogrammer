import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AsciiBird {
    public static final int BOARD_WIDTH = 20;
    public static final int BOARD_HEIGHT = 10;
    public static final int GRAVITY = 2;
    public static final int VELOCITY = 2;
    public static final int OBSTACLE_MIN_HEIGHT = 2;
    public static final int OBSTACLE_MAX_HEIGHT = 4;
    public static final int SPAWN_TIMER_MIN = 7;
    public static final int SPAWN_TIMER_MAX = 10;

    private GameState gameState = new GameState();

    public void play() {
        Scanner input = new Scanner(System.in);
        while (true) {
            printBoard();

            if (gameState.isGameOver()) {
                System.out.println("Game over!");
                break;
            }

            int move = -1;
            while (move < 0 || move > 4) {
                System.out.printf("(score %d) 0-4? ", gameState.getScore());
                move = input.nextInt();                              
            }

            gameState.update(move);            
        }
    }

    public void printBoard() {
        char[][] board = new char[BOARD_HEIGHT][BOARD_WIDTH];
        Arrays.stream(board).forEach(a -> Arrays.fill(a, '.'));

        for (Obstacle obstacle : gameState.getObstacles()) {
            for (int i = obstacle.getY(); i < obstacle.getY() + obstacle.getHeight(); i++) {
                board[i][obstacle.getX()] = '#';
            }
        }

        Bird bird = gameState.getBird();
        board[bird.getY()][bird.getX()] = gameState.isGameOver() ? 'X' : '@';

        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AsciiBird game = new AsciiBird();
        game.play();
    }
}

class GameState {
    private static final int BIRD_X = 1;
    private Random random = new Random();
    private List<Obstacle> obstacles = new ArrayList<>();
    private Bird bird = new Bird(BIRD_X, 5);
    private int score = 0;
    private boolean gameOver = false;
    private int topSpawnTimer;
    private int bottomSpawnTimer;

    public GameState() {
        topSpawnTimer = randomInt(AsciiBird.SPAWN_TIMER_MIN, AsciiBird.SPAWN_TIMER_MAX);
        bottomSpawnTimer = randomInt(AsciiBird.SPAWN_TIMER_MIN, AsciiBird.SPAWN_TIMER_MAX);
    }

    private int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private boolean checkCollisions() {
        for (Obstacle obstacle : obstacles) {
            if (bird.getX() == obstacle.getX() && bird.getY() >= obstacle.getY() && bird.getY() <= obstacle.getY() + obstacle.getHeight() - 1) {
                return true;
            }
        }

        return false;
    }

    public void update(int move) {
        if (move == 0) {
            bird.setY(bird.getY() + AsciiBird.GRAVITY);
        } else {
            bird.setY(bird.getY() - move);
            if (bird.getY() < 0) {
                bird.setY(0);
            }
        }

        if ((topSpawnTimer -= AsciiBird.VELOCITY) <= 0) {
            obstacles.add(new Obstacle(randomInt(AsciiBird.OBSTACLE_MIN_HEIGHT, AsciiBird.OBSTACLE_MAX_HEIGHT), 
                AsciiBird.BOARD_WIDTH - 1, 0));
            topSpawnTimer = randomInt(AsciiBird.SPAWN_TIMER_MIN, AsciiBird.SPAWN_TIMER_MAX);
        }

        if ((bottomSpawnTimer -= AsciiBird.VELOCITY) <= 0) {
            int height = randomInt(AsciiBird.OBSTACLE_MIN_HEIGHT, AsciiBird.OBSTACLE_MAX_HEIGHT);
            obstacles.add(new Obstacle(height, AsciiBird.BOARD_WIDTH - 1, AsciiBird.BOARD_HEIGHT - height));
            bottomSpawnTimer = randomInt(AsciiBird.SPAWN_TIMER_MIN, AsciiBird.SPAWN_TIMER_MAX);
        }

        for (Iterator<Obstacle> i = obstacles.iterator(); i.hasNext(); ) {
            Obstacle obstacle = i.next();
            obstacle.setX(obstacle.getX() - AsciiBird.VELOCITY);
            if (obstacle.getX() <= 0) {
                i.remove();
                score++;
            } 
        }

        if (bird.getY() > AsciiBird.BOARD_HEIGHT - 1 || checkCollisions()) {
            gameOver = true;
            bird.setY(Math.min(AsciiBird.BOARD_HEIGHT - 1, bird.getY()));
        }        
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    public Bird getBird() {
        return bird;
    }
}

class GameObject {
    private int x;
    private int y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class Bird extends GameObject {
    public Bird(int x, int y) {
        super(x, y);
    }
}

class Obstacle extends GameObject {
    private int height;

    public Obstacle(int height, int x, int y) {
        super(x, y);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}