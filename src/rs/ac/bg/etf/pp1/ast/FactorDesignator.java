// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class FactorDesignator extends Factor {

    private Designator Designator;
    private BracketActParsOcc BracketActParsOcc;

    public FactorDesignator (Designator Designator, BracketActParsOcc BracketActParsOcc) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.BracketActParsOcc=BracketActParsOcc;
        if(BracketActParsOcc!=null) BracketActParsOcc.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public BracketActParsOcc getBracketActParsOcc() {
        return BracketActParsOcc;
    }

    public void setBracketActParsOcc(BracketActParsOcc BracketActParsOcc) {
        this.BracketActParsOcc=BracketActParsOcc;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(BracketActParsOcc!=null) BracketActParsOcc.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(BracketActParsOcc!=null) BracketActParsOcc.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(BracketActParsOcc!=null) BracketActParsOcc.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorDesignator(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(BracketActParsOcc!=null)
            buffer.append(BracketActParsOcc.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorDesignator]");
        return buffer.toString();
    }
}
