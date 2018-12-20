
public class AVLTree {
	 public No raiz;

	    //Insere No

	    public void insere(int k) {
	        No novoNo = new No(k);
	        inserirAVL(this.raiz, novoNo);
	    }

	    public void inserirAVL(No aComparar, No aInserir) {

	        if (aComparar == null) {
	            this.raiz = aInserir;

	        } else {

	            if (aInserir.getValor() < aComparar.getValor()) {

	                if (aComparar.getEsquerda() == null) {
	                    aComparar.setEsquerda(aInserir);
	                    aInserir.setPai(aComparar);
	                    verificarBalanceamento(aComparar);

	                } else {
	                    inserirAVL(aComparar.getEsquerda(), aInserir);
	                }

	            } else if (aInserir.getValor() > aComparar.getValor()) {

	                if (aComparar.getDireita() == null) {
	                    aComparar.setDireita(aInserir);
	                    aInserir.setPai(aComparar); //setar o valor como pai do inserido
	                    verificarBalanceamento(aComparar);

	                } else {
	                    inserirAVL(aComparar.getDireita(), aInserir);
	                }

	            } else {
	                System.out.println("O nó "+aInserir.getValor()+" já existe!");
	            }
	        }
	    }


	    public void verificarBalanceamento(No NoAtual) {
	        setBalanceamento(NoAtual);
	        int balanceamento = NoAtual.getBalanceamento();
	                            //<-1
	        if (balanceamento == -2) {

	            if (altura(NoAtual.getEsquerda().getEsquerda()) >= altura(NoAtual.getEsquerda().getDireita())) { 
	                //LL → caso Left-Left (rotação a direita)
	                System.out.println("Fez uma rotaçao LL no No:" + NoAtual);
	                NoAtual = rotacaoDireita(NoAtual);

	            } else {
	                //LR → caso Left-Right (rotação esquerda-direita)
	                System.out.println("Fez uma rotacao LR no No:" + NoAtual);
	                NoAtual = duplaRotacaoEsquerdaDireita(NoAtual);
	            }
	                                //>1
	        } else if (balanceamento == 2) {

	            if (altura(NoAtual.getDireita().getDireita()) >= altura(NoAtual.getDireita().getEsquerda())) { 
	            	//olha se estãono mesmo nível
	                //RR → caso Right-Right (rotação a esquerda)
	                System.out.println("Fez uma rotacao RR no No:" + NoAtual);
	                NoAtual = rotacaoEsquerda(NoAtual);

	            } else {
	                //RL → caso Right-Left (rotação direita-esquerda)
	                System.out.println("Fez uma rotacao RL no No:" + NoAtual);
	                NoAtual = duplaRotacaoDireitaEsquerda(NoAtual);
	            }
	        }

	        if (NoAtual.getPai() != null) {
	            verificarBalanceamento(NoAtual.getPai());
	        } else {
	            this.raiz = NoAtual;
	        }
	    }

	    //Rotacoes

	    //RR → caso Right-Right (rotação a esquerda)
	    public No rotacaoEsquerda(No inicial) {

	        No direita = inicial.getDireita();
	        direita.setPai(inicial.getPai()); //sub-árvore a direita permanece

	        inicial.setDireita(direita.getEsquerda()); //sub-arvore a esquerda vai ser a sub-arvore da direita

	        if (inicial.getDireita() != null) {
	            inicial.getDireita().setPai(inicial);
	        }

	        direita.setEsquerda(inicial);
	        inicial.setPai(direita);

	        if (direita.getPai() != null) {

	            if (direita.getPai().getDireita() == inicial) {
	                direita.getPai().setDireita(direita);

	            } else if (direita.getPai().getEsquerda() == inicial) {
	                direita.getPai().setEsquerda(direita);
	            }
	        }

	        setBalanceamento(inicial);
	        setBalanceamento(direita);

	        return direita;
	    }
	    /*
	     * O nó X que está no nível do meio dos três
			envolvidos toma o lugar do nó com FB=-2
			A sub-árvore direita do nó X permanece
			A sub-árvore esquerda do nó X será
			colocada como sub-árvore direita do nó Y
			O filho esquerdo do nó X aponta para o nó Y
	     */

	    //LL → caso Left-Left (rotação a direita)
	    public No rotacaoDireita(No inicial) {

	        No esquerda = inicial.getEsquerda();
	        esquerda.setPai(inicial.getPai());

	        inicial.setEsquerda(esquerda.getDireita());

	        if (inicial.getEsquerda() != null) {
	            inicial.getEsquerda().setPai(inicial);
	        }

	        esquerda.setDireita(inicial);
	        inicial.setPai(esquerda);

	        if (esquerda.getPai() != null) {

	            if (esquerda.getPai().getDireita() == inicial) {
	                esquerda.getPai().setDireita(esquerda);

	            } else if (esquerda.getPai().getEsquerda() == inicial) {
	                esquerda.getPai().setEsquerda(esquerda);
	            }
	        }

	        setBalanceamento(inicial);
	        setBalanceamento(esquerda);

	        return esquerda;
	    }
	    /*
	     * O nó X que está no nível do meio dos três
			envolvidos toma o lugar do nó com FB=-2
			A sub-árvore esquerda do nó X permanece
			A sub-árvore direita do nó X será colocada
			como sub-árvore esquerda do nó Y
			O filho direito do nó X aponta para o nó Y
	     */

