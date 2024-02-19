// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class DesignatorIdentDoubleOccured extends Designator {

    private String nameNamespace;
    private String varName;
    private IdentExprList IdentExprList;

    public DesignatorIdentDoubleOccured (String nameNamespace, String varName, IdentExprList IdentExprList) {
        this.nameNamespace=nameNamespace;
        this.varName=varName;
        this.IdentExprList=IdentExprList;
        if(IdentExprList!=null) IdentExprList.setParent(this);
    }

    public String getNameNamespace() {
        return nameNamespace;
    }

    public void setNameNamespace(String nameNamespace) {
        this.nameNamespace=nameNamespace;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public IdentExprList getIdentExprList() {
        return IdentExprList;
    }

    public void setIdentExprList(IdentExprList IdentExprList) {
        this.IdentExprList=IdentExprList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentExprList!=null) IdentExprList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentExprList!=null) IdentExprList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentExprList!=null) IdentExprList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorIdentDoubleOccured(\n");

        buffer.append(" "+tab+nameNamespace);
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(IdentExprList!=null)
            buffer.append(IdentExprList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorIdentDoubleOccured]");
        return buffer.toString();
    }
}
