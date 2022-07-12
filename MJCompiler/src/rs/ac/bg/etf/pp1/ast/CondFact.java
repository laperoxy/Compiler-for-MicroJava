// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class CondFact implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private Expr Expr;
    private ExprRelopOpt ExprRelopOpt;

    public CondFact (Expr Expr, ExprRelopOpt ExprRelopOpt) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.ExprRelopOpt=ExprRelopOpt;
        if(ExprRelopOpt!=null) ExprRelopOpt.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public ExprRelopOpt getExprRelopOpt() {
        return ExprRelopOpt;
    }

    public void setExprRelopOpt(ExprRelopOpt ExprRelopOpt) {
        this.ExprRelopOpt=ExprRelopOpt;
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
        if(ExprRelopOpt!=null) ExprRelopOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(ExprRelopOpt!=null) ExprRelopOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(ExprRelopOpt!=null) ExprRelopOpt.traverseBottomUp(visitor);
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

        if(ExprRelopOpt!=null)
            buffer.append(ExprRelopOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFact]");
        return buffer.toString();
    }
}
