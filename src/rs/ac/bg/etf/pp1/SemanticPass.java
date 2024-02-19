package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.structure.HashTableDataStructure;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;


public class SemanticPass extends VisitorAdaptor{
	
	Logger log = Logger.getLogger(getClass());
	boolean errorDetected = false;
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	//=========================================================================================
	Struct currType = null;
	Obj currMeth = null;
	boolean returnFound = false;
	boolean inNamespace = false;
	boolean nizImaElem = false;
	int NamespaceBr = 1;
	static Struct boolType = new Struct(Struct.Bool);
	static Struct enumType = new Struct(Struct.Enum);
	int nVars = 0;
	static HashMap<String, List<Obj>> imenaProstora = new HashMap<>();
	String imeNamespacea = "";
	
	public void visit(ConstDeclarations constDeclarations) {
		String name = constDeclarations.getConstName();
		
		if(inNamespace) {
			List<Obj> l = imenaProstora.get(imeNamespacea);
			for(Obj o : l) {
				if(o.getName().equals(name)) report_error("Greska simbol :" + name + " vec definisan u namespaceu: " + imeNamespacea + "linija: " + constDeclarations.getLine(), constDeclarations);
			}
			if(!currType.equals(constDeclarations.getNCBConst().obj.getType())) {
				report_error("Greska simbol :" + name + " nije odgovarajuceg tipa: " + constDeclarations.getLine(), constDeclarations);
			}
			Obj o2 =Tab.insert(Obj.Con, imeNamespacea+"::"+name, constDeclarations.getNCBConst().obj.getType());
			 //Obj o2 = new Obj(Obj.Con, name, constDeclarations.getNCBConst().obj.getType());
			o2.setAdr(constDeclarations.getNCBConst().obj.getAdr());
			//o2.setLevel(0);
			
			report_info("deklarisana konstanta "+name+" sa vrednosti "+o2.getAdr()+"u namespaceu "+ imeNamespacea+" na liniji "+constDeclarations.getLine(), constDeclarations);
			
			l.add(o2);
		}else {

			Obj o = Tab.find(name);
			
			if(o == Tab.noObj) {
				//PROVERA DA LI JE ODGOVARAJUCI TIP
				if(!currType.equals(constDeclarations.getNCBConst().obj.getType())) {
					report_error("Greska simbol :" + name + " nije odgovarajuceg tipa: " + constDeclarations.getLine(), constDeclarations);
				}
				//JESTE ODGOVARAJUCI TIP
				Obj o1 = Tab.insert(Obj.Con, name, constDeclarations.getNCBConst().obj.getType());
				o1.setAdr(constDeclarations.getNCBConst().obj.getAdr());
				
				report_info("deklarisana konstanta "+name+" sa vrednosti "+o1.getAdr()+" na liniji "+constDeclarations.getLine(), constDeclarations);
			}else {
				report_error("Greska simbol :" + name + " vec definisan na liniji: " + constDeclarations.getLine(), constDeclarations);
			}
		}
		
	}
	
