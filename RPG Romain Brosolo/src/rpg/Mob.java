package rpg;
import java.util.ArrayList;

abstract class Mob extends Entity {

  protected static int[][] mobStats = {
      { 30,10, 5, 0,5 },
      {  50,20,10,0,20},
      { 40,15,5,0,15},
    };

  protected static String[][] mobChar = {
    { "Zombie", "                  ....." +
    "\n" +
    "                 C C  /" +
    "\n" +
    "                /<   /" +
    "\n" +
    " ___ __________/_#__=o" +
    "\n" +
    "/(- /(\\_\\________   \\ " +
    "\n" +
    "\\ ) \\ )_      \\o     \\" +
    "\n" +
    "/|\\ /|\\       |'     |"  +
    "\n" +
    "              |     _|"  +
    "\n" +
    "              /o   __\\"  +
    "\n" +
    "             / '     |"  +
    "\n" +
    "            / /      |"  +
    "\n" +
    "           /_/\\______|"  +
    "\n" +
    "          (   _(    <"   +
    "\n" +
    "           \\    \\    \\"   +
    "\n" +
    "            \\    \\    |"   +
    "\n" +
    "             \\____\\____\\"   +
    "\n" +
    "             ____\\_\\__\\_\\","♠" },
    { "Creeper","███████████████████████" +
    "\n" +
    "████░░░░░█████░░░░░████" +
    "\n" +
    "████░░░░░█████░░░░░████" +
    "\n" +
    "████░░░░░█████░░░░░████" +
    "\n" +
    "█████████░░░░░█████████" +
    "\n" +
    "█████████░░░░░█████████" +
    "\n" +
    "███████░░░░░░░░░███████" +
    "\n" +
    "███████░░░░░░░░░███████" +
    "\n" +
    "███████░░░░░░░░░███████" +
    "\n" +
    "███████░░█████░░███████" +
    "\n" +
    "███████████████████████" +
    "\n","♦"},
    { "Chauve-Souris", "       ^^     "+"\n"+
    "  /\\__{^^}__/\\"+"\n"+
    " / _        _ \\"+"\n"+
    " \\/ \\/\\ww/\\/ \\/"+"\n","♣"},
  };
  protected int loot;
  protected int apparition;
  protected String[] names = { "Zombie", "Creeper", "Chauve-Souris" };
  protected String skin;
  protected ArrayList<String> inventory;

  public Mob(
      int type,
    int x,
    int y
    ) {
    super(mobChar[type][0],mobStats[type][0], mobStats[type][1], mobStats[type][2], mobStats[type][3], mobChar[type][2], x, y, new Weapons("", 0,0,true){
      
    });
  this.loot = mobStats[type][4];
  this.skin = mobChar[type][1];
}

}

