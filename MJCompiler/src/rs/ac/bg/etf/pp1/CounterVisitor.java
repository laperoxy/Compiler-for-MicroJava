package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.FormPars;
import rs.ac.bg.etf.pp1.ast.FormParsAngles;
import rs.ac.bg.etf.pp1.ast.FormParsListAnglesYes;
import rs.ac.bg.etf.pp1.ast.FormParsListNoAnglesYes;
import rs.ac.bg.etf.pp1.ast.FormParsNoAngles;
import rs.ac.bg.etf.pp1.ast.IdentAnglesListYes;
import rs.ac.bg.etf.pp1.ast.IdentNoAnglesListYes;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VarDeclAngles;
import rs.ac.bg.etf.pp1.ast.VarDeclNoAngles;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {

	protected int count;

	public int getCount() {
		return count;
	}

	public static class FormParamCounter extends CounterVisitor {
		
		public void visit(FormParsNoAngles formParsNoAngles) {
			count++;
		}
		
		public void visit(FormParsAngles formParsAngles) {
			count++;
		}
		
		public void visit(FormParsListNoAnglesYes formParsListNoAnglesYes) {
			count++;
		}
		
		public void visit(FormParsListAnglesYes formParsListAnglesYes) {
			count++;
		}


	}

	public static class VarCounter extends CounterVisitor {

		public void visit(VarDeclNoAngles varDeclNoAngles) {
			count++;
		}
		
		public void visit(VarDeclAngles varDeclAngles) {
			count++;
		}
		
		public void visit(IdentNoAnglesListYes identNoAnglesListYes) {
			count++;
		}
		
		public void visit(IdentAnglesListYes identAnglesListYes) {
			count++;
		}

	}

}
