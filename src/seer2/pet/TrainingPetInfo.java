package seer2.pet;

import seer2.hu.ByteArray;

public class TrainingPetInfo extends PetInfo{
    public int id,battlePotential,startTrainingTime,fightExp;
    public TrainingPetInfo(ByteArray param1) {
        this.resourceId = param1.readUnsignedInt();
        this.id = param1.readUnsignedInt();
        this.level = param1.readUnsignedInt();
        this.character = param1.readUnsignedInt();
        this.battlePotential = param1.readUnsignedInt();
        this.startTrainingTime = param1.readUnsignedInt();
        this.fightExp = param1.readUnsignedInt();
    }
}
