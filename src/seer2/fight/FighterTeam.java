package seer2.fight;

import seer2.hu.Vector;

public class FighterTeam {

    public TeamInfo teamInfo;
    public Vector<Fighter> fighterVec;
    public Vector<FighterInfo> fighterInfoVec;
    public Vector<Fighter> changeFighterVec;
    public Vector<FighterInfo> changeFighterInfoVec;
    public Vector<Fighter> initFighterVec;
    public FighterTeam(TeamInfo param1)
    {
        this.teamInfo = param1;
        this.fighterVec = new Vector<>();
        this.fighterInfoVec = new Vector<>();
        this.changeFighterVec = new Vector<>();
        this.changeFighterInfoVec = new Vector<>();
        this.initFighterVec = new Vector<>();
//        for (FightUserInfo fui : this.teamInfo.fightUserInfoVec)
//        {
//            for(FighterInfo fi : fui.fighterInfoVec)
//            {
//                this.fighterInfoVec.push(fi);
//            }
//            for (FighterInfo fi : fui.changeFighterInfoVec)
//            {
//                this.changeFighterInfoVec.push(fi);
//            }
//        }
    }
}
