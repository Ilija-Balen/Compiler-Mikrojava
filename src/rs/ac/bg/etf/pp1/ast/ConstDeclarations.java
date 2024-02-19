// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclarations extends ConstDecl {

    private Type Type;
    private String constName;
    private NCBConst NCBConst;
    private NCBList NCBList;

    public ConstDeclarations (Type Type, String constName, NCBConst NCBConst, NCBList NCBList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.constName=constName;
        this.NCBConst=NCBConst;
        if(NCBConst!=null) NCBConst.setParent(this);
        this.NCBList=NCBList;
        if(NCBList!=null) NCBList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public NCBConst getNCBConst() {
        return NCBConst;
    }

    public void setNCBConst(NCBConst NCBConst) {
        this.NCBConst=NCBConst;
    }

    public NCBList getNCBList() {
        return NCBList;
    }

    public void setNCBList(NCBList NCBList) {
        this.NCBList=NCBList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(NCBConst!=null) NCBConst.accept(visitor);
        if(NCBList!=null) NCBList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(NCBConst!=null) NCBConst.traverseTopDown(visitor);
        if(NCBList!=null) NCBList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(NCBConst!=null) NCBConst.traverseBottomUp(visitor);
        if(NCBList!=null) NCBList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclarations(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(NCBConst!=null)
            buffer.append(NCBConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NCBList!=null)
            buffer.append(NCBList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclarations]");
        return buffer.toString();
    }
}