	public void visit(NCBLists ncbLists) {
		String name = ncbLists.getConstName();
		if(inNamespace) {
			List<Obj> l = imenaProstora.get(imeNamespacea);
			for(Obj o : l) {
				if(o.getName().equals(name)) report_error("Greska simbol :" + name + " vec definisan u namespaceu: " + imeNamespacea + "linija: " + ncbLists.getLine(), ncbLists);
			}
			if(!currType.equals(ncbLists.getNCBConst().obj.getType())) {
				report_error("Greska simbol :" + name + " nije odgovarajuceg tipa: " + ncbLists.getLine(), ncbLists);
			}
			Obj o2 = Tab.insert(Obj.Con, imeNamespacea+"::"+name, ncbLists.getNCBConst().obj.getType());
			//Obj o2 = new Obj(Obj.Con, name, ncbLists.getNCBConst().obj.getType());
			o2.setAdr(ncbLists.getNCBConst().obj.getAdr());
			//o2.setLevel(0);
			report_info("deklarisana konstanta "+name+" sa vrednosti "+o2.getAdr()+"u namespaceu "+ imeNamespacea+" na liniji "+ncbLists.getLine(), ncbLists);
			
			l.add(o2);
		}else {
			Obj o = Tab.find(name);
			
			if(o == Tab.noObj) {
				//PROVERA DA LI JE ODGOVARAJUCI TIP
				if(!currType.equals(ncbLists.getNCBConst().obj.getType())) {
					report_error("Greska simbol :" + name + " nije odgovarajuceg tipa: " + ncbLists.getLine(), ncbLists);
				}
				//JESTE ODGOVARAJUCI TIP
				Obj o1 = Tab.insert(Obj.Con, name, ncbLists.getNCBConst().obj.getType());
				o1.setAdr(ncbLists.getNCBConst().obj.getAdr());
				report_info("deklarisana konstanta "+name+" sa vrednosti "+o1.getAdr()+" na liniji "+ncbLists.getLine(), ncbLists);
				
			}else {
				report_error("Greska simbol :" + name + " vec definisan na liniji: " + ncbLists.getLine(), ncbLists);
			}
		}
		
	}
	
	public void visit(NumConst numConst) {
		numConst.obj = new Obj(Obj.Con, "", Tab.intType);
		numConst.obj.setAdr(numConst.getNumConst());
	}
	
	public void visit(NCBNumConst numConst) {
		numConst.obj = new Obj(Obj.Con, "", Tab.intType);
		numConst.obj.setAdr(numConst.getNumConst());
	}
	
	public void visit(CharConst charConst) {
		charConst.obj = new Obj(Obj.Con, "", Tab.charType);
		charConst.obj.setAdr(charConst.getCharValue());
	}
	
	public void visit(NCBCharConst charConst) {
		charConst.obj = new Obj(Obj.Con, "", Tab.charType);
		charConst.obj.setAdr(charConst.getCharValue());
	}
	
	public void visit(BoolConst boolConst) {
		
		boolConst.obj = new Obj(Obj.Con, "", boolType);

		int bul = (boolConst.getBoolValue().equals("true"))? 1 : 0;
		boolConst.obj.setAdr(bul);
	}
	
	public void visit(NCBBoolConst boolConst) {
		
		boolConst.obj = new Obj(Obj.Con, "", boolType);

		int bul = (boolConst.getBoolValue().equals("true"))? 1 : 0;
		boolConst.obj.setAdr(bul);
	}
	
	public void visit(VarElemIdent varDecl) {
		String name = varDecl.getVarName();
		
		if(inNamespace) {
			List<Obj> l = imenaProstora.get(imeNamespacea);
			for(Obj o : l) {
				if(o.getName().equals(name)) report_error("Greska simbol :" + name + " vec definisan u namespaceu: " + imeNamespacea + "linija: " + varDecl.getLine(), varDecl);
			}
			
			
			report_info("Deklairsana promenljiva : " + name + " u namespaceu " + imeNamespacea, varDecl);
			Obj o2 = Tab.insert(Obj.Var, imeNamespacea+"::"+name, currType);
			//Obj o2 = new Obj(Obj.Var, name, currType);
			//o2.setLevel(0);
			l.add(o2);
		}
		else {
			Obj o = Tab.currentScope().findSymbol(name);
			
			if(o ==null) {
				Obj o1 = Tab.insert(Obj.Var, name, currType);
				report_info("Deklairsana promenljiva : " + o1.getName(), varDecl);
			}else {
				report_error("Greska simbol :" + name + " vec definisan u tabeli simbola,  na liniji: " + varDecl.getLine(), varDecl);
			}
		}	
	}
	
