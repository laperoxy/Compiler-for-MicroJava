// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class DesigListItems extends DesignatorList {

    private DesignatorListItem DesignatorListItem;
    private DesignatorList DesignatorList;

    public DesigListItems (DesignatorListItem DesignatorListItem, DesignatorList DesignatorList) {
        this.DesignatorListItem=DesignatorListItem;
        if(DesignatorListItem!=null) DesignatorListItem.setParent(this);
        this.DesignatorList=DesignatorList;
        if(DesignatorList!=null) DesignatorList.setParent(this);
    }

    public DesignatorListItem getDesignatorListItem() {
        return DesignatorListItem;
    }

    public void setDesignatorListItem(DesignatorListItem DesignatorListItem) {
        this.DesignatorListItem=DesignatorListItem;
    }

    public DesignatorList getDesignatorList() {
        return DesignatorList;
    }

    public void setDesignatorList(DesignatorList DesignatorList) {
        this.DesignatorList=DesignatorList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorListItem!=null) DesignatorListItem.accept(visitor);
        if(DesignatorList!=null) DesignatorList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorListItem!=null) DesignatorListItem.traverseTopDown(visitor);
        if(DesignatorList!=null) DesignatorList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorListItem!=null) DesignatorListItem.traverseBottomUp(visitor);
        if(DesignatorList!=null) DesignatorList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesigListItems(\n");

        if(DesignatorListItem!=null)
            buffer.append(DesignatorListItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorList!=null)
            buffer.append(DesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesigListItems]");
        return buffer.toString();
    }
}
