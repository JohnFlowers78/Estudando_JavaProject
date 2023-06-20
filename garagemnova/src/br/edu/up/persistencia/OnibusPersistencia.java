package br.edu.up.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.edu.up.entidades.Onibus;

public class OnibusPersistencia {
    
    public static boolean incluir(Onibus onibus) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(onibus);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean alterar(Onibus onibus) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.merge(onibus);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Onibus onibus) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(manager.contains(onibus) ? onibus : manager.merge(onibus));
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Onibus procurarPorId(int id) {
        EntityManager manager = EntityManagerFactory.getInstance();
        return manager.find(Onibus.class, id);
    }

    @SuppressWarnings("unchecked")
	public static List<Onibus> getOnibus() {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Onibus");
        return consulta.getResultList();
    }
    
    public static boolean onibusExiste(int idOnibus) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query query = manager.createQuery("SELECT COUNT(o) FROM Onibus o WHERE o.id = :idOnibus");
        query.setParameter("idOnibus", idOnibus);
        int count = ((Number) query.getSingleResult()).intValue();
        return count > 0;
    }
    
	/*
	 * public static Onibus getOnibusPorRota(int idRota) { EntityManager manager =
	 * EntityManagerFactory.getInstance(); Query consulta = manager.
	 * createQuery("SELECT o FROM Onibus o JOIN o.rota r WHERE r.id = :idRota");
	 * consulta.setParameter("idRota", idRota);
	 * 
	 * @SuppressWarnings("unchecked") List<Onibus> onibus =
	 * consulta.getResultList(); if (!onibus.isEmpty()) { return onibus.get(0); }
	 * return null; }
	 */
}
