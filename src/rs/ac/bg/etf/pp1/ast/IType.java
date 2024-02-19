// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class IType extends Type {

    private IdentType IdentType;

    public IType (IdentType IdentType) {
        this.IdentType=IdentType;
        if(IdentType!=null) IdentType.setParent(this);
    }

    public IdentType getIdentType() {
        return IdentType;
    }

    public void setIdentType(IdentType IdentType) {
        this.IdentType=IdentType;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentType!=null) IdentType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentType!=null) IdentType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentType!=null) IdentType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IType(\n");

        if(IdentType!=null)
            buffer.append(IdentType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IType]");
        return buffer.toString();
    }
}
