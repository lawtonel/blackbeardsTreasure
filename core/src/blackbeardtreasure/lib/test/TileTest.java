package blackbeardtreasure.lib.test;

import blackbeardtreasure.lib.*;
import blackbeardtreasure.lib.Game;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import static blackbeardtreasure.lib.Coordinates.coordinateXtoPixels;
import static blackbeardtreasure.lib.Coordinates.coordinateYtoPixels;
import static blackbeardtreasure.lib.Coordinates.pixelsToCoordinates;
import static blackbeardtreasure.lib.TutorialState.*;

public class TileTest extends ApplicationAdapter implements InputProcessor {
    private TextureRegion setUpScreenRgn, characterSelectRegion, pauseScreenRgn, optionsMenuRgn, highlightedArea, rulesScreenRgn;
    private TextureRegion[] diceFrames;
    private Animation diceAnimation;
    private float elapsedTime;

    private Texture p1winImg;
    private Texture p2winImg;
    private Texture p3winImg;
    private Texture p4winImg;
    private Texture sparkImg;
    private Texture easyImg;
    private Texture mediumImg;
    private Texture hardImg;

    private TiledMapRenderer tiledMapRenderer;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    public static boolean isMac = false;

    private final int cellSize = scale(50);
    private int lastRoll;

    private GameState currentState = GameState.SETUP;

    private BitmapFont font, playFont, playCurrentFont, bbFont;

    private Game bbGame;
    private GameBoard gameBoard;

    private Sound footstep, getItem, evilLaugh, sword, nope;
    private Sound emWin, billyWin, nipWin, sanWin;
    private Sound emDuel, billyDuel, nipDuel, sanDuel;
    private Sound emDuelWin, billyDuelWin, nipDuelWin, sanDuelWin;
    private Music bg;

    private final Player[] players = new Player[4];
    private String gameMasterStr;

    private Pixmap pm;

    private boolean isPlayer1Computer, isPlayer2Computer, isPlayer3Computer, isPlayer4Computer,
            hasLeftDuelistMoved, hasRightDuelistMoved, diceRolling;

    private char leftDuelistMove, rightDuelistMove;

    private TutorialState currentTutorialState = S1;

    public static int scale(int num) {
        if(isMac) {
            return num / 2;
        } else {
            return num;
        }
    }

    public static float scale(float num) {
        if(isMac) {
            return num / 2;
        } else {
            return num;
        }
    }

    public static String retinaURL(){
        if(isMac) {
            return "retina/";
        } else {
            return "";
        }
    }

    private void createMusic() {
        footstep = Gdx.audio.newSound(Gdx.files.internal("music/footsteps.mp3"));
        getItem = Gdx.audio.newSound(Gdx.files.internal("music/get-item.mp3"));
        evilLaugh = Gdx.audio.newSound(Gdx.files.internal("music/evil laugh.mp3"));
        bg = Gdx.audio.newMusic(Gdx.files.internal("music/bg_music.mp3"));
        sword = Gdx.audio.newSound(Gdx.files.internal("music/steelsword.mp3"));
        nope = Gdx.audio.newSound(Gdx.files.internal("music/nope.mp3"));
        emWin = Gdx.audio.newSound(Gdx.files.internal("music/EmWin.mp3"));
        billyWin = Gdx.audio.newSound(Gdx.files.internal("music/BillyWin.mp3"));
        nipWin = Gdx.audio.newSound(Gdx.files.internal("music/NipWin.mp3"));
        sanWin = Gdx.audio.newSound(Gdx.files.internal("music/SanWin.mp3"));
        emDuel = Gdx.audio.newSound(Gdx.files.internal("music/EmDuel.mp3"));
        billyDuel = Gdx.audio.newSound(Gdx.files.internal("music/BillyDuel.mp3"));
        nipDuel = Gdx.audio.newSound(Gdx.files.internal("music/NipDuel.mp3"));
        sanDuel = Gdx.audio.newSound(Gdx.files.internal("music/SanDuel.mp3"));
        emDuelWin = Gdx.audio.newSound(Gdx.files.internal("music/EmDuelWin.mp3"));
        billyDuelWin = Gdx.audio.newSound(Gdx.files.internal("music/BillyDuelWin.mp3"));
        nipDuelWin = Gdx.audio.newSound(Gdx.files.internal("music/NipDuelWin.mp3"));
        sanDuelWin = Gdx.audio.newSound(Gdx.files.internal("music/SanDuelWin.mp3"));
    }

    private void createPlayers() {
        // Players
        if (!isPlayer1Computer) {
            players[0] = new HumanPlayer(
                    "Swashbucklin' Em",
                    new Coordinates(0, 13),
                    gameBoard,
                    p1winImg,
                    emWin,
                    emDuel,
                    emDuelWin,
                    new TextureRegion(new Texture("images/"+retinaURL()+"player1-1.png"), 0, 0, cellSize, cellSize),
                    0,
                    scale(200),
                    0
            );
        } else {
            players[0] = new DrunkenSailorPlayer(
                    "Swashbucklin' Em",
                    new Coordinates(0, 13),
                    p1winImg,
                    emWin,
                    emDuel,
                    emDuelWin,
                    new TextureRegion(new Texture("images/"+retinaURL()+"player1-1.png"), 0, 0, cellSize, cellSize),
                    0,
                    scale(200),
                    gameBoard
            );
        }
        if (!isPlayer2Computer) {
            players[1] = new HumanPlayer(
                    "Lookout Billy",
                    new Coordinates(0, 0),
                    gameBoard,
                    p2winImg,
                    billyWin,
                    billyDuel,
                    billyDuelWin,
                    new TextureRegion(new Texture("images/"+retinaURL()+"player2.png"), 0, 0, cellSize, cellSize),
                    0,
                    scale(850),
                    0
            );
        } else {
            players[1] = new DrunkenSailorPlayer(
                    "Lookout Billy",
                    new Coordinates(0, 0),
                    p2winImg,
                    billyWin,
                    billyDuel,
                    billyDuelWin,
                    new TextureRegion(new Texture("images/"+retinaURL()+"player2.png"), 0, 0, cellSize, cellSize),
                    0,
                    scale(850),
                    gameBoard
            );
        }
        if (!isPlayer3Computer) {
            players[2] = new HumanPlayer(
                    "Captain Nip",
                    new Coordinates(13, 0),
                    gameBoard,
                    p3winImg,
                    nipWin,
                    nipDuel,
                    nipDuelWin,
                    new TextureRegion(new Texture("images/"+retinaURL()+"player3.png"), 0, 0, cellSize, cellSize),
                    scale(650),
                    scale(850),
                    0
            );
        } else {
            players[2] = new DrunkenSailorPlayer(
                    "Captain Nip",
                    new Coordinates(13, 0),
                    p3winImg,
                    nipWin,
                    nipDuel,
                    nipDuelWin,
                    new TextureRegion(new Texture("images/"+retinaURL()+"player3.png"), 0, 0, cellSize, cellSize),
                    scale(650),
                    scale(850),
                    gameBoard
            );
        }
        if (!isPlayer4Computer) {
            players[3] = new HumanPlayer(
                    "San the Ruthless",
                    new Coordinates(13, 13),
                    gameBoard,
                    p4winImg,
                    sanWin,
                    sanDuel,
                    sanDuelWin,
                    new TextureRegion(new Texture("images/"+retinaURL()+"player4.png"), 0, 0, cellSize, cellSize),
                    scale(650),
                    scale(200),
                    0
            );
        } else {
            players[3] = new DrunkenSailorPlayer(
                    "San the Ruthless",
                    new Coordinates(13, 13),
                    p4winImg,
                    sanWin,
                    sanDuel,
                    sanDuelWin,
                    new TextureRegion(new Texture("images/"+retinaURL()+"player4.png"), 0, 0, cellSize, cellSize),
                    scale(650),
                    scale(200),
                    gameBoard
            );
        }
        bbGame = new GameImpl(players);
    }

