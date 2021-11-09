package rpg;
import java.util.ArrayList;

abstract class Player extends Entity {
    protected String caste;
    protected String[] optis = {"Vous êtes partagé entre attaque et sorts", "Vous êtes doté d'une grande puissance d'attaque mais d'une faible défense", "Vous êtes très résistant aux dégats","Vous êtes doté de grands pouvoirs"};
    protected int argent = 70;
    protected int attaqueMagique;
    protected static String[][] playerChar= {{"Mage","Vous êtes partagé entre attaque et sorts"},{"Elfe","Vous êtes doté d'une grande puissance d'attaque mais d'une faible défense"},{"Guerrier","Vous êtes très résistant aux dégats"},{"Sorcier","Vous êtes doté de grands pouvoirs"}};
    
    protected static int[][] playerStats = {{100,15,10,50,10},{80,25,10,0,0},{100,10,20,0,0},{120,5,20,100,20}};
    protected int hpMax;
    protected int manaMax;
    protected ArrayList<Item> inventory = new ArrayList();
    public Player(String n, int x) {
        super(n,playerStats[x][0], playerStats[x][1], playerStats[x][2], playerStats[x][3], "⬘", 6, 1,new Weapons("", 0,0,true){
        });
        this.nom = n;
        this.attaqueMagique =  playerStats[x][4];
        this.caste = playerChar[x][0];
        this.hpMax = playerStats[x][0];
        this.manaMax = playerStats[x][3];
    }
}