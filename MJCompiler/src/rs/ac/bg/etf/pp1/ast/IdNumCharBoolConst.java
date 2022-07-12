// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class IdNumCharBoolConst implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String varName;
    private NumCharBoolConst NumCharBoolConst;

    public IdNumCharBoolConst (String varName, NumCharBoolConst NumCharBoolConst) {
        this.varName=varName;
        this.NumCharBoolConst=NumCharBoolConst;
        if(NumCharBoolConst!=null) NumCharBoolConst.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public NumCharBoolConst getNumCharBoolConst() {
        return NumCharBoolConst;
    }

    public void setNumCharBoolConst(NumCharBoolConst NumCharBoolConst) {
        this.NumCharBoolConst=NumCharBoolConst;
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
        if(NumCharBoolConst!=null) NumCharBoolConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NumCharBoolConst!=null) NumCharBoolConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NumCharBoolConst!=null) NumCharBoolConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IdNumCharBoolConst(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(NumCharBoolConst!=null)
            buffer.append(NumCharBoolConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IdNumCharBoolConst]");
        return buffer.toString();
    }
}
