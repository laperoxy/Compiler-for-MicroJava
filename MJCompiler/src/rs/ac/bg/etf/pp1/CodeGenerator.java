package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

import java.util.ArrayList;

import rs.ac.bg.etf.pp1.*;
import rs.ac.bg.etf.pp1.CounterVisitor.*;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;

	boolean minus = false;

	public static final Struct BoolType = new Struct(Struct.Bool);

	private Obj powObj = new Obj(Obj.Meth, "pow", new Struct(Struct.Int));
	private Obj powObjPom = new Obj(Obj.Meth, "powPom", new Struct(Struct.Int));

//	public CodeGenerator() {
//
//		powObj.setAdr(Code.pc);
//		Code.put(Code.enter);
//		Code.put(3);
//		Code.put(3);
//		Code.put(Code.load_1);
//		Code.put(Code.const_1);
//		Code.putFalseJump(Code.eq, 25);
//		Code.put(Code.load);
//		Code.put(0);
//		Code.put(Code.exit);
//		Code.put(Code.return_);
//		Code.put(Code.load);
//		Code.put(0);
//		Code.put(Code.load_2);
//		Code.put(Code.mul);
//		Code.put(Code.load_1);
//		Code.put(Code.const_1);
//		Code.put(Code.sub);
//		Code.put(Code.load_2);
//		int offset = powObj.getAdr() - Code.pc;
//		Code.put(Code.call);
//		Code.put2(offset);
//		Code.put(Code.exit);
//		Code.put(Code.return_);
//
//		powObjPom.setAdr(Code.pc);
//		Code.put(Code.enter);
//		Code.put(2);
//		Code.put(2);
//		Code.put(Code.load);
//		Code.put(0);
//		Code.put(Code.const_1);
//		Code.putFalseJump(Code.eq, 50);
//		Code.put(Code.const_1);
//		Code.put(Code.exit);
//		Code.put(Code.return_);
//		Code.put(Code.load);
//		Code.put(0);
//		Code.put(Code.load_1);
//		Code.put(Code.load);
//		Code.put(0);
//
//		offset = powObj.getAdr() - Code.pc;
//		Code.put(Code.call);
//		Code.put2(offset);
//
//		Code.put(Code.exit);
//		Code.put(Code.return_);
//
//	}

	public int getMainPc() {
		return mainPc;
	}

	public void visit(MinusOptional minusOptional) {
		System.out.println("Minus");
		minus = true;
	}

	public void visit(SingleStatementRead singleStatementRead) {
		Obj desigObj = singleStatementRead.getDesignator().obj;
		if (singleStatementRead.getDesignator().obj.getType().getKind() == Struct.Array) {
			Code.load(desigObj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			Code.put(Code.read);
		} else {
			Code.put(Code.read);
			Code.store(desigObj);
		}

	}

	public void visit(SingleStatementPrint singleStatementPrint) {

//		if(printElem) {
//			Code.put();
//		}

		// System.out.println(singleStatementPrint.getExpr().struct.getKind());

		if (singleStatementPrint.getExpr().struct == Tab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else if (singleStatementPrint.getExpr().struct == Tab.charType) {
			Code.loadConst(3);
			Code.put(Code.bprint);
		} else {
			Code.loadConst(3);
			Code.put(Code.print);
		}
	}

	public void visit(TermListYes termListYes) {
		if (termListYes.getAddop() instanceof AddopPlus)
			Code.put(Code.add);
		else
			Code.put(Code.sub);
	}

	public void visit(FactorListYes factorListYes) {
		if (factorListYes.getMulop() instanceof MulopMul) {
			Code.put(Code.mul);
		} else if (factorListYes.getMulop() instanceof MulopDiv) {
			Code.put(Code.div);
		} else {
			Code.put(Code.rem);
		}
	}

	public void visit(BaseExpListYes baseExpListYes) {

//		int offset = powObjPom.getAdr() - Code.pc;
//		Code.put(Code.call);
//		Code.put2(offset);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);// 3 1

		Code.put(Code.dup);
		Code.loadConst(1);

		Code.putFalseJump(Code.ne, 0);
		int adrPom = Code.pc;

		Code.loadConst(1);// 3 2 1
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);

		int myAdr = Code.pc;
		System.out.println(myAdr);

		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup2);// 3 2 1 2 1
		Code.put(Code.mul);// 3 2 1 2
		Code.put(Code.dup_x1);// 3 2 2 1 2
		Code.put(Code.pop);
		Code.put(Code.pop);// 2 4 3

		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);

		Code.loadConst(1);
		Code.put(Code.sub);// 3

		Code.put(Code.dup);
		Code.loadConst(0);

		Code.putFalseJump(Code.eq, myAdr);

		// Code.put(Code.dup_x1);
		Code.put(Code.pop);

		Code.fixup(adrPom - 2);

		Code.put(Code.dup_x1);
		Code.put(Code.pop);

		Code.put(Code.pop);

		// Code.fixup(adrPom);

