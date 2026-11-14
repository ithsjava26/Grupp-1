package equipment_and_inventory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionsTest {

    @Test
    void initiateAllPotionsWorks(){
        var potion = new Potions();
        potion.initiateAllPotions();

        assertFalse(potion.allPotions.isEmpty());
    }

    @Test
    void getPotionByIdWorks(){
        var potion = new Potions();
        potion.initiateAllPotions();

        Records.Potion savedPotion = potion.getPotionById(1);
        assertEquals(potion.allPotions.get(1), savedPotion);
    }

    @Test
    void getPotionWorksEvenIfMapIsNotInitiated(){
        var potion = new Potions();

        Records.Potion savedPotion = potion.getPotionById(3);
        assertEquals(potion.allPotions.get(3), savedPotion);
    }

    @Test
    void getRandomPotionWorksEvenIfMapIsInitiated(){
        var potion = new Potions();

        Records.Potion savedPotion = potion.getRandomPotion();
        assertFalse(potion.allPotions.isEmpty());
    }

}