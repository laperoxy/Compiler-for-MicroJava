// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class IdentAnglesListYes extends IdentAnglesList {

    private String varName;
    private IdentAnglesList IdentAnglesList;

    public IdentAnglesListYes (String varName, IdentAnglesList IdentAnglesList) {
        this.varName=varName;
        this.IdentAnglesList=IdentAnglesList;
        if(IdentAnglesList!=null) IdentAnglesList.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public IdentAnglesList getIdentAnglesList() {
        return IdentAnglesList;
    }

    public void setIdentAnglesList(IdentAnglesList IdentAnglesList) {
        this.IdentAnglesList=IdentAnglesList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentAnglesList!=null) IdentAnglesList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentAnglesList!=null) IdentAnglesList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentAnglesList!=null) IdentAnglesList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IdentAnglesListYes(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(IdentAnglesList!=null)
            buffer.append(IdentAnglesList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IdentAnglesListYes]");
        return buffer.toString();
    }
}
