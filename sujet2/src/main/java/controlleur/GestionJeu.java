package controlleur;

import modèle.*;
import vue.*;

public class GestionJeu {
    private Ihm vue = new Ihm();
    private Joueur joueur1;
    private Joueur joueur2;
    private Plateau plateau = new Plateau();

    public void lancerJeu(){
        boolean valid = false;
        joueur1 = new Joueur(vue.nom(1), 1);
        do{
            joueur2 = new Joueur(vue.nom(2), 2);
            if(!joueur2.getNom().equals(joueur1.getNom())){
                valid = true;
            }
        }while (!valid);
        vue.afficherEtat(plateau);
        Joueur joueurCourant = joueur1;
        do{
            coup(joueurCourant);
            vue.afficherEtat(plateau);
            joueurCourant = (joueurCourant == joueur1)? joueur2 : joueur1;
        }while(true);
    }

    public boolean coup(Joueur joueur){
        boolean valid = false;
        int l, c;
        int[] in;
        do{
            in  = vue.coup(joueur);
            l  = in[0]-1;
            c  = in[1]-1;
            if(plateau.coupValide(l, c)){
                valid = true;
            }
            else {
                vue.erreur("Coup impossible !");
            }
        }while(!valid);
        plateau.modifier(l,c,joueur.getId());
        return valid;
    }
}
