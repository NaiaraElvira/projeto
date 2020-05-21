package br.com.naiara.enumerator;

import java.util.ArrayList;

import br.com.naiara.domain.Tipo;

public enum PerfilEnum {
	ADMIN("Administrador"),
    ANALI("Analista"),
    GEREN("Gerente");
 
    private String descricao;
 
    PerfilEnum(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
    
    public static ArrayList<Tipo> listarEnum() {
    	ArrayList<Tipo> array = new ArrayList<Tipo>();
		for (PerfilEnum t : PerfilEnum.values()) {
	    	Tipo tipo = new Tipo();
			tipo.setDescricao(t.getDescricao());
			tipo.setName(t.name());
			array.add(tipo);
		  }

        return array;
    }
}
