package equipment_and_inventory;


import java.util.ArrayList;

import static equipment_and_inventory.Armor.*;
import static equipment_and_inventory.Inventory.*;
import static equipment_and_inventory.Potions.*;
import static equipment_and_inventory.Weapons.*;

public class Loot{

    public void getLoot(){

        int amount = lootAmount();

        ArrayList<Object> loot = new ArrayList<>();

        int position = 1;
        for (int i = 0; i < amount; i++){
            int typeRoll = (int) (Math.random() * 3 + 1);


            switch (typeRoll){
                case 1:
                    Records.Weapon fetchedWeapon = getRandomWeapon();
                    IO.print(position + ". ");
                    printLoot(fetchedWeapon);
                    loot.add(fetchedWeapon);
                    break;
                case 2: Records.Armor fetchedArmor = getRandomArmor();
                        IO.print(position + ". ");
                        printLoot(fetchedArmor);
                        loot.add(fetchedArmor);
                    break;
                case 3:
                    Records.Potion fetchedPotion = getRandomPotion();
                    IO.print(position + ". ");
                    printLoot(fetchedPotion);
                    loot.add(fetchedPotion);
                    break;
            }
            position++;
        }

        boolean running  = true;
        while(running){

            int choice = 1;
            int nav= 0;
            IO.println("Which drop are you picking");

            for(int i = 0; i < loot.size(); i++){
                if(loot.get(i) instanceof Records.Weapon){
                    IO.print(choice + ". ");
                    printLoot((Records.Weapon) loot.get(i));
                    choice++;
                }else if(loot.get(i) instanceof Records.Armor){
                    IO.print(choice + ". ");
                    printLoot((Records.Armor) loot.get(i));
                    choice++;
                }else{
                    IO.print(choice + ". ");
                    printLoot((Records.Potion) loot.get(i));
                    choice++;
                }
            }
            IO.println(choice  + ". Skip selection");

            while(nav < 1 || nav > loot.size() + 1){
                try{
                    nav = Integer.parseInt(IO.readln());
                }catch(NumberFormatException e){
                    nav = 0;
                    IO.println("Your option needs to be within acceptable parameters");
                }

                if(choice <1 || choice > loot.size() + 1){
                    IO.println("Your option needs to be within acceptable parameters");
                }
            }


            if(choice == 2) {
                switch (nav) {
                    case 1:
                        if(loot.getFirst() instanceof Records.Weapon){
                            Inventory.takeEquipment((Records.Weapon)loot.getFirst());
                        }else if(loot.getFirst() instanceof Records.Armor){
                            Inventory.takeEquipment((Records.Armor)loot.getFirst());
                        }else{
                            Inventory.takeEquipment((Records.Potion)loot.getFirst());
                        }
                        running = false;
                        break;
                    case 2:
                        running = false;
                        break;


                }
            }else if(choice == 3) {
                switch (nav) {
                    case 1:
                        if(loot.getFirst() instanceof Records.Weapon){
                            Inventory.takeEquipment((Records.Weapon)loot.getFirst());
                        }else if(loot.getFirst() instanceof Records.Armor){
                            Inventory.takeEquipment((Records.Armor)loot.getFirst());
                        }else{
                            Inventory.takeEquipment((Records.Potion)loot.getFirst());
                        }
                        running = false;
                        break;
                    case 2:
                        if(loot.get(1) instanceof Records.Weapon){
                            Inventory.takeEquipment((Records.Weapon)loot.get(1));
                        }else if(loot.get(1) instanceof Records.Armor){
                            Inventory.takeEquipment((Records.Armor)loot.get(1));
                        }else{
                            Inventory.takeEquipment((Records.Potion)loot.get(1));
                        }
                        running = false;
                        break;
                    case 3:
                        running = false;
                        break;
                }
            }else{
                switch (nav) {
                    case 1:
                        if(loot.getFirst() instanceof Records.Weapon){
                            Inventory.takeEquipment((Records.Weapon)loot.getFirst());
                        }else if(loot.getFirst() instanceof Records.Armor){
                            Inventory.takeEquipment((Records.Armor)loot.getFirst());
                        }else{
                            Inventory.takeEquipment((Records.Potion)loot.getFirst());
                        }
                        running = false;
                        break;
                    case 2:
                        if(loot.get(1) instanceof Records.Weapon){
                            Inventory.takeEquipment((Records.Weapon)loot.get(1));
                        }else if(loot.get(1) instanceof Records.Armor){
                            Inventory.takeEquipment((Records.Armor)loot.get(1));
                        }else{
                            Inventory.takeEquipment((Records.Potion)loot.get(1));
                        }
                        running = false;
                        break;
                    case 3:
                        if(loot.get(2) instanceof Records.Weapon){
                            Inventory.takeEquipment((Records.Weapon)loot.get(2));
                        }else if(loot.get(2) instanceof Records.Armor){
                            Inventory.takeEquipment((Records.Armor)loot.get(2));
                        }else{
                            Inventory.takeEquipment((Records.Potion)loot.get(2));
                        }
                        running = false;
                        break;
                    case 4:
                        running = false;
                        break;
                }
            }


        }

    }

    void main(){
        initiateAllEquipment();
        getLoot();


    }


    //Kan öka mängd och göra så att starter mängden är högre än 1
    public static int lootAmount(){
        int amount = 1;

        for(int i = 0; i < 2; i++){
            int random = (int) (Math.random() * (3) + 1);
            if(random == 1){
                amount++;
            }
        }
        return amount;
    }


    private void printLoot(Records.Weapon weapon){
        IO.println(weapon.name() + ": Attack " + weapon.attack());
    }

    private void printLoot(Records.Armor armor){
        IO.println(armor.name() + ": Armor " + armor.defence());
    }

    private void printLoot(Records.Potion potion){
        IO.println(potion.name() + ": " + potion.effect() + " " + potion.effectStrength());
    }

}
