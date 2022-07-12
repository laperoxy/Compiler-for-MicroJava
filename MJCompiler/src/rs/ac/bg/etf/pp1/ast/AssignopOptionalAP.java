// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class AssignopOptionalAP extends AssignopOpt {

    private ActPairsOpt ActPairsOpt;

    public AssignopOptionalAP (ActPairsOpt ActPairsOpt) {
        this.ActPairsOpt=ActPairsOpt;
        if(ActPairsOpt!=null) ActPairsOpt.setParent(this);
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
        if(ActPairsOpt!=null) ActPairsOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActPairsOpt!=null) ActPairsOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActPairsOpt!=null) ActPairsOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignopOptionalAP(\n");

        if(ActPairsOpt!=null)
            buffer.append(ActPairsOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssignopOptionalAP]");
        return buffer.toString();
    }
}
