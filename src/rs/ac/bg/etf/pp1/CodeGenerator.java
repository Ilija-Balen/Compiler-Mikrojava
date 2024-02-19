package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor{
	
	private int mainPc;
	private boolean nizINC = false;
	private boolean saLeveElemNiza = false;
	private boolean staviAstore = false;
	private int width = 0;
	private int width1 = 0;
	public int getMainPc() {
		return mainPc;
	}
	
	private void odradiPrint(Struct s) {
		if(width==0) {
			Code.loadConst(5);
		}else if(width > 0) {
			Code.loadConst(width);
		}
		
		if(s == Tab.charType){
			Code.put(Code.bprint);
		}else if (s == Tab.intType){
			Code.put(Code.print);
		}else if(s == Tab.charType){
			Code.put(Code.bprint);
		}else {
			Code.put(Code.print);
		}
	}
	public void visit(DesignatorStmtAss designator) {
		if(staviAstore) {
			Code.put(Code.astore);
			staviAstore = false;
		}else {
			Code.store(designator.getDesignator().obj);
		}
		nizINC = false;
	}
	
	public void visit(NumConst numConst) {	
		
		if(numConst.getParent().getClass() == FactorNumConst.class) {
			Code.load(numConst.obj);
		}else {
			Code.loadConst(numConst.obj.getAdr());
		}
	}
	
	public void visit(CharConst charConst) {
		Code.loadConst(charConst.getCharValue());
	}
	
	public void visit(BoolConst boolConst) {
		int n = 0;
		if(boolConst.getBoolValue()) n= 1;
		Code.loadConst(n);
	}
	
	public void visit(DesignatorStmtINC desStmt) {
		if(nizINC) {
			Code.load(desStmt.getDesignator().obj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			Code.put(Code.dup2);//Stek: adr, index, adr, index
			Code.put(Code.aload);
			//Trenutno na steku elem niza -> Stek: adr, index, elemNiza
			Code.loadConst(1);
			Code.put(Code.add);
			Code.put(Code.astore);
		}else {
			Code.load(desStmt.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(desStmt.getDesignator().obj);	
		}
		nizINC = false;
	}
	
	public void visit(DesignatorStmtDEC desStmt) {
		if(nizINC) {
			Code.load(desStmt.getDesignator().obj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			Code.put(Code.dup2);//Stek: adr, index, adr, index
			Code.put(Code.aload);
			//Trenutno na steku elem niza -> Stek: adr, index, elemNiza
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.put(Code.astore);
		}else {
			Code.load(desStmt.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.store(desStmt.getDesignator().obj);
		}	
		
		nizINC = false;
	}
	
	public void visit(IdentExprSQBraceIdent id) {
		nizINC = true;
	}
	public void visit(FactorDesignator f) {
		nizINC = false;
	}
	public void visit(AddopTermLists add) {
		
		if(add.getAddop() instanceof AddopPLUS)
			Code.put(Code.add);
		else
			Code.put(Code.sub);
	}
	
	public void visit(MulopFactorLists mull) {
		if(mull.getMulop() instanceof MulopMUL) {
			Code.put(Code.mul);
		}else if(mull.getMulop() instanceof MulopDIVISION) {
			Code.put(Code.div);
		}else {
			Code.put(Code.rem);
		}
			
				
	}
	
	public void visit(DesignatorNoIdentDoubleOccured designator) {
		
		if(designator.getParent().getClass() == FactorDesignator.class) {
			if(designator.getIdentExprList().getLine() != 0) {
				Code.load(designator.obj);
				Code.put(Code.dup_x1);
				Code.put(Code.pop);
				Code.put(Code.aload);
			}else {
				Code.load(designator.obj);
			}
		}	
		if(designator.getParent().getClass() == DesignatorStmtAss.class && designator.getIdentExprList().getLine()!= 0) {
			Code.load(designator.obj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			staviAstore = true;
		}
	}
	
	public void visit(DesignatorIdentDoubleOccured designator) {
		if(designator.getParent().getClass() == FactorDesignator.class) {
			if(designator.getIdentExprList().getLine()!= 0) {
				
				Code.load(designator.obj);
				Code.put(Code.dup_x1);
				Code.put(Code.pop);
				Code.put(Code.aload);
			}else {
				Code.load(designator.obj);	
			}
		}
		if(designator.getParent().getClass() == DesignatorStmtAss.class) {
			
			if(designator.getIdentExprList().getLine() != 0) {
				Code.load(designator.obj);
				Code.put(Code.dup_x1);
				Code.put(Code.pop);
				staviAstore = true;
			}	
		}		
		
		
	}
	
	public void visit(Term term) {
		
		if(term.getParent().getClass() == ExprTermMinus.class) Code.put(Code.neg);
	
	}
	public void visit(CommaCOcc c) {
		width = c.getNumConst().obj.getAdr();
	}
	
	public void visit(NoCommaConstOcc c) {
		width = 0;
	}
	
	public void visit(FactorType fType) {
		if(fType.struct.getKind() == Struct.Int) {
			
			Code.put(Code.newarray);
			Code.loadConst(1);
			
		}else {
			//char je 
			Code.put(Code.newarray);
			Code.loadConst(0);
		}
	}
	
	
	
	public void visit(StatementPrint stmt) {
		if(stmt.getCommaConstOcc().getLine()!=0) {
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			width1 = width;
			width = 0;
			odradiPrint(stmt.getExpr().struct);
			width = width1;
			odradiPrint(Tab.intType);
			width = -1;
		}else {
			if(width==0) {
				Code.loadConst(5);
			}else if(width > 0) {
				Code.loadConst(width);
			}
			width = -1;
			if(stmt.getExpr().struct == Tab.charType){
				Code.put(Code.bprint);
			}else if (stmt.getExpr().struct == Tab.intType){
				Code.put(Code.print);
			}else if(stmt.getExpr().struct.getElemType() == Tab.charType){
				Code.put(Code.bprint);
			}else {
				Code.put(Code.print);
			}
		}		
	}
	
	public void visit(StatementRead stmt) {
		if(nizINC) {//nizINC se odnosi i na READ statement tj oznacava prisustvo ident[i] ili ident::ident[i]
			Code.load(stmt.getDesignator().obj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			//Stek: adr, index
			Code.put(Code.read);
			Code.put(Code.astore);
		}else {
			Code.put(Code.read);
			Code.store(stmt.getDesignator().obj);
		}
		
		nizINC = false;
	}
	
	public void visit(TVtype methDecl) {
		
		if("main".equalsIgnoreCase(methDecl.getMethodName())) {
			mainPc = Code.pc;
		}
		methDecl.obj.setAdr(Code.pc);
		//Collect arguments and local variables
		SyntaxNode methParent = methDecl.getParent();
		
		VarCounter varCnt = new VarCounter();
		methParent.traverseTopDown(varCnt);
		
		//OVDE IDE FORMPARAM COUNTER
		
		//Generate entry
		Code.put(Code.enter);
		Code.put(0);
		Code.put(0 + varCnt.getCount());
		
	}
	
	public void visit(TVvoid methDecl) {
		if("main".equalsIgnoreCase(methDecl.getMethodName())) {
			mainPc = Code.pc;
		}
		methDecl.obj.setAdr(Code.pc);
		//Collect arguments and local variables
		SyntaxNode methParent = methDecl.getParent();
		
		VarCounter varCnt = new VarCounter();
		methParent.traverseTopDown(varCnt);
		
		//OVDE IDE FORMPARAM COUNTER
		
		//Generate entry
		Code.put(Code.enter);
		Code.put(0);
		Code.put(0 + varCnt.getCount());
	}
	
	public void visit(MethDecl methDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
}
