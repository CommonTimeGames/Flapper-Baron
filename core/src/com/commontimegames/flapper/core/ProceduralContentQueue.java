package com.commontimegames.flapper.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by c14726 on 5/3/14.
 */
public class ProceduralContentQueue {
    private LinkedList <ProceduralContent> contentQueue;
    private LinkedList <ProceduralContent> tempQueue;

    public ProceduralContentQueue(){
        contentQueue = new LinkedList<ProceduralContent>();
        tempQueue = new LinkedList<ProceduralContent>();
    }

    public void add(ProceduralContent g){
        contentQueue.add(g);
    }

    public void scroll(){
        for(ProceduralContent g: contentQueue){
            g.scroll(Constants.WORLD_SCROLL_SPEED);
        }
    }

    public void recycleOffScreen(){

        for(Iterator<ProceduralContent> i = contentQueue.iterator();
                i.hasNext();){

            ProceduralContent p = i.next();

            if(p.isOffscreen()){
                i.remove();
                tempQueue.addFirst(p);
            }
        }

        for(Iterator<ProceduralContent> i = tempQueue.iterator();
                i.hasNext();){
            ProceduralContent p = i.next();
            p.reset();
            contentQueue.addFirst(p);
        }

        tempQueue.clear();
    }

    public interface ProceduralContent{
        /* For recycling procedural objects */
        public boolean isOffscreen();
        public void scroll(float amount);
        public void reset();
    }

}
