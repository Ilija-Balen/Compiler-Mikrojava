// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class VDecl extends CvcDeclList {

    private CvcDeclList CvcDeclList;
    private VarDecl VarDecl;

    public VDecl (CvcDeclList CvcDeclList, VarDecl VarDecl) {
        this.CvcDeclList=CvcDeclList;
        if(CvcDeclList!=null) CvcDeclList.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public CvcDeclList getCvcDeclList() {
        return CvcDeclList;
    }

    public void setCvcDeclList(CvcDeclList CvcDeclList) {
        this.CvcDeclList=CvcDeclList;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CvcDeclList!=null) CvcDeclList.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CvcDeclList!=null) CvcDeclList.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CvcDeclList!=null) CvcDeclList.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VDecl(\n");

        if(CvcDeclList!=null)
            buffer.append(CvcDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VDecl]");
        return buffer.toString();
    }
}
