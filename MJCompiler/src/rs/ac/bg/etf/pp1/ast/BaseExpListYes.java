// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class BaseExpListYes extends BaseExpList {

    private Expop Expop;
    private BaseExp BaseExp;
    private BaseExpList BaseExpList;

    public BaseExpListYes (Expop Expop, BaseExp BaseExp, BaseExpList BaseExpList) {
        this.Expop=Expop;
        if(Expop!=null) Expop.setParent(this);
        this.BaseExp=BaseExp;
        if(BaseExp!=null) BaseExp.setParent(this);
        this.BaseExpList=BaseExpList;
        if(BaseExpList!=null) BaseExpList.setParent(this);
    }

    public Expop getExpop() {
        return Expop;
    }

    public void setExpop(Expop Expop) {
        this.Expop=Expop;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expop!=null) Expop.accept(visitor);
        if(BaseExp!=null) BaseExp.accept(visitor);
        if(BaseExpList!=null) BaseExpList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expop!=null) Expop.traverseTopDown(visitor);
        if(BaseExp!=null) BaseExp.traverseTopDown(visitor);
        if(BaseExpList!=null) BaseExpList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expop!=null) Expop.traverseBottomUp(visitor);
        if(BaseExp!=null) BaseExp.traverseBottomUp(visitor);
        if(BaseExpList!=null) BaseExpList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BaseExpListYes(\n");

        if(Expop!=null)
            buffer.append(Expop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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
        buffer.append(") [BaseExpListYes]");
        return buffer.toString();
    }
}
