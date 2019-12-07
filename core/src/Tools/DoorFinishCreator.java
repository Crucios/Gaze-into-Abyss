package Tools;

import Screens.Level_1.Level_1;
import Screens.WinScreen;
import Sprites.DoorFinish;
import Sprites.DoorLevel;
import Sprites.Player;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.gazeintoabyss.GazeintoAbyss;

public class DoorFinishCreator {
    DoorFinish doorFinish;
    public DoorFinishCreator(GazeintoAbyss game, World world, TiledMap map, Player player, WinScreen winScreen,
                             String nameObject,boolean isLock,String lock, Level_1 prevLevel)
    {
        for(MapObject object : map.getLayers().get(nameObject).getObjects().getByType(RectangleMapObject.class)) {
            doorFinish = new DoorFinish(game, world, map, object, player, winScreen,isLock,lock, prevLevel);
        }
    }
}