    @Override
    public void create() {
        leftDuelistMove = 'n';
        rightDuelistMove = 'n';

        hasLeftDuelistMoved = false;
        hasRightDuelistMoved = false;
        isPlayer1Computer = false;
        isPlayer2Computer = true;
        isPlayer3Computer = true;
        isPlayer4Computer = true;

        //Mouse
        pm = new Pixmap(Gdx.files.internal("images/"+retinaURL()+"mouse-pointer.png"));

        createMusic();
        bg.setVolume(0.2f);

        // Gameboard constructor with no params auto makes the main level
        gameBoard = new GameBoardImpl();

        // Collection of game objects
        batch = new SpriteBatch();

        // Width and Height of the map
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        // Game view
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();

        // Game Map
        TiledMap tiledMap = new TmxMapLoader().load("map/"+retinaURL()+"newboardhub.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        // Setup Screens
        Texture setUpScreenImg = new Texture("images/"+retinaURL()+"V1StartScreen.png");
        Texture characterSelectImg = new Texture("images/"+retinaURL()+"V1GameSetUp.png");
        setUpScreenRgn = new TextureRegion(setUpScreenImg, 0, 0, scale(900), scale(900));
        characterSelectRegion = new TextureRegion(characterSelectImg, 0, 0, scale(900), scale(900));

        // Win Screens
        p1winImg = new Texture("win/"+retinaURL()+"p1win.png");
        p2winImg = new Texture("win/"+retinaURL()+"p2win.png");
        p3winImg = new Texture("win/"+retinaURL()+"p3win.png");
        p4winImg = new Texture("win/"+retinaURL()+"p4win.png");

        // Paused Screen
        Texture pauseScreenImg = new Texture("images/"+retinaURL()+"pauseScreen.png");
        pauseScreenRgn = new TextureRegion(pauseScreenImg, 0, 0, scale(900), scale(900));

        // Options menu
        sparkImg = new Texture("setup/"+retinaURL()+"Spark.png");
        easyImg = new Texture("setup/"+retinaURL()+"Mode easy.png");
        mediumImg = new Texture("setup/"+retinaURL()+"Mode Normal.png");
        hardImg = new Texture("setup/"+retinaURL()+"Mode Hard.png");
        Texture optionsImg = new Texture("setup/"+retinaURL()+"Options Menu.png");
        optionsMenuRgn = new TextureRegion(optionsImg, 0, 0, scale(900), scale(900));
        Texture rulesImg = new Texture("setup/rules.png");
        rulesScreenRgn = new TextureRegion(rulesImg, 0, 0, scale(900), scale(900));

        // Key setup
        new Texture("images/"+retinaURL()+"Rum.png");
        new Texture("images/"+retinaURL()+"Hat.png");
        new Texture("images/"+retinaURL()+"parrot.png");
        new Texture("images/"+retinaURL()+"sword.png");
        new Texture("images/"+retinaURL()+"treasure.png");

        // dice setup
        Texture diceImg = new Texture("images/"+retinaURL()+"dice.png");
        TextureRegion[][] tmpFrames = TextureRegion.split(diceImg, scale(200), scale(200));
        diceFrames = new TextureRegion[6];
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                diceFrames[index++] = tmpFrames[i][j];
            }
        }
        diceRolling = false;
        lastRoll = 1;

        diceAnimation = new Animation(1f / 6f, diceFrames);

        // Player text - not their turn
        playFont = new BitmapFont();
        playFont.setColor(Color.BLACK);
        playFont.getData().setScale(scale(1f), scale(1f));
        // Player text - their turn
        playCurrentFont = new BitmapFont();
        playCurrentFont.setColor(Color.BLUE);
        playCurrentFont.getData().setScale(scale(1f), scale(1f));

        // Game text
        font = new BitmapFont();
        font.getData().setScale(scale(2), scale(2));

        // BlackBeard text
        bbFont = new BitmapFont();
        bbFont.getData().setScale(scale(1.5f), scale(1.5f));
        bbFont.setColor(Color.BLACK);