	public void visit(VarElemArray varDecl) {
		String name = varDecl.getArrName();
		
		if(inNamespace) {
			List<Obj> l = imenaProstora.get(imeNamespacea);
			for(Obj o : l) {
				if(o.getName().equals(name)) report_error("Greska simbol :" + name + " vec definisan u namespaceu: " + imeNamespacea + "linija: " + varDecl.getLine(), varDecl);
			}
					
			Struct arrType = new Struct(Struct.Array, currType);
			Obj o2 =Tab.insert(Obj.Var, imeNamespacea+"::"+name, arrType);
			//Obj o2 = new Obj(Obj.Var, name, arrType);
			//o2.setLevel(0);
			report_info("Deklairsana promenljiva : " + o2.getName()+ "u namespace u" + imeNamespacea, varDecl);
			report_info("Tip arrType: " + currType.getKind(), varDecl);
			
			l.add(o2);
		}else {
			Obj o = Tab.currentScope().findSymbol(name);
			
			if(o == null) {
				Struct arrType = new Struct(Struct.Array, currType);
				
				Obj o1 = Tab.insert(Obj.Var, name, arrType);
				report_info("Deklairsana promenljiva : " + o1.getName(), varDecl);
				report_info("Tip arrType: " + currType.getKind(), varDecl);
			
			}else {
				report_error("Greska simbol :" + name + " vec definisan u tabeli simbola,  na liniji: " + varDecl.getLine(), varDecl);
			}
		}
		
	}
	
	public void visit(TVtype methDecl) {
		
		String name = methDecl.getMethodName();

		methDecl.getType().struct = currType;
		currMeth = Tab.insert(Obj.Meth, name, currType);
		report_info("Deklairsana metoda : " + name, methDecl);

		methDecl.obj = currMeth;
		Tab.openScope();
		
	}
	
	public void visit(TVvoid methDecl) {
		currType = Tab.noType;

		String name = methDecl.getMethodName();

		currMeth = Tab.insert(Obj.Meth, name, currType);
		report_info("Deklairsana metoda : " + name, methDecl);

		methDecl.obj = currMeth;
		Tab.openScope();

	}
	
	
	public void visit(MethDecl methDecl) {
		if(!returnFound && currMeth.getType() != Tab.noType){
			report_error("Semanticka greska na liniji " + methDecl.getLine() + ": funkcija " + currMeth.getName() + " nema return iskaz!", null);
    	}
		
		Tab.chainLocalSymbols(currMeth);
		//FALI ISPIS
		//KAO I postavljanje nivoa
		Tab.closeScope();
		
		returnFound = false;
		currMeth = null;
	}
	
	
	public void visit(Names namespaces) {
		
//		Tab.chainLocalSymbols(namespaces.getNamespaceName().obj);
//		Tab.closeScope();
		
		inNamespace = false;
	}
	
	public void visit(NamespaceName namespaces) {
		String name = namespaces.getNName();
		Obj o = Tab.find(name);
		
		if(o == Tab.noObj) {
			o = Tab.insert(Obj.Type, name, enumType);
			
			report_info("Deklairsan namespace : " + name, namespaces);
			namespaces.obj = o;
			
			inNamespace = true;
			imeNamespacea = name;
			List<Obj> l = new ArrayList<>();
			imenaProstora.put(name, l);
		}else {
			report_error("Ime za Namespace na mestu " + namespaces.getLine() + ": name " + name + " zauzeto", null);
		}
		
		
		
		//Tab.openScope();
	}
	
//	public void visit(CDecl cdecl) {
//		if (cdecl.getParent().getClass() == Names.class) {
//			if(!imenaProstora.containsKey(imeNamespacea)) {
//				List<String> lista = new ArrayList<>();
//				lista.add(cdecl.getConstDecl().)
//				imenaProstora.put(imeNamespacea, lista);
//			}
//			
//			//if(imenaProstora.get(imeNamespacea).subList(0, imena))
//		}
//	}
	
