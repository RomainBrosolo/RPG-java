package rpg;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    System.out.println(" ____  _                                      \n"
    		+ "| __ )(_) ___ _ ____   _____ _ __  _   _  ___ \n"
    		+ "|  _ \\| |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| | | |/ _ \\\n"
    		+ "| |_) | |  __/ | | \\ V /  __/ | | | |_| |  __/\n"
    		+ "|____/|_|\\___|_| |_|\\_/ \\___|_| |_|\\__,_|\\___|");
    System.out.println("\nCréation de votre personnage en cours...");
    Thread.sleep(1000);
    System.out.println("Choisissez votre nom : ");
    Scanner scanner = new Scanner(System.in);
    String name = scanner.nextLine();
    System.out.println("Choisissez votre classe : ");
    System.out.println("Mage [0] Elfe [1] Guerrier [2] Sorcier [3]");
    int caste = scanner.nextInt();
    while (caste < 0 || caste >3) {
      System.out.println("Saisissez un nombre correct");
      caste = scanner.nextInt();
    }
    Player player = new Player(name, caste) {};
    System.out.println(
      "Vous avez choisi d'être un " + player.caste + ".\n" +
      player.playerChar[caste][1]);
    Thread.sleep(1500);
    System.out.println("\nDescription de votre personnage : " +
      "\n" +
      "Pseudo : " +
      player.nom +
      "\n" +
      "Classe : " +
      player.caste +
      "\n" +
      "HP : " +
      player.hp +
      "\n" +
      "Attaque : " +
      player.attaque +
      "\n" +
      "Defense : " +
      player.defense +
      "\n" +
      "Mana : " +
      player.mana +
      "\n" +
      "Argent : " +
      player.argent +"\n"
    );

    //Matrice de la map
    int l = 10, h = 6; // longueur et largeur de la map
    final ArrayList<Mob> mobList = new ArrayList();
    final String[][] map = new String[h][l];
    PNJ marchand = new PNJ(1, 10) {};
    Heal heal = new Heal(1, 1) {};
    marchand.addStock(
      new Weapons("Hache", 55, 15,false) {},
      new Weapons("Couteau", 10, 5,false) {}
    );
    while (player.hp>0) {
      deplacement(player, map, mobList, marchand, heal);
    } ;
    if(player.hp<=0){
      System.out.println("GAME OVER");
    }
  }

  //GENERATION DE LA MAP
  public static void Map(
    String[][] map,
    Player player,
    PNJ marchand,
    Heal heal,
    ArrayList<Mob> mobList,
    String grass
  ) {

    map[marchand.y][marchand.x] = marchand.ascii;
    map[heal.y][heal.x] = heal.ascii;
    for (Mob el : mobList) {
      map[el.y][el.x] = el.ascii;
    }
    
    
    map[player.y][player.x] = player.ascii;

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == null) {
          map[i][j] = grass;
        }
      }
    }
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }

    System.out.println("\n");
   
    System.out.println("Légende : \n" +
      player.ascii +
      " = Joueur | " +
      marchand.ascii +
      " = Marchand | " +
      " ♦/♠/♣  = MOB | " +
      "♥  = HEAL"+
      "\n" +
      "Déplacez-vous avec les touches Z, Q, S, D | e pour équipement | i pour informations :"
    );
  }

  //Combat
  public static void Fight(Player player, Mob mob, String[][] map, ArrayList<Mob> mobList) {
    System.out.println("\nVous vous battez maintenant avec " + mob.nom);
    while (mob.hp > 0) {
      System.out.println("---------------------");
      System.out.println(mob.skin);
      System.out.println(mob.nom + " HP : " + mob.hp);
      System.out.println("---------------------");
      System.out.println(player.nom + " stats :");
      System.out.println("  hp : " + player.hp);
      System.out.println("  Attaque : " + player.attaque);
      System.out.println("---------------------");
      System.out.println("Choisissez votre option (Attaque: a, Magie : m, Rien : n): ");
      Scanner clavier = new Scanner(System.in);
      String nextMove = clavier.nextLine();
      switch(nextMove){
        case "a":
          System.out.println(" Vous attaquez " + mob.nom+" avec "+player.attaque+" deg");
          player.attackEntity(mob);
          System.out.println(mob.nom + "HP : " + mob.hp);
          System.out.println(mob.nom + " vous attaque"+" avec "+mob.attaque+" deg");
          mob.attackEntity(player);
          System.out.println("\n");
          System.out.println(player.nom + "  HP : " + player.hp);
          break;
        case "m":
        System.out.println("ce sort vous coute 5 mana");
        System.out.println(" Vous attaquez " + mob.nom+" avec "+player.attaqueMagique+" deg");
        player.magicEntity(mob,player);
          System.out.println(mob.nom + "HP : " + mob.hp);
          System.out.println(mob.nom + " vous attaque"+" avec "+mob.attaque+" deg");
          mob.attackEntity(player);
          System.out.println("\n");
          System.out.println(player.nom + "  HP : " + player.hp);
          break;
        case "n":
          System.out.println(mob.nom + " vous attaque"+" avec "+mob.attaque);
          mob.attackEntity(player);
          System.out.println("\n");
          System.out.println(player.nom + "  HP : " + player.hp);
          break;
        default:
          System.out.println("Entrez une action valide");
      }
      
      if (mob.hp <= 0) { //win fight
        
        System.out.println("Le " + mob.nom + " a été battu");
        player.argent += mob.loot;
        System.out.println("Vous remportez " + mob.loot + " ©");
        System.out.println();
        mob.alive = false;
      }
      if(player.hp<0){
        return;
      }
    }
    mobList.remove(mob);
  }

  //Deplacement sur la map
  public static void deplacement(
    Player player,
    String[][] map,
    ArrayList<Mob> mobList,
    PNJ marchand,
    Heal heal
  ) {
    String grass ="□";
    Map(map, player, marchand,heal, mobList, grass);

    mobsGenerator(mobList, map, player);
    map[player.y][player.x] = null;

    Scanner clavier = new Scanner(System.in);
    String nextMove = clavier.nextLine();
    System.out.println(nextMove);

    switch (nextMove) {
      case "z":
        if (player.y == 0) {
          System.out.println("Hors de la map");
        } else {

        	player.y -= 1; //y-1
        }
        break;
      case "q":
        if (player.x == 0) {
          System.out.println("Hors de la map");
        } else {

        	player.x -= 1; //y-1
        }
        break;
      case "s":
        if (player.y + 1 == map.length) {
          System.out.println("Hors de la map");
        } else {

        	player.y += 1; //y+1
        }
        break;
      case "d":
        if (player.x + 1 == map[0].length) {
          System.out.println("Hors de la map");
        } else {

        	player.x += 1; //x+1
        }
        break;
      case "i":
        info(player);
        break;
      case "e":
        equipement(player);
        break;
      default:
        System.out.println("Entrez un deplacement valide");
        
      }
          if (player.y == marchand.y && player.x == marchand.x) {
            marchand.marchand(player);
          }
          if (player.y == heal.y && player.x == heal.x) {
            heal.heal(player);
          }
  }

  public static void info(Player player){
  System.out.println("Voici vos stats : \n");
  System.out.println(
    "HP : " +
    player.hp +
    "\n" +
    "Attaque : " +
    player.attaque +
    "\n" +
    "Defense : " +
    player.defense +
    "\n" +
    "Mana : " +
    player.mana +
    "\n" +
    "Argent : " +
    player.argent+
    "\n"
  );
  for(Item el : player.inventory){
    System.out.println(el.nom+" : "+el.dommage +" deg");
  }
}

  public static void equipement(Player player){
    System.out.println("Voulez-vous changez d'equipement ?");
    if(player.inventory.size()==0){
      System.out.println("Vous n'avez aucune arme");
    }
    for(int i = 0; i< player.inventory.size();i++){
      if(player.inventory.get(i).equiped==false){
        System.out.println("("+(i+1)+") "+player.inventory.get(i).nom+" : "+player.inventory.get(i).dommage +" deg");
        }
      }
        Scanner clavier = new Scanner(System.in);
        String nextMove =clavier.nextLine();
   
    switch(nextMove){
      case "1":
      int nexInt = Integer.parseInt(nextMove);
      player.changeWeapons(player.inventory.get(nexInt -1));
      break;
      case "2":
      nexInt = Integer.parseInt(nextMove);
      player.changeWeapons(player.inventory.get(nexInt -1));
      break;
      default:
      System.out.println("Entrez une réponse valide");
    }
  }  

  public static void mobsGenerator(ArrayList<Mob> mobList, String[][] map,Player player) {
    if (mobList.size() < 5) {
      int m = randomInt(0, 3);
      int spawn = randomInt(0, 10);
      if (spawn < 3) {
        int h = randomInt(0, map.length), l = randomInt(0, map[0].length);
        if (map[h][l].compareTo("□") == 0) {
          Mob mob = new Mob(m, h+1, l+1) {};
          mobList.add(mob);
        }
      }
    }
    
    for(int i =0; i<mobList.size();i++){
      if (player.y == mobList.get(i).y && player.x == mobList.get(i).x) {
          Fight(player, mobList.get(i), map, mobList);
        }
    }
  }

  public static int randomInt(int min, int max) {
    int result = ThreadLocalRandom.current().nextInt(min, max);
    return result;
  }
}
