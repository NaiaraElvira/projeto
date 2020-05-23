package br.com.naiara.fiilter;

import java.io.IOException;
import java.security.Key;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import br.com.naiara.utils.KeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;


@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {
	@Inject
    private Logger logger;
    
    @Inject
    private KeyGenerator keyGenerator;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {

        	Jws<Claims> claims = validateToken(token);
        	JWTPrincipal principal  = buildPrincipal(claims);
        	
        	if (principal != null) {
                JWTSecurityContext ctx = new JWTSecurityContext(
                                                principal,
                                                requestContext.getSecurityContext().isSecure());
                requestContext.setSecurityContext(ctx);
            } else {
                throw new NotAuthorizedException(
                        "Unauthorized: Unable to extract claims from JWT",
                        Response.status(Status.UNAUTHORIZED));
            }
                                  

        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
    
    private JWTPrincipal buildPrincipal(final Jws<Claims> claims) {
        JWTPrincipal principal = null;

        try {
            if (claims != null) {
                String subject   = claims.getBody().getSubject();
                String perfil    = (String) claims.getBody().get("perfil");

                // TODO: Extract custom attributes, e.g. roles, organization affiliation etc. and put into principal.

                principal = new JWTPrincipal(subject, perfil);
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        return principal;
    }
    
    private Jws<Claims> validateToken(final String token) {

        	 Key key = keyGenerator.generateKey();
        	 Jws<Claims> jwt = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			 
			 Date expirationTime = jwt.getBody().getExpiration();
			 
			 Date now = new Date();
			 
             if (expirationTime.compareTo(now) <= 0) {
                 throw new NotAuthorizedException(
                             "Unauthorized: too late, token expired",
                             Response.status(Status.UNAUTHORIZED));
             }
      
        return jwt;
    }
    
    public static class JWTSecurityContext implements SecurityContext {

        private JWTPrincipal principal;
        private boolean      isSecure;
        private Set<String>  roles = new HashSet<>();

       
        public JWTSecurityContext(final JWTPrincipal principal, final boolean isSecure) {
            this.principal  = principal;
            this.isSecure   = isSecure;
            List<String> names  = principal.getRoles();
            for(String perfil : names){
            	roles.add(perfil);
            }
           
        }


        @Override
        public String getAuthenticationScheme() {
            return "JWT"; // informational
        }


        @Override
        public Principal getUserPrincipal() {
            return principal;
        }


        @Override
        public boolean isSecure() {
            return isSecure;
        }

  
        @Override
        public boolean isUserInRole(final String role) {
            return roles.contains(role);
        }
    }
}