	public void visit(IdType type) {
		String name = type.getTypeName();
		Obj o = Tab.find(name);
		
		if(name.equals("bool")) {
			
			type.struct = boolType;
			Obj o1 = Tab.insert(Obj.Type, name, boolType);
			currType = o1.getType();
		}else {
			if(o == Tab.noObj) {
				report_error("Greska tip :" + name + " nije pronadjen u tabeli simbola na liniji: " + type.getLine(), null);	
				type.struct = Tab.noType;
			}else {
				if(o.getKind() == Obj.Type) {
					type.struct = o.getType();
					currType = o.getType();
				}else {
					report_error("Greska simbol :" + name + " tipa: "+ type.getTypeName() + " na liniji: "+ type.getLine(), type);	
					type.struct = Tab.noType;
				}
			}
		}
	}
	
	public void visit(IType type) {
		type.struct = type.getIdentType().struct;
	}
	
	public void visit(StatementReturn retStatement) {
		returnFound = true;
		if(retStatement.getExprOcc().struct.getKind() == Struct.Array) {
			if(!currMeth.getType().compatibleWith(retStatement.getExprOcc().struct.getElemType())) {
				report_error("Greska na liniji " + retStatement.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije"
						+ "Izraz prepoznat kao Array " + currMeth.getName(), null);	    	
				
			}
		}else
		if(!currMeth.getType().compatibleWith(retStatement.getExprOcc().struct)) {
			report_error("Greska na liniji " + retStatement.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currMeth.getName(), null);	    	
		}
	}
	
	public void visit(StmtListRepeat stmt) {
		if(returnFound && stmt.getParent().getClass() != MethDecl.class) {
			report_error("GRESKA, RETURN ne sme da stoji van tela Metoda odnosno globalnih fja", stmt);
		}
	}
	
	public void visit(EOcc eocc) {
		eocc.struct = eocc.getExpr().struct;
	}
	
	public void visit(ExprTerm exprTerm) {
		exprTerm.struct = exprTerm.getTerm().struct;
		
		if(exprTerm.getAddopTermList().getLine() != 0) {
			if(exprTerm.getTerm().struct.getKind() == Struct.Array) {
				if(exprTerm.getTerm().struct.getElemType() != Tab.intType){
					report_error("Greska na liniji " + exprTerm.getLine() + " : " + "tip Term AddopTermList nije INT tipa"
							+ "Term je Array", exprTerm);
				}
			}else
			if(exprTerm.getTerm().struct != Tab.intType) {
				report_error("Greska na liniji " + exprTerm.getLine() + " : " + "tip Term AddopTermList nije INT tipa", exprTerm);
			}
		}
	}
	
	public void visit(Term term) {
		term.struct = term.getFactor().struct;
		
		if(term.getMulopFactorList().getLine() != 0) {
			if(term.struct.getKind() == Struct.Array) {
				//Proveri tip njegovog elementa
				if(term.struct.getElemType() != Tab.intType) {
					report_error("Greska na liniji " + term.getLine() + " : " + "tip Term = Term  mulop Factor nije INT tipa", term);				
				}
			}else {
				if(term.struct != Tab.intType) {
					report_error("Greska na liniji " + term.getLine() + " : " + "tip Term = Term  mulop Factor nije INT tipa", term);
				}	
			}		
		}
	}
	
	public void visit(FactorDesignator fDesignator) {
		fDesignator.struct = fDesignator.getDesignator().obj.getType();
		nizImaElem = false;
	}
	
	public void visit(FactorNumConst fNumConst) {
		fNumConst.struct = Tab.intType;
	}
	
	public void visit(FactorCharConst fCharConst) {
		fCharConst.struct = Tab.charType;
	}
	
	public void visit(FactorBoolConst fBoolConst) {
		fBoolConst.struct = boolType;
	}
	
	public void visit(FactorType fType) {
		fType.struct = fType.getType().struct;
		
		if(fType.getExpr().struct != Tab.intType) {
			report_error("Greska na liniji " + fType.getLine() + " : " + "tip Factor = new Type [ Expr ] nije INT tipa", fType);
		}	
	}
	
