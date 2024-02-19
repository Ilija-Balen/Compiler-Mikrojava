// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class CommaCOcc extends CommaConstOcc {

    private NumConst NumConst;

    public CommaCOcc (NumConst NumConst) {
        this.NumConst=NumConst;
        if(NumConst!=null) NumConst.setParent(this);
    }

    public NumConst getNumConst() {
        return NumConst;
    }

    public void setNumConst(NumConst NumConst) {
        this.NumConst=NumConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NumConst!=null) NumConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NumConst!=null) NumConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NumConst!=null) NumConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CommaCOcc(\n");

        if(NumConst!=null)
            buffer.append(NumConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CommaCOcc]");
        return buffer.toString();
    }
}
