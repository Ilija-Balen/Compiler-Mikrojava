// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class StatementReturn extends Statement {

    private ExprOcc ExprOcc;

    public StatementReturn (ExprOcc ExprOcc) {
        this.ExprOcc=ExprOcc;
        if(ExprOcc!=null) ExprOcc.setParent(this);
    }

    public ExprOcc getExprOcc() {
        return ExprOcc;
    }

    public void setExprOcc(ExprOcc ExprOcc) {
        this.ExprOcc=ExprOcc;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprOcc!=null) ExprOcc.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprOcc!=null) ExprOcc.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprOcc!=null) ExprOcc.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementReturn(\n");

        if(ExprOcc!=null)
            buffer.append(ExprOcc.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementReturn]");
        return buffer.toString();
    }
}