	public void visit(FactorExpr fExpr) {
		fExpr.struct = fExpr.getExpr().struct;
	}
	
	public void visit(StatementRead stmtRd) {
		if(stmtRd.getDesignator().obj.getKind() ==  Obj.Var || stmtRd.getDesignator().obj.getKind() == Obj.Elem || stmtRd.getDesignator().obj.getKind() == Obj.Fld) {
			//Designator je Var ili Array tipa
			
		}else {
			report_error("Greska na liniji " + stmtRd.getLine() + " : " + "tip designator = Expr ,Designator nije Var ili elem niza ", stmtRd);	    						
		}
		
		if(stmtRd.getDesignator().obj.getType() != Tab.intType && 
				stmtRd.getDesignator().obj.getType() != Tab.charType && 
					stmtRd.getDesignator().obj.getType() != boolType &&
							stmtRd.getDesignator().obj.getType().getElemType() != Tab.intType &&
									stmtRd.getDesignator().obj.getType().getElemType() != Tab.charType &&
											stmtRd.getDesignator().obj.getType().getElemType() != boolType) {
			
			report_error("Greska na liniji " + stmtRd.getLine() + " : " + "tip designator = Expr ,Designator nije Var ili elem niza ", stmtRd);
		}
		nizImaElem = false;
	}
	
