//package seer2.server.config;
//
//import org.xml.sax.Attributes;
//import org.xml.sax.SAXException;
//import org.xml.sax.helpers.DefaultHandler;
//import seer2.server.fight.Fighter;
//import seer2.server.fight.buff.*;
//import seer2.server.fight.buff.skill.GeneralBuff;
//import seer2.server.utils.ArrayUtils;
//
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//import java.util.HashMap;
//
//public class BuffEffectOld {
//    public int id;
//    private String type;
//    private String value;
//    public IBuff buff;
//
//    private static HashMap<Integer, BuffEffectOld> map;
//
//    public static void addBuff(Fighter fighter, int buffOrSkillId) {
//        BuffEffectOld s = map.get(buffOrSkillId);
//        if (s == null) return;
//        if (s.buff == null) s.createBuff();
//        if(s.buff instanceof MultiBuff){
//            ((MultiBuff) s.buff).addBuff(fighter.arena,fighter);
//        }else{
//            throw new UnsupportedOperationException();
//        }
//    }
//
//    private void createBuff() {
//        switch (type) {
//            case "general":
//                buff = new GeneralBuff(ArrayUtils.parseFromString(value));
//                break;
//            case "skill":
//                try {
//                    buff = (IBuff) Class.forName("seer2.server.fight.buff.skill.SkillBuff" + value).getConstructor(int.class).newInstance(id);
//                } catch (ReflectiveOperationException e) {
//                    throw new UnsupportedOperationException(e.getMessage());
//                }
//                break;
//            default:
//                throw new UnsupportedOperationException("不存在此类型iBuff:" + type);
//        }
//    }
//
//
//    static {
//        map = new HashMap<>();
//        SAXParserFactory factory = SAXParserFactory.newInstance();
//        try {
//            SAXParser parser = factory.newSAXParser();
//            parser.parse("src/config/skill-effect.xml", new SkillEffectParserHandler());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void main(String[] args) {
//        map.forEach((Integer i, BuffEffectOld s) -> {
//            System.out.println(s);
//        });
//
//    }
//
//    static class SkillEffectParserHandler extends DefaultHandler {
//
//        @Override
//        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//            super.startElement(uri, localName, qName, attributes);
//            if (qName.equals("skill")) {
//                BuffEffectOld effect = new BuffEffectOld();
//                effect.id = Integer.parseInt(attributes.getValue("id"));
//                effect.type = attributes.getValue("type");
//                effect.value = attributes.getValue("value");
//                map.put(effect.id, effect);
//            }
//        }
//    }
//}
//
