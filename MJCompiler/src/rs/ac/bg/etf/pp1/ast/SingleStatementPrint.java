// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class SingleStatementPrint extends SingleStatement {

    private Expr Expr;
    private NumConstOpt NumConstOpt;

    public SingleStatementPrint (Expr Expr, NumConstOpt NumConstOpt) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.NumConstOpt=NumConstOpt;
        if(NumConstOpt!=null) NumConstOpt.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public NumConstOpt getNumConstOpt() {
        return NumConstOpt;
    }

    public void setNumConstOpt(NumConstOpt NumConstOpt) {
        this.NumConstOpt=NumConstOpt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(NumConstOpt!=null) NumConstOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(NumConstOpt!=null) NumConstOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(NumConstOpt!=null) NumConstOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatementPrint(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NumConstOpt!=null)
            buffer.append(NumConstOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatementPrint]");
        return buffer.toString();
    }
}
