package equipment_and_inventory;

public class Records {


    public record Weapon(String name, int attack){}


    public record Armor(String name, int defence){}


    public record Potion(String name, String effect, int effectStrength){}
}
