// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class CDecl extends CvcDeclList {

    private CvcDeclList CvcDeclList;
    private ConstDecl ConstDecl;

    public CDecl (CvcDeclList CvcDeclList, ConstDecl ConstDecl) {
        this.CvcDeclList=CvcDeclList;
        if(CvcDeclList!=null) CvcDeclList.setParent(this);
        this.ConstDecl=ConstDecl;
        if(ConstDecl!=null) ConstDecl.setParent(this);
    }

    public CvcDeclList getCvcDeclList() {
        return CvcDeclList;
    }

    public void setCvcDeclList(CvcDeclList CvcDeclList) {
        this.CvcDeclList=CvcDeclList;
    }

    public ConstDecl getConstDecl() {
        return ConstDecl;
    }

    public void setConstDecl(ConstDecl ConstDecl) {
        this.ConstDecl=ConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CvcDeclList!=null) CvcDeclList.accept(visitor);
        if(ConstDecl!=null) ConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CvcDeclList!=null) CvcDeclList.traverseTopDown(visitor);
        if(ConstDecl!=null) ConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CvcDeclList!=null) CvcDeclList.traverseBottomUp(visitor);
        if(ConstDecl!=null) ConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CDecl(\n");

        if(CvcDeclList!=null)
            buffer.append(CvcDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDecl!=null)
            buffer.append(ConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CDecl]");
        return buffer.toString();
    }
}
