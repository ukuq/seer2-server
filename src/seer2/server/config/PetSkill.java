package seer2.server.config;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.HashMap;

public class PetSkill {
    public int id;
    public String name;
    public int category;
    public int type,power,accuracy,anger;
    public String tips;

    private static HashMap<Integer,PetSkill> map;
    private static PetSkill none;
    public static PetSkill get(int id){
        PetSkill s =  map.get(id);
        if(s==null){
            return none;
        }
        return s;
    }


    static {
        none = new PetSkill();
        none.tips = none.name="无效技能";
        map = new HashMap<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try{
            SAXParser parser = factory.newSAXParser();
            parser.parse("src/config/skill-moves.xml", new PetSkillParserHandler());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "PetSkill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", power=" + power +
                ", accuracy=" + accuracy +
                ", anger=" + anger +
                ", tips='" + tips + '\'' +
                '}'+"\n";
    }

    public static void main(String[] args) {
        System.out.println(PetSkill.get(10002));

        System.out.println();

    }

    static class PetSkillParserHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(qName.equals("Move")){
                PetSkill skill = new PetSkill();
                skill.id = Integer.parseInt(attributes.getValue("ID"));
                skill.name = attributes.getValue("Name");
                skill.category = Integer.parseInt(attributes.getValue("Category"));
                skill.type = Integer.parseInt(attributes.getValue("Type"));
                skill.power = Integer.parseInt(attributes.getValue("Power"));
                skill.accuracy = Integer.parseInt(attributes.getValue("Accuracy"));
                skill.anger = Integer.parseInt(attributes.getValue("Anger"));
                skill.tips = attributes.getValue("Tips");
                map.putIfAbsent(skill.id,skill);
            }
        }
    }
}

