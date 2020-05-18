//package br.com.naiara.fiilter;
//
//import java.io.IOException;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import br.com.naiara.utils.JWTUtils;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.SignatureException;
//
//
//public class JWTFilter implements Filter{
//	@Override
//    public void init(FilterConfig filterConfig) throws ServletException {}
//
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        
//        if(req.getRequestURI().startsWith("/api/login")) {
//        	chain.doFilter(request, response);
//        	return;
//        }
//        
//        String token = req.getHeader(JWTUtils.TOKEN_HEADER);
//        
//        if(token == null || token.trim().isEmpty()) {
//        	res.setStatus(401);
//        	return;
//        }
//        
//        try {
//			Jws<Claims> parser = JWTUtils.decote(token);
//			System.out.println("USER ====> "+ parser.getBody().getSubject());
//			chain.doFilter(request, response);
//		} catch (SignatureException e) {
//			// TODO: handle exception
//			res.setStatus(401);
//		}
//		
//	}
//
//	@Override
//    public void destroy() {}
//
//}
