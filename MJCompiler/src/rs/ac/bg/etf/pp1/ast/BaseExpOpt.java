// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class BaseExpOpt extends BaseExp {

    private Type Type;
    private ExprOptLR ExprOptLR;

    public BaseExpOpt (Type Type, ExprOptLR ExprOptLR) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ExprOptLR=ExprOptLR;
        if(ExprOptLR!=null) ExprOptLR.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ExprOptLR getExprOptLR() {
        return ExprOptLR;
    }

    public void setExprOptLR(ExprOptLR ExprOptLR) {
        this.ExprOptLR=ExprOptLR;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ExprOptLR!=null) ExprOptLR.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ExprOptLR!=null) ExprOptLR.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ExprOptLR!=null) ExprOptLR.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BaseExpOpt(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprOptLR!=null)
            buffer.append(ExprOptLR.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BaseExpOpt]");
        return buffer.toString();
    }
}
