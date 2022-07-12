// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class VarDeclAngles extends VarDecl {

    private Type Type;
    private String varName;
    private IdentAnglesList IdentAnglesList;

    public VarDeclAngles (Type Type, String varName, IdentAnglesList IdentAnglesList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varName=varName;
        this.IdentAnglesList=IdentAnglesList;
        if(IdentAnglesList!=null) IdentAnglesList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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
        if(Type!=null) Type.accept(visitor);
        if(IdentAnglesList!=null) IdentAnglesList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(IdentAnglesList!=null) IdentAnglesList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(IdentAnglesList!=null) IdentAnglesList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclAngles(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(IdentAnglesList!=null)
            buffer.append(IdentAnglesList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclAngles]");
        return buffer.toString();
    }
}
