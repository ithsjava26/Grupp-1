package com.grupparbete.java27.equipment_and_inventory;

public class Records {

    //Kan lägga till mera t.ex om några vapen ska ha en DOT effect
    public record Weapon(String name, int attack){}

    //Skulle kunna lägga till thorns eller någon form av regeneration
    public record Armor(String name, int def){}

    //Potion är bara ett standardnamn ni får gärna komma med förslag
    public record Potion(String name, String effect, int effectStrength){}
}
