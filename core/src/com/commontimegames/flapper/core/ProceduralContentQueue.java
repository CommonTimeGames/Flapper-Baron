package com.commontimegames.flapper.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by c14726 on 5/3/14.
 */
public class ProceduralContentQueue {
    private LinkedList <GameObject> contentQueue;
    private LinkedList <GameObject> tempQueue;

    public ProceduralContentQueue(){
        contentQueue = new LinkedList<GameObject>();
        tempQueue = new LinkedList<GameObject>();
    }

    public void add(GameObject g){
        contentQueue.add(g);
    }

    public void scroll(){
        for(GameObject g: contentQueue){
            g.scroll(Constants.WORLD_SCROLL_SPEED);
        }
    }

    public void recycleOffScreen(){

        for(Iterator<GameObject> i = contentQueue.iterator();
                i.hasNext();){

            GameObject g = i.next();

            if(g.isOffscreen()){
                i.remove();
                tempQueue.addFirst(g);
            }
        }
        int offsetCounter = 0;

        for(Iterator<GameObject> i = tempQueue.iterator();
                i.hasNext();){

            GameObject g = i.next();

            g.reset();
            g.setPositionY(Constants.WORLD_HEIGHT
                            + (Constants.CONTENT_OFFSET * offsetCounter));
            contentQueue.addFirst(g);

            offsetCounter++;
        }

        tempQueue.clear();
    }

}
