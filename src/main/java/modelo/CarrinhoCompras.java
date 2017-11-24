package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarrinhoCompras {
    
    private List<Item> itens = new ArrayList<>();

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void adicionarItem(int quantidade, Livro livro) {
        itens.add(new Item(quantidade, livro));
    }
    
    public void removerItem(int posicaoItem) {
        itens.remove(posicaoItem);
    }
       
    public void esvaziar() {
        itens = new ArrayList<>();
    }
}
