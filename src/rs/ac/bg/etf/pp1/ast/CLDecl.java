// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class CLDecl extends CvcDeclList {

    private CvcDeclList CvcDeclList;
    private ClassDecl ClassDecl;

    public CLDecl (CvcDeclList CvcDeclList, ClassDecl ClassDecl) {
        this.CvcDeclList=CvcDeclList;
        if(CvcDeclList!=null) CvcDeclList.setParent(this);
        this.ClassDecl=ClassDecl;
        if(ClassDecl!=null) ClassDecl.setParent(this);
    }

    public CvcDeclList getCvcDeclList() {
        return CvcDeclList;
    }

    public void setCvcDeclList(CvcDeclList CvcDeclList) {
        this.CvcDeclList=CvcDeclList;
    }

    public ClassDecl getClassDecl() {
        return ClassDecl;
    }

    public void setClassDecl(ClassDecl ClassDecl) {
        this.ClassDecl=ClassDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CvcDeclList!=null) CvcDeclList.accept(visitor);
        if(ClassDecl!=null) ClassDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CvcDeclList!=null) CvcDeclList.traverseTopDown(visitor);
        if(ClassDecl!=null) ClassDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CvcDeclList!=null) CvcDeclList.traverseBottomUp(visitor);
        if(ClassDecl!=null) ClassDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CLDecl(\n");

        if(CvcDeclList!=null)
            buffer.append(CvcDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDecl!=null)
            buffer.append(ClassDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CLDecl]");
        return buffer.toString();
    }
}
