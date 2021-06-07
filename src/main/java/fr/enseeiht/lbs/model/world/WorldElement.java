package main.java.fr.enseeiht.lbs.model.world;


public enum WorldElement {
	PLAIN, //-> No effect
	DESERT,//-> speed*0.7
	WATER,//-> speed*0.3 + cooldown*1.5
	FOREST,// ??less accuracy from long range, speed*0.8 for heavy units and flying units
	//LAVA, //??get burning buff while in the Lava, speed*1.5
	//SNOW //?? get freezing buff, speed*0.7
	ROCK;//troops can't get into it and have to

}