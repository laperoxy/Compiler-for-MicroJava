package rs.ac.bg.etf.pp1;

import java.util.ArrayList;

import org.apache.log4j.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	boolean errorDetected = false;

	int printCallCount = 0;
	int varDeclCount = 0;

	String curConstType = null;
	boolean constFault = false;
	int constVal = 0;
	Obj curConst = null;

	Obj curMethod = null;
	Struct curRetType = null;

	Obj curRecord = null;

	boolean retFound = false;

	Struct retWithExpr = null;

	public static final Struct BoolType = new Struct(Struct.Bool);
	public static final Struct TypeVoid = new Struct(Struct.None);
	public static final Struct ArrayTypeInt = new Struct(Struct.Array, Tab.intType);
	public static final Struct ArrayTypeChar = new Struct(Struct.Array, Tab.charType);
	public static final Struct ArrayTypeBool = new Struct(Struct.Array, BoolType);

	public SemanticAnalyzer() {
		Tab.insert(Obj.Type, "void", TypeVoid);
		Tab.insert(Obj.Type, "bool", BoolType);
		Tab.insert(Obj.Type, "arrayInt", ArrayTypeInt);
		Tab.insert(Obj.Type, "arrayChar", ArrayTypeChar);
		Tab.insert(Obj.Type, "arrayBool", ArrayTypeBool);
	}

	Logger log = Logger.getLogger(getClass());
	int nVars;

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	public boolean passed() {
		return !errorDetected;
	}

	// =============================PROG=============================

	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(Program program) {
		nVars = Tab.currentScope.getnVars();
		System.out.println(nVars);
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}


	// =============================BASE EXPR==========================

	public void visit(BaseExpDesig baseExpDesig) {
		Obj desigObj = Tab.find(baseExpDesig.getDesignator().getDesigName());
		desigObj = baseExpDesig.getDesignator().obj;
		// report_info("Usao u baseExpDesig " + desigObj.getKind(), null);
		if (desigObj == Tab.noObj) {
			report_error("Ne postoji desiognator(BaseExpDesig)!", null);
		} else {
			baseExpDesig.struct = desigObj.getType();
			// report_info("Usao u baseExpDesig " + baseExpDesig.struct.getKind(), null);
		}

	}

	public void visit(BaseExpOpt baseExpOpt) {
		if (baseExpOpt.getExprOptLR() instanceof LRExprOptional) {
			if (((LRExprOptional) baseExpOpt.getExprOptLR()).getExpr().struct != Tab.intType) {
				report_error("Indeks niza mora biti tipa int (BaseExpOpt)!", null);
			} else {
				baseExpOpt.struct = baseExpOpt.getType().struct == Tab.intType ? ArrayTypeInt
						: baseExpOpt.getType().struct == Tab.charType ? ArrayTypeChar : ArrayTypeBool;
			}
		}
	}

	public void visit(BaseExpExpr baseExpExpr) {
		baseExpExpr.struct = baseExpExpr.getExpr().struct;
	}

	public void visit(LRExprOptional exprOptional) {
		if (exprOptional.getExpr().struct != Tab.intType) {
			report_error("Velicina niza mora biti int(LRExprOptional)!", null);
		}
	}

	public void visit(BaseExpNum baseExpNumber) {
		baseExpNumber.struct = Tab.intType;
	}

	public void visit(BaseExpChar baseExpChar) {
		baseExpChar.struct = Tab.charType;
	}

	public void visit(BaseExpBool baseExpBool) {
		baseExpBool.struct = BoolType;
	}

	public void visit(BaseExpListYes baseExpList) {
		if (baseExpList.getBaseExpList() instanceof BaseExpListNo) {
			baseExpList.struct = baseExpList.getBaseExp().struct;
		} else {
			if (baseExpList.getBaseExpList().struct.compatibleWith(baseExpList.getBaseExp().struct)) {
				baseExpList.struct = baseExpList.getBaseExp().struct;
			} else {
				report_error("Strukture nisu kompatibilne BaseExpListYes!", null);
			}
		}
	}

	public void visit(Factor factor) {
		// report_info("Usao u factor", null);
		if (factor.getBaseExpList() instanceof BaseExpListNo) {
			factor.struct = factor.getBaseExp().struct;
		} else {
			if (factor.getBaseExpList().struct.compatibleWith(factor.getBaseExp().struct)) {
				if (factor.getBaseExp().struct == Tab.intType && factor.getBaseExpList().struct == Tab.intType)
					factor.struct = factor.getBaseExp().struct;
				else
					report_error("Strukture nisu kompatibilne Factor!", null);
			} else {
				report_error("Strukture nisu kompatibilne Factor!", null);
			}
		}
	}

	public void visit(FactorListYes factorListYes) {
		if (factorListYes.getFactorList() instanceof FactorListNo) {
			factorListYes.struct = factorListYes.getFactor().struct;
		} else {
			if (factorListYes.getFactorList().struct.compatibleWith(factorListYes.getFactor().struct)) {
				factorListYes.struct = factorListYes.getFactor().struct;
			} else {
				report_error("Strukture nisu kompatibilne FactorListYes!", null);
			}
		}
	}

	public void visit(Term term) {
		// report_info("Usao u term", null);
		if (term.getFactorList() instanceof FactorListNo) {
			term.struct = term.getFactor().struct;
		} else {
			if (term.getFactorList().struct.compatibleWith(term.getFactor().struct)) {
				if (term.getFactor().struct == Tab.intType && term.getFactorList().struct == Tab.intType)
					term.struct = term.getFactor().struct;
			} else {
				report_error("Strukture nisu kompatibilne Term!", null);
			}
		}
	}

	public void visit(TermListYes termListYes) {
		if (termListYes.getTermList() instanceof TermListNo) {
			termListYes.struct = termListYes.getTerm().struct;
		} else {
			if (termListYes.getTermList().struct.compatibleWith(termListYes.getTerm().struct)) {
				termListYes.struct = termListYes.getTerm().struct;
			} else {
				report_error("Strukture nisu kompatibilne TermListYes!", null);
			}
		}
	}

	public void visit(Expr expr) {
		// report_info("Usao u expr", null);
		if (expr.getMinusOpt() instanceof MinusOptional) {
			if (expr.getTerm().struct != Tab.intType) {
				report_error("Strukture nisu kompatibilne Term!", null);
			}
		}

		if (expr.getTermList() instanceof TermListNo) {
			expr.struct = expr.getTerm().struct;
		} else {
			if (expr.getTermList().struct.compatibleWith(expr.getTerm().struct)) {
				if (expr.getTerm().struct == Tab.intType && expr.getTermList().struct == Tab.intType)
					expr.struct = expr.getTerm().struct;
				else
					report_error("Strukture nisu kompatibilne Expr!", null);
			} else {
				report_error("Strukture nisu kompatibilne Expr!", null);
			}
		}
	}

	// ============================DESIGNATOR STATEMENT==========================

	public void visit(DesigListItemExpr desigListItemExpr) {
		if (desigListItemExpr.getExpr().struct != Tab.intType)
			report_error("Tip elementa niza mora biti int (DesigListItemExpr) linija: " + desigListItemExpr.getLine(),
					null);
	}

	public void visit(Designator designator) {
		Obj obj = Tab.find(designator.getDesigName());
		//System.out.println(obj.getAdr());
		if (obj == Tab.noObj) {
			report_error("Ime designatora " + designator.getDesigName() + " nije deklarisano!", null);
		} else {
			designator.obj = obj;
			if (designator.getDesignatorList() instanceof DesigListItems) {
				if (((DesigListItems) designator.getDesignatorList())
						.getDesignatorListItem() instanceof DesigListItemExpr) {
					if (obj.getType().getKind() != Struct.Array)
						report_error("Greska na liniji " + designator.getLine() + " : designator "
								+ designator.getDesigName() + " mora biti tipa niz! ", null);
					else
						designator.obj = new Obj(Obj.Elem, obj.getName(), obj.getType() == ArrayTypeInt ? Tab.intType
								: obj.getType() == ArrayTypeChar ? Tab.charType : BoolType);
						designator.obj.setAdr(obj.getAdr());
				}
			}
		}

		// report_info("Usao u designator " + designator.obj.getKind(), null);

	}

	public void visit(ActPairsListYes actPairsListYes) {
		if (actPairsListYes.getActPairsList() instanceof ActPairsListNo) {
			actPairsListYes.struct = actPairsListYes.getExpr().struct;
		} else {
			if (actPairsListYes.getActPairsList().struct.compatibleWith(actPairsListYes.getExpr().struct)) {
				actPairsListYes.struct = actPairsListYes.getExpr().struct;
			} else {
				report_error("Strukture nisu kompatibilne ActPairsListYes!", null);
			}
		}
	}

	public void visit(ActPairs actPairs) {
		if (actPairs.getActPairsList() instanceof ActPairsListNo) {
			actPairs.struct = actPairs.getExpr().struct;
		} else {
			if (actPairs.getActPairsList().struct.compatibleWith(actPairs.getExpr().struct)) {
				actPairs.struct = actPairs.getExpr().struct;
			} else {
				report_error("Strukture nisu kompatibilne ActPairs!", null);
			}
		}
	}

	public void visit(DesignatorStatemet designatorStatemet) {
		Obj desigFound = Tab.find(designatorStatemet.getDesignator().getDesigName());
		if (desigFound == Tab.noObj) {
			report_error("Designator nije pronadjen DesignatorStatemet!", null);
		} else {
			if ((designatorStatemet.getAssignopOpt() instanceof AssignopOptionalInc)
					|| (designatorStatemet.getAssignopOpt() instanceof AssignopOptionalDec)) {
				if (designatorStatemet.getDesignator().obj.getType() != Tab.intType) {
					report_error("Strukture nisu kompatibilne DesignatorStatemet!", null);
				} else {
					if (designatorStatemet.getDesignator().obj.getKind() == Obj.Var
							|| designatorStatemet.getDesignator().obj.getKind() == Obj.Elem) {
						//designatorStatemet.getDesignator().obj.setAdr(constVal);
						designatorStatemet.struct = designatorStatemet.getDesignator().obj.getType();
					}
					else
						report_error("Tipovi nisu kompatibilne DesignatorStatemet!", null);
				}

			} else if (designatorStatemet.getAssignopOpt() instanceof AssignopOptionalAssignop) {
				Obj desig = designatorStatemet.getDesignator().obj;
				if (desig.getKind() == Obj.Var || desig.getKind() == Obj.Elem) {
					Struct tipExpr = ((AssignopOptionalAssignop) designatorStatemet.getAssignopOpt()).getExpr().struct;
					if (desig.getType().compatibleWith(tipExpr)) {
						designatorStatemet.struct = desig.getType();
					} else {
						report_error("" + tipExpr.getKind() + desig.getType().getKind(), null);
						report_error("Strukture nisu kompatibilne DesignatorStatemet!", null);
					}

				} else {
					report_error("Tipovi nisu kompatibilni DesignatorStatemet!", null);
				}
			} else if (designatorStatemet.getAssignopOpt() instanceof AssignopOptionalAP) {

			}
		}
	}

	// =============================COND================================

	public void visit(ExprRelopOptonal exprRelopOpt) {
		exprRelopOpt.struct = exprRelopOpt.getExpr().struct;
	}

	public void visit(CondFact condFact) {
		if (condFact.getExprRelopOpt() instanceof ExprRelopOptonalNo) {
			if (condFact.getExpr().struct == BoolType) {
				condFact.struct = BoolType;
			} else {
				report_error("Expr nije bool(CondFact)!", null);
			}
		} else {
			if (condFact.getExprRelopOpt().struct.compatibleWith(condFact.getExpr().struct)) {
				if (condFact.getExpr().struct.isRefType() && condFact.getExprRelopOpt().struct.isRefType()) {
					Relop curRelop = ((ExprRelopOptonal) condFact.getExprRelopOpt()).getRelop();
					if (!(curRelop instanceof RelopSame) && !(curRelop instanceof RelopNotSame)) {
						report_error("Pogresan tip operatora za klasu ili niz Term(CondFact)!", null);
					}
				}
				condFact.struct = BoolType;
			} else {
				report_error("Strukture nisu kompatibilne Term(CondFact)!", null);
			}
		}
	}

	public void visit(CondFactListYes condFactListYes) {
		if (condFactListYes.getCondFactList() instanceof CondFactListNo) {
			condFactListYes.struct = condFactListYes.getCondFact().struct;
		} else {
			if (condFactListYes.getCondFactList().struct.compatibleWith(condFactListYes.getCondFact().struct)) {
				condFactListYes.struct = condFactListYes.getCondFact().struct;
			} else {
				report_error("Strukture nisu kompatibilne CondFactListYes!", null);
			}
		}
	}

	public void visit(CondTerm condTerm) {
		if (condTerm.getCondFactList() instanceof CondFactListNo) {
			condTerm.struct = condTerm.getCondFact().struct;
		} else {
			if (condTerm.getCondFactList().struct.compatibleWith(condTerm.getCondFact().struct)) {
				condTerm.struct = condTerm.getCondFact().struct;
			} else {
				report_error("Strukture nisu kompatibilne CondFactListYes!", null);
			}
		}
	}

	public void visit(CondTermListYes condTermListYes) {
		if (condTermListYes.getCondTermList() instanceof CondTermListNo) {
			condTermListYes.struct = BoolType;
		} else {
			if (condTermListYes.getCondTermList().struct.compatibleWith(condTermListYes.getCondTerm().struct)) {
				condTermListYes.struct = BoolType;
			} else {
				report_error("Strukture nisu kompatibilne CondTermListYes!", null);
			}
		}
	}

	public void visit(Condition condition) {
		if (condition.getCondTermList() instanceof CondTermListNo) {
			condition.struct = condition.getCondTerm().struct;
		} else {
			if (condition.getCondTermList().struct.compatibleWith(condition.getCondTerm().struct)) {
				condition.struct = condition.getCondTerm().struct;
			} else {
				report_error("Strukture nisu kompatibilne Condition!", null);
			}
		}
	}

	// ==============================SINGLE STATEMENT======================

	public void visit(SingleStatementPrint singleStatementPrint) {
		Struct exprStruct = singleStatementPrint.getExpr().struct;
		if (exprStruct != Tab.intType && exprStruct != Tab.charType && exprStruct != BoolType)
			report_error("Expr u printu mora biti tipa (int, char, bool) (SingleStatementPrint)!", null);
	}

	public void visit(SingleStatementRead singleStatementRead) {
		Obj exprObj = singleStatementRead.getDesignator().obj;
		if (exprObj.getKind() == Obj.Var || exprObj.getKind() == Obj.Elem) {
			if (exprObj.getType() != Tab.intType && exprObj.getType() != Tab.charType
					&& exprObj.getType() != BoolType) {
				report_error("Expr u read-u mora biti tipa (int, char, bool) (singleStatementRead)!", null);
			}
		} else {
			report_error("Read radi samo sa promenljivama i elemntima niza (SingleStatementRead)!", null);
		}
	}

	// =============================VarDecl=============================

	ArrayList<IdentNoAnglesListYes> vars = new ArrayList<IdentNoAnglesListYes>();
	ArrayList<IdentAnglesListYes> arrs = new ArrayList<IdentAnglesListYes>();

	public void visit(VarDeclNoAngles varDecl) {
		Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), varDecl.getType().struct);
		report_info("Deklarisana promenljiva " + varDecl.getVarName(), varDecl);
		for (int i = 0; i < vars.size(); ++i) {
			Tab.insert(Obj.Var, vars.get(i).getVarName(), varDecl.getType().struct);
			report_info("Deklarisana promenljiva " + vars.get(i).getVarName(), varDecl);
		}
		for (int i = 0; i < arrs.size(); ++i) {
			Tab.insert(Obj.Var, arrs.get(i).getVarName(), varDecl.getType().struct == Tab.intType ? ArrayTypeInt
					: varDecl.getType().struct == Tab.charType ? ArrayTypeChar : ArrayTypeBool);
			report_info("Deklarisana promenljiva " + arrs.get(i).getVarName(), varDecl);
		}
		arrs.clear();
		vars.clear();
	}

	public void visit(VarDeclAngles varDecl) {
		Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), varDecl.getType().struct == Tab.intType ? ArrayTypeInt
				: varDecl.getType().struct == Tab.charType ? ArrayTypeChar : ArrayTypeBool);
		report_info("Deklarisana promenljiva " + varDecl.getVarName(), varDecl);
		for (int i = 0; i < vars.size(); ++i) {
			Tab.insert(Obj.Var, vars.get(i).getVarName(), varDecl.getType().struct);
			report_info("Deklarisana promenljiva " + vars.get(i).getVarName(), varDecl);
		}
		for (int i = 0; i < arrs.size(); ++i) {
			Tab.insert(Obj.Var, arrs.get(i).getVarName(), varDecl.getType().struct == Tab.intType ? ArrayTypeInt
					: varDecl.getType().struct == Tab.charType ? ArrayTypeChar : ArrayTypeBool);
			report_info("Deklarisana promenljiva " + arrs.get(i).getVarName(), varDecl);
		}
		arrs.clear();
		vars.clear();
	}

	public void visit(IdentNoAnglesListYes varDec) {
		vars.add(varDec);
	}

	public void visit(IdentAnglesListYes varDec) {
		arrs.add(varDec);
	}

	// =============================TYPE=============================

	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip" + type.getTypeName() + " u tabeli simbola!", null);
			type.struct = Tab.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			} else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;	
			}
		}
	}
	
	// =============================CONST=============================
	ArrayList<IdNumCharBoolConstListYes> consts = new ArrayList<IdNumCharBoolConstListYes>();

	public void visit(ConstType constType) {
		curConstType = constType.getType().getTypeName();
	}

	public void visit(IdNumCharBoolConst constDeclItem) {
		Obj exists = Tab.find(constDeclItem.getVarName());

		if (exists != Tab.noObj) {
			report_error("Ime " + constDeclItem.getVarName() + " vec postoji u tabeli simbola ", constDeclItem);
		} else {
			report_info("Deklarisana konstanta " + constDeclItem.getVarName(), constDeclItem);
			if (curConstType.equals("int") && !constFault) {
				curConst = Tab.insert(Obj.Con, constDeclItem.getVarName(), Tab.intType);
			} else if (curConstType.equals("char") && !constFault) {
				curConst = Tab.insert(Obj.Con, constDeclItem.getVarName(), Tab.charType);
			} else if (curConstType.equals("bool") && !constFault) {
				curConst = Tab.insert(Obj.Con, constDeclItem.getVarName(), BoolType);
			}

			curConst.setAdr(constVal);

			constFault = false;
			constVal = 0;
		}

	}

	public void visit(NumConst num) {
		if (curConstType.equals("int")) {
			constVal = num.getNum();
		} else {
			report_error("Greska u deklaraciji tipovi nisu isti: " + curConstType + " i int", num);
			constFault = true;
		}
	}

	public void visit(CharConst chars) {
		if (curConstType.equals("char")) {
			String str = chars.getCh();
			constVal = (int) str.charAt(1);
		} else {
			report_error("Greska u deklaraciji tipovi nisu isti: " + curConstType + " i char", chars);
			constFault = true;
		}
	}

	public void visit(BoolConst bool) {
		if (curConstType.equals("bool")) {
			if (bool.getB().equals("true")) {
				constVal = 1;
			} else {
				constVal = 0;
			}
		} else {
			report_error("Greska u deklaraciji tipovi nisu isti: " + curConstType + " i bool", bool);
			constFault = true;
		}
	}

	// =============================METHOD DECL=============================

	public void visit(MethodTypeName methodTypeName) {
		curRetType = methodTypeName.getReturnType() instanceof ReturnTypeYes
				? ((ReturnTypeYes) methodTypeName.getReturnType()).getType().struct
				: TypeVoid;
		// System.out.println("Cofi je cigance " + curRetType.getKind());
		curMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), curRetType);
		methodTypeName.obj = curMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
	}

	public void visit(MethodDecl methodDecl) {

		if (curRetType != TypeVoid && !retFound) {
			report_error("Greska na liniji " + methodDecl.getLine() + " : funkcija " + curMethod.getName()
					+ " nema povratan iskaz! ", null);
		}

		if (curRetType == TypeVoid && retWithExpr != null) {
			report_error("Greska na liniji " + methodDecl.getLine() + " : funkcija " + curMethod.getName()
					+ " VOID ima povratnu vrednost! ", null);
		}

		if (curRetType != TypeVoid && !curRetType.equals(retWithExpr)) {
			report_error("Greska na liniji " + methodDecl.getLine() + " : funkcija " + curMethod.getName()
					+ " ima pogresan tip povratne vrednosti! ", null);
		}

		Tab.chainLocalSymbols(curMethod);
		Tab.closeScope();

		curMethod = null;
		curRetType = null;
		retFound = false;
		retWithExpr = null;
	}

	public void visit(BaseExpDesigFunc funcCall) {
		Obj func = funcCall.getDesignator().obj;
		if (Obj.Meth == func.getKind()) {
			if (Tab.noType == func.getType()) {
				report_error("Semanticka greska " + funcCall.getLine() + " : ime " + func.getName()
						+ " ne moze se koristiti u izrazima jer nema pov vred!", null);
			} else {
				report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
				funcCall.struct = func.getType();
			}

		} else {
			report_error("Greska na liniji " + funcCall.getLine() + " : ime " + func.getName() + " nije funkcija!",
					null);
			funcCall.struct = Tab.noType;
		}
	}

	public void visit(SingleStatementRetExpr singleReturn) {
		retFound = true;
	}

	public void visit(ExprOptional exprOptional) {
		retWithExpr = exprOptional.getExpr().struct;
		// System.out.println("Cofi nije cigance: " + retWithExpr.getKind());
	}

	// =============================FORM PARS=============================
	ArrayList<FormParsListNoAnglesYes> form_vars = new ArrayList<FormParsListNoAnglesYes>();
	ArrayList<FormParsListAnglesYes> form_arrs = new ArrayList<FormParsListAnglesYes>();

	public void visit(FormParsNoAngles formPars) {
		Obj formNode = Tab.insert(Obj.Var, formPars.getParName(), formPars.getType().struct);
		report_info("Formalni parametar " + formPars.getParName(), formPars);
		for (int i = 0; i < form_vars.size(); i++) {
			Tab.insert(Obj.Var, form_vars.get(i).getParName(), formPars.getType().struct);
			report_info("Formalni parametar " + form_vars.get(i).getParName(), formPars);
		}
		for (int i = 0; i < form_arrs.size(); i++) {
			Tab.insert(Obj.Var, form_arrs.get(i).getParName(), formPars.getType().struct == Tab.intType ? ArrayTypeInt
					: formPars.getType().struct == Tab.charType ? ArrayTypeChar : ArrayTypeBool);
			report_info("Formalni parametar " + form_arrs.get(i).getParName(), formPars);
		}
		form_vars.clear();
		form_arrs.clear();
	}

	public void visit(FormParsAngles formPars) {
		Obj formNode = Tab.insert(Obj.Var, formPars.getParName(),
				formPars.getType().struct == Tab.intType ? ArrayTypeInt
						: formPars.getType().struct == Tab.charType ? ArrayTypeChar : ArrayTypeBool);
		report_info("Formalni parametar " + formPars.getParName(), formPars);
		for (int i = 0; i < form_vars.size(); i++) {
			Tab.insert(Obj.Var, form_vars.get(i).getParName(), formPars.getType().struct);
			report_info("Formalni parametar " + form_vars.get(i).getParName(), formPars);
		}
		for (int i = 0; i < form_arrs.size(); i++) {
			Tab.insert(Obj.Var, form_arrs.get(i).getParName(), formPars.getType().struct == Tab.intType ? ArrayTypeInt
					: formPars.getType().struct == Tab.charType ? ArrayTypeChar : ArrayTypeBool);
			report_info("Formalni parametar " + form_arrs.get(i).getParName(), formPars);
		}
		form_vars.clear();
		form_arrs.clear();
	}

	public void visit(FormParsListNoAnglesYes formPars) {
		form_vars.add(formPars);
	}

	public void visit(FormParsListAnglesYes formPars) {
		form_arrs.add(formPars);
	}

	// =============================RECORD=============================

	public void visit(RecordName recordName) {
		Obj exists = Tab.find(recordName.getRecName());
		if (exists == Tab.noObj) {
			curRecord = Tab.insert(Obj.Type, recordName.getRecName(), new Struct(Struct.Class));
			Tab.openScope();
		} else {
			report_error("Greska rekord: " + recordName.getRecName() + " je vec definisan!", recordName);
		}
	}

	public void visit(RecordDecl recDecl) {
		recDecl.obj = curRecord;
		Tab.chainLocalSymbols(curRecord.getType());
		Tab.closeScope();
		curRecord = null;
	}

}
