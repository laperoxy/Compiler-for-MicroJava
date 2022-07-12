// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class IdNumCharBoolConstListYes extends IdNumCharBoolConstList {

    private IdNumCharBoolConst IdNumCharBoolConst;
    private IdNumCharBoolConstList IdNumCharBoolConstList;

    public IdNumCharBoolConstListYes (IdNumCharBoolConst IdNumCharBoolConst, IdNumCharBoolConstList IdNumCharBoolConstList) {
        this.IdNumCharBoolConst=IdNumCharBoolConst;
        if(IdNumCharBoolConst!=null) IdNumCharBoolConst.setParent(this);
        this.IdNumCharBoolConstList=IdNumCharBoolConstList;
        if(IdNumCharBoolConstList!=null) IdNumCharBoolConstList.setParent(this);
    }

    public IdNumCharBoolConst getIdNumCharBoolConst() {
        return IdNumCharBoolConst;
    }

    public void setIdNumCharBoolConst(IdNumCharBoolConst IdNumCharBoolConst) {
        this.IdNumCharBoolConst=IdNumCharBoolConst;
    }

    public IdNumCharBoolConstList getIdNumCharBoolConstList() {
        return IdNumCharBoolConstList;
    }

    public void setIdNumCharBoolConstList(IdNumCharBoolConstList IdNumCharBoolConstList) {
        this.IdNumCharBoolConstList=IdNumCharBoolConstList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdNumCharBoolConst!=null) IdNumCharBoolConst.accept(visitor);
        if(IdNumCharBoolConstList!=null) IdNumCharBoolConstList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdNumCharBoolConst!=null) IdNumCharBoolConst.traverseTopDown(visitor);
        if(IdNumCharBoolConstList!=null) IdNumCharBoolConstList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdNumCharBoolConst!=null) IdNumCharBoolConst.traverseBottomUp(visitor);
        if(IdNumCharBoolConstList!=null) IdNumCharBoolConstList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IdNumCharBoolConstListYes(\n");

        if(IdNumCharBoolConst!=null)
            buffer.append(IdNumCharBoolConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IdNumCharBoolConstList!=null)
            buffer.append(IdNumCharBoolConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IdNumCharBoolConstListYes]");
        return buffer.toString();
    }
}
