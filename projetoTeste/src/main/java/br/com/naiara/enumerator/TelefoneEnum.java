package br.com.naiara.enumerator;

import java.util.ArrayList;

import br.com.naiara.domain.Tipo;

public enum TelefoneEnum {
	CEL("Celular"),
    FIX("Fixo"),
    COM("Comericial");
 
    private String descricao;
 
    TelefoneEnum(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
    
    public static ArrayList<Tipo> listarEnum() {
    	ArrayList<Tipo> array = new ArrayList<Tipo>();
		for (TelefoneEnum t : TelefoneEnum.values()) {
	    	Tipo tipo = new Tipo();
			tipo.setDescricao(t.getDescricao());
			tipo.setName(t.name());
			array.add(tipo);
		  }

        return array;
    }

}
