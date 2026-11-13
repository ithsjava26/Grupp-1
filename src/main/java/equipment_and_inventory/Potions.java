package equipment_and_inventory;

import java.util.TreeMap;

public class Potions {

    static TreeMap<Object, Records.Potion> allPotions = new TreeMap<>();

    public static void initiatePotions(){
        allPotions.put(1, new Records.Potion("Small recovery drink", "recovers hp", 25));
        allPotions.put(2, new Records.Potion("Medium recovery drink", "recovers hp", 50));
        allPotions.put(3, new Records.Potion("Large recovery drink", "recovers hp", 100));
    }

    public static Records.Potion getRandomPotion(){
        return allPotions.get(((int) (Math.random() * (5)) + 1));
    }

    public static Records.Potion getPotionById(int id){
        if(allPotions.containsKey(id)){
            return allPotions.get(id);
        }else{
            IO.println("Could not find Potion");
        }

        return null;
    }


}
