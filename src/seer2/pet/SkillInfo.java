package seer2.pet;

import seer2.hu.ByteArray;
import seer2.hu.Message;
import seer2.hu.Vector;
import seer2.message.LoginInfo;
import seer2.user.UserInfo;

public class SkillInfo {
    public int id;
    public String effectId;
    public String soundId;
    public SkillDefinition definition;
    public boolean isHideSkill = false;
    public SkillInfo(int id){
        this.id=id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

    public static Vector<SkillInfo> parseSkills(ByteArray param1){
        Vector<SkillInfo> vector = new Vector<>();
        int c1 = param1.readUnsignedInt();
        for (int i = 0; i < c1; i++) {
            vector.push(new SkillInfo(param1.readUnsignedInt()));
        }
        return vector;
    }

    public static void main(String[] args) {
        ByteArray ba = new ByteArray(Message.trans("d5, 6e, a9, 35, 57, 50, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, d2, 01, 00, 00, 85, 01, 00, 00, 00, 00, 00, 00, fb, 8d, 6a, 5b, 06, 00, 00, 00, a3, 86, 01, 00, d5, 86, 01, 00, d6, 86, 01, 00, d7, 86, 01, 00, 9f, 87, 01, 00, 5a, 89, 01, 00, 00, 00, 00, 00, 01, 00, 00, 00, 54, cb, 95, 5e, 00, 0a, 00, 00, 01, 64, 11, 00, 00, 00, 1b, 00, 00, 00, 85, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 16, 00, 00, 00, 14, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 32, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 02, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, b0, 04, 00, 00, 00, 01, 4e, 4f, 4e, 4f, 2e, 33, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00"));
        var u = new UserInfo();
        u.parseEnterMap(ba);
        System.out.println();

    }
}
