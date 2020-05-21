package br.com.naiara.fiilter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class JWTPrincipal implements Principal{
        private String name;
        private List<String> roles = new ArrayList<String>();

        public JWTPrincipal(final String name, String perfil) {
            this.name = name;
            roles.add(perfil);
        }

        @Override
        public String getName() {
            return name;
        }
       
        
        public void setName(String name) {
            this.name = name;
        }

     
        public List<String> getRoles() {
            return roles;
        }

        
        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }