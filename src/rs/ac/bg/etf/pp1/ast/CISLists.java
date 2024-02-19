// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class CISLists extends CISList {

    private CISList CISList;
    private VarElem VarElem;

    public CISLists (CISList CISList, VarElem VarElem) {
        this.CISList=CISList;
        if(CISList!=null) CISList.setParent(this);
        this.VarElem=VarElem;
        if(VarElem!=null) VarElem.setParent(this);
    }

    public CISList getCISList() {
        return CISList;
    }

    public void setCISList(CISList CISList) {
        this.CISList=CISList;
    }

    public VarElem getVarElem() {
        return VarElem;
    }

    public void setVarElem(VarElem VarElem) {
        this.VarElem=VarElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CISList!=null) CISList.accept(visitor);
        if(VarElem!=null) VarElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CISList!=null) CISList.traverseTopDown(visitor);
        if(VarElem!=null) VarElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CISList!=null) CISList.traverseBottomUp(visitor);
        if(VarElem!=null) VarElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CISLists(\n");

        if(CISList!=null)
            buffer.append(CISList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarElem!=null)
            buffer.append(VarElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CISLists]");
        return buffer.toString();
    }
}
