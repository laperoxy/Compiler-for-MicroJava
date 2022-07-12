// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class Factor implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private BaseExp BaseExp;
    private BaseExpList BaseExpList;

    public Factor (BaseExp BaseExp, BaseExpList BaseExpList) {
        this.BaseExp=BaseExp;
        if(BaseExp!=null) BaseExp.setParent(this);
        this.BaseExpList=BaseExpList;
        if(BaseExpList!=null) BaseExpList.setParent(this);
    }

    public BaseExp getBaseExp() {
        return BaseExp;
    }

    public void setBaseExp(BaseExp BaseExp) {
        this.BaseExp=BaseExp;
    }

    public BaseExpList getBaseExpList() {
        return BaseExpList;
    }

    public void setBaseExpList(BaseExpList BaseExpList) {
        this.BaseExpList=BaseExpList;
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
        if(BaseExp!=null) BaseExp.accept(visitor);
        if(BaseExpList!=null) BaseExpList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(BaseExp!=null) BaseExp.traverseTopDown(visitor);
        if(BaseExpList!=null) BaseExpList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(BaseExp!=null) BaseExp.traverseBottomUp(visitor);
        if(BaseExpList!=null) BaseExpList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor(\n");

        if(BaseExp!=null)
            buffer.append(BaseExp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(BaseExpList!=null)
            buffer.append(BaseExpList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor]");
        return buffer.toString();
    }
}
