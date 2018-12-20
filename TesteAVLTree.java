

public class TesteAVLTree {
	public static void main(String[] args){
        AVLTree arvore1 = new AVLTree();
        AVLTree arvore2 = new AVLTree();
        AVLTree arvore3 = new AVLTree();


        
        System.out.println("Árvore 1:");
        arvore1.insere(50);
        arvore1.insere(30);
        arvore1.insere(10);
        arvore1.insere(5);
        arvore1.insere(70);
        arvore1.insere(40);

        System.out.println();
        arvore1.imprimirPrefixado();
        arvore1.remover(30);
        arvore1.imprimirPrefixado();

       
        System.out.println("Árvore 2:");
        arvore2.insere(70);
        arvore2.insere(50);
        arvore2.insere(80);
        arvore2.insere(71);
        arvore2.insere(90);
        arvore2.insere(75);

        System.out.println();
        arvore2.imprimirPrefixado();
        
        System.out.println("Árvore 3:");
        arvore3.insere(50);
        arvore3.insere(30);
        arvore3.insere(10);
        arvore3.insere(70);
        arvore3.insere(40);
        arvore3.insere(35);

        System.out.println();
        arvore3.imprimirPrefixado();
        arvore3.remover(40);
        arvore3.imprimirPrefixado();
        

	}
}
