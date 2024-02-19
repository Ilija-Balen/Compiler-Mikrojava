package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VarElemArray;
import rs.ac.bg.etf.pp1.ast.VarElemIdent;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor{
	
	protected int count = 0;
	
	public int getCount() {
		return count;
	}
	
	
	public static class VarCounter extends CounterVisitor{
		
		public void visit(VarElemIdent varDecl){
			count++;
		}
		public void visit(VarElemArray vard) {
			count++;
		}
	}
}
