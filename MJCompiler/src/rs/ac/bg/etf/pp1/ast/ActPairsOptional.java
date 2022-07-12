// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class ActPairsOptional extends ActPairsOpt {

    private ActPairs ActPairs;

    public ActPairsOptional (ActPairs ActPairs) {
        this.ActPairs=ActPairs;
        if(ActPairs!=null) ActPairs.setParent(this);
    }

    public ActPairs getActPairs() {
        return ActPairs;
    }

    public void setActPairs(ActPairs ActPairs) {
        this.ActPairs=ActPairs;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActPairs!=null) ActPairs.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActPairs!=null) ActPairs.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActPairs!=null) ActPairs.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActPairsOptional(\n");

        if(ActPairs!=null)
            buffer.append(ActPairs.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActPairsOptional]");
        return buffer.toString();
    }
}