	public void visit(StatementPrint stmtPrint) {
		
		if(stmtPrint.getExpr().struct != Tab.intType &&
				stmtPrint.getExpr().struct != Tab.charType && 
						stmtPrint.getExpr().struct != boolType &&
							stmtPrint.getExpr().struct.getElemType() != Tab.intType &&
								stmtPrint.getExpr().struct.getElemType() != Tab.charType &&
									stmtPrint.getExpr().struct.getElemType() != boolType ) {
			
			report_error("Greska na liniji " + stmtPrint.getLine() + " : " + "tip designator = Expr ,Designator nije Var ili elem niza ", stmtPrint);
		}
	}
	
	
	public void visit(DesignatorStmtAss desStmt) {
		
		if(desStmt.getDesignator().obj.getKind() ==  Obj.Var || desStmt.getDesignator().obj.getKind() == Obj.Elem || desStmt.getDesignator().obj.getKind() == Obj.Fld) {
			//Designator je Var ili Array tipa
			
		}else {
			report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip designator = Expr ,Designator nije Var ili elem niza ", desStmt);	    						
		}
		
		if(desStmt.getDesignator().obj.getType().getKind() == Struct.Array ) {//LEVO JE ARRAY
			//Provere da li je kompatibilno ukoliko JESTE NIZ
			
			if(desStmt.getExpr().struct.getKind() == Struct.Array) {
				//UKOLIKO JE I DRUGI NIZ
				if(!desStmt.getDesignator().obj.getType().getElemType().compatibleWith(desStmt.getExpr().struct.getElemType())) {
					report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip designator = Expr ne slazu se sa tipom vrednosti,"
							+ " pri cemu je Designator Array i Expr je Array", desStmt);
				}
			} else {
				if(!desStmt.getDesignator().obj.getType().getElemType().compatibleWith(desStmt.getExpr().struct)) {
					
					report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip designator = Expr ne slazu se sa tipom vrednosti,"
							+ " pri cemu je Designator Array", desStmt);	
				}	
			}			
		}else if(desStmt.getExpr().struct != null) {
			if(desStmt.getExpr().struct.getKind() == Struct.Array){
				//DESNO JE ARRAY, levo nije array
				if(!desStmt.getDesignator().obj.getType().compatibleWith(desStmt.getExpr().struct.getElemType())) {
					report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip designator = Expr ne slazu se sa tipom vrednosti"
							+ "levo nije ARRAY, DESNO JE ARRAY", desStmt);	    				
				}
			}else if(!desStmt.getDesignator().obj.getType().compatibleWith(desStmt.getExpr().struct)) {
				report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip designator = Expr ne slazu se sa tipom vrednosti", desStmt);	    				
			}
				
			
		}else {
			//provera da li je ono sa leve strane nejednakosti kompatibilno sa onim sa desne strane ukoliko NIJE NIZ
			if(!desStmt.getDesignator().obj.getType().compatibleWith(desStmt.getExpr().struct)) {
				report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip designator = Expr ne slazu se sa tipom vrednosti", desStmt);	    				
			}
		}
		nizImaElem = false;
	}
	
	
	public void visit(DesignatorStmtINC desStmt) {
		Struct s = new Struct(Struct.Array);
		if(desStmt.getDesignator().obj.getKind() ==  Obj.Var || desStmt.getDesignator().obj.getKind() == Obj.Elem || desStmt.getDesignator().obj.getKind() == Obj.Fld) {
			//Designator je Var ili Array tipa
			//desStmt.obj = desStmt.getDesignator().obj;
		}else {
			report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip Designator++ ,Designator nije Var ili elem niza ", desStmt);	    						
		}
		if(desStmt.getDesignator().obj.getType().getKind() == Struct.Array) {
			if(desStmt.getDesignator().obj.getType().getElemType() != Tab.intType || !nizImaElem) {
				report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip Designator++ Ne moze se uvecati sam NIZ", desStmt);	    				

			}
		}else
		if(desStmt.getDesignator().obj.getType() != Tab.intType) {
			report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip Designator++ nije INT tipa", desStmt);	    				
		}
		nizImaElem = false;
	}
	
	public void visit(DesignatorStmtDEC desStmt) {
		Struct s = new Struct(Struct.Array);
		if(desStmt.getDesignator().obj.getKind() ==  Obj.Var || desStmt.getDesignator().obj.getKind() == Obj.Elem || desStmt.getDesignator().obj.getKind() == Obj.Fld) {
			//Designator je Var ili Array tipa
			
		}else {
			report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip Designator-- ,Designator nije Var ili elem niza ", desStmt);	    						
		}
		if(desStmt.getDesignator().obj.getType().getKind() == Struct.Array) {
			if(desStmt.getDesignator().obj.getType().getElemType() != Tab.intType || !nizImaElem) {
				report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip Designator++ Ne moze se uvecati sam NIZ", desStmt);	    				

			}
		}else
		if(desStmt.getDesignator().obj.getType() != Tab.intType) {
			report_error("Greska na liniji " + desStmt.getLine() + " : " + "tip Designator-- nije INT tipa", desStmt);	    				
		}
		nizImaElem = false;
	}
	
	
	public void visit(ExprTermMinus expr) {
		expr.struct = expr.getTerm().struct;
		if(expr.getTerm().struct.getKind() == Struct.Array) {
			if(expr.getTerm().struct.getElemType() != Tab.intType) {
				report_error("Greska na liniji " + expr.getLine() + " : " + "tip MINUS Term AddopTermList nije INT tipa"
						+ "gde je Term Array", expr);
			}
		}else
		if(expr.getTerm().struct != Tab.intType) {
			report_error("Greska na liniji " + expr.getLine() + " : " + "tip MINUS Term AddopTermList nije INT tipa", expr);
		}
	}
	
	
	public void visit(AddopTermLists expr) {
		if(expr.getTerm().struct.getKind() == Struct.Array) {
			if(expr.getTerm().struct.getElemType() != Tab.intType) {
				report_error("Greska na liniji " + expr.getLine() + " : " + "AddopTermList Addop Term nije INT tipa"
						+ "Pritom je Array", expr);
			}
		}else
		if(expr.getTerm().struct != Tab.intType) {
			report_error("Greska na liniji " + expr.getLine() + " : " + "tip AddopTermList Addop Term nije INT tipa", expr);
		}
	}
	
	public void visit(MulopFactorLists term) {
		term.struct = term.getFactor().struct;
		
		if(term.getFactor().struct.getKind() == Struct.Array) {
			//PROVERA ZA ARRAY
			if(term.getFactor().struct.getElemType() != Tab.intType) {
				report_error("Greska na liniji " + term.getLine() + " : " + "tip Term = Term Mulop Factor nije INT tipa"
						+ "Pritom je Factor Array", term);
			}
		}else {
			if(term.getFactor().struct != Tab.intType) {
				report_error("Greska na liniji " + term.getLine() + " : " + "tip Term = Term Mulop Factor nije INT tipa", term);
			}
		}
		
	}
	
