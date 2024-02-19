// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class ICCIType extends Type {

    private IdentColonIdentType IdentColonIdentType;

    public ICCIType (IdentColonIdentType IdentColonIdentType) {
        this.IdentColonIdentType=IdentColonIdentType;
        if(IdentColonIdentType!=null) IdentColonIdentType.setParent(this);
    }

    public IdentColonIdentType getIdentColonIdentType() {
        return IdentColonIdentType;
    }

    public void setIdentColonIdentType(IdentColonIdentType IdentColonIdentType) {
        this.IdentColonIdentType=IdentColonIdentType;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentColonIdentType!=null) IdentColonIdentType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentColonIdentType!=null) IdentColonIdentType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentColonIdentType!=null) IdentColonIdentType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ICCIType(\n");

        if(IdentColonIdentType!=null)
            buffer.append(IdentColonIdentType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ICCIType]");
        return buffer.toString();
    }
}