        // Keyboard and Mouse Input Sensor
        Gdx.input.setInputProcessor(this);
    }

    private void renderSetup() {
        batch.draw(setUpScreenRgn, 0, 0, scale(900),scale(900));
    }

    private void renderPlaying(){
        populatePlayerNames();
        drawKeys();
        drawPlayers();

        if (bbGame.whoseTurn() instanceof HumanPlayer) {

            // Game instructions
            if (bbGame.getBlackbeardState() == GameMasterState.NO_ACTION) {
                if (bbGame.whoseTurn().getMovement() != 0) {
                    bbFont.draw(batch, "Use the directional arrows to move", scale(250), scale(150));
                    bbFont.draw(batch, "Move remaining: " + bbGame.whoseTurn().getMovement(), scale(330), scale(100));
                } else {
                    bbFont.draw(batch, bbGame.whoseTurn().getName() + " it's your turn!", scale(250),scale( 150));
                    bbFont.draw(batch, "Press 'r' to roll dice", scale(320), scale(100));
                }
            } else if (bbGame.getBlackbeardState() == GameMasterState.ANNOUNCE_ACTION) {
                bbFont.draw(batch, "Think you can win that easily?! I'll stop you!”\n" + bbGame.whoseTurn().getName() + "!", scale(250), scale(150));
                bbFont.draw(batch, "Press 'c' to continue", scale(250), scale(75));
            } else if (bbGame.getBlackbeardState() == GameMasterState.TAKE_ACTION) {
                bbFont.draw(batch, gameMasterStr, scale(250), scale(150));
                bbFont.draw(batch, "Press 'c' to continue", scale(250), scale(75));
            }


            // Highlighted area for movement
            TextureRegion highlightedArea = new TextureRegion(new Texture("images/"+retinaURL()+"highlighted-area.png"), 0, 0, cellSize, cellSize);
            // Show highlighted area player can move
            int area = bbGame.whoseTurn().getMovement();
            int highlightedX = coordinateXtoPixels(bbGame.whoseTurn().getLocation());
            int highlightedY = coordinateYtoPixels(bbGame.whoseTurn().getLocation());

            int topLoop = highlightedY;
            int bottomLoop = highlightedY;
            int firstRightLoop = highlightedX;
            int firstLeftLoop = highlightedX;

            for(int i = 1; i <= area; i++){
                topLoop+=cellSize;
                bottomLoop-=cellSize;
                firstRightLoop+=cellSize;
                firstLeftLoop-=cellSize;
                // Top
                if(topLoop >= scale(200) && topLoop <= scale(900)) {
                    batch.draw(highlightedArea, highlightedX, topLoop);
                }
                // Bottom
                if(bottomLoop >= scale(200) && bottomLoop <= scale(900)) {
                    batch.draw(highlightedArea, highlightedX, bottomLoop);
                }
                // Right
                if(firstRightLoop >= 0 && firstRightLoop <= scale(900)) {
                    batch.draw(highlightedArea, firstRightLoop, highlightedY);
                }
                // Left
                if(firstLeftLoop >= 0 && firstLeftLoop <= scale(900)) {
                    batch.draw(highlightedArea, firstLeftLoop, highlightedY);
                }
                /*
                int row = area-(i-1);

                int rightLoop = highlightedX;
                int leftLoop = highlightedX;
                for(int j = 1; j <= row-1; j++) {
                    rightLoop+=cellSize;
                    leftLoop-=cellSize;
                    // Right Bottom
                    if(rightLoop >= 0 && rightLoop <= scale(900) && bottomLoop >= scale(200) && bottomLoop <= scale(900)) {
                        batch.draw(highlightedArea, rightLoop, bottomLoop);
                    }
                    // Left Bottom
                    if(leftLoop >= 0 && leftLoop <= scale(900) && bottomLoop >= scale(200) && bottomLoop <= scale(900)) {
                        batch.draw(highlightedArea, leftLoop, bottomLoop);
                    }

                    // Right Top
                    if(rightLoop >= 0 && rightLoop <= scale(900) && topLoop >= scale(200) && topLoop <= scale(900)) {
                        batch.draw(highlightedArea, rightLoop, topLoop);
                    }
                    // Left Top
                    if(leftLoop >= 0 && leftLoop <= scale(900) && topLoop >= scale(200) && topLoop <= scale(900)) {
                        batch.draw(highlightedArea, leftLoop, topLoop);
                    }
                }*/

            }

        } else {
            if(bbGame.whoseTurn().getCPUstate() == CPUState.WAITING) {
                bbFont.draw(batch, "It's " + bbGame.whoseTurn().getName() + "'s turn!", scale(250), scale(150));
                bbFont.draw(batch, "Press 'c' to continue", scale(250), scale(100));
            } else if(bbGame.whoseTurn().getCPUstate() == CPUState.ROLLING) {
                bbFont.draw(batch, bbGame.whoseTurn().getName() + " is rolling the dice!", scale(250), scale(150));
                bbFont.draw(batch, "Press 'c' to continue", scale(250),scale( 100));
            } else if(bbGame.whoseTurn().getCPUstate() == CPUState.MOVING) {
                bbFont.draw(batch, bbGame.whoseTurn().getName() + " has rolled a " + lastRoll, scale(250), scale(150));
                bbFont.draw(batch, "Press 'c' to continue", scale(250), scale(100));
            }
        }
    }

    private void renderPaused() {
        for (Player player : bbGame.getAllPlayers()) {
            batch.draw(player.getAvatarCanvas(), player.getX(), player.getY());
        }
        batch.draw(pauseScreenRgn, 0, 0);
    }

    private void renderOptions() {
        batch.draw(optionsMenuRgn, 0, 0);
        // Volume settings
        if(bg.getVolume() == 0f){
            batch.draw(sparkImg, scale(170), scale(480));
        } else if(bg.getVolume() == 0.2f){
            batch.draw(sparkImg, scale(244),scale( 480));
        } else if(bg.getVolume() == 0.4f){
            batch.draw(sparkImg, scale(308), scale(480));
        } else if(bg.getVolume() == 0.6f){
            batch.draw(sparkImg, scale(382),scale( 480));
        } else if(bg.getVolume() == 0.8f){
            batch.draw(sparkImg, scale(456),scale( 480));
        } else {
            batch.draw(sparkImg, scale(530), scale(480));
        }
        // Difficulty settings
        switch (bbGame.getDifficultyLevel()) {
            case EASY:
                batch.draw(easyImg, scale(243), scale(293));
                break;
            case MEDIUM:
                batch.draw(mediumImg, scale(420),scale(290));
                break;
            case HARD:
                batch.draw(hardImg, scale(600),scale(291));
                break;
        }
    }

    private void renderWon() {
        batch.draw(bbGame.whoseTurn().getWinScreen(), 0,0);
        bg.stop();
    }

    private void renderCharacterSelect() {
        batch.draw(characterSelectRegion, 0, 0);
        if (!isPlayer1Computer) {
            font.draw(batch, "PLAYER", scale(90), scale(550));
            font.draw(batch, "CPU", scale(270),scale( 550));
        } else {
            font.draw(batch, "PLAYER", scale(260), scale(550));
            font.draw(batch, "CPU", scale(120),scale( 550));
        }

        if (!isPlayer2Computer) {
            font.draw(batch, "PLAYER", scale(545),scale( 550));
            font.draw(batch, "CPU", scale(730),scale( 550));
        } else {
            font.draw(batch, "PLAYER", scale(700),scale( 550));
            font.draw(batch, "CPU", scale(570),scale( 550));
        }

        if (!isPlayer3Computer) {
            font.draw(batch, "PLAYER", scale(90), scale(100));
            font.draw(batch, "CPU", scale(270),scale( 100));
        } else {
            font.draw(batch, "PLAYER", scale(240), scale(100));
            font.draw(batch, "CPU", scale(130),scale( 100));
        }

        if (!isPlayer4Computer) {
            font.draw(batch, "PLAYER", scale(550),scale( 100));
            font.draw(batch, "CPU", scale(730),scale( 100));
        } else {
            font.draw(batch, "PLAYER", scale(700),scale( 100));
            font.draw(batch, "CPU", scale(570), scale(100));
        }
    }

    public void renderDuel() {
        Texture bg = new Texture("images/"+retinaURL()+"dueling-screen.png");
        TextureRegion bgr = new TextureRegion(bg, 0, 0, scale(900),scale( 900));
        batch.draw(bgr, 0, 0);

        Texture leftDuelist = null;
        String leftDuelistName = "";
        if (bbGame.getLeftDuelist().getName().equals("Swashbucklin' Em")) {
            leftDuelistName = "Swashbucklin' Em";
            leftDuelist = new Texture("images/"+retinaURL()+"duel-players/player-1-left.png");
        } else if (bbGame.getLeftDuelist().getName().equals("Lookout Billy")) {
            leftDuelistName = "Lookout Billy";
            leftDuelist = new Texture("images/"+retinaURL()+"duel-players/player-2-left.png");
        } else if (bbGame.getLeftDuelist().getName().equals("Captain Nip")) {
            leftDuelistName = "Captain Nip";
            leftDuelist = new Texture("images/"+retinaURL()+"duel-players/player-3-left.png");
        } else {
            leftDuelistName = "San the Ruthless";
            leftDuelist = new Texture("images/"+retinaURL()+"duel-players/player-4-left.png");
        }

        Texture rightDuelist = null;
        String rightDuelistName = "";
        if (bbGame.getRightDuelist().getName().equals("Swashbucklin' Em")) {
            rightDuelistName = "Swashbucklin' Em";
            rightDuelist = new Texture("images/"+retinaURL()+"duel-players/player-1-right.png");
        } else if (bbGame.getRightDuelist().getName().equals("Lookout Billy")) {
            rightDuelistName = "Lookout Billy";
            rightDuelist = new Texture("images/"+retinaURL()+"duel-players/player-2-right.png");
        } else if (bbGame.getRightDuelist().getName().equals("Captain Nip")) {
            rightDuelistName = "Captain Nip";
            rightDuelist = new Texture("images/"+retinaURL()+"duel-players/player-3-right.png");
        } else {
            rightDuelistName = "San the Ruthless";
            rightDuelist = new Texture("images/"+retinaURL()+"duel-players/player-4-right.png");
        }

        TextureRegion leftDuelistRegion = new TextureRegion(leftDuelist, 0, 0, scale(288),scale( 400));
        TextureRegion rightDuelistRegion = new TextureRegion(rightDuelist, 0, 0, scale(288),scale( 400));
        batch.draw(leftDuelistRegion, scale(100),scale( 120));
        batch.draw(rightDuelistRegion, scale(470),scale( 120));

        bbFont.draw(batch, leftDuelistName, scale(375), scale(845));
        bbFont.draw(batch, "VS", scale(445), scale(820));
        bbFont.draw(batch, rightDuelistName, scale(375), scale(795));

        if (hasLeftDuelistMoved) {
            bbFont.draw(batch, "Left Player has moved", scale(335), scale(765));
        }

        if (hasRightDuelistMoved) {
            bbFont.draw(batch, "Right Player has moved", scale(335), scale(745));
        }

        if (hasLeftDuelistMoved && hasRightDuelistMoved) {
            bbFont.draw(batch, "Press 'c' to continue", scale(358), scale(720));

            // Draw case
            if (leftDuelistMove == rightDuelistMove) {
                bbFont.draw(batch, rightDuelistName + ": WINS!", scale(335), scale(700));
            }
            // Win cases - Rock beats scissors
            else if (bbGame.getLeftDuelist().getMove() == 'q' && bbGame.getRightDuelist().getMove() == 'p'
                    // Paper beats rock
                    || bbGame.getLeftDuelist().getMove() == 'w' && bbGame.getRightDuelist().getMove() == 'i'
                    // Scissors beats paper
                    || bbGame.getLeftDuelist().getMove() == 'e' && bbGame.getRightDuelist().getMove() == 'o') {
                bbFont.draw(batch, leftDuelistName + ": WINS!", scale(335), scale(700));
            }
            // Lose case
            else {
                bbFont.draw(batch, rightDuelistName + ": WINS!", scale(335),scale( 700));
            }
        }
    }

    private void renderHowToPlay() {
        Texture bg = new Texture("images/"+retinaURL()+"V1HowToPlay.png");
        TextureRegion bgr = new TextureRegion(bg, 0, 0, scale(900),scale( 900));
        batch.draw(bgr, 0, 0);
    }

    @Override
    public void render() {
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));

        // Background
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Camera
        camera.update();

        // Tiled Map
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        // Draw
        batch.begin();

        //Audio
        bg.play();

        switch (currentState) {
            case SETUP:
                renderSetup();
                break;
            case PLAYING:
                renderPlaying();
                renderDice();
                break;
            case PAUSED:
                renderPaused();
                break;
            case WON:
                renderWon();
                break;
            case CHARACTERSELECT:
                renderCharacterSelect();
                break;
            case DUEL:
                renderDuel();
                break;
            case HOWTOPLAY:
                renderHowToPlay();
                break;
            case OPTIONS:
                renderOptions();
                break;
            case RULES:
                renderRules();
                break;
            case TUTORIAL:
                renderTutorial();
                break;
        }

        batch.end();
    }

    private void renderTutorial() {
        switch(currentTutorialState) {
            case S1:
                batch.draw(new TextureRegion(new Texture("tutorial/"+retinaURL()+"1.png"), 0, 0, scale(900), scale(900)), 0, 0);
                break;
            case S2:
                batch.draw(new TextureRegion(new Texture("tutorial/"+retinaURL()+"2.png"), 0, 0, scale(900), scale(900)), 0, 0);
                break;
            case S3:
                batch.draw(new TextureRegion(new Texture("tutorial/"+retinaURL()+"3.png"), 0, 0, scale(900), scale(900)), 0, 0);
                break;
            case S4:
                batch.draw(new TextureRegion(new Texture("tutorial/"+retinaURL()+"4.png"), 0, 0, scale(900), scale(900)), 0, 0);
                break;
            case S5:
                batch.draw(new TextureRegion(new Texture("tutorial/"+retinaURL()+"5.png"), 0, 0, scale(900), scale(900)), 0, 0);
                break;
            case S6:
                batch.draw(new TextureRegion(new Texture("tutorial/"+retinaURL()+"6.png"), 0, 0, scale(900), scale(900)), 0, 0);
                break;
            case S7:
                batch.draw(new TextureRegion(new Texture("tutorial/"+retinaURL()+"7.png"), 0, 0, scale(900), scale(900)), 0, 0);
                break;
            case S8:
                batch.draw(new TextureRegion(new Texture("tutorial/"+retinaURL()+"8.png"), 0, 0, scale(900), scale(900)), 0, 0);
                break;
            case S9:
                batch.draw(new TextureRegion(new Texture("tutorial/"+retinaURL()+"9.png"), 0, 0, scale(900), scale(900)), 0, 0);
                break;
            case S10:
                batch.draw(new TextureRegion(new Texture("tutorial/"+retinaURL()+"10.png"), 0, 0, scale(900), scale(900)), 0, 0);
                break;
        }
    }

    private void renderRules() {
        for (Player player : bbGame.getAllPlayers()) {
            batch.draw(player.getAvatarCanvas(), player.getX(), player.getY());
        }
        batch.draw(rulesScreenRgn, 0, 0);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(currentState){

            case SETUP:
                break;
            case CHARACTERSELECT:
                break;
            case PLAYING:
                if (bbGame.whoseTurn().getMovement() > 0) {
                    int x = bbGame.whoseTurn().getLocation().getX();
                    int y = bbGame.whoseTurn().getLocation().getY();

                if ((keycode == Input.Keys.LEFT
                        && bbGame.whoseTurn().getX() - cellSize >= 0
                        && x - 1 >= 0
                        && gameBoard.isAt(new Coordinates(x - 1, y)) != LocationName.ROCK)
                        || ((keycode == Input.Keys.RIGHT
                        && bbGame.whoseTurn().getX() - cellSize >= 0
                        && x - 1 >= 0
                        && gameBoard.isAt(new Coordinates(x - 1, y)) != LocationName.ROCK)
                        && bbGame.whoseTurn().isDrunk())
                        ) {
                    float playerX = bbGame.whoseTurn().getX();
                    bbGame.whoseTurn().setX(playerX -= cellSize);
                    int movement = bbGame.whoseTurn().getMovement() - 1;
                    bbGame.whoseTurn().setMovement(movement);
                    bbGame.whoseTurn().setLocation(new Coordinates(x - 1, y));
                } else if ((keycode == Input.Keys.RIGHT
                        && bbGame.whoseTurn().getX() + cellSize <= scale(900)
                        && x + 1 <= 13
                        && gameBoard.isAt(new Coordinates(x + 1, y)) != LocationName.ROCK)
                        || ((keycode == Input.Keys.LEFT
                        && bbGame.whoseTurn().getX() + cellSize <= scale(900)
                        && x + 1 <= 13
                        && gameBoard.isAt(new Coordinates(x + 1, y)) != LocationName.ROCK)
                        && bbGame.whoseTurn().isDrunk())
                        ) {
                    int playerX = (int) bbGame.whoseTurn().getX();
                    playerX += cellSize;
                    bbGame.whoseTurn().setX(playerX);
                    int movement = bbGame.whoseTurn().getMovement() - 1;
                    bbGame.whoseTurn().setMovement(movement);
                    bbGame.whoseTurn().setLocation(new Coordinates(x + 1, y));
                } else if ((keycode == Input.Keys.UP
                        && bbGame.whoseTurn().getY() + cellSize <= scale(1100)
                        && y - 1 >= 0
                        && gameBoard.isAt(new Coordinates(x, y - 1)) != LocationName.ROCK)
                        || ((keycode == Input.Keys.DOWN
                        && bbGame.whoseTurn().getY() + cellSize <= scale(1100)
                        && y - 1 >= 0
                        && gameBoard.isAt(new Coordinates(x, y - 1)) != LocationName.ROCK)
                        && bbGame.whoseTurn().isDrunk())
                        ) {
                    float playerY = bbGame.whoseTurn().getY();
                    playerY += cellSize;
                    bbGame.whoseTurn().setY(playerY);
                    int movement = bbGame.whoseTurn().getMovement() - 1;
                    bbGame.whoseTurn().setMovement(movement);
                    bbGame.whoseTurn().setLocation(new Coordinates(x, y - 1));
                } else if ((keycode == Input.Keys.DOWN
                        && bbGame.whoseTurn().getY() - cellSize >= scale(200)
                        && y + 1 <= 13
                        && gameBoard.isAt(new Coordinates(x, y + 1)) != LocationName.ROCK)
                        || ((keycode == Input.Keys.UP
                        && bbGame.whoseTurn().getY() - cellSize >= scale(200)
                        && y + 1 <= 13
                        && gameBoard.isAt(new Coordinates(x, y + 1)) != LocationName.ROCK)
                        && bbGame.whoseTurn().isDrunk())
                        ) {
                    float playerY = bbGame.whoseTurn().getY();
                    playerY -= cellSize;
                    bbGame.whoseTurn().setY(playerY);
                    int movement = bbGame.whoseTurn().getMovement() - 1;
                    bbGame.whoseTurn().setMovement(movement);
                    bbGame.whoseTurn().setLocation(new Coordinates(x, y + 1));
                }

                footstep.play();

                if (bbGame.whoseTurn().getMovement() == 0
                        && (keycode == Input.Keys.DOWN
                        || keycode == Input.Keys.LEFT
                        || keycode == Input.Keys.UP
                        || keycode == Input.Keys.RIGHT)) {
                    bbGame.whoseTurn().setIsDrunk(false);
                    bbGame.setNextTurn();
                    switch (bbGame.getDifficultyLevel()) {
                        case EASY:
                            if (Math.random() >= 0.9) {
                                bbGame.setBlackBeardState(GameMasterState.ANNOUNCE_ACTION);
                                evilLaugh.play(1);
                            }
                            break;
                        case MEDIUM:
                            if (Math.random() >= 0.8) {
                                bbGame.setBlackBeardState(GameMasterState.ANNOUNCE_ACTION);
                                evilLaugh.play(1);
                            }
                            break;
                        case HARD:
                            if (Math.random() >= 0.7) {
                                bbGame.setBlackBeardState(GameMasterState.ANNOUNCE_ACTION);
                                evilLaugh.play(1);
                            }
                            break;
                    }
                }
                }
                break;
            case PAUSED:
                break;
            case WON:
                break;
            case DUEL:
                break;
            case HOWTOPLAY:
                break;
            case OPTIONS:
                break;
            case RULES:
                break;
            case TUTORIAL:
                switch(currentTutorialState){
                    case S1:
                        break;
                    case S2:
                        if(keycode == Input.Keys.UP) {
                            currentTutorialState = S3;
                        }
                        break;
                    case S3:
                        if(keycode == Input.Keys.UP) {
                            currentTutorialState = S4;
                        }
                        break;
                    case S4:
                        break;
                    case S5:
                        break;
                    case S6:
                        break;
                    case S7:
                        break;
                    case S8:
                        break;
                    case S9:
                        break;
                    case S10:
                        break;
                }
                break;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        character = Character.toLowerCase(character);

        //Pause and Start Game
        if (character == 'p' && (currentState == GameState.SETUP || currentState == GameState.PAUSED)) {
            currentState = GameState.PLAYING;
        } else if (character == 'p' && currentState == GameState.PLAYING) {
            currentState = GameState.PAUSED;
        }

        switch(currentState){
            case SETUP:
                break;
            case CHARACTERSELECT:
                break;
            case PLAYING:
                // Roll dice
                if (character == 'r' && bbGame.whoseTurn().getMovement() == 0 && bbGame.getBlackbeardState() == GameMasterState.NO_ACTION) {
                    diceRolling = true;
                    Random rand = new Random();
                    lastRoll = rand.nextInt(6) + 1;
                    bbGame.whoseTurn().setMovement(lastRoll);
                }
                //TODO replace with 'click to continue'
                // Announce Gamemaster action
                if(character == 'c'
                        && currentState == GameState.PLAYING
                        && bbGame.getBlackbeardState() == GameMasterState.ANNOUNCE_ACTION) {
                    bbGame.setBlackBeardState(GameMasterState.TAKE_ACTION);
                    gameMasterStr = bbGame.gameMasterAction();
                } else if(character == 'c'
                        && currentState == GameState.PLAYING
                        && bbGame.getBlackbeardState() == GameMasterState.TAKE_ACTION) {
                    bbGame.setBlackBeardState(GameMasterState.NO_ACTION);
                } else {
                    //do nothing - do not allow player to continue and skip interference!
                }

                // Skip next player CPU
                if (character == 'c'
                        && currentState == GameState.PLAYING
                        && bbGame.whoseTurn().getCPUstate() == CPUState.WAITING
                        && bbGame.getBlackbeardState() == GameMasterState.NO_ACTION) {
                    Random rand = new Random();
                    lastRoll = (rand.nextInt(6) + 1);
                    bbGame.whoseTurn().setCPUstate(CPUState.ROLLING);
                } else if (character == 'c'
                        && currentState == GameState.PLAYING
                        && bbGame.whoseTurn().getCPUstate() == CPUState.ROLLING
                        && bbGame.getBlackbeardState() == GameMasterState.NO_ACTION) {
                    bbGame.whoseTurn().setCPUstate(CPUState.MOVING);
                } else if (character == 'c'
                        && currentState == GameState.PLAYING
                        && bbGame.whoseTurn().getCPUstate() == CPUState.MOVING
                        && bbGame.getBlackbeardState() == GameMasterState.NO_ACTION) {
                    Coordinates move = bbGame.whoseTurn().selectMove(lastRoll);
                    bbGame.whoseTurn().movePlayer(move);
                    if(bbGame.whoseTurn().hasTreasure()) {
                        currentState = GameState.WON;
                    } else {
                        bbGame.whoseTurn().setCPUstate(CPUState.WAITING);
                        bbGame.setNextTurn();
                    }
                }

                // Get key
                if (character == 't') {
                    if (gameBoard.isAt(bbGame.whoseTurn().getLocation()) == LocationName.TREASURE
                            && bbGame.whoseTurn().getPlayerKeys().size() >= 4) {
                        bbGame.whoseTurn().takeKey(LocationName.TREASURE);
                        Sound victory = bbGame.whoseTurn().getWinSound();
                        victory.play();
                        currentState = GameState.WON;
                    } else if (gameBoard.isAt(bbGame.whoseTurn().getLocation()) == LocationName.TREASURE){
                        nope.play();
                    } else {
                        Coordinates playerCoords = bbGame.whoseTurn().getLocation();
                        for (Location location : gameBoard.getLocations()) {
                            Map<Coordinates, Key> keysMap = location.getLocationKeys();
                            Set<Coordinates> coords = keysMap.keySet();
                            for (int i = 0; i < coords.size(); i++) {
                                if (coords.contains(playerCoords)) {
                                    Key key = keysMap.get(playerCoords);
                                    key.setAcquired(true);
                                    bbGame.whoseTurn().takeKey(location.getName());
                                    getItem.play();
                                }
                            }
                        }
                    }

                }

                if (character == 'd' && bbGame.whoseTurn().canDuel()) {
                for (Player player : bbGame.getAllPlayers()) {
                    if (!player.getName().equals(bbGame.whoseTurn().getName()) && bbGame.whoseTurn().getLocation().toString().equals(player.getLocation().toString())) {
                        currentState = GameState.DUEL;
                        bbGame.setDuelOver(false);
                        bbGame.setLeftDuelist(bbGame.whoseTurn());
                        bbGame.setRightDuelist(player);
                        Sound duel = bbGame.whoseTurn().getDuelSound();
                        duel.play();
                        sword.play();
                    }
                }
            }
                break;
            case PAUSED:
                break;
            case WON:
                break;
            case DUEL:
                if (!bbGame.isDuelOver()
                    && bbGame.getLeftDuelist() != null
                    && bbGame.getRightDuelist() != null) {

                // Set left duelist move
                if (character == 'q'
                        || character == 'w'
                        || character == 'e') {
                    hasLeftDuelistMoved = true;
                    leftDuelistMove = character;
                    bbGame.getLeftDuelist().setMove(character);
                }

                // Set right duelist move
                if (character == 'i'
                        || character == 'o'
                        || character == 'p') {
                    leftDuelistMove = character;
                    hasRightDuelistMoved = true;
                    bbGame.getRightDuelist().setMove(character);
                }

                if (character == 'c'
                        && bbGame.getLeftDuelist().getMove() != 'n'
                        && bbGame.getRightDuelist().getMove() != 'n') {


                    // Draw case
                    if (bbGame.getLeftDuelist().getMove() == bbGame.getRightDuelist().getMove()) {
                        bbGame.getLeftDuelist().giveKey(bbGame.getRightDuelist());
                        bbGame.getRightDuelist().getDuelWinSound().play();
                    }
                    // Win cases - Rock beats scissors
                    else if (bbGame.getLeftDuelist().getMove() == 'q' && bbGame.getRightDuelist().getMove() == 'p'
                            // Paper beats rock
                            || bbGame.getLeftDuelist().getMove() == 'w' && bbGame.getRightDuelist().getMove() == 'i'
                            // Scissors beats paper
                            || bbGame.getLeftDuelist().getMove() == 'e' && bbGame.getRightDuelist().getMove() == 'o') {
                        bbGame.getRightDuelist().giveKey(bbGame.getLeftDuelist());
                        bbGame.getLeftDuelist().getDuelWinSound().play();
                    }
                    // Lose case
                    else {
                        bbGame.getLeftDuelist().giveKey(bbGame.getRightDuelist());
                        bbGame.getRightDuelist().getDuelWinSound().play();
                    }



                    leftDuelistMove = 'n';
                    rightDuelistMove = 'n';
                    hasRightDuelistMoved = false;
                    hasLeftDuelistMoved = false;
                    bbGame.setDuelOver(true);
                    bbGame.getRightDuelist().revertMove();
                    bbGame.getLeftDuelist().revertMove();
                    bbGame.getLeftDuelist().setmovesBeforeNextDuel();
                    bbGame.setLeftDuelist(null);
                    bbGame.setRightDuelist(null);
                    currentState = GameState.PLAYING;
                }
            }
                break;
            case HOWTOPLAY:
                break;
            case OPTIONS:
                break;
            case RULES:
                break;
            case TUTORIAL:
                switch(currentTutorialState) {
                    case S1:
                        if(character == 'r') {
                            currentTutorialState = S2;
                        }
                        break;
                    case S2:
                        break;
                    case S3:
                        break;
                    case S4:
                        break;
                    case S5:
                        if(character == 't'){
                            currentTutorialState = S6;
                        }
                        break;
                    case S6:
                        break;
                    case S7:
                        if(character == 't'){
                            currentTutorialState = S8;
                        }
                        break;
                    case S8:
                        break;
                    case S9:
                        break;
                    case S10:
                        break;
                }
                break;
        }


        return false;
    }

    public boolean canMoveToSpace(Coordinates coordinates) {
        return bbGame.getBoard().isAt(coordinates) != LocationName.ROCK;
    }

    public void tryMovePlayer(int screenX, int screenY, int highlightedX, int highlightedY, int area){
        int fixedScreenY = scale(900) - screenY;

        int topLoop = highlightedY;
        int bottomLoop = highlightedY;
        int firstRightLoop = highlightedX;
        int firstLeftLoop = highlightedX;

        loop: for(int i = 1; i <= area; i++) {
            int topCell = cellSize * i;
            int xCell = cellSize * i;
            // TOP Choices
            if (screenX >= highlightedX && screenX <= highlightedX + cellSize && fixedScreenY >= highlightedY + topCell && fixedScreenY <= highlightedY + (topCell + cellSize) && area >= i) {
                Coordinates potentialMove = new Coordinates(bbGame.whoseTurn().getLocation().getX(), bbGame.whoseTurn().getLocation().getY() - i);
                if (canMoveToSpace(potentialMove)) {
                    bbGame.whoseTurn().movePlayer(potentialMove);
                    bbGame.whoseTurn().setMovement(area - i);
                    area -= i;
                    if (area == 0) {
                        bbGame.setNextTurn();
                    }
                    break loop;
                }
            }
            // Bottom Choices
            if (screenX >= highlightedX && screenX <= highlightedX + cellSize && fixedScreenY <= highlightedY - topCell + cellSize && fixedScreenY >= highlightedY - topCell && area >= i) {
                Coordinates potentialMove = new Coordinates(bbGame.whoseTurn().getLocation().getX(), bbGame.whoseTurn().getLocation().getY() + i);
                if (canMoveToSpace(potentialMove)) {
                    bbGame.whoseTurn().movePlayer(potentialMove);
                    bbGame.whoseTurn().setMovement(area - i);
                    area -= i;
                    if (area == 0) {
                        bbGame.setNextTurn();
                    }
                    break loop;
                }
            }
            // Right Choices
            if (screenX >= highlightedX + xCell && screenX <= highlightedX + xCell + cellSize && fixedScreenY >= highlightedY && fixedScreenY <= highlightedY + cellSize && area >= i) {
                Coordinates potentialMove = new Coordinates(bbGame.whoseTurn().getLocation().getX() + i, bbGame.whoseTurn().getLocation().getY());
                if (canMoveToSpace(potentialMove)) {
                    bbGame.whoseTurn().movePlayer(potentialMove);
                    bbGame.whoseTurn().setMovement(area - i);
                    area -= i;
                    if (area == 0) {
                        bbGame.setNextTurn();
                    }
                    break loop;
                }
            }
            // Left Choices
            if (screenX >= highlightedX - xCell && screenX <= highlightedX - xCell + cellSize && fixedScreenY >= highlightedY && fixedScreenY <= highlightedY + cellSize && area >= i) {
                Coordinates potentialMove = new Coordinates(bbGame.whoseTurn().getLocation().getX() - i, bbGame.whoseTurn().getLocation().getY());
                if (canMoveToSpace(potentialMove)) {
                    bbGame.whoseTurn().movePlayer(potentialMove);
                    bbGame.whoseTurn().setMovement(area - i);
                    area -= i;
                    if (area == 0) {
                        bbGame.setNextTurn();
                    }
                    break loop;
                }
            }

            topLoop += cellSize;
            bottomLoop -= cellSize;
            firstRightLoop += cellSize;
            firstLeftLoop -= cellSize;

            int row = area - (i - 1);

            int rightLoop = highlightedX;
            int leftLoop = highlightedX;
            for (int j = 1; j <= row - 1; j++) {
                rightLoop += cellSize;
                leftLoop -= cellSize;
                // Right Bottom
                if(screenX >= rightLoop && screenX <= rightLoop+cellSize && screenY <= bottomLoop + cellSize && screenY >= bottomLoop) {
                    Coordinates potentialMove = new Coordinates(bbGame.whoseTurn().getLocation().getX() + ((rightLoop - highlightedX)/cellSize), bbGame.whoseTurn().getLocation().getY() - ((highlightedY-bottomLoop)/cellSize));
                    if (canMoveToSpace(potentialMove)) {
                        bbGame.whoseTurn().movePlayer(potentialMove);
                        bbGame.whoseTurn().setMovement(area - i);
                        area -= i;
                        if (area == 0) {
                            bbGame.setNextTurn();
                        }
                        break loop;
                    }
                }
                // Left Bottom
                if (leftLoop >= 0 && leftLoop <= scale(900) && bottomLoop >= scale(200) && bottomLoop <= scale(900)) {
                    //batch.draw(highlightedArea, leftLoop, bottomLoop);
                }

                // Right Top
                if (rightLoop >= 0 && rightLoop <= scale(900) && topLoop >= scale(200) && topLoop <= scale(900)) {
                    //batch.draw(highlightedArea, rightLoop, topLoop);
                }
                // Left Top
                if (leftLoop >= 0 && leftLoop <= scale(900) && topLoop >= scale(200) && topLoop <= scale(900)) {
                    //batch.draw(highlightedArea, leftLoop, topLoop);
                }
            }
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Mouse touch to play the game
        switch (currentState) {
            case SETUP:
                if (screenX >= scale(349) && screenX <= scale(349 + 188) && screenY >= scale(379) && screenY <= scale(379 + 52)) {
                    currentState = GameState.CHARACTERSELECT;
                }
                if (screenX >= scale(332) && screenX <= scale(332 + 223) && screenY >= scale(451) && screenY <= scale(451 + 59)) {
                    currentState = GameState.HOWTOPLAY;
                }
                if (screenX >= scale(330) && screenX <= scale(550) && screenY >= scale(530) && screenY <= scale(530+60)) {
                    currentState = GameState.TUTORIAL;
                }
                break;
            case CHARACTERSELECT:
                if (screenX >= scale(321) && screenX <= scale(320 + 250) && screenY >= scale(407) && screenY <= scale(407 + 86)) {
                    currentState = GameState.PLAYING;
                    createPlayers();
                }

                // Player 1 choice
                if (screenX >= scale(229) && screenX <= scale(229 + 157) && screenY >= scale(332) && screenY <= scale(332 + 54)) {
                    isPlayer1Computer = !isPlayer1Computer;
                }

                // Player 2 choice
                if (screenX >= scale(681) && screenX <= scale(681 + 157) && screenY >= scale(332) && screenY <= scale(332 + 54)) {
                    isPlayer2Computer = !isPlayer2Computer;
                }

                // Player 1 choice
                if (screenX >= scale(229) && screenX <= scale(229 + 157) && screenY >= scale(781) && screenY <= scale(781 + 54)) {
                    isPlayer3Computer = !isPlayer3Computer;
                }

                // Player 2 choice
                if (screenX >= scale(681) && screenX <= scale(681 + 157) && screenY >= scale(781) && screenY <= scale(781 + 54)) {
                    isPlayer4Computer = !isPlayer4Computer;
                }
                break;
            case PLAYING:
                // Show highlighted area player can move
                int area = bbGame.whoseTurn().getMovement();
                int highlightedX = coordinateXtoPixels(bbGame.whoseTurn().getLocation());
                int highlightedY = coordinateYtoPixels(bbGame.whoseTurn().getLocation());
                tryMovePlayer(screenX,screenY,highlightedX,highlightedY,area);

                break;
            case PAUSED:
                if(screenX >= scale(348) && screenX <= scale(573) && screenY >= scale(379) && screenY <= scale(410)) {currentState = GameState.PLAYING;}
                if(screenX >= scale(409) && screenX <= scale(505) && screenY >= scale(438) && screenY <= scale(463)) {currentState = GameState.RULES;}
                if(screenX >= scale(388) && screenX <= scale(531) && screenY >= scale(496) && screenY <= scale(519)) {currentState = GameState.OPTIONS;}
                if(screenX >= scale(372) && screenX <= scale(546) && screenY >= scale(549) && screenY <= scale(576)) {currentState = GameState.SETUP;}
                break;
            case WON:
                if(screenX >= 700 && screenX <= 900 && screenY >= 800 && screenY <= 900) {currentState = GameState.SETUP;}
                break;
            case DUEL:
                break;
            case HOWTOPLAY:
                if (screenX >= scale(169) && screenX <= scale(169 + 98) && screenY >= scale(23) && screenY <= scale(23 + 35)) {
                    currentState = GameState.SETUP;
                }

                break;
            case OPTIONS:
                if(screenX >= scale(685) && screenX <= scale(765) && screenY >= scale(232) && screenY <= scale(268)) {currentState = GameState.PAUSED;}
                if(screenX >= scale(184) && screenX <= scale(203) && screenY >= scale(245) && screenY <= scale(480)) {bg.setVolume(0f);}
                if(screenX >= scale(252) && screenX <= scale(272) && screenY >= scale(245) && screenY <= scale(480)) {bg.setVolume(0.2f);}
                if(screenX >= scale(322) && screenX <= scale(342) && screenY >= scale(245) && screenY <= scale(480)) {bg.setVolume(0.4f);}
                if(screenX >= scale(395) && screenX <= scale(415) && screenY >= scale(245) && screenY <= scale(480)) {bg.setVolume(0.6f);}
                if(screenX >= scale(465) && screenX <= scale(485) && screenY >= scale(245) && screenY <= scale(480)) {bg.setVolume(0.8f);}
                if(screenX >= scale(540) && screenX <= scale(560) && screenY >= scale(245) && screenY <= scale(480)) {bg.setVolume(1.0f);}
                if(screenX >= scale(232) && screenX <= scale(310) && screenY >= scale(563) && screenY <= scale(658)) {bbGame.setDifficulty(Difficulty.EASY);}
                if(screenX >= scale(395) && screenX <= scale(515) && screenY >= scale(563) && screenY <= scale(658)) {bbGame.setDifficulty(Difficulty.MEDIUM);}
                if(screenX >= scale(587) && screenX <= scale(675) && screenY >= scale(563) && screenY <= scale(658)) {bbGame.setDifficulty(Difficulty.HARD);}

                break;
            case RULES:
                // Back button returns to pause menu when displayed in game
                // TODO implement rules screen in start menu
                if(screenX >= 0 && screenX <= scale(100) && screenY >= 0 && screenY <= scale(100)) {currentState = GameState.PAUSED;}
                break;
            case TUTORIAL:
                if(screenX >= 0 && screenX <= scale(150) && screenY >= scale(820) && screenY <= scale(900)) {currentState = GameState.SETUP;}
                switch(currentTutorialState){
                    case S1:
                        if(screenX >= scale(750) && screenX <= scale(900) && screenY >= scale(750) && screenY <= scale(870)) {currentTutorialState = S2;}
                        break;
                    case S2:
                        if(screenX >= scale(14) && screenX <= scale(29) && screenY >= scale(517) && screenY <= scale(532)) {currentTutorialState = S4;}
                        break;
                    case S3:
                        if(screenX >= scale(14) && screenX <= scale(29) && screenY >= scale(517) && screenY <= scale(532)) {currentTutorialState = S4;}
                        break;
                    case S4:
                        if(screenX >= 0 && screenX <= 900 && screenY >= 0 && screenY <= 820) {currentTutorialState = S5;}
                        break;
                    case S5:
                        break;
                    case S6:
                        if(screenX >= 0 && screenX <= scale(900) && screenY >= 0 && screenY <= scale(820)) {currentTutorialState = S7;}
                        break;
                    case S7:
                        break;
                    case S8:
                        if(screenX >= 0 && screenX <= scale(900) && screenY >= 0 && screenY <= scale(820)) {currentTutorialState = S9;}
                        break;
                    case S9:
                        if(screenX >= 0 && screenX <= scale(900) && screenY >= scale(72) && screenY <= scale(900)) {currentTutorialState = S10;}
                        if(screenX >= scale(775) && screenX <= scale(900) && screenY >= 0 && screenY <= scale(71)) {
                            currentTutorialState = S1;
                            currentState = GameState.SETUP;
                        }
                        break;
                    case S10:
                        if(screenX >= scale(0) && screenX <= scale(100) && screenY >= scale(21) && screenY <= scale(100)) {currentTutorialState = S9;}
                        if(screenX >= scale(775) && screenX <= scale(900) && screenY >= 0 && screenY <= scale(71)) {
                            currentTutorialState = S1;
                            currentState = GameState.SETUP;
                        }
                        if(screenX >= scale(327) && screenX <= scale(327+232) && screenY >= 840 && screenY <= scale(900)) {
                            currentTutorialState = S1;
                            currentState = GameState.CHARACTERSELECT;
                        }
                        break;
                }
                break;
        }


        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private void populatePlayerNames() {
        if (bbGame.whoseTurn() == players[0]) {
            playCurrentFont.draw(batch, players[0].getName(), scale(750), scale(680));
            playFont.draw(batch, players[1].getName(), scale(760), scale(580));
            playFont.draw(batch, players[2].getName(), scale(760), scale(480));
            playFont.draw(batch, players[3].getName(), scale(750), scale(380));
        } else if (bbGame.whoseTurn() == players[1]) {
            playFont.draw(batch, players[0].getName(), scale(750), scale(680));
            playCurrentFont.draw(batch, players[1].getName(), scale(760), scale(580));
            playFont.draw(batch, players[2].getName(), scale(760), scale(480));
            playFont.draw(batch, players[3].getName(), scale(750), scale(380));
        } else if (bbGame.whoseTurn() == players[2]) {
            playFont.draw(batch, players[0].getName(), scale(750), scale(680));
            playFont.draw(batch, players[1].getName(), scale(760),scale( 580));
            playCurrentFont.draw(batch, players[2].getName(), scale(760), scale(480));
            playFont.draw(batch, players[3].getName(), scale(750), scale(380));
        } else {
            playFont.draw(batch, players[0].getName(), scale(750),scale( 680));
            playFont.draw(batch, players[1].getName(), scale(760),scale( 580));
            playFont.draw(batch, players[2].getName(), scale(760),scale( 480));
            playCurrentFont.draw(batch, players[3].getName(), scale(750), scale(380));
        }
    }

    private void drawKeys() {
        // Draw keys
        if (players[0].getPlayerKeys().size() > 0) {
            for (Key key : players[0].getPlayerKeys()) {
                if (key != null) {
                    LocationName keyType = key.getDefaultLocation();
                    switch (keyType) {
                        case SHADE:
                            batch.draw(key.getKeyCanvas(), scale(700),scale( 600));
                            break;
                        case CUTLASS:
                            batch.draw(key.getKeyCanvas(), scale(750), scale(600));
                            break;
                        case RUM:
                            batch.draw(key.getKeyCanvas(), scale(850), scale(600));
                            break;
                        case PARROT:
                            batch.draw(key.getKeyCanvas(), scale(800), scale(600));
                            break;
                    }
                }
            }
        }
        if (players[1].getPlayerKeys().size() > 0) {
            for (Key key : players[1].getPlayerKeys()) {
                if (key != null) {
                    LocationName keyType = key.getDefaultLocation();
                    switch (keyType) {
                        case SHADE:
                            batch.draw(key.getKeyCanvas(), scale(700), scale(500));
                            break;
                        case CUTLASS:
                            batch.draw(key.getKeyCanvas(), scale(750),scale( 500));
                            break;
                        case RUM:
                            batch.draw(key.getKeyCanvas(), scale(850),scale(500));
                            break;
                        case PARROT:
                            batch.draw(key.getKeyCanvas(), scale(800),scale(500));
                            break;
                    }
                }
            }
        }
        if (players[2].getPlayerKeys().size() > 0) {
            for (Key key : players[2].getPlayerKeys()) {
                if (key != null) {
                    LocationName keyType = key.getDefaultLocation();
                    switch (keyType) {
                        case SHADE:
                            batch.draw(key.getKeyCanvas(), scale(700),scale( 400));
                            break;
                        case CUTLASS:
                            batch.draw(key.getKeyCanvas(), scale(750),scale(400));
                            break;
                        case RUM:
                            batch.draw(key.getKeyCanvas(), scale(850),scale(400));
                            break;
                        case PARROT:
                            batch.draw(key.getKeyCanvas(), scale(800), scale(400));
                            break;
                    }
                }
            }
        }
        if (players[3].getPlayerKeys().size() > 0) {
            for (Key key : players[3].getPlayerKeys()) {
                if (key != null) {
                    LocationName keyType = key.getDefaultLocation();
                    switch (keyType) {
                        case SHADE:
                            batch.draw(key.getKeyCanvas(), scale(700), scale(300));
                            break;
                        case CUTLASS:
                            batch.draw(key.getKeyCanvas(), scale(750), scale(300));
                            break;
                        case RUM:
                            batch.draw(key.getKeyCanvas(), scale(850), scale(300));
                            break;
                        case PARROT:
                            batch.draw(key.getKeyCanvas(), scale(800), scale(300));
                            break;
                    }
                }
            }
        }

        Location[] locations = gameBoard.getLocations();
        for (Location location : locations) {
            Map<Coordinates, Key> keysMap = location.getLocationKeys();
            Set<Coordinates> coords = keysMap.keySet();
            for (Coordinates c : coords) {
                Key keyToDraw = keysMap.get(c);
                if (!keyToDraw.hasBeenAcquired()) {
                    batch.draw(keyToDraw.getKeyCanvas(), coordinateXtoPixels(keyToDraw.getStartPosition()), coordinateYtoPixels(keyToDraw.getStartPosition()));
                }
            }
        }
    }

    private void drawPlayers() {
        // Draw players
        for (Player player : bbGame.getAllPlayers()) {
            Texture highlight = new Texture("images/"+retinaURL()+"highlight.png");
            Texture boat = new Texture("images/"+retinaURL()+"boat.png");
            if (player == bbGame.whoseTurn()) {
                batch.draw(highlight, player.getX(), player.getY());
            }
            batch.draw(player.getAvatarCanvas(), player.getX(), player.getY());
            if (gameBoard.isAt(player.getLocation()) == LocationName.SEA) {
                batch.draw(boat, player.getX(), player.getY());
            }
        }
    }

    private void renderDice() {
        if (bbGame.whoseTurn() instanceof HumanPlayer) {
            if (diceRolling) {
                elapsedTime += Gdx.graphics.getDeltaTime();
                batch.draw((TextureRegion) diceAnimation.getKeyFrame(elapsedTime, true), scale(695),scale( 10));
                if (diceAnimation.isAnimationFinished(elapsedTime)) {
                    elapsedTime = 0;
                    diceRolling = false;
                }
            } else {
                batch.draw(diceFrames[lastRoll - 1], scale(695),scale( 10));
            }
        } else {
            if(bbGame.whoseTurn().getCPUstate() == CPUState.ROLLING) {
                elapsedTime += Gdx.graphics.getDeltaTime();
                batch.draw((TextureRegion) diceAnimation.getKeyFrame(elapsedTime, true), scale(695),scale( 10));
                if (diceAnimation.isAnimationFinished(elapsedTime)) {
                    elapsedTime = 0;
                    batch.draw(diceFrames[lastRoll - 1], scale(695), scale(10));
                }
            } else {
                    batch.draw(diceFrames[lastRoll - 1], scale(695), scale(10));
            }
        }
    }
}