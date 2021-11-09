package rpg;

abstract class Item {
    protected int prix; 
    protected String nom;
    protected int dommage; 
    protected boolean equiped;
    
    public Item(String nom, int prix, int dommage, boolean f) {
        this.nom = nom;
        this.prix = prix;
        this.dommage = dommage;
        this.equiped = f;
    }
}