//	public void visit(ExprSQBraces expr) {
//		//expr.struct = expr.getExpr().struct;
//		if(expr.getExpr().struct != Tab.intType) {
//			report_error("Greska na liniji " + expr.getLine() + " : " + "tip Factor = new Type [ Expr ] nije INT tipa", expr);
//		}	
//	}
	
	public void visit(DesignatorNoIdentDoubleOccured designator) {

		String name = designator.getVarName();
		Obj o = Tab.find(name);
		if(o == Tab.noObj){
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+name+" nije deklarisano! ", null);
   		}
		if(designator.getIdentExprList().getLine() != 0) {
			if(o.getType().getKind() == Struct.Array) {
				
				designator.obj = o;
			}else {
				report_error("Greska na liniji " + designator.getLine() + " : " + "tip Designator = IDENT [ Expr ] nije Array tipa", designator);
			}
		}else {
			designator.obj = o;
		}
	}
	
	public void visit(IdentExprSQBraceIdent expr) {
		if(expr.getExpr().struct.getKind() == Struct.Array) {
			//Ukoliko je Array, treba proveriti tip njegovih elemenata
			if(expr.getExpr().struct.getElemType() != Tab.intType) {
				report_error("Greska na liniji " + expr.getLine() + " : " + "tip Designator = IDENT [ Expr ] nije INT tipa"
						+ "pri cemu je u [] Array", expr);
			}
		}else {
			if(expr.getExpr().struct != Tab.intType) {
				report_error("Greska na liniji " + expr.getLine() + " : " + "tip Designator = IDENT [ Expr ] nije INT tipa", expr);
			}
		}
		nizImaElem = true;
	}
	
	public void visit(DesignatorIdentDoubleOccured expr) {
		
		String name1 = expr.getNameNamespace();
		String name2 = expr.getVarName();
		
		Obj o1 = Tab.find(name1);
		Obj o2 = Tab.noObj;
		
		List<Obj> l = imenaProstora.get(name1);
		for(Obj o : l) {
			if(o.getName().equals(name1+"::"+name2)) {
				o2 = o;
				break;
			}
		}
		
		if(o1 == Tab.noObj || o2 == Tab.noObj) {
			
			report_error("Greska na liniji " + expr.getLine()+ " : ime "+ name1 + "ili "+ name2 +" nije deklarisano!"
					+ "Takodje verovatno ne postoji promenljiva unutar Namespacea ", null);
			
		}else {
			
			if(o1.getType() != enumType)report_error("Greska na liniji " + expr.getLine() + " : " + "tip Designator = IDENT :: IDENT nije enum tipa", expr);
			if(o2.getKind() != Obj.Var && o2.getKind() != Obj.Con && o2.getKind() != Obj.Meth)report_error("Greska na liniji " + expr.getLine() + " : " + "tip Designator = IDENT :: IDENT drugi ident nije odredjenog tipa", expr);
		
			expr.obj = o2;
		}
		
		
	}
	
	
	
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
		Tab.openScope();
	}
	
	public void visit(Program program) {
		nVars = Tab.currentScope().getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	
	
	public boolean passed(){
    	return !errorDetected;
    }
}
