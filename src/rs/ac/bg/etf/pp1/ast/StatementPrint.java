// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class StatementPrint extends Statement {

    private Expr Expr;
    private CommaConstOcc CommaConstOcc;

    public StatementPrint (Expr Expr, CommaConstOcc CommaConstOcc) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.CommaConstOcc=CommaConstOcc;
        if(CommaConstOcc!=null) CommaConstOcc.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public CommaConstOcc getCommaConstOcc() {
        return CommaConstOcc;
    }

    public void setCommaConstOcc(CommaConstOcc CommaConstOcc) {
        this.CommaConstOcc=CommaConstOcc;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(CommaConstOcc!=null) CommaConstOcc.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(CommaConstOcc!=null) CommaConstOcc.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(CommaConstOcc!=null) CommaConstOcc.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementPrint(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CommaConstOcc!=null)
            buffer.append(CommaConstOcc.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementPrint]");
        return buffer.toString();
    }
}
