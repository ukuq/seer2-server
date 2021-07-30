package seer2.server.fight;

public class RoundRecord {
    public int atker;
    public int defener;
    public int atkerId;
    public int defenerId;
    public int skillId;
    public int angerConsume;
    public int round;
    public int reason;
    public int damage;
    public int superHit;
    public int atkTimes;
    public float random;

    public BuffEffect[] effects;

    public int faster;

    public static final int REASON_PET_DYING = 1;
    public static final int REASON_CAN_NOT_MOVE = 1;
    public static final int REASON_SKILL_NOT_EXIST = 2;
    public static final int REASON_ANGER_NOT_ENOUGH = 3;
    public static final int REASON_SKILL_NOT_HIT = 4;
    public static final int REASON_SKILL_MISS = 5;

    @Override
    public String toString() {
        return "RoundRecord{" +
                "atker=" + atker +
                ", defener=" + defener +
                ", atkerId=" + atkerId +
                ", defenerId=" + defenerId +
                ", skillId=" + skillId +
                ", angerConsume=" + angerConsume +
                ", round=" + round +
                ", reason=" + reason +
                ", damage=" + damage +
                ", superHit=" + superHit +
                ", atkTimes=" + atkTimes +
                ", random=" + random +
                '}';
    }
}
