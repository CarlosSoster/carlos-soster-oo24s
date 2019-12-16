package br.edu.utfpr.pb.carlos.soster.oo24s.model;

public enum ETipoProduto {
    PRODUTO(1), SERVICO(2);
    
    private final Integer id;
    
    ETipoProduto(Integer id){
        this.id = id;
    }
    
    public Integer getId(){
        return id;
    }
    
    public static ETipoProduto findById(Integer id){
        for (ETipoProduto tc : ETipoProduto.values()){
            if (tc.getId().equals(id)) return tc;
        }
        throw new IllegalArgumentException("Tipo inválido");
    }
}
