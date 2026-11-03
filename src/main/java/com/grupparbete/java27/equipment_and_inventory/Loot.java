package com.grupparbete.java27.equipment_and_inventory;


import java.util.Random;

public class Loot {

    public void getLoot(){

        int amount = lootAmount();

       for (int i = 0; i < amount; i++){
           int typeRoll = (int) (Math.random() * 3 + 1);

           switch (typeRoll){
               case 1:
                   // Om 1 ta emot tillbaka ett weapon()
                   break;
               case 2: // om 2 ta emot tillbaka en armor()
                   break;
               case 3: // om 3 ta emot en potion()
                   break;
           }
       }
    }


    //Kan öka mängd och göra så att starter mängden är högre än 1
    public int lootAmount(){
        int amount = 1;

        for(int i = 0; i < 2; i++){
            int random = (int) (Math.random() * (3) + 1);
            if(random == 1){
                amount++;
            }
        }
        return amount;
    }





}
