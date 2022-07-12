// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class ConstVCRDListYes extends ConstVarClassRecDeclList {

    private ConstVarClassRecDeclItem ConstVarClassRecDeclItem;
    private ConstVarClassRecDeclList ConstVarClassRecDeclList;

    public ConstVCRDListYes (ConstVarClassRecDeclItem ConstVarClassRecDeclItem, ConstVarClassRecDeclList ConstVarClassRecDeclList) {
        this.ConstVarClassRecDeclItem=ConstVarClassRecDeclItem;
        if(ConstVarClassRecDeclItem!=null) ConstVarClassRecDeclItem.setParent(this);
        this.ConstVarClassRecDeclList=ConstVarClassRecDeclList;
        if(ConstVarClassRecDeclList!=null) ConstVarClassRecDeclList.setParent(this);
    }

    public ConstVarClassRecDeclItem getConstVarClassRecDeclItem() {
        return ConstVarClassRecDeclItem;
    }

    public void setConstVarClassRecDeclItem(ConstVarClassRecDeclItem ConstVarClassRecDeclItem) {
        this.ConstVarClassRecDeclItem=ConstVarClassRecDeclItem;
    }

    public ConstVarClassRecDeclList getConstVarClassRecDeclList() {
        return ConstVarClassRecDeclList;
    }

    public void setConstVarClassRecDeclList(ConstVarClassRecDeclList ConstVarClassRecDeclList) {
        this.ConstVarClassRecDeclList=ConstVarClassRecDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstVarClassRecDeclItem!=null) ConstVarClassRecDeclItem.accept(visitor);
        if(ConstVarClassRecDeclList!=null) ConstVarClassRecDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVarClassRecDeclItem!=null) ConstVarClassRecDeclItem.traverseTopDown(visitor);
        if(ConstVarClassRecDeclList!=null) ConstVarClassRecDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVarClassRecDeclItem!=null) ConstVarClassRecDeclItem.traverseBottomUp(visitor);
        if(ConstVarClassRecDeclList!=null) ConstVarClassRecDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstVCRDListYes(\n");

        if(ConstVarClassRecDeclItem!=null)
            buffer.append(ConstVarClassRecDeclItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstVarClassRecDeclList!=null)
            buffer.append(ConstVarClassRecDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstVCRDListYes]");
        return buffer.toString();
    }
}
