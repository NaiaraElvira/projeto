//package br.com.naiara.utils;
//
//import javax.annotation.Resource;
//import javax.ejb.SessionContext;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.SecurityContext;
//
//import br.com.naiara.fiilter.JWTPrincipal;
//
//public class JWTUtils {
//	@Resource
//    SessionContext context;
//	
//	public  String getUsurioLogado() {
//		String f = context.getCallerPrincipal().getName();
//
//		return f ;
//	}
//	
//}
