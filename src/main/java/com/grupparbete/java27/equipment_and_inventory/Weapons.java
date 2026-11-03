package com.grupparbete.java27.equipment_and_inventory;

import com.grupparbete.java27.Records;

import java.util.TreeMap;
public class Weapons {

    TreeMap<Object, Records.Weapon> allWeapons = new TreeMap<>();

    Records.Weapon a = new Records.Weapon("a", 4);
    Records.Weapon b = new Records.Weapon("b", 3);
    Records.Weapon c = new Records.Weapon("c", 2);
    Records.Weapon d = new Records.Weapon("d", 1);
    Records.Weapon e = new Records.Weapon("e", 1);
    Records.Weapon f = new Records.Weapon("f", 3);

    private void initiateAllWeapons() {
        allWeapons.put(1, a);
        allWeapons.put(2, b);
        allWeapons.put(3, c);
        allWeapons.put(4, d);
        allWeapons.put(5, e);
        allWeapons.put(6, f);
    }

    Records.Weapon[] getNoneSimilarWeapons(int amount){
        Records.Weapon[] sendItems = new Records.Weapon[3];
        Records.Weapon first = null;
        Records.Weapon second = null;
        Records.Weapon third = null;


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
