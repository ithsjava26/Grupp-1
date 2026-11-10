package equipment_and_inventory;

import java.util.TreeMap;

public class Armor {

    static TreeMap<Object, Records.Armor> allArmors = new TreeMap<>();


    public static void initiateAllArmors(){
        allArmors.put(1, new Records.Armor("a", 2));
        allArmors.put(2, new Records.Armor("b", 4));
        allArmors.put(3, new Records.Armor("c", 6));
        allArmors.put(4, new Records.Armor("d", 8));
        allArmors.put(5, new Records.Armor("e", 10));
        allArmors.put(6, new Records.Armor("f", 12));
        allArmors.put(7, new Records.Armor("g", 14));
        allArmors.put(8, new Records.Armor("h", 16));
        allArmors.put(9, new Records.Armor("i", 18));
    }

    public static Records.Armor getRandomArmor(){

        return allArmors.get(((int) (Math.random() * (6)) + 1));
    }



    public static Records.Armor getArmorById(int id){
        if(allArmors.containsKey(id)){
            return allArmors.get(id);
        }else{
            IO.println("Could not find Armor");
        }

        return null;
    }
}