	    //LR → caso Left-Right (rotação esquerda-direita)
	    public No duplaRotacaoEsquerdaDireita(No inicial) {
	        inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
	        return rotacaoDireita(inicial);
	    }

	    //RL → caso Right-Left (rotação direita-esquerda)
	    public No duplaRotacaoDireitaEsquerda(No inicial) {
	        inicial.setDireita(rotacaoDireita(inicial.getDireita()));
	        return rotacaoEsquerda(inicial);
	    }

	    public int altura(No no) {
			return altura1(no)-1; //começa de zero
		}
		private int altura1(No no) {
			if(no == null)
			       return 0;
			else {
			   	if (altura1(no.getEsquerda()) > altura1(no.getDireita()))
			   	   return ( 1 + altura1(no.getEsquerda()) );
			   	else
				   return ( 1 + altura1(no.getDireita()) );
			}
		}

	    private void setBalanceamento(No no) {
	        no.setBalanceamento((altura(no.getDireita()) - altura(no.getEsquerda())));
	    
	    }

	    public void imprimirInterfixado() {
	    	System.out.println("Interfixado:");
	    	imprimirInterfixado2(raiz);
	    }

	    private void imprimirInterfixado2(No v) {
	    	if (v!=null) {
	            imprimirInterfixado2(v.getEsquerda());
	            System.out.println(v.getValor() + " Altura: "+altura(v)+" - Profundidade: "+profundidade(v)+ " - IndiceBalanceamento: "+v.getBalanceamento()*-1);
	            imprimirInterfixado2(v.getDireita());
	        }
	    }
	    public void imprimirPrefixado() {
	    	System.out.println("Prefixado:");
	    	imprimirPrefixado2(raiz);
	    	System.out.println();
			System.out.println();
		}
		
		private void imprimirPrefixado2(No v) {
			if (v!=null) {
				System.out.println(v.getValor() + " Altura: "+altura(v)+" - Profundidade: "+profundidade(v)+ " - IndiceBalanceamento: "+v.getBalanceamento()*-1);
				imprimirPrefixado2(v.getEsquerda());
				imprimirPrefixado2(v.getDireita());
			}
		}
		public void imprimirPosfixado() {
	    	System.out.println("Posfixado:");
	    	imprimirPosfixado2(raiz);
	    	System.out.println();
			System.out.println();
		}
		
		private void imprimirPosfixado2(No v) {
			if (v!=null) {
				imprimirPosfixado2(v.getEsquerda());
				imprimirPosfixado2(v.getDireita());
				System.out.println(v.getValor() + " Altura: "+altura(v)+" - Profundidade: "+profundidade(v)+ " - IndiceBalanceamento: "+v.getBalanceamento()*-1);
			}
		}
		
		public No remove(No no, int valor) { //raiz e valor para remover
			if(no==null)
				return null;
			else if(no.getValor()>valor) //busca o valor
				no.setEsquerda(remove(no.getEsquerda(),valor));
			else if(no.getValor()<valor)
				no.setDireita(remove(no.getDireita(),valor));
			else {//Achou o valor
				if(no.getEsquerda()==null&&no.getDireita()==null)//Quando é folha 
					no=null;
				else if(no.getEsquerda()==null) {//So tem filho na direita
					No novo = no;
					no=no.getDireita(); //recebe o da direita
					no.setPai(novo.getPai()); //seta o pai do anterior para esse no que subiu
				}
				else if(no.getDireita() == null) {//So tem filho na esquerda
					No novo = no;
					no=no.getEsquerda();
					no.setPai(novo.getPai());
				}
				else {//Tem os dois filhos
					No novo = no.getEsquerda();
					while(novo.getDireita()!=null) {
						novo=novo.getDireita();
					}
					no.setValor(novo.getValor()); //seta o maior valor da subarvore da esquerda como no no lugar de antes
					novo.setValor(valor);
					no.setEsquerda(remove(no.getEsquerda(),valor)); //subindo lado esquerdo, mantem o lado direito e sobe o esquerdo
				}
			}
			return no;
		}
		

	    public void remover(int k) {
	        remove(this.raiz, k);
	    }
		
		
		public int profundidade(No no) {
			int prof = profundidade1(no);
			
			if(prof == -1) {
				System.out.println("Nó não existe");
				return 0;
			}
			else
				return prof;
			
		}
		private int profundidade1(No no) {
			if(no==raiz)
				return 0;
			else if(no==null||no.getValor()==null)
				return -1;
			else
				return 1+profundidade(no.getPai());
		}


	    
}
  