package seer2.server.fight;

import seer2.server.config.PetSkill;
import seer2.server.data.PetData;
import seer2.server.utils.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Fighter {
    public List<FightPet> pets;
    public FightPet fightPet;

    public BuffEffect buffEffect;
    private int anger;
    private PetSkill skill;

    public int uid;
    public byte side;

    private static final float[] six = {0.25f, 0.29f, 0.33f, 0.4f, 0.5f, 0.67f, 1, 1.5f, 2.0f, 2.5f, 3.0f, 3.5f, 4.0f};

    public Fighter(int uid, List<PetData> petDataList) {
        this.uid = uid;
        this.pets = petDataList.stream().map(FightPet::new).filter(p -> p.getHp() > 0).limit(6).collect(Collectors.toList());
        fightPet = pets.get(0);
        fightPet.position = 1;
    }

    public boolean hasAlivePet() {
        return pets.stream().noneMatch(p -> p.getHp() > 0);
    }

    public int getAtk(int category) {
        return category == 1 ? fightPet.getAtk() : (
                category == 2 ? fightPet.getSpAtk() : (
                        category >= 80 ? (int) ((fightPet.getAtk() + fightPet.getSpAtk()) * 0.6) : 0
                ));
    }

    public int getDef(int category) {
        return category == 1 ? fightPet.getDef() : (
                category == 2 ? fightPet.getSpDef() : (
                        category >= 80 ? (int) ((fightPet.getDef() + fightPet.getSpDef()) * 0.6) : 0
                ));
    }


    public int getAnger() {
        return anger;
    }

    public void setAnger(int anger) {
        this.anger = Util.between(anger, 0, 100);
    }

    public void addAnger(int anger) {
        setAnger(this.anger + anger);
    }


    public int addHp(int hp) {
        var o = fightPet.getHp();
        fightPet.setHp(fightPet.getHp() + hp);
        return fightPet.getHp() - o;
    }

    public boolean isDying() {
        return this.fightPet.getHp() <= 0;
    }

    public PetSkill getSkill() {
        return skill == null ? PetSkill.get(0) : skill;
    }

    public void setSkillById(int id) {
        this.skill = fightPet.getSkillIds().contains(id) ? PetSkill.get(id) : PetSkill.get(0);
    }
}
