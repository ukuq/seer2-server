package seer2.pet;

import seer2.hu.Vector;


public class PetFightStarInfo {
    public Vector<Integer> type,level;
    public PetFightStarInfo(){
        type = new Vector<>();
        level = new Vector<>();
    }
    public void add(int level,int type){
        this.level.push(level);
        this.type.push(type);
    }
    public void clear(){
        this.level.clear();
        this.type.clear();
    }
}
