// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class MethDecl extends MethodDecl {

    private TVList TVList;
    private FormParsOcc FormParsOcc;
    private VarDeclList VarDeclList;
    private StatementList StatementList;

    public MethDecl (TVList TVList, FormParsOcc FormParsOcc, VarDeclList VarDeclList, StatementList StatementList) {
        this.TVList=TVList;
        if(TVList!=null) TVList.setParent(this);
        this.FormParsOcc=FormParsOcc;
        if(FormParsOcc!=null) FormParsOcc.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public TVList getTVList() {
        return TVList;
    }

    public void setTVList(TVList TVList) {
        this.TVList=TVList;
    }

    public FormParsOcc getFormParsOcc() {
        return FormParsOcc;
    }

    public void setFormParsOcc(FormParsOcc FormParsOcc) {
        this.FormParsOcc=FormParsOcc;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TVList!=null) TVList.accept(visitor);
        if(FormParsOcc!=null) FormParsOcc.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TVList!=null) TVList.traverseTopDown(visitor);
        if(FormParsOcc!=null) FormParsOcc.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TVList!=null) TVList.traverseBottomUp(visitor);
        if(FormParsOcc!=null) FormParsOcc.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethDecl(\n");

        if(TVList!=null)
            buffer.append(TVList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsOcc!=null)
            buffer.append(FormParsOcc.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethDecl]");
        return buffer.toString();
    }
}
