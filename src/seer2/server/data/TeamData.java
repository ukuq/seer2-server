package seer2.server.data;

import seer2.hu.ByteArray;

public class TeamData{
    int id,logoFront,logoBack,logoColor,dissolveDay,userTotalContribute;
    byte userPost;
    String name;

    {
        name="无名";
    }

    public void writeBase(ByteArray b) {
        b.writeUnsignedInt(id);
        b.writeUTFBytes(name,25);
        b.writeVar(logoFront,logoBack,logoColor);
    }
    public void writeMore(ByteArray b) {
        b.writeVar(dissolveDay,userPost,userTotalContribute,0);
    }
}
