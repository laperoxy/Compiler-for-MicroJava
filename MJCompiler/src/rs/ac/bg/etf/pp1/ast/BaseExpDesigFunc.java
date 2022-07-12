// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class BaseExpDesigFunc extends BaseExp {

    private Designator Designator;
    private ActPairsOpt ActPairsOpt;

    public BaseExpDesigFunc (Designator Designator, ActPairsOpt ActPairsOpt) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.ActPairsOpt=ActPairsOpt;
        if(ActPairsOpt!=null) ActPairsOpt.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public ActPairsOpt getActPairsOpt() {
        return ActPairsOpt;
    }

    public void setActPairsOpt(ActPairsOpt ActPairsOpt) {
        this.ActPairsOpt=ActPairsOpt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(ActPairsOpt!=null) ActPairsOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ActPairsOpt!=null) ActPairsOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ActPairsOpt!=null) ActPairsOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BaseExpDesigFunc(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActPairsOpt!=null)
            buffer.append(ActPairsOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BaseExpDesigFunc]");
        return buffer.toString();
    }
}
