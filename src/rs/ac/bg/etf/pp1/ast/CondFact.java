// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class CondFact implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Expr Expr;
    private RelopExprOcc RelopExprOcc;

    public CondFact (Expr Expr, RelopExprOcc RelopExprOcc) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.RelopExprOcc=RelopExprOcc;
        if(RelopExprOcc!=null) RelopExprOcc.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public RelopExprOcc getRelopExprOcc() {
        return RelopExprOcc;
    }

    public void setRelopExprOcc(RelopExprOcc RelopExprOcc) {
        this.RelopExprOcc=RelopExprOcc;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(RelopExprOcc!=null) RelopExprOcc.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(RelopExprOcc!=null) RelopExprOcc.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(RelopExprOcc!=null) RelopExprOcc.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFact(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RelopExprOcc!=null)
            buffer.append(RelopExprOcc.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFact]");
        return buffer.toString();
    }
}
