package Prototype1;

public class KnownElements extends Element{
    private String knownName;
    private double greatestPercent;
    //Constructor.
    public KnownElements(){
        super.name = null;
        greatestPercent = 0;
    }
    public KnownElements(String n, double p){
        super.name = n;
        greatestPercent = p;
    }
    //Returns the name of known element.
    public String getKnownName(){
        return super.name;
    }
    public double getGreatestPercent(){
        return greatestPercent;
    }
    
}
