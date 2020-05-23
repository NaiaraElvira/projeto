package br.com.naiara.resource;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.naiara.domain.Credencials;
import br.com.naiara.entity.Operador;
import br.com.naiara.enumerator.PerfilEnum;
import br.com.naiara.utils.KeyGenerator;
import br.com.naiara.utils.PasswordUtils;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class Login {

    @Context
    private UriInfo uriInfo;

    @Inject
    private KeyGenerator keyGenerator;

    @PersistenceContext
    private EntityManager em;

   
    @POST
    @Path("/login")
    public Response authenticateUser(Credencials credencials) {

        try {
        	Operador operator = authenticate(credencials.getLogin(),  credencials.getPassword());

            String primeiroNome = operator.getNome().split(" ")[0];

            String token = issueToken(credencials.getLogin(), operator.getPerfil(), primeiroNome);
            
    

            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private Operador authenticate(String login, String password) throws Exception {
        TypedQuery<Operador> query = em.createNamedQuery(Operador.FIND_BY_LOGIN_PASSWORD, Operador.class);
        query.setParameter("login", login);
        query.setParameter("senha", PasswordUtils.digestPassword(password));
        Operador user = query.getSingleResult();
        
        if (user == null)
            throw new SecurityException("Invalid user/password");
        return user;
    }

    private String issueToken(String login, PerfilEnum perfil, String primeiroNome) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .claim("perfil", perfil.name())
                .claim("nome", primeiroNome)
                .compact();
        return jwtToken;

    }

    
    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
