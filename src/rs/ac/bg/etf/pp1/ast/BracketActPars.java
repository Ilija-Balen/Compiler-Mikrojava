// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class BracketActPars extends BracketActParsOcc {

    private ActParsOcc ActParsOcc;

    public BracketActPars (ActParsOcc ActParsOcc) {
        this.ActParsOcc=ActParsOcc;
        if(ActParsOcc!=null) ActParsOcc.setParent(this);
    }

    public ActParsOcc getActParsOcc() {
        return ActParsOcc;
    }

    public void setActParsOcc(ActParsOcc ActParsOcc) {
        this.ActParsOcc=ActParsOcc;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActParsOcc!=null) ActParsOcc.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsOcc!=null) ActParsOcc.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsOcc!=null) ActParsOcc.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BracketActPars(\n");

        if(ActParsOcc!=null)
            buffer.append(ActParsOcc.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BracketActPars]");
        return buffer.toString();
    }
}
