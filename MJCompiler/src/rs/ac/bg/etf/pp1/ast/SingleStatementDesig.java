// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class SingleStatementDesig extends SingleStatement {

    private DesignatorStatemet DesignatorStatemet;

    public SingleStatementDesig (DesignatorStatemet DesignatorStatemet) {
        this.DesignatorStatemet=DesignatorStatemet;
        if(DesignatorStatemet!=null) DesignatorStatemet.setParent(this);
    }

    public DesignatorStatemet getDesignatorStatemet() {
        return DesignatorStatemet;
    }

    public void setDesignatorStatemet(DesignatorStatemet DesignatorStatemet) {
        this.DesignatorStatemet=DesignatorStatemet;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatemet!=null) DesignatorStatemet.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatemet!=null) DesignatorStatemet.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatemet!=null) DesignatorStatemet.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleStatementDesig(\n");

        if(DesignatorStatemet!=null)
            buffer.append(DesignatorStatemet.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleStatementDesig]");
        return buffer.toString();
    }
}