//		int offset = Code.pc - myAdr;
//		Code.put(offset);
		// Code.putFalseJump(Code.eq, offset);
		// Code.put(Code.jcc);

	}

	int adrToJumpTo = 0;
	boolean labelaZabelezena = false;
	boolean gotoZabelezen = false;

	public void visit(SingleStatementGo singleStatementGo) {
		gotoZabelezen = true;
		if(labelaZabelezena) {
			Code.putJump(adrToJumpTo);
		}else {
			Code.putJump(0);
			adrToJumpTo = Code.pc;
		}
	}

	public void visit(Label label) {
		if (label.getParent() instanceof StatementSingleLabel) {
			labelaZabelezena = true;
			if (gotoZabelezen) {
				Code.fixup(adrToJumpTo - 2);
			} else {
				adrToJumpTo = Code.pc;
			}
		}
	}

	public void visit(BaseExpNum baseExpNum) {
		Obj num = Tab.insert(Obj.Con, "$", baseExpNum.struct);
		num.setLevel(0);
		num.setAdr(baseExpNum.getN1());
		Code.load(num);
		if (minus) {
			minus = false;
			Code.put(Code.neg);
		}
	}

	public void visit(BaseExpChar baseExpChar) {

		Obj chr = Tab.insert(Obj.Con, "$", baseExpChar.struct);
		chr.setLevel(0);
		String str = baseExpChar.getC1();
		str = str.substring(1, str.length() - 1);
		chr.setAdr(str.charAt(0));

		Code.load(chr);
	}

	public void visit(BaseExpBool baseExpBool) {
		Obj bl = Tab.insert(Obj.Con, "$", baseExpBool.struct);
		bl.setLevel(0);
		String boolVal = baseExpBool.getB1();
		if (boolVal.equals("true")) {
			bl.setAdr(1);
		} else {
			bl.setAdr(0);
		}

		Code.load(bl);
	}

	public void visit(LRExprOptional lrExprOptional) {
		// System.out.println("Pravi niz");
		initArray = true;
		if (lrExprOptional.getExpr().struct == Tab.intType || lrExprOptional.getExpr().struct == BoolType) {
			Code.put(Code.newarray);
			Code.put(1);
		} else if (lrExprOptional.getExpr().struct == Tab.charType) {
			Code.put(Code.newarray);
			Code.put(0);
		}
	}

	public void visit(MethodTypeName methodTypeName) {

		if ("main".equalsIgnoreCase(methodTypeName.getMethName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// System.out.println(methodTypeName.obj.getAdr());
		// Collect arguments and local vars
		SyntaxNode methNode = methodTypeName.getParent();

		VarCounter varCnt = new VarCounter();
		methNode.traverseTopDown(varCnt);

		FormParamCounter formParsCnt = new FormParamCounter();
		methNode.traverseTopDown(formParsCnt);

		Code.put(Code.enter);
		Code.put(formParsCnt.getCount());
		Code.put(formParsCnt.getCount() + varCnt.getCount());

	}

	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(DesignatorStatemet designatorStatemet) {
		Obj desigObj = designatorStatemet.getDesignator().obj;
		if (designatorStatemet.getAssignopOpt() instanceof AssignopOptionalAssignop) {
			// System.out.println(desigObj.getKind());
			// Code.store(desigObj);
			if (initArray || desigObj.getKind() == Obj.Var && desigObj.getType().getKind() != Struct.Array) {
				Code.store(desigObj);
			} else if (desigObj.getType().getKind() == Struct.Array) {
				if (desigObj.getType().getElemType().getKind() == Struct.Int)
					Code.put(Code.astore);
				else if (desigObj.getType().getElemType().getKind() == Struct.Char)
					Code.put(Code.bastore);
			}
		} else if (designatorStatemet.getAssignopOpt() instanceof AssignopOptionalAP) {
			Obj funcObj = designatorStatemet.getDesignator().obj;
			int offset = funcObj.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
			if (designatorStatemet.getDesignator().obj.getType() != Tab.noType) {
				Code.put(Code.pop);
			}
		} else if (designatorStatemet.getAssignopOpt() instanceof AssignopOptionalInc) {
			if (designatorStatemet.getDesignator().obj.getType().getKind() == Struct.Array) {
				Code.put(Code.dup2);
				Code.put(Code.aload);
				Code.put(Code.const_1);
				Code.put(Code.add);
				Code.put(Code.astore);
			} else {
				if (designatorStatemet.getDesignator().obj.getKind() == 5) {
					Code.put(Code.getstatic);
					Code.put2(desigObj.getAdr());
					Code.put(Code.dup_x1);
					Code.put(Code.pop);
					Code.put(Code.dup2);
					Code.put(Code.aload);
					Code.loadConst(1);
					Code.put(Code.add);
					Code.store(desigObj);
				} else {
					Code.load(desigObj);
					Code.loadConst(1);
					Code.put(Code.add);
					Code.store(desigObj);
				}

			}
		} else if (designatorStatemet.getAssignopOpt() instanceof AssignopOptionalDec) {
			if (designatorStatemet.getDesignator().obj.getType().getKind() == Struct.Array) {
				Code.put(Code.dup2);
				Code.put(Code.aload);
				Code.put(Code.const_1);
				Code.put(Code.add);
				Code.put(Code.astore);
			} else {
				if (designatorStatemet.getDesignator().obj.getKind() == 5) {
					Code.put(Code.getstatic);
					Code.put2(desigObj.getAdr());
					Code.put(Code.dup_x1);
					Code.put(Code.pop);
					Code.put(Code.dup2);
					Code.put(Code.aload);
					Code.loadConst(1);
					Code.put(Code.sub);
					Code.store(desigObj);
				} else {
					Code.load(desigObj);
					Code.loadConst(1);
					Code.put(Code.sub);
					Code.store(desigObj);
				}
			}
		}

	}

	boolean initArray = false;
	boolean initVar = false;

	public void visit(Designator designator) {

		Obj desig = designator.obj;
		SyntaxNode parent = designator.getParent();

		if (DesignatorStatemet.class != parent.getClass()) {

			// Code.load(designator.obj);
//			if (parent.getParent().getParent().getParent().getParent().getClass() != SingleStatementPrint.class) {
//				if (desig.getKind() != 5) {
//					Code.load(designator.obj);
//				} else {
//					Code.put(Code.getstatic);
//					Code.put2(desig.getAdr());
//					Code.put(Code.dup_x1);
//					Code.put(Code.pop);
//					Code.put(Code.aload);
//				}
//			} else {
			if (desig.getKind() != 5) {
				Code.load(designator.obj);
			} else {
				if (desig.getType().getKind() == Struct.Int) {
					Code.put(Code.getstatic);
					Code.put2(desig.getAdr());
					Code.put(Code.dup_x1);
					Code.put(Code.pop);
					Code.put(Code.aload);
				} else {
					Code.put(Code.getstatic);
					Code.put2(desig.getAdr());
					Code.put(Code.dup_x1);
					Code.put(Code.pop);
					Code.put(Code.baload);
				}

			}
			if (minus) {
				minus = false;
				Code.put(Code.neg);
			}
//			}

		}

		if (DesignatorStatemet.class == parent.getClass()) {
			if (((DesignatorStatemet) parent).getAssignopOpt() instanceof AssignopOptionalAssignop) {
				if (designator.getDesignatorList() instanceof DesigListItems) {
					if (((DesigListItems) designator.getDesignatorList())
							.getDesignatorListItem() instanceof DesigListItemExpr) {
						Code.put(Code.getstatic);
						Code.put2(designator.obj.getAdr());// index, adr, val
						Code.put(Code.dup_x1);
						Code.put(Code.pop);
					}
				}
			}
		}
//		else if (parent instanceof Factor) {
//			if (designator.getDesignatorList() instanceof DesigListItems) {
//				if (((DesigListItems) designator.getDesignatorList())
//						.getDesignatorListItem() instanceof DesigListItemExpr) {
//					System.out.println(designator.obj.getKind());
//					// Code.load(designator.obj);
//					Code.put(Code.getstatic);
//					Code.put2(designator.obj.getAdr());// index, adr, val
////					Code.put(Code.dup_x1);
////					Code.put(Code.pop);
//				}
//			}
//		}

	}

	public void visit(BaseExpDesigFunc baseExpDesigFunc) {
		Obj funcObj = baseExpDesigFunc.getDesignator().obj;
		int offset = funcObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}

//	int operand1 = 0;
//
//	public void visit(Factor factor) {
//		((BaseExpNum) factor.getBaseExp()).getN1();
//	}
//
//	public void visit(BaseExpListYes baseExpListYes) {
//
//	}

	public void visit(SingleStatementRetExpr singleStatementRetExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

}