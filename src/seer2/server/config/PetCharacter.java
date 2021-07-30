package seer2.server.config;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.*;

public class PetCharacter {
    public int id;
    public String name;
    public float[] effect;

    private static HashMap<Integer,PetCharacter> map;
    private static PetCharacter none;
    private static List<Integer> keys;

    public static PetCharacter get(int id){
        PetCharacter s =  map.get(id);
        if(s==null){
            return none;
        }
        return s;
    }

    public static int getRandomId(){
        return keys.get(new Random().nextInt(keys.size()));
    }

    static {
        none = new PetCharacter();
        none.name="无性格";
        none.effect = new float[]{1,1,1,1,1,1};
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try{
            SAXParser parser = factory.newSAXParser();
            parser.parse("src/config/character-config.xml", new PetCharacterParserHandler());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "PetCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", effect=" + Arrays.toString(effect) +
                '}';
    }

    public static void main(String[] args) {
        map.forEach((Integer i,PetCharacter p)->{
            System.out.println(p);
        });
    }

    static class PetCharacterParserHandler extends DefaultHandler {

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            map = new HashMap<>();
            keys = new ArrayList<>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(qName.equals("pet-character")){
                PetCharacter character = new PetCharacter();
                character.id = Integer.parseInt(attributes.getValue("id"));
                character.name = attributes.getValue("name");
                character.effect = new float[6];
                character.effect[0] = Float.parseFloat(attributes.getValue("atk"));
                character.effect[1] = Float.parseFloat(attributes.getValue("def"));
                character.effect[2] = Float.parseFloat(attributes.getValue("sp-atk"));
                character.effect[3] = Float.parseFloat(attributes.getValue("sp-def"));
                character.effect[4] = Float.parseFloat(attributes.getValue("spd"));
                character.effect[5] = Float.parseFloat(attributes.getValue("hp"));
                map.put(character.id,character);
                keys.add(character.id);
            }
        }
    }
}
