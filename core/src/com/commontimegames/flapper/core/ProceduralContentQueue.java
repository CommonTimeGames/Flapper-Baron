package com.commontimegames.flapper.core;

import java.util.LinkedList;

/**
 * Created by c14726 on 5/3/14.
 */
public class ProceduralContentQueue {
    private LinkedList <GameObject> contentQueue;

    public ProceduralContentQueue(){
        contentQueue = new LinkedList<GameObject>();
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
        GameObject g = contentQueue.peekLast();

        if(g.isOffscreen()){
            contentQueue.removeLast();

            GameObject head = contentQueue.peekFirst();

            g.setPositionY(head.getPositionY());
            g.reset();

            contentQueue.addFirst(g);
        }

    }

}
