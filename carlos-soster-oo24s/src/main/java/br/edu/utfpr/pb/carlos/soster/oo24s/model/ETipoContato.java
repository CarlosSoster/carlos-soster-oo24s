package br.edu.utfpr.pb.carlos.soster.oo24s.model;

public enum ETipoContato {
    TELEFONE(1), CELULAR(2);
    
    private final Integer id;
    
    ETipoContato(Integer id){
        this.id = id;
    }
    
    public Integer getId(){
        return id;
    }
    
    public static ETipoContato findById(Integer id){
        for (ETipoContato tc : ETipoContato.values()){
            if (tc.getId().equals(id)) return tc;
        }
        throw new IllegalArgumentException("Tipo inválido");
    }
}
