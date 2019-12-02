package Sprites;

import com.badlogic.gdx.physics.box2d.World;

public class Key {
	public String id;
	public Key() {
		this.id = "";
	}
	public Key(String id) {
		this.id = id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
}
