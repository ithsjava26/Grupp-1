package equipment_and_inventory;

import java.util.TreeMap;

public class Weapons {

    static TreeMap<Object, Records.Weapon> allWeapons = new TreeMap<>();

    public static void initiateAllWeapons() {
        allWeapons.put(1, new Records.Weapon("Bare hands", 8));
        allWeapons.put(2, new Records.Weapon("Needle", 10));
        allWeapons.put(3, new Records.Weapon("Metal Pipe", 13));
        allWeapons.put(4, new Records.Weapon("Baseball bat", 15));
        allWeapons.put(5, new Records.Weapon("Knife", 18));
        allWeapons.put(6, new Records.Weapon("Nailed baseball bat", 20));
        allWeapons.put(7, new Records.Weapon("Sword?", 22));
    }



    public static Records.Weapon getRandomWeapon(){

        return allWeapons.get(((int) (Math.random() * (6)) + 1));
    }



    public Records.Weapon getWeaponById(int id){
        if(allWeapons.containsKey(id)){
            return allWeapons.get(id);
        }else{
            IO.println("Could not find Weapon");
        }

        return null;
    }

    Records.Weapon[] getUpTo3Weapons(int amount){
        Records.Weapon[] sendItems = new Records.Weapon[3];
        Records.Weapon first = null;
        Records.Weapon second = null;
        Records.Weapon third = null;

        if (amount > 3){
            amount = 3;
        }

        switch(amount){
            case 1:
                sendItems[0] = (allWeapons.get((int) (Math.random() * (6)) + 1));
                break;
            case 2:
                first = allWeapons.get(((int) (Math.random() * (6)) + 1));
                second =  allWeapons.get(((int) (Math.random() * (6)) + 1));
                while(second == first){
                    second =  allWeapons.get(((int) (Math.random() * (6)) + 1));
                }
                sendItems[0] = first;
                sendItems[1] = second;
                break;
            case 3:
                first = allWeapons.get(((int) (Math.random() * (6)) + 1));
                second =  allWeapons.get(((int) (Math.random() * (6)) + 1));
                while(second == first){
                    second =  allWeapons.get(((int) (Math.random() * (6)) + 1));
                }
                third = allWeapons.get(((int) (Math.random() * (6)) + 1));
                while(third == first || third == second){
                    third = allWeapons.get(((int) (Math.random() * (6)) + 1));
                }
                sendItems[0] = first;
                sendItems[1] = second;
                sendItems[2] = third;
                break;
        }

        return sendItems;
    }
}
