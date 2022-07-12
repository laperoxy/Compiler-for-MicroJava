// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class CMDeclOptional extends CMDeclOpt {

    private ConstructorDeclOpt ConstructorDeclOpt;
    private MethodDeclList MethodDeclList;

    public CMDeclOptional (ConstructorDeclOpt ConstructorDeclOpt, MethodDeclList MethodDeclList) {
        this.ConstructorDeclOpt=ConstructorDeclOpt;
        if(ConstructorDeclOpt!=null) ConstructorDeclOpt.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public ConstructorDeclOpt getConstructorDeclOpt() {
        return ConstructorDeclOpt;
    }

    public void setConstructorDeclOpt(ConstructorDeclOpt ConstructorDeclOpt) {
        this.ConstructorDeclOpt=ConstructorDeclOpt;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorDeclOpt!=null) ConstructorDeclOpt.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorDeclOpt!=null) ConstructorDeclOpt.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorDeclOpt!=null) ConstructorDeclOpt.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CMDeclOptional(\n");

        if(ConstructorDeclOpt!=null)
            buffer.append(ConstructorDeclOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CMDeclOptional]");
        return buffer.toString();
    }
}
