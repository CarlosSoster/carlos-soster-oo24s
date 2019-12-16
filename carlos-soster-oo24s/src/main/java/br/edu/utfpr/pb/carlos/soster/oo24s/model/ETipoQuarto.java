package br.edu.utfpr.pb.carlos.soster.oo24s.model;

public enum ETipoQuarto {
    ECONOMICO(1), SUPERIOR(2), LUXO(3);
    
    private final Integer id;
    
    ETipoQuarto(Integer id){
        this.id = id;
    }
    
    public Integer getId(){
        return id;
    }
    
    public static ETipoQuarto findById(Integer id){
        for (ETipoQuarto tc : ETipoQuarto.values()){
            if (tc.getId().equals(id)) return tc;
        }
        throw new IllegalArgumentException("Tipo inválido");
    }
}
