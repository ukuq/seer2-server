package seer2.server.config;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import seer2.server.constants.PetAttribute;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.*;

public class Monster {
    public int numbersId, id, type, growthType, yieldingExp, catchRate, yieldingEV, evolvesFrom, evolvesTo, evolvingLv, freeForbidden, starLv, features;
    public int minHeight, maxHeight, minWeight, maxWeight;
    // ="39 44" Weight="7 12" ="1001" Gender="0 10"
    public List<Integer> skillIds;
    public String name;
    private static Monster none;
    private static Map<Integer, Monster> map;
    public final int[] basic = new int[6];
    public static List<Integer> keys;

    public static boolean valid(int id) {
        return map.containsKey(id);
    }

    public static Monster get(int id) {
        Monster s = map.get(id);
        if (s == null) {
            return none;
        }
        return s;
    }

    {
        skillIds = new ArrayList<>();
    }


    public static int getRandomId() {
        return keys.get(new Random().nextInt(keys.size()));
    }

    static {
        none = new Monster();
        none.name = "无效精灵";
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse("src/config/pet-config.xml", new MonsterParserHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Monster{" +
                "numbersId=" + numbersId +
                ", id=" + id +
                ", type=" + type +
                ", growthType=" + growthType +
                ", yieldingExp=" + yieldingExp +
                ", catchRate=" + catchRate +
                ", yieldingEV=" + yieldingEV +
                ", evolvesFrom=" + evolvesFrom +
                ", evolvesTo=" + evolvesTo +
                ", evolvingLv=" + evolvingLv +
                ", freeForbidden=" + freeForbidden +
                ", starLv=" + starLv +
                ", features=" + features +
                ", minHeight=" + minHeight +
                ", maxHeight=" + maxHeight +
                ", minWeight=" + minWeight +
                ", maxWeight=" + maxWeight +
                ", skillIds=" + skillIds +
                ", name='" + name + '\'' +
                ", basic=" + Arrays.toString(basic) +
                "}\n";
    }

    public static void main(String[] args) {
        System.out.println(map);
    }

    static class MonsterParserHandler extends DefaultHandler {

        private Monster monster;


        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            map = new HashMap<>();
            keys = new ArrayList<>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            String tmp;
            if (qName.equals("Monster")) {
                //YieldingExp="125" CatchRate="255" YieldingEV="1" EvolvesFrom="2" EvolvesTo="0" EvolvFlag="77" EvolvingLv="100"  Height="97 102" Weight="26 31" Features="1001" Gender="0 10" FreeForbidden="0" StarLv="2"
                monster = new Monster();
                monster.numbersId = Integer.parseInt(attributes.getValue("NumbersID"));
                monster.id = Integer.parseInt(attributes.getValue("ID"));
                monster.name = attributes.getValue("DefName");
                monster.type = Integer.parseInt(attributes.getValue("Type"));
                monster.basic[PetAttribute.ATK] = Integer.parseInt(attributes.getValue("Atk"));
                monster.basic[PetAttribute.DEF] = Integer.parseInt(attributes.getValue("Def"));
                monster.basic[PetAttribute.SPATK] = Integer.parseInt(attributes.getValue("SpAtk"));
                monster.basic[PetAttribute.SPDEF] = Integer.parseInt(attributes.getValue("SpDef"));
                monster.basic[PetAttribute.SPEED] = Integer.parseInt(attributes.getValue("Spd"));
                monster.basic[PetAttribute.HP] = Integer.parseInt(attributes.getValue("HP"));
                tmp = attributes.getValue("EvolvesFrom");
                monster.evolvesFrom = tmp == null ? 0 : Integer.parseInt(tmp);
                tmp = attributes.getValue("EvolvesTo");
                monster.evolvesTo = tmp == null ? 0 : Integer.parseInt(tmp);
                map.put(monster.id, monster);
                keys.add(monster.id);
            } else if (qName.equals("Move")) {
                monster.skillIds.add(Integer.parseInt(attributes.getValue("ID")));
            }
        }
    }
}
