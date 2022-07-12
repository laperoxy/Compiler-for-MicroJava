// generated with ast extension for cup
// version 0.8
// 26/5/2022 19:5:30


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private ETypeOpt ETypeOpt;
    private VarDeclList VarDeclList;
    private CMDeclOpt CMDeclOpt;

    public ClassDecl (String I1, ETypeOpt ETypeOpt, VarDeclList VarDeclList, CMDeclOpt CMDeclOpt) {
        this.I1=I1;
        this.ETypeOpt=ETypeOpt;
        if(ETypeOpt!=null) ETypeOpt.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.CMDeclOpt=CMDeclOpt;
        if(CMDeclOpt!=null) CMDeclOpt.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public ETypeOpt getETypeOpt() {
        return ETypeOpt;
    }

    public void setETypeOpt(ETypeOpt ETypeOpt) {
        this.ETypeOpt=ETypeOpt;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public CMDeclOpt getCMDeclOpt() {
        return CMDeclOpt;
    }

    public void setCMDeclOpt(CMDeclOpt CMDeclOpt) {
        this.CMDeclOpt=CMDeclOpt;
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
        if(ETypeOpt!=null) ETypeOpt.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(CMDeclOpt!=null) CMDeclOpt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ETypeOpt!=null) ETypeOpt.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(CMDeclOpt!=null) CMDeclOpt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ETypeOpt!=null) ETypeOpt.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(CMDeclOpt!=null) CMDeclOpt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ETypeOpt!=null)
            buffer.append(ETypeOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CMDeclOpt!=null)
            buffer.append(CMDeclOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
