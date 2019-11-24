package pacman.fileManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pacman.engine.core.Block.Block;
import pacman.engine.core.Entity.EntityType;
import pacman.engine.core.Map.Map;
import pacman.gameplay.Bonus.advantageBonus.PacGomme;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class LabyrinthFile{
    private static ArrayList<Map> maps = null;
    public static void loadMaps() {
        try {

            File fXmlFile = new File("maps.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            System.out.println(root.getNodeName());

            NodeList nList = doc.getElementsByTagName("map");
            maps = new ArrayList<Map>();
            Map map = null;
            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node mapNode = nList.item(temp);
                if (mapNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) mapNode;

                    int mapX = Integer.parseInt(eElement.getAttribute("x"));
                    int mapY = Integer.parseInt(eElement.getAttribute("y"));
                    map = new Map(mapX,mapY);
                    map.setId(eElement.getAttribute("id"));

                    NodeList blocList = eElement.getElementsByTagName("bloc");

                    for (int temp1 = 0; temp1 < blocList.getLength(); temp1++)
                    {
                        Node blocNode = blocList.item(temp1);
                        if (blocNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element blocElement = (Element) blocNode;

                            int blocX = Integer.parseInt(blocElement.getAttribute("x"));
                            int blocY = Integer.parseInt(blocElement.getAttribute("y"));

                            try {
                                EntityType blocKind = EntityType.valueOf(blocElement.getAttribute("type"));

                                switch (blocKind) {
                                    case EMPTY:
                                        break;
                                    case BLOCK:
                                        map.setStaticEntity(blocX, blocY, new Block());
                                        break;
                                    case PACK_GOMME:
                                        map.setStaticEntity(blocX, blocY, new PacGomme());
                                        break;
                                    default:
                                        //TODO
                                        break;
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println("x " + blocX +  " y " + blocY);
                            }
                        }
                    }

                    maps.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
