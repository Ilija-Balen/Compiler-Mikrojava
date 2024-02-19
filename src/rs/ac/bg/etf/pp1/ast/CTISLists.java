// generated with ast extension for cup
// version 0.8
// 12/0/2024 15:13:5


package rs.ac.bg.etf.pp1.ast;

public class CTISLists extends CTISList {

    private CTISList CTISList;
    private Type Type;
    private FormElem FormElem;

    public CTISLists (CTISList CTISList, Type Type, FormElem FormElem) {
        this.CTISList=CTISList;
        if(CTISList!=null) CTISList.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.FormElem=FormElem;
        if(FormElem!=null) FormElem.setParent(this);
    }

    public CTISList getCTISList() {
        return CTISList;
    }

    public void setCTISList(CTISList CTISList) {
        this.CTISList=CTISList;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public FormElem getFormElem() {
        return FormElem;
    }

    public void setFormElem(FormElem FormElem) {
        this.FormElem=FormElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CTISList!=null) CTISList.accept(visitor);
        if(Type!=null) Type.accept(visitor);
        if(FormElem!=null) FormElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CTISList!=null) CTISList.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FormElem!=null) FormElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CTISList!=null) CTISList.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FormElem!=null) FormElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CTISLists(\n");

        if(CTISList!=null)
            buffer.append(CTISList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormElem!=null)
            buffer.append(FormElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CTISLists]");
        return buffer.toString();
    }
}
