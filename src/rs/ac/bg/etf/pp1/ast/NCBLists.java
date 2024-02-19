// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class NCBLists extends NCBList {

    private NCBList NCBList;
    private String constName;
    private NCBConst NCBConst;

    public NCBLists (NCBList NCBList, String constName, NCBConst NCBConst) {
        this.NCBList=NCBList;
        if(NCBList!=null) NCBList.setParent(this);
        this.constName=constName;
        this.NCBConst=NCBConst;
        if(NCBConst!=null) NCBConst.setParent(this);
    }

    public NCBList getNCBList() {
        return NCBList;
    }

    public void setNCBList(NCBList NCBList) {
        this.NCBList=NCBList;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NCBList!=null) NCBList.accept(visitor);
        if(NCBConst!=null) NCBConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NCBList!=null) NCBList.traverseTopDown(visitor);
        if(NCBConst!=null) NCBConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NCBList!=null) NCBList.traverseBottomUp(visitor);
        if(NCBConst!=null) NCBConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NCBLists(\n");

        if(NCBList!=null)
            buffer.append(NCBList.toString("  "+tab));
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

        buffer.append(tab);
        buffer.append(") [NCBLists]");
        return buffer.toString();
    }
}
