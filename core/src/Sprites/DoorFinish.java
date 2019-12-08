package Sprites;

import Screens.Level_1.Level_1;
import Screens.WinScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DoorFinish extends InteractiveTileObject {
    private GazeintoAbyss game;
    private Player player;
    private WinScreen WScreen;
    private Vector2 newGameCamPosition;
    private double nextMaxRight;
    private Vector2 nextPlayerPosition;
    protected String lock;
    protected boolean isLock;
    protected Level_1 prevLevel;
    protected boolean setToDispose;
    protected boolean hasDestroyed;

    public DoorFinish(GazeintoAbyss game, World world, TiledMap map, MapObject object,
                     Player player, WinScreen WScreen, boolean isLock, String lock, Level_1 prevLevel) {
        super(world, map , object, true);
        fixture.setUserData(this);
        this.game = game;
        this.player = player;
        this.WScreen = WScreen;
        this.isLock = isLock;
        this.lock = lock;
        this.prevLevel = prevLevel;
        setToDispose = false;
        hasDestroyed = false;
    }

    @Override
    public void onHit() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            //cek if door locked
            if(isLock) {
                ArrayList<Key> keys;
                keys = player.getKeys();
                boolean cek = false;
                for(Key key: keys) {
                    if(key.getId() == lock) {
                    	GazeintoAbyss.manager.get("Resources/Sound/door-unlock.mp3",Sound.class).play();
                    	GazeintoAbyss.manager.get("Resources/Sound/door-open.ogg",Sound.class).play();
                        System.out.println("Door open");
                        isLock = false;
                        setToDispose = true;
                        game.setScreen(WScreen);
                        /*WScreen.getGamecam().position.set(newGameCamPosition,0);
                        WScreen.setMaxRight(nextMaxRight);
                        player.setNextLevelPosition(nextPlayerPosition, nextLevel.getWorld());

                        try(FileWriter fileWriter = new FileWriter("Save_Files.txt")){
                            fileWriter.write(player.toString());
                        } catch (IOException e) {
                            System.out.println("File Error!");
                        }
                        cek = true;*/
                    }
                }
                if(!cek) {
                	GazeintoAbyss.manager.get("Resources/Sound/door-locked.mp3",Sound.class).play();
                    System.out.println("Door Locked");
                }
            }
            else {
            	GazeintoAbyss.manager.get("Resources/Sound/door-open.mp3",Sound.class).play();
                setToDispose = true;
                game.setScreen(WScreen);
                /*nextLevel.getGamecam().position.set(newGameCamPosition,0);
                nextLevel.setMaxRight(nextMaxRight);
                player.setNextLevelPosition(nextPlayerPosition, nextLevel.getWorld());

                try(FileWriter fileWriter = new FileWriter("Save_Files.txt")){
                    fileWriter.write(player.toString());
                } catch (IOException e) {
                    System.out.println("File Error!");
                }*/
            }
        }
    }

    public boolean isSetToDispose() {
        return setToDispose;
    }

    public void setSetToDispose(boolean setToDispose) {
        this.setToDispose = setToDispose;
    }

    public boolean isHasDestroyed() {
        return hasDestroyed;
    }

    public void setHasDestroyed(boolean hasDestroyed) {
        this.hasDestroyed = hasDestroyed;
    }
}
