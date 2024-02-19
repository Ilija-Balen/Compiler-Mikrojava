// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class VarDeclaration extends VarDecl {

    private Type Type;
    private VarElem VarElem;
    private CISList CISList;

    public VarDeclaration (Type Type, VarElem VarElem, CISList CISList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarElem=VarElem;
        if(VarElem!=null) VarElem.setParent(this);
        this.CISList=CISList;
        if(CISList!=null) CISList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarElem getVarElem() {
        return VarElem;
    }

    public void setVarElem(VarElem VarElem) {
        this.VarElem=VarElem;
    }

    public CISList getCISList() {
        return CISList;
    }

    public void setCISList(CISList CISList) {
        this.CISList=CISList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarElem!=null) VarElem.accept(visitor);
        if(CISList!=null) CISList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarElem!=null) VarElem.traverseTopDown(visitor);
        if(CISList!=null) CISList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarElem!=null) VarElem.traverseBottomUp(visitor);
        if(CISList!=null) CISList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclaration(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarElem!=null)
            buffer.append(VarElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CISList!=null)
            buffer.append(CISList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclaration]");
        return buffer.toString();
    }
}
